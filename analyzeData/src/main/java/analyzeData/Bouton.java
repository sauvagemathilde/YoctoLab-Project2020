package analyzeData;

import com.yoctopuce.YoctoAPI.YAPI_Exception;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Bouton {
	Rectangle fond_bouton;
	Text nom;
	
	public Bouton(final String nom_btn, Color color, int width, int height, int posX, int posY, final String style) {
		//fond du bouton
		fond_bouton = new Rectangle();
        fond_bouton.setWidth(width);
        fond_bouton.setHeight(height);
        fond_bouton.setArcWidth(10);
        fond_bouton.setArcHeight(10);
        fond_bouton.setLayoutX(posX);
        fond_bouton.setLayoutY(posY);
        fond_bouton.setFill(color);
        
        //texte du bouton
        nom = new Text(nom_btn);
        nom.setFont(new Font(12));
      	nom.setFill(Color.BLACK);
      	nom.setY(posY+18);
      	if (style=="chooseTemp") {
      		nom.setX(posX+8);
      	} else if (style=="chooseDisplay") {
      		nom.setX(posX+5);
      	} else if (style=="chooseColor") {
      		nom.setX(posX+3);
      	}
     		
     	fond_bouton.setOnMousePressed(new EventHandler<MouseEvent>(){
     		public void handle(MouseEvent me){
     			appuyer();
            }
        });
        fond_bouton.setOnMouseReleased(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                try {
					relacher(style);
				} catch (YAPI_Exception e) {
					e.printStackTrace();
				}
            }
        });
	}
	
	public Bouton(final String nom_bouton, Color color, int posX, int posY, final Stage stage) {
        //fond du bouton
		fond_bouton = new Rectangle();
        fond_bouton.setWidth(110);
        fond_bouton.setHeight(40);
        fond_bouton.setArcWidth(10);
        fond_bouton.setArcHeight(10);
        fond_bouton.setLayoutX(posX);
        fond_bouton.setLayoutY(posY);
        fond_bouton.setFill(color);
        
		//texte du bouton
		nom = new Text(nom_bouton);
		nom.setFont(new Font(12));
		nom.setFill(Color.BLACK);
		nom.setX(posX+18);
		nom.setY(posY+22);
		
		fond_bouton.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                appuyer();
            }
        });
        fond_bouton.setOnMouseReleased(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
            	try {
					relacherEtQuitter(stage);
				} catch (YAPI_Exception e) {
					e.printStackTrace();
				}
            }
        });
	}
	
	
	Rectangle getRect() {
		return fond_bouton;
	}
	
	Text getName() {
		return nom;
	}
	
	

	void appuyer(){
        fond_bouton.setFill(Color.DARKGREY);
        fond_bouton.setTranslateY(2);
        nom.setTranslateY(2);
    }
    
    void relacher(String style) throws YAPI_Exception {
       fond_bouton.setTranslateY(-1);
       nom.setTranslateY(-1);
       if (style=="chooseDisplay") {
    	   fond_bouton.setFill(Color.LIGHTBLUE);
        	if (getName().getText() == "Stop display of the temperature") {
            	nom.setText("Display temperature");
            	Main.chooseDisplay.choice.setText("nothing displayed");
            	Main.chosenDisplay = getName().getText();
            	
            } else if (getName().getText() == "Display temperature") {
            	nom.setText("Stop display of the temperature");
            	Main.chosenDisplay = getName().getText();
            	Main.chooseDisplay.choice.setText("temperature displayed");
            } else {
            	System.out.println("error");
            }
        } else if (style=="chooseTemp") {
        	fond_bouton.setFill(Color.LIGHTBLUE);
        	Main.chosenTemperature = Integer.parseInt(getName().getText());
        	Main.chooseTemp.choice.setText(getName().getText());
        	
        } else if (style=="chooseColor1") {
        	chooseColorBtn tab = Main.chooseColor1;
        	for (int i=0;i<tab.choices.length;i++) {//recherche de la couleur choisie
        		if (tab.choices[i]==getName().getText()) {
        			fond_bouton.setFill(Color.web("#"+tab.colors[i]));
        			Main.chosenColor1 = tab.codeColor[i];
        			Main.chooseColor1.choice.setText(tab.choices[i]);
        		}
        	}
        } else if (style=="chooseColor2") {
        	chooseColorBtn tab = Main.chooseColor2;
        	for (int i=0;i<tab.choices.length;i++) {//recherche de la couleur choisie
        		if (tab.choices[i]==getName().getText()) {
        			fond_bouton.setFill(Color.web("#"+tab.colors[i]));
        			Main.chosenColor2 = tab.codeColor[i];
        			Main.chooseColor2.choice.setText(tab.choices[i]);
        		}
        	}
        }
    }
    
    void relacherEtQuitter(Stage stage) throws YAPI_Exception {
    	fond_bouton.setFill(Color.WHITE);
        fond_bouton.setTranslateY(-2);
        nom.setTranslateY(-2);
        Main.colorLed.setColor(1,0x000000);
        System.exit(0);
        stage.close();
    }
}