package analyzeData;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class chooseTempBtn {
	int choices[] = {15,20,25,30,35,40};
	Bouton fils[];
	Text chooseTempText;
	Text choice;
	
	public chooseTempBtn(String texte, Color color, int posX, int posY) {
		chooseTempText = new Text(posX,posY,texte);
    	chooseTempText.setFill(Color.DARKCYAN);
    	choice = new Text(220,posY,String.valueOf(Main.chosenTemperature));
		fils = new Bouton[choices.length];
		for (int i=0;i<choices.length;i++) {
			Bouton btn = new Bouton(String.valueOf(choices[i]),color,30,30,5+i*(35),posY+5, "chooseTemp");
			fils[i]=btn;
		}
	}
	
	Text getText() {
		return chooseTempText;
	}

}
