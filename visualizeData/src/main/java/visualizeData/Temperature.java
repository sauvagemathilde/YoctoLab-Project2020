package visualizeData;

import com.yoctopuce.YoctoAPI.YTemperature;
import javafx.scene.text.Text;

public class Temperature {
	YTemperature sensorTemperature;
	Text tempText;
	Text tempValue;
	
	Temperature(String texte, int xText, int yText, String temp, int xTemp, int yTemp) {
		tempText = new Text(xText, yText, texte);
		tempValue = new Text(xTemp, yTemp, temp);
	}
	
	Text getTempText() {
		return tempText;
	}
	
	Text getTempValue() {
		return tempValue;
	}
	
	YTemperature getSensor() {
		return sensorTemperature;
	}	
	
	void setTempText(String texte) {
		tempText.setText(texte);
	}
	
	void setTempValue(String texte) {
		tempValue.setText(texte);
	}
	
	void setSensor(String sensorName) {
		sensorTemperature = YTemperature.FindTemperature(sensorName) ;
	}
}