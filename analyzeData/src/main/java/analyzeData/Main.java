package analyzeData;

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
	static int width = 360;
	static int height = 460;
	static int part1 = 15;
	static int part2 = 155+30;
	static Text welcom;
	static Text buildCom;
	
	static String URLHub;
	static String nameHub;
	static String nameMeteo;
	static String nameColor;
	static String nameDisplay;
	
	static Hub hub;
	static Temperature temperature;
	static Display display;
	static ColorLed colorLed;
	
	static chooseDisplayBtn chooseDisplay;
	static chooseTempBtn chooseTemp;
	static chooseColorBtn chooseColor1;
	static chooseColorBtn chooseColor2;
	
	static int chosenColor1 = 0xF00909;
	static int chosenColor2 = 0xF00909;
	static int chosenTemperature = 45;
	static String chosenDisplay = "nothing displayed";
	
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
		welcom = new Text(32, part1, "Visualize the data collected by your computer");
		welcom.setFill(Color.PURPLE);
		buildCom = new Text(32, part2, "Make the devices communicate with each other");
		buildCom.setFill(Color.PURPLE);
		    	
    	//initialize the devices
    	hub = new Hub("HUB",2,part1+30,"OFFLINE",220,part1+30);
    	temperature = new Temperature("TEMPERATURE",2,part1+55,"OFFLINE",220,part1+55);
    	display = new Display("DISPLAY",2,part1+80,"OFFLINE",220,part1+80);
    	colorLed = new ColorLed(new FirstLed("LED 1",2,part1+105,Color.BLACK,220,part1+93,55,15,"OFF"),
    							new SecondLed("LED 2",2,part1+130,Color.BLACK,220,part1+118,55,15,"OFF"));
    	
    	//add buttons
    	chooseTemp = new chooseTempBtn("Choose the threshold temperature :",Color.LIGHTBLUE,2,part2+20);
    	chooseDisplay = new chooseDisplayBtn("Display temperature :",Color.LIGHTBLUE,2,part2+70);
    	chooseColor1 = new chooseColorBtn("Choose the color of LED 1 :",Color.LIGHTBLUE,2,part2+120, "chooseColor1");
    	chooseColor2 = new chooseColorBtn("Choose the color of LED 2 :",Color.LIGHTBLUE,2,part2+170, "chooseColor2");
    	quit_button = new Bouton("Quit simulation", Color.PINK,125,height-45, stage);
    	
    	//set stage
    	addToStage();
    	scene = new Scene(root, width, height, Color.WHITE);
    	
        launch(args);
    }
	
	public void start(Stage stage) {
		Main.stage = stage;
		
		//get the data
    	hub.initialize(URLHub);
    	temperature.initialize(URLHub);
    	display.initialize(URLHub, chooseDisplay.getBtn());
    	colorLed.initialize(URLHub);
    	
    	//show stage
    	Main.stage.setTitle("analyzeData");
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
		//add elements to the stage
    	root.getChildren().add(quit_button.getRect());
    	root.getChildren().add(quit_button.getName());
    	root.getChildren().add(welcom);
    	root.getChildren().add(buildCom);
    	root.getChildren().add(hub.getHubText());
    	root.getChildren().add(hub.getHubValue());
    	root.getChildren().add(temperature.getTempText());
    	root.getChildren().add(temperature.getTempValue());
    	root.getChildren().add(display.getDisplayText());
    	root.getChildren().add(display.getDisplayValue());
    	root.getChildren().add(colorLed.getFirstLed().getLedText());
    	root.getChildren().add(colorLed.getFirstLed().getLedRect());
    	root.getChildren().add(colorLed.getFirstLed().getLedON());
    	root.getChildren().add(colorLed.getSecondLed().getLedText());
    	root.getChildren().add(colorLed.getSecondLed().getLedRect());
    	root.getChildren().add(colorLed.getSecondLed().getLedON());
    	
    	root.getChildren().add(chooseTemp.getText());
    	for (int i=0;i<chooseTemp.fils.length;i++) {
    		root.getChildren().add(chooseTemp.fils[i].getRect());
    		root.getChildren().add(chooseTemp.fils[i].getName());
    	};
    	root.getChildren().add(chooseTemp.choice);
    	
    	root.getChildren().add(chooseColor1.getText());
    	for (int i=0;i<chooseColor1.fils.length;i++) {
    		root.getChildren().add(chooseColor1.fils[i].getRect());
    	};
    	root.getChildren().add(chooseColor1.choice);
    	
    	root.getChildren().add(chooseColor2.getText());
    	for (int i=0;i<chooseColor2.fils.length;i++) {
    		root.getChildren().add(chooseColor2.fils[i].getRect());
    	};
    	root.getChildren().add(chooseColor2.choice);
    	
    	root.getChildren().add(chooseDisplay.getText());
    	root.getChildren().add(chooseDisplay.btn.getRect());
    	root.getChildren().add(chooseDisplay.btn.getName());
    	root.getChildren().add(chooseDisplay.choice);
	}
}