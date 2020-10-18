package visualizeData;

import com.yoctopuce.YoctoAPI.YHumidity;
import javafx.scene.text.Text;

public class Humidity {
	YHumidity sensorHumidity;
	Text humText;
	Text humValue;
	
	Humidity(String texte, int xText, int yText, String hum, int xHum, int yHum) {
		humText = new Text(xText, yText, texte);
		humValue = new Text(xHum, yHum, hum);
	}
	
	Text getHumText() {
		return humText;
	}
	
	Text getHumValue() {
		return humValue;
	}
	
	YHumidity getSensor() {
		return sensorHumidity;
	}	
	
	void setHumText(String texte) {
		humText.setText(texte);
	}
	
	void setHumValue(String texte) {
		humValue.setText(texte);
	}
	
	void setSensor(String sensorName) {
		sensorHumidity = YHumidity.FindHumidity(sensorName) ;
	}
}