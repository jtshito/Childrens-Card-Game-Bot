package com.yugioh.bot;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
public class Bot extends Robot{

	public Bot(GraphicsDevice screen) throws AWTException {
		super(screen);
		// TODO Auto-generated constructor stub
	}
	/*Duelist Challenge Cycle
	 * 1. Duelist Challenges Page - Press Enter
	 * 2. Duelist Challenger Page - Press Enter
	 * 3. Rock/Paper/Scissors     - Press Enter
	 * 4. Turn Select             - Press Enter
	 * 5. Duel                    - Place mouse over deck
	 * 							  - Wait for surrender flag
	 * 							  - Click on deck
	 * 							  - Press Enter
	 * 							  - Click on Yes
	 * 							  - Press Enter
	 * 							  - Press 3
	 * 							  - Press Enter
	 */
	public void pressEnter() {
		System.out.println("PRESSING ENTER");
		keyPress(KeyEvent.VK_ENTER);
		delay(500);
		keyRelease(KeyEvent.VK_ENTER);
	}
	public void pressThree() {
		System.out.println("PRESSING 3");
		keyPress(KeyEvent.VK_3);
		delay(500);
		keyRelease(KeyEvent.VK_3);
	}
	public void click() {
		System.out.println("CLICKING");
		mousePress(InputEvent.BUTTON1_MASK);
		delay(500);
		mouseRelease(InputEvent.BUTTON1_MASK);
		
	}
	public void surrender() {
		delay(1000);
		pressEnter();
		delay(25);
		pressEnter();
		delay(8000);
		pressEnter();
		delay(3000);
		pressEnter();
		delay(5000);
		mouseMove(1460, 757); System.out.println("Moving Mouse to Deck");
		delay(8000);
		boolean myTurn = false;
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		BufferedImage image;
		Color color = new Color(248,248,250);
		while(myTurn == false) {
			System.out.println("Searching for Flag");
			//image = createScreenCapture(new Rectangle(0,0,(int) screenDim.getWidth(), (int) screenDim.getHeight()));
			if(getPixelColor(1432,661).getBlue() >247 && getPixelColor(1432,661).getRed() > 250 && getPixelColor(1432,661).getGreen() >250) {
				myTurn = true;
			}
			mouseMove(1461,757);
			mouseMove(1460,757);
			delay(1000);
			
		}
		click();
		pressEnter();
		delay(500);
		mouseMove(903,577); System.out.println("Moving Mouse to YES");
		click();
		delay(750);
		pressEnter();
		delay(750);
		pressThree();
		delay(500);
		pressEnter();
	}
	
	public void buy() {
		delay(300);
		pressEnter();
		delay(25);
		pressEnter();
		delay(25);
		pressEnter();
		delay(5500);
		boolean myTurn = false;
		int count = 0;
		while(myTurn == false) {
			System.out.println("Searching for Border");
			//image = createScreenCapture(new Rectangle(0,0,(int) screenDim.getWidth(), (int) screenDim.getHeight()));
			if(getPixelColor(1465,660).getRed() >252) {
				myTurn = true;
			}
			delay(500);
			count++;
			if(count % 5 == 0) {
				pressEnter();
			}
		}
		pressEnter();
	}

}
