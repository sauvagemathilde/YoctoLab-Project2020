package analyzeData;

import com.yoctopuce.YoctoAPI.YAPI;
import com.yoctopuce.YoctoAPI.YAPI_Exception;
import com.yoctopuce.YoctoAPI.YColorLedCluster;
import com.yoctopuce.YoctoAPI.YModule;
import com.yoctopuce.YoctoAPI.YSensor;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ColorLed {
	int hardwaredetect;
	YSensor sensor;
	FirstLed firstLed;
	SecondLed secondLed;
	YModule module;
	YColorLedCluster cluster; //controle of the leds
	
	ColorLed(FirstLed firLed, SecondLed secLed) {
		firstLed =  firLed;
		secondLed = secLed;
	}
	
	FirstLed getFirstLed() {
		return firstLed;
	}
	
	SecondLed getSecondLed() {
		return secondLed;
	}
	
	YColorLedCluster getCluster() {
		return cluster;
	}
	
	void setCluster(String sensorName) {
		cluster = YColorLedCluster.FindColorLedCluster(sensorName);
	}
	
	void setColor(int led, int rgbValue) throws YAPI_Exception {
		cluster.set_rgbColor(led-1, led, rgbValue);
	}
	
	void setFirstLed(String texte, Color color, String ON) {
		firstLed.setLedText(texte);
		firstLed.setLedRect(color);
		firstLed.setLedON(ON);
	}
	
	void setSecondLed(String texte, Color color, String ON) {
		secondLed.setLedText(texte);
		secondLed.setLedRect(color);
		secondLed.setLedON(ON);
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
          if (module == null) {
        	  module = YModule.FindModule(Main.nameColor);
          }
          if (module != null) {
        	  firstLed.setSensor(Main.nameColor+".colorLed1");
        	  secondLed.setSensor(Main.nameColor+".colorLed2");
        	  cluster = YColorLedCluster.FirstColorLedCluster();
        	  setFirstLed(firstLed.getSensor().get_friendlyName(),Color.web("#"+firstLed.getSensor().get_advertisedValue()), "ON");
        	  setSecondLed(secondLed.getSensor().get_friendlyName(),Color.web("#"+secondLed.getSensor().get_advertisedValue()), "ON");
              if (Main.temperature.getSensor().get_currentValue()>Main.chosenTemperature) {
            	  setColor(1,Main.chosenColor1);
            	  setColor(2,Main.chosenColor2);
              } else {
            	  setColor(1,0x000000);
            	  setColor(2,0x000000);
              }
          }
        } catch (YAPI_Exception e) {
        	setFirstLed("LED 1",Color.BLACK,"OFF");
        	//module = null;
        }
    }
}