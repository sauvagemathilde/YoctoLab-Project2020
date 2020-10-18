package analyzeData;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class chooseColorBtn {
	String choices[] = {"White","Red","Blue","Green","Yellow","Purple"};
	int codeColor[] = {0xFFFFFF, 0xF00909, 0x0931F0, 0x10F009, 0xFFEC00, 0xC734C7};
	String colors[] = {"FFFFFF", "F00909", "0931F0", "10F009", "FFEC00", "C734C7"};
	Bouton fils[];
	Text chooseColorText;
	Text choice;
	
	public chooseColorBtn(String texte, Color color, int posX, int posY, String style) {
		chooseColorText = new Text(posX,posY,texte);
		chooseColorText.setFill(Color.DARKCYAN);
		fils = new Bouton[choices.length];
		for (int i=0;i<choices.length;i++) {
			fils[i] = new Bouton(choices[i],Color.web("#"+colors[i]),40,30,5+i*(45),posY+5, style);
			fils[i].fond_bouton.setStroke(Color.BLACK);
			if (style=="chooseColor1") {
				if (codeColor[i]==Main.chosenColor1) {
					choice = new Text(220,posY, choices[i]);
				}
			} else {
				if (codeColor[i]==Main.chosenColor2) {
					choice = new Text(220,posY, choices[i]);
				}
			}
		}
	}
	
	Text getText() {
		return chooseColorText;
	}
}
