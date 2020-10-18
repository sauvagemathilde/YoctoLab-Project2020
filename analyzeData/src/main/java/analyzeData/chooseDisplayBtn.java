package analyzeData;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class chooseDisplayBtn {
	Text chooseDisplayText;
	Text choice;
	Bouton btn;
	
	public chooseDisplayBtn(String texte, Color color, int posX, int posY) {
		chooseDisplayText = new Text(posX,posY,texte);
    	chooseDisplayText.setFill(Color.DARKCYAN);
    	
    	choice = new Text(220,posY,Main.chosenDisplay);
		btn = new Bouton("Display temperature",Color.LIGHTBLUE,180,30,posX,posY+5,"chooseDisplay");
	}
	
	Text getText() {
		return chooseDisplayText;
	}
	Bouton getBtn() {
		return btn;
	}

}
