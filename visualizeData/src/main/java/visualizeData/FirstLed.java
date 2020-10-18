package visualizeData;

import com.yoctopuce.YoctoAPI.YAPI_Exception;
import com.yoctopuce.YoctoAPI.YColorLed;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class FirstLed {
	YColorLed sensorLed;
	Text ledText;
	Rectangle ledRectColor;
	Text ledON;
	
	FirstLed(String texte, int xText, int yText, Color colorRect, int xRect, int yRect, int widthRect, int heightRect, String ON) {
		ledText = new Text(xText,yText,texte);
		ledRectColor = new Rectangle(widthRect,heightRect,colorRect);
		ledRectColor.setX(xRect);
		ledRectColor.setY(yRect);
		ledON = new Text(xRect+widthRect+10, yRect+heightRect/1.3, ON);
	}
	
	Text getLedText() {
		return ledText;
	}
	
	Rectangle getLedRect() {
		return ledRectColor;
	}
	
	Text getLedON() {
		return ledON;
	}
	
	Color getRectColor() {
		return (Color) ledRectColor.getFill();
	}
	
	Color getColor() throws YAPI_Exception {
		return Color.web("#"+getSensor().get_advertisedValue());
	}
	
	YColorLed getSensor() {
		return sensorLed;
	}
	
	void setLedText(String texte) {
		ledText.setText(texte);
	}
	
	void setLedRect(Color color) {
		ledRectColor.setFill(color);
	}
	
	void setLedON(String ON) {
		ledON.setText(ON);
	}
	
	void setSensor(String sensorName) {
		sensorLed = YColorLed.FindColorLed(sensorName) ;
	}
}