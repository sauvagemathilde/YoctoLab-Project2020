package visualizeData;

import com.yoctopuce.YoctoAPI.YAPI_Exception;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Bouton extends Parent {
	Rectangle fond_bouton;
	Text nom;
	
	public Bouton(final String nom_bouton, Color color, int posX, int posY, final Stage stage, final ColorLed colorLed) {
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
					relacher(stage);
				} catch (YAPI_Exception e) {
					e.printStackTrace();
				}
            }
        });
		this.getChildren().add(fond_bouton);
		this.getChildren().add(nom);
	}
	
	void appuyer(){
        fond_bouton.setFill(Color.DARKGREY);
        fond_bouton.setTranslateY(2);
        nom.setTranslateY(2);
    }
	
	void relacher(Stage stage) throws YAPI_Exception {
    	fond_bouton.setFill(Color.WHITE);
        fond_bouton.setTranslateY(-2);
        nom.setTranslateY(-2);
        Main.colorLed.setColor(1,0x000000);
        System.exit(0);
        stage.close();
    }
}