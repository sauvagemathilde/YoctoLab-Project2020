package visualizeData;

import com.yoctopuce.YoctoAPI.YAPI;
import com.yoctopuce.YoctoAPI.YAPI_Exception;
import com.yoctopuce.YoctoAPI.YDisplay;
import com.yoctopuce.YoctoAPI.YModule;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Display {
	int hardwaredetect;
	YModule module;
	YDisplay display;
	Text displayText;
	Text displayValue;
	
	Display(String texte, int xText, int yText, String display, int xDisplay, int yDisplay) {
		displayText = new Text(xText, yText, texte);
		displayValue = new Text(xDisplay, yDisplay, display);
	}
	
	Text getDisplayText() {
		return displayText;
	}
	
	Text getDisplayValue() {
		return displayValue;
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
        	  module = YModule.FindModule(Main.nameDisplay);
        	  display = YDisplay.FindDisplay(Main.nameDisplay+".display");
          }
          if (module != null) {
        	  displayText.setText(display.getFriendlyName());
        	  displayValue.setText("Plugged in");
          }
          
        } catch (YAPI_Exception e) {
        	displayText.setText("DISPLAY");
      	  	displayValue.setText("OFFLINE");
      	  	module = null;
        }
    }

}