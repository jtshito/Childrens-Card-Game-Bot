package com.yugioh.bot;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
import java.util.concurrent.atomic.AtomicBoolean;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class MainWindow {

	private JFrame frame;
	private AtomicBoolean isBuy = new AtomicBoolean();
	private AtomicBoolean isRunning = new AtomicBoolean();
	public int workerNum = 0;
	private SwingWorker worker = null;
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JRadioButton rdbtnSurrender = new JRadioButton("Surrender");
		rdbtnSurrender.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setBuy(new AtomicBoolean(false));
			}
		});

		buttonGroup.add(rdbtnSurrender);
		GridBagConstraints gbc_rdbtnSurrender = new GridBagConstraints();
		gbc_rdbtnSurrender.gridwidth = 2;
		gbc_rdbtnSurrender.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSurrender.gridx = 1;
		gbc_rdbtnSurrender.gridy = 1;
		panel.add(rdbtnSurrender, gbc_rdbtnSurrender);
		
		final JToggleButton tglbtnRun = new JToggleButton("Run");
		tglbtnRun.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			}
		});
		tglbtnRun.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			}
		});
		tglbtnRun.addMouseListener(new MouseAdapter() {//Start the SwingWorker thread that run the bot code
			public void mouseClicked(MouseEvent e) {
				if(getWorker() != null) {
					System.out.println("Killing Bot :(");
					tglbtnRun.setText("Run");
					if(getWorker().cancel(true)) {System.out.println("COULD NOT KILL WORKER");}
					setWorker(null);
					setIsRunning(new AtomicBoolean(false));
				}
				System.out.println("Running Bot: isRunning = " + isRunning);
				if(tglbtnRun.isSelected() == true) {
					setIsRunning(new AtomicBoolean(true));
					tglbtnRun.setText("Stop");
				}
				else {
					tglbtnRun.setText("Run");
					setIsRunning(new AtomicBoolean(false));
				}
			    setWorker(new SwingWorker<Void, Void>(){
					protected Void doInBackground() throws Exception {
						tglbtnRun.setText("Stop");
						System.out.println("...IN SWINGWORKER");
						GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
						GraphicsDevice gd = ge.getDefaultScreenDevice();
						Bot bot = new Bot(gd);
						while(!isCancelled()) {
						if(!isBuy.get()) {
							
							while(isRunning.get()) {
								System.out.println(Thread.currentThread());
								bot.surrender();
							}
						}
						else {
							while(isRunning.get()) {
								bot.buy();
							}
						}
						
						}
						return null;
					}
					protected void done() {
						System.out.println(getWorker() + "WORKER KILLED");
					}
				});System.out.println(getIsRunning());
				if(isRunning.get()) {
					workerNum++;
					System.out.println("gonna execute the worker");
					getWorker().execute();
				}
			}
		});
		GridBagConstraints gbc_tglbtnRun = new GridBagConstraints();
		gbc_tglbtnRun.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnRun.gridx = 5;
		gbc_tglbtnRun.gridy = 1;
		panel.add(tglbtnRun, gbc_tglbtnRun);
		
		JToggleButton tglbtnCoordTool = new JToggleButton("Coord Tool");
		GridBagConstraints gbc_tglbtnCoordTool = new GridBagConstraints();
		gbc_tglbtnCoordTool.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCoordTool.gridx = 10;
		gbc_tglbtnCoordTool.gridy = 1;
		panel.add(tglbtnCoordTool, gbc_tglbtnCoordTool);
		
		JRadioButton rdbtnBuyPacks = new JRadioButton("Buy Packs");
		rdbtnBuyPacks.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setBuy(new AtomicBoolean(true));
			}
		});
		buttonGroup.add(rdbtnBuyPacks);
		GridBagConstraints gbc_rdbtnBuyPacks = new GridBagConstraints();
		gbc_rdbtnBuyPacks.gridwidth = 2;
		gbc_rdbtnBuyPacks.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBuyPacks.gridx = 1;
		gbc_rdbtnBuyPacks.gridy = 2;
		panel.add(rdbtnBuyPacks, gbc_rdbtnBuyPacks);
		
		JLabel lblStopped = new JLabel("Stopped");
		GridBagConstraints gbc_lblStopped = new GridBagConstraints();
		gbc_lblStopped.insets = new Insets(0, 0, 0, 5);
		gbc_lblStopped.gridx = 5;
		gbc_lblStopped.gridy = 4;
		panel.add(lblStopped, gbc_lblStopped);
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
