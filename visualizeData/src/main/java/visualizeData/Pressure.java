package visualizeData;

import com.yoctopuce.YoctoAPI.YPressure;
import javafx.scene.text.Text;

public class Pressure {
	YPressure sensorPressure;
	Text presText;
	Text presValue;
	
	Pressure(String texte, int xText, int yText, String pres, int xPres, int yPres) {
		presText = new Text(xText, yText, texte);
		presValue = new Text(xPres, yPres, pres);
	}
	
	Text getPresText() {
		return presText;
	}
	
	Text getPresValue() {
		return presValue;
	}
	
	YPressure getSensor() {
		return sensorPressure;
	}	
	
	void setPresText(String texte) {
		presText.setText(texte);
	}
	
	void setPresValue(String texte) {
		presValue.setText(texte);
	}
	
	void setSensor(String sensorName) {
		sensorPressure = YPressure.FindPressure(sensorName) ;
	}
}