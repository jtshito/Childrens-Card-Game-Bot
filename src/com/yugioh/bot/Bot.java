package com.yugioh.bot;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
public class Bot extends Robot{
	public int deckX, deckY, yesX, yesY, flagX, flagY, borderX, borderY, flagRed, flagGreen, flagBlue, borderRed, borderGreen, borderBlue;
	public Bot(GraphicsDevice screen) throws AWTException {
		super(screen);
		DomParser parser = new DomParser("config.xml");
		List<Integer> xmlData = parser.parseCoordinates();
		
		deckX = xmlData.remove(0);
		deckY = xmlData.remove(0);
		yesX = xmlData.remove(0);
		yesY = xmlData.remove(0);
		flagX = xmlData.remove(0);
		flagY = xmlData.remove(0);
		borderX = xmlData.remove(0);
		borderY = xmlData.remove(0);
		flagRed= xmlData.remove(0);
		flagGreen = xmlData.remove(0);
		flagBlue = xmlData.remove(0);
		borderRed = xmlData.remove(0);
		borderGreen = xmlData.remove(0);
		borderBlue = xmlData.remove(0);

		
	}
	public void checkForCancel() {
		System.out.println(Thread.currentThread().isInterrupted());
		try {
			Thread.sleep(1);
			return;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("KILLED THE WORKER :D");
			e.printStackTrace();
		}
	}
	public void surrender1() {
		checkForCancel();
		for(int i = 0; i<10;i++) {System.out.println(i + " " + Thread.currentThread().isInterrupted()); delay(500);}
	}
	public Integer[] getCoord() {
		Integer[] coord = {0,0,0,0,0};
		checkForCancel();
		coord[0] = MouseInfo.getPointerInfo().getLocation().x;
		coord[1] = MouseInfo.getPointerInfo().getLocation().y;
		Color color = getPixelColor(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
		coord[2] = color.getRed();
		coord[3] = color.getGreen();
		coord[4] = color.getBlue();
		return coord;
	}
	/*Duelist Challenge Cycle
	 * 1. Duelist Challenges Page - Press Enter
	 * 
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
		checkForCancel();
		delay(1000);
		checkForCancel();
		pressEnter();
		delay(25);
		checkForCancel();
		pressEnter();
		checkForCancel();
		delay(8000);
		checkForCancel();
		pressEnter();
		delay(3000);
		checkForCancel();
		pressEnter();
		checkForCancel();
		delay(5000);
		checkForCancel();
		mouseMove(deckX, deckY); System.out.println("Moving Mouse to Deck");
		delay(8000);
		checkForCancel();
		boolean myTurn = false;
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		BufferedImage image;
		Color color = new Color(248,248,250);
		while(myTurn == false) {
			checkForCancel();
			System.out.println("Searching for Flag");
			//image = createScreenCapture(new Rectangle(0,0,(int) screenDim.getWidth(), (int) screenDim.getHeight()));
			if(getPixelColor(flagX,flagY).getBlue() >flagBlue && getPixelColor(flagX,flagY).getRed() > flagRed && getPixelColor(flagX,flagY).getGreen() >flagGreen) {
				myTurn = true;
			}
			checkForCancel();
			mouseMove(deckX+1,deckY);
			mouseMove(deckX,deckY);
			delay(250);
			
		}
		click();
		checkForCancel();
		pressEnter();
		delay(500);
		mouseMove(yesX,yesY); System.out.println("Moving Mouse to YES");
		checkForCancel();
		click();
		delay(750);
		checkForCancel();
		pressEnter();
		checkForCancel();
		delay(750);
		checkForCancel();
		pressThree();
		checkForCancel();
		delay(500);
		pressEnter();
	}
	
	public void buy() {
		checkForCancel();
		delay(300);
		pressEnter();
		checkForCancel();
		delay(25);
		pressEnter();
		checkForCancel();
		delay(25);
		pressEnter();
		checkForCancel();
		delay(5500);
		checkForCancel();
		boolean myTurn = false;
		int count = 0;
		while(myTurn == false) {
			checkForCancel();
			System.out.println("Searching for Border");
			//image = createScreenCapture(new Rectangle(0,0,(int) screenDim.getWidth(), (int) screenDim.getHeight()));
			if(getPixelColor(borderX,borderY).getRed() > borderRed) {
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
