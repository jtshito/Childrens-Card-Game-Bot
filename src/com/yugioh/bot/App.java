package com.yugioh.bot;
import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.*;
import java.awt.Robot;
public class App {

	/*public static void main(String[] args) throws AWTException{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		Bot bot = new Bot(gd);
		
		while(true) {
			bot.mouseMove(1460, 757);
			bot.delay(2000);
		}*/
		
		/*while(true) {
			System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x + 
		              ", " + 
		              MouseInfo.getPointerInfo().getLocation().y + ")");
			Color color = bot.getPixelColor(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
			System.out.println(color);
		}
		
		int count = 0;
		bot.delay(3000);
		while(true) {

			
			//
			bot.surrender();
			//bot.buy();
			count++;
			System.out.println(count + " SUCCESSFUL CYCLES!");
		}
		
	}*/

}
