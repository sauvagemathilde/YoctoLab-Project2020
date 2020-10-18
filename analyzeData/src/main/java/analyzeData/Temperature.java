package analyzeData;

import com.yoctopuce.YoctoAPI.YAPI;
import com.yoctopuce.YoctoAPI.YAPI_Exception;
import com.yoctopuce.YoctoAPI.YSensor;
import com.yoctopuce.YoctoAPI.YTemperature;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Temperature {
	int hardwaredetect;
	YSensor sensor;
	YTemperature sensorTemperature;
	Text tempText;
	Text tempValue;
	
	Temperature(String texte, int xText, int yText, String temp, int xTemp, int yTemp) {
		tempText = new Text(xText, yText, texte);
		tempValue = new Text(xTemp, yTemp, temp);
	}
	
	Text getTempText() {
		return tempText;
	}
	
	Text getTempValue() {
		return tempValue;
	}
	
	YTemperature getSensor() {
		return sensorTemperature;
	}	
	
	void setTempText(String texte) {
		tempText.setText(texte);
	}
	
	void setTempValue(String texte) {
		tempValue.setText(texte);
	}
	
	void setSensor(String sensorName) {
		sensorTemperature = YTemperature.FindTemperature(sensorName) ;
	}
	
	public void initialize(String URL) {
		Timeline timeline = new Timeline();
    	
    	try {
    		YAPI.RegisterHub(URL);
        } catch (YAPI_Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Init error");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            Platform.exit();
        }
    	timeline = new Timeline();
    	timeline.setCycleCount(Timeline.INDEFINITE);
    	timeline.setAutoReverse(false);
    	timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(100),
                        new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                periodicTimer();
                            }
                        }));
    	timeline.play();
    }
	
	void periodicTimer() {
        try {
          if (hardwaredetect == 0) {
            YAPI.UpdateDeviceList();
          }
          hardwaredetect = (hardwaredetect + 1) % 20;
          YAPI.HandleEvents();
          if (sensor == null) {
        	  sensor = YSensor.FindSensor(Main.nameMeteo);
        	  setSensor(Main.nameMeteo+".temperature");
          }
          if (sensor != null) {
        	  setTempText(Main.temperature.getSensor().get_friendlyName());
        	  setTempValue(Main.temperature.getSensor().get_currentValue() + Main.temperature.getSensor().get_unit());
          }
        } catch (YAPI_Exception e) {
        	setTempText("TEMPERATURE");
    		setTempValue("OFFLINE");
      	  	sensor = null;
        }
    }
}