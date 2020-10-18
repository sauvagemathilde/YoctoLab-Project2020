package visualizeData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.yoctopuce.YoctoAPI.YAPI_Exception;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	static Stage stage;
	static Scene scene;
	static Group root;
	
	static Bouton quit_button;
	static int width = 290;
	static int height = 260;
	static Text welcom;
	
	static String URLHub;
	static String nameHub;
	static String nameMeteo;
	static String nameColor;
	static String nameDisplay;
	
	static Hub hub;
	static Meteo meteo;
	static ColorLed colorLed;
	static Display display;
	
	public static void main(String[] args) throws IOException {
		//get the URL of the hub from properties file
		Properties properties = new Properties();
		FileInputStream input = new FileInputStream("src/main/resources/rest.properties");
		properties.load(input);
		URLHub = properties.getProperty("rest.serveraddress");
		nameHub = properties.getProperty("name.module.hub");
		nameMeteo = properties.getProperty("name.module.meteo");
		nameColor = properties.getProperty("name.module.color");
		nameDisplay = properties.getProperty("name.module.display");
		
		//set graphical interface
    	root = new Group();
    	welcom = new Text(20, 15, "Visualize the data collected by your computer");
    	welcom.setFill(Color.PURPLE);
    	quit_button = new Bouton("Quit simulation", Color.PINK,90,210, stage, colorLed);
    	
    	//initialize the devices
    	hub = new Hub("HUB",2,45,"OFFLINE",190,45);
    	meteo = new Meteo( 	new Temperature("TEMPERATURE",2,70,"OFFLINE",190,70),
    						new Humidity("HUMIDITY",2,95,"OFFLINE",190,95),
    						new Pressure("PRESSURE",2,120,"OFFLINE",190,120));
    	display = new Display("DISPLAY",2,145,"OFFLINE",190,145);
    	colorLed = new ColorLed(new FirstLed("LED 1",2,170,Color.BLACK,190,158,55,15,"OFF"),
    							new SecondLed("LED 2",2,195,Color.BLACK,190,188,55,15,"OFF"));   	
    	
    	addToStage();
    	scene = new Scene(root, width, height, Color.WHITE);
    	
        launch(args);
    }
	
	public void start(Stage stage) throws IOException {
		Main.stage = stage;
		
		//get the data
    	hub.initialize(URLHub);
    	meteo.initialize(URLHub);
    	display.initialize(URLHub);
    	colorLed.initialize(URLHub); 	
    	
    	//show stage
    	Main.stage.setTitle("visualizeData");
    	Main.stage.setResizable(false);
    	Main.stage.setScene(scene);
    	Main.stage.show();
    	
    	Main.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    	    public void handle(WindowEvent event) {
    	    	try {
					colorLed.setColor(1,0x000000);
				} catch (YAPI_Exception e) {
					e.printStackTrace();
				}
    	    }
    	});
	}
	
	public static void addToStage() {
		root.getChildren().add(quit_button);
    	root.getChildren().add(welcom);
    	root.getChildren().add(hub.getHubText());
    	root.getChildren().add(hub.getHubValue());
    	root.getChildren().add(meteo.getTemp().getTempText());
    	root.getChildren().add(meteo.getHum().getHumText());
    	root.getChildren().add(meteo.getPres().getPresText());
    	root.getChildren().add(meteo.getTemp().getTempValue());
    	root.getChildren().add(meteo.getHum().getHumValue());
    	root.getChildren().add(meteo.getPres().getPresValue());
    	root.getChildren().add(display.getDisplayText());
    	root.getChildren().add(display.getDisplayValue());
    	root.getChildren().add(colorLed.getLed1().getLedText());
    	root.getChildren().add(colorLed.getLed2().getLedText());
    	root.getChildren().add(colorLed.getLed1().getLedRect());
    	root.getChildren().add(colorLed.getLed2().getLedRect());
    	root.getChildren().add(colorLed.getLed1().getLedON());
    	root.getChildren().add(colorLed.getLed2().getLedON());
    	
	}
}