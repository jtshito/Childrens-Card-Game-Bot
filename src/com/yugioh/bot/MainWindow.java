package com.yugioh.bot;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;

import javax.swing.JRadioButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;
import javax.swing.JButton;

public class MainWindow {

	private JFrame frame;
	private AtomicBoolean isBuy = new AtomicBoolean();
	private AtomicBoolean isRunning = new AtomicBoolean();
	private AtomicBoolean isCoordRunning = new AtomicBoolean();
	public int workerNum = 0;
	private SwingWorker worker, coordWorker;
	public SwingWorker getWorker() {
		return worker;
	}

	public void setWorker(SwingWorker worker) {
		this.worker = worker;
	}

	public AtomicBoolean getIsRunning() {
		return isRunning;
	}

	public void setIsRunning(AtomicBoolean isRunning) {
		this.isRunning = isRunning;

	}

	public AtomicBoolean isBuy() {
		return isBuy;
	}

	public void setBuy(AtomicBoolean isBuy) {
		this.isBuy = isBuy;
	}

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final Action action = new SwingAction();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 675, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{87, 56, 38, 125, 95, 0};
		gbl_panel.rowHeights = new int[]{30, 25, 25, 35, 31, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		final JLabel lblStopped = new JLabel("STOPPED");
		GridBagConstraints gbc_lblStopped = new GridBagConstraints();
		gbc_lblStopped.insets = new Insets(0, 0, 5, 5);
		gbc_lblStopped.gridx = 3;
		gbc_lblStopped.gridy = 1;
		panel.add(lblStopped, gbc_lblStopped);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblStopped.setText("Stopped");
				worker.cancel(true);
				worker = null;
				isRunning.set(false);
			}
		});
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 5, 5);
		gbc_btnStop.gridx = 2;
		gbc_btnStop.gridy = 2;
		panel.add(btnStop, gbc_btnStop);

		
		
		final JLabel lblCoord = new JLabel("Coord");
		GridBagConstraints gbc_lblCoord = new GridBagConstraints();
		gbc_lblCoord.insets = new Insets(0, 0, 5, 0);
		gbc_lblCoord.gridx = 4;
		gbc_lblCoord.gridy = 2;
		panel.add(lblCoord, gbc_lblCoord);
		
		final JLabel lblRgb = new JLabel("RGB");
		GridBagConstraints gbc_lblRgb = new GridBagConstraints();
		gbc_lblRgb.insets = new Insets(0, 0, 5, 0);
		gbc_lblRgb.gridx = 4;
		gbc_lblRgb.gridy = 3;
		panel.add(lblRgb, gbc_lblRgb);
		
		JLabel label_1 = new JLabel("");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 4;
		panel.add(label_1, gbc_label_1);
		
		JLabel lblDeck = new JLabel("Deck");
		GridBagConstraints gbc_lblDeck = new GridBagConstraints();
		gbc_lblDeck.anchor = GridBagConstraints.EAST;
		gbc_lblDeck.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeck.gridx = 0;
		gbc_lblDeck.gridy = 5;
		panel.add(lblDeck, gbc_lblDeck);
		
		DomParser parser = new DomParser("config.xml");
		List<Integer> coords = parser.parseCoordinates();
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setText(Integer.toString(coords.get(0)));
	
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 5;
		panel.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		textField_4.setText(Integer.toString(coords.get(1)));
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 4;
		gbc_label.gridy = 5;
		panel.add(label, gbc_label);
		
		JLabel lblYes = new JLabel("Yes");
		GridBagConstraints gbc_lblYes = new GridBagConstraints();
		gbc_lblYes.anchor = GridBagConstraints.EAST;
		gbc_lblYes.insets = new Insets(0, 0, 5, 5);
		gbc_lblYes.gridx = 0;
		gbc_lblYes.gridy = 6;
		panel.add(lblYes, gbc_lblYes);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 6;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		textField_1.setText(Integer.toString(coords.get(2)));
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 6;
		panel.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		textField_5.setText(Integer.toString(coords.get(3)));
		
		JLabel lblFlag = new JLabel("Flag");
		GridBagConstraints gbc_lblFlag = new GridBagConstraints();
		gbc_lblFlag.anchor = GridBagConstraints.EAST;
		gbc_lblFlag.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlag.gridx = 0;
		gbc_lblFlag.gridy = 7;
		panel.add(lblFlag, gbc_lblFlag);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 7;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		textField_2.setText(Integer.toString(coords.get(4)));
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 2;
		gbc_textField_6.gridy = 7;
		panel.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		textField_6.setText(Integer.toString(coords.get(5)));
		
		JLabel lblBorder = new JLabel("Border");
		GridBagConstraints gbc_lblBorder = new GridBagConstraints();
		gbc_lblBorder.anchor = GridBagConstraints.EAST;
		gbc_lblBorder.insets = new Insets(0, 0, 5, 5);
		gbc_lblBorder.gridx = 0;
		gbc_lblBorder.gridy = 8;
		panel.add(lblBorder, gbc_lblBorder);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 8;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		textField_3.setText(Integer.toString(coords.get(6)));
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 2;
		gbc_textField_7.gridy = 8;
		panel.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		textField_7.setText(Integer.toString(coords.get(7)));
		
		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 9;
		panel.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		textField_8.setText(Integer.toString(coords.get(8)));
		
		textField_9 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 2;
		gbc_textField_9.gridy = 9;
		panel.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);
		textField_9.setText(Integer.toString(coords.get(9)));
		
		textField_10 = new JTextField();
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 3;
		gbc_textField_10.gridy = 9;
		panel.add(textField_10, gbc_textField_10);
		textField_10.setColumns(10);
		textField_10.setText(Integer.toString(coords.get(10)));
		
		textField_11 = new JTextField();
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 0, 5);
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 1;
		gbc_textField_11.gridy = 10;
		panel.add(textField_11, gbc_textField_11);
		textField_11.setColumns(10);
		textField_11.setText(Integer.toString(coords.get(11)));
		
		textField_12 = new JTextField();
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.insets = new Insets(0, 0, 0, 5);
		gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_12.gridx = 2;
		gbc_textField_12.gridy = 10;
		panel.add(textField_12, gbc_textField_12);
		textField_12.setColumns(10);
		textField_12.setText(Integer.toString(coords.get(12)));
		
		textField_13 = new JTextField();
		GridBagConstraints gbc_textField_13 = new GridBagConstraints();
		gbc_textField_13.insets = new Insets(0, 0, 0, 5);
		gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_13.gridx = 3;
		gbc_textField_13.gridy = 10;
		panel.add(textField_13, gbc_textField_13);
		textField_13.setColumns(10);
		JButton btnUpdateCoordinates = new JButton("Update Coordinates");
		textField_13.setText(Integer.toString(coords.get(13)));
		
		btnUpdateCoordinates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DomParser parser = new DomParser("config.xml");
				List<Integer> coords = new ArrayList();
				coords.add(Integer.valueOf((textField.getText())));
				coords.add(Integer.valueOf((textField_4.getText())));
				coords.add(Integer.valueOf((textField_1.getText())));
				coords.add(Integer.valueOf((textField_5.getText())));
				coords.add(Integer.valueOf((textField_2.getText())));
				coords.add(Integer.valueOf((textField_6.getText())));
				coords.add(Integer.valueOf((textField_3.getText())));
				coords.add(Integer.valueOf((textField_7.getText())));
				coords.add(Integer.valueOf((textField_8.getText())));
				coords.add(Integer.valueOf((textField_9.getText())));
				coords.add(Integer.valueOf((textField_10.getText())));
				coords.add(Integer.valueOf((textField_11.getText())));
				coords.add(Integer.valueOf((textField_12.getText())));
				coords.add(Integer.valueOf((textField_13.getText())));
				
				parser.modifyCoordinates(coords);
			}
		});
		GridBagConstraints gbc_btnUpdateCoordinates = new GridBagConstraints();
		gbc_btnUpdateCoordinates.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdateCoordinates.gridx = 3;
		gbc_btnUpdateCoordinates.gridy = 8;
		panel.add(btnUpdateCoordinates, gbc_btnUpdateCoordinates);
		
		final JToggleButton tglbtnCoordTool = new JToggleButton("Coord Tool");
		tglbtnCoordTool.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(isCoordRunning.get()){
					System.out.println("Cancelling coordWorker");
					isCoordRunning.set(false);
					coordWorker.cancel(true);
					coordWorker = null;
					return;
				}
				coordWorker = new SwingWorker<Integer, Integer[]>(){
					@Override
					protected Integer doInBackground() throws Exception {
						
						GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
						GraphicsDevice gd = ge.getDefaultScreenDevice();
						Bot bot = new Bot(gd);
						isCoordRunning.set(true);
						while(isCoordRunning.get() == true) {
							System.out.println("isCoordRunning is: " + isCoordRunning.get());
							publish(bot.getCoord());
							bot.delay(250);
						}
						return null;
					}
					protected void process(List<Integer[]> coords) {
						Integer[] mostRecentArray = coords.get(coords.size()-1);
						lblCoord.setText(mostRecentArray[0].toString() + ", " + mostRecentArray[1].toString());
						lblRgb.setText(mostRecentArray[2] + ", " + mostRecentArray[3] + ", " + mostRecentArray[4]);
					}
					
				};coordWorker.execute();
			}
		});
		
		final JButton tglbtnRun = new JButton("Run");
		tglbtnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isBuy.get()) {lblStopped.setText("Running Buy");}
				if(!isBuy.get()) {lblStopped.setText("Running Surrender");}
				if(isRunning.get()){
					System.out.println("Bot is already running...");
					return;
				}
				worker = new SwingWorker<Integer, Integer[]>(){
					@Override
					protected Integer doInBackground() throws Exception {
						
						GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
						GraphicsDevice gd = ge.getDefaultScreenDevice();
						Bot bot = new Bot(gd);
						isRunning.set(true);
						while(isRunning.get() == true) {
							System.out.println("isCoordRunning is: " + isCoordRunning.get());
							if(isBuy.get() == false) {
								bot.surrender();
							}
							if(isBuy.get() == true) {
								bot.buy();
							}
						}
						return null;
					}
					
				};worker.execute();
			}
		});
		
		JRadioButton rdbtnSurrender = new JRadioButton("Surrender");
		rdbtnSurrender.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				isBuy.set(false);
			}
		});
		
				buttonGroup.add(rdbtnSurrender);
				GridBagConstraints gbc_rdbtnSurrender = new GridBagConstraints();
				gbc_rdbtnSurrender.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnSurrender.gridx = 0;
				gbc_rdbtnSurrender.gridy = 1;
				panel.add(rdbtnSurrender, gbc_rdbtnSurrender);
		GridBagConstraints gbc_tglbtnRun = new GridBagConstraints();
		gbc_tglbtnRun.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnRun.gridx = 2;
		gbc_tglbtnRun.gridy = 1;
		panel.add(tglbtnRun, gbc_tglbtnRun);
		GridBagConstraints gbc_tglbtnCoordTool = new GridBagConstraints();
		gbc_tglbtnCoordTool.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnCoordTool.gridx = 4;
		gbc_tglbtnCoordTool.gridy = 1;
		panel.add(tglbtnCoordTool, gbc_tglbtnCoordTool);
		
		JRadioButton rdbtnBuyPacks = new JRadioButton("Buy Packs");
		rdbtnBuyPacks.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				isBuy.set(true);;
			}
		});
		buttonGroup.add(rdbtnBuyPacks);
		GridBagConstraints gbc_rdbtnBuyPacks = new GridBagConstraints();
		gbc_rdbtnBuyPacks.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBuyPacks.gridx = 0;
		gbc_rdbtnBuyPacks.gridy = 2;
		panel.add(rdbtnBuyPacks, gbc_rdbtnBuyPacks);
		
		JLabel lblFlagRgb = new JLabel("Flag RGB");
		GridBagConstraints gbc_lblFlagRgb = new GridBagConstraints();
		gbc_lblFlagRgb.anchor = GridBagConstraints.EAST;
		gbc_lblFlagRgb.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlagRgb.gridx = 0;
		gbc_lblFlagRgb.gridy = 9;
		panel.add(lblFlagRgb, gbc_lblFlagRgb);
		
		
		JLabel lblBorderRgb = new JLabel("Border RGB");
		GridBagConstraints gbc_lblBorderRgb = new GridBagConstraints();
		gbc_lblBorderRgb.anchor = GridBagConstraints.EAST;
		gbc_lblBorderRgb.insets = new Insets(0, 0, 0, 5);
		gbc_lblBorderRgb.gridx = 0;
		gbc_lblBorderRgb.gridy = 10;
		panel.add(lblBorderRgb, gbc_lblBorderRgb);
		
		
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
