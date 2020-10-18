package visualizeData;

import com.yoctopuce.YoctoAPI.YAPI;
import com.yoctopuce.YoctoAPI.YAPI_Exception;
import com.yoctopuce.YoctoAPI.YHubPort;
import com.yoctopuce.YoctoAPI.YModule;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Hub {
	int hardwaredetect;
	
	YModule hubModule;
	YHubPort hubPort1;
	YHubPort hubPort2;
	YHubPort hubPort3;
	
	int nbrPorts;	
	Text hubText;
	Text hubValue;
	
	Hub(String texte, int xText, int yText, String hub, int xHub, int yHub) {
		hubText = new Text(xText, yText, texte);
		hubValue = new Text(xHub, yHub, hub);
	}
	
	Text getHubText() {
		return hubText;
	}
	
	Text getHubValue() {
		return hubValue;
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
		nbrPorts = 0;
        try {
        	
          if (hardwaredetect == 0) {
            YAPI.UpdateDeviceList();
          }
          hardwaredetect = (hardwaredetect + 1) % 20;
          YAPI.HandleEvents();
          if (hubModule == null) {
        	  hubModule = YModule.FindModule(Main.nameHub);
        	  hubPort1 = YHubPort.FindHubPort(Main.nameHub+".hubPort1");
        	  hubPort2 = YHubPort.FindHubPort(Main.nameHub+".hubPort2");
        	  hubPort3 = YHubPort.FindHubPort(Main.nameHub+".hubPort3");
          }
          if (hubModule != null) {
        	  if (hubPort1.getPortState()==3) { 
        		  nbrPorts += 1;
        	  }
        	  if (hubPort2.getPortState()==3) { 
        		  nbrPorts += 1;
        	  }
        	  if (hubPort3.getPortState()==3) { 
        		  nbrPorts += 1;
        	  }
        	  if (nbrPorts == 0 || nbrPorts == 1) {
        		  hubValue.setText((String.valueOf(nbrPorts+" port connecté"))) ;
        	  } else {
        		  hubValue.setText((String.valueOf(nbrPorts+" ports connectés"))) ;
        	  }
        	  hubText.setText(hubModule.getFriendlyName());
          }
        } catch (YAPI_Exception e) {
        	hubText.setText("HUB");
        	hubValue.setText("OFFLINE");
        	hubModule = null;
        }
    }
}