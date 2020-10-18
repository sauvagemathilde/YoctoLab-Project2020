package visualizeData;

import com.yoctopuce.YoctoAPI.YAPI;
import com.yoctopuce.YoctoAPI.YAPI_Exception;
import com.yoctopuce.YoctoAPI.YSensor;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public class Meteo {
	int hardwaredetect;
	YSensor sensor;
	Temperature temperature;
	Humidity humidity;
	Pressure pressure;
	
	Meteo(Temperature temp, Humidity hum, Pressure pres) {
		temperature = temp;
		humidity = hum;
		pressure = pres;
	}
	
	Temperature getTemp() {
		return temperature;
	}
	
	Humidity getHum() {
		return humidity;
	}
	
	Pressure getPres() {
		return pressure;
	}
	
	void setTemp(String texte, String value) {
		temperature.setTempText(texte);
		temperature.setTempValue(value);
	}
	
	void setHum(String texte, String value) {
		humidity.setHumText(texte);
		humidity.setHumValue(value);
	}
	
	void setPres(String texte, String value) {
		pressure.setPresText(texte);
		pressure.setPresValue(value);
	}
	
	void initialize(String URL) {
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
        	  sensor = YSensor.FirstSensor();
        	  temperature.setSensor(Main.nameMeteo+".temperature");
        	  humidity.setSensor(Main.nameMeteo+".humidity");
        	  pressure.setSensor(Main.nameMeteo+".pressure");
          }
          if (sensor != null) {
        	  setTemp(temperature.getSensor().get_friendlyName(),temperature.getSensor().get_currentValue() + temperature.getSensor().get_unit());
        	  setHum(humidity.getSensor().get_friendlyName(),humidity.getSensor().get_currentValue() + humidity.getSensor().get_unit());
        	  setPres(pressure.getSensor().get_friendlyName(),pressure.getSensor().get_currentValue() + pressure.getSensor().get_unit());
          }
        } catch (YAPI_Exception e) {
        	setTemp("TEMPERATURE","OFFLINE");
        	setHum("HUMIDITY","OFFLINE");
        	setPres("PRESSURE","OFFLINE");
      	  	sensor = null;
        }
    }
}