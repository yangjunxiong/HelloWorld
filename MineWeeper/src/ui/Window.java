package ui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Table;


public class Window {
	private int row, column, x, y;
	private double rate;
	private final int gridX = 40, gridY = 40;
	private final int screenX = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	private final int screenY = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	private Table table;
	private JFrame frame;
	private JPanel panel;
	private JButton overButton;
	private Icon bombIcon, flagIcon, puzzleIcon, overIcon, restartIcon, icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8;
	private boolean flag = true;
	
	class MyMouseListener extends MouseAdapter {
		private int x, y;
		private JButton button;
		public MyMouseListener(int x, int y) {
			this.x = x;
			this.y = y;
			button = table.getGrid(x, y).getButton();
		}
		public void mouseClicked(MouseEvent a) {
			if (!button.isEnabled())
				return;
			if (a.isMetaDown())
				actionRight();
			else
				actionLeft();
			for (int i=0; i<panel.getComponentCount(); i++)
				if (panel.getComponent(i).isEnabled() && !table.getGrid(i/column, i%column).isBomb())
					return;
			overButton.setIcon(restartIcon);
			if (flag)
				JOptionPane.showConfirmDialog(null, "¹§Ï²Äã»ñµÃÁËÊ¤Àû!", "Ê¤Àû", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		}
		private void actionRight() {
			if (button.getIcon() == null)
				button.setIcon(flagIcon);
			else if (button.getIcon() == flagIcon)
				button.setIcon(puzzleIcon);
			else
				button.setIcon(null);
		}
		private void actionLeft() {
			if (button.getIcon() == flagIcon || button.getIcon() == puzzleIcon)
				return;
			button.setEnabled(false);
			button.setBackground(Color.GRAY);
			table.getGrid(x, y).setClicked();
			if (table.getGrid(x, y).isBomb())
				overButton.doClick();
			else {
				if (table.getGrid(x, y).getNumOfBomb() > 0)
					switch (table.getGrid(x, y).getNumOfBomb()) {
					case 1: button.setIcon(icon1); button.setDisabledIcon(icon1); break;
					case 2: button.setIcon(icon2); button.setDisabledIcon(icon2); break;
					case 3: button.setIcon(icon3); button.setDisabledIcon(icon3); break;
					case 4: button.setIcon(icon4); button.setDisabledIcon(icon4); break;
					case 5: button.setIcon(icon5); button.setDisabledIcon(icon5); break;
					case 6: button.setIcon(icon6); button.setDisabledIcon(icon6); break;
					case 7: button.setIcon(icon7); button.setDisabledIcon(icon7); break;
					case 8: button.setIcon(icon8); button.setDisabledIcon(icon8); break;
					}
				try {
					if (table.getGrid(x, y-1).getNumOfBomb() == 0) {
						table.getGrid(x, y-1).setClicked();
						table.getGrid(x, y-1).getButton().setEnabled(false);
						table.getGrid(x, y-1).getButton().setBackground(Color.GRAY);
					}
				}
				catch (Exception ex) {}
				try {
					if (table.getGrid(x, y+1).getNumOfBomb() == 0) {
						table.getGrid(x, y+1).setClicked();
						table.getGrid(x, y+1).getButton().setEnabled(false);
						table.getGrid(x, y+1).getButton().setBackground(Color.GRAY);
					}
				}
				catch (Exception ex) {}
				try {
					if (table.getGrid(x-1, y).getNumOfBomb() == 0) {
						table.getGrid(x-1, y).setClicked();
						table.getGrid(x-1, y).getButton().setEnabled(false);
						table.getGrid(x-1, y).getButton().setBackground(Color.GRAY);
					}
				}
				catch (Exception ex) {}
				try {
					if (table.getGrid(x+1, y).getNumOfBomb() == 0) {
						table.getGrid(x+1, y).setClicked();
						table.getGrid(x+1, y).getButton().setEnabled(false);
						table.getGrid(x+1, y).getButton().setBackground(Color.GRAY);
					}
				}
				catch (Exception ex) {}
			}
		}
	}
	
	class OverActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getIcon() == overIcon) {
				button.setIcon(restartIcon);
				gameOver();
			}
			else {
				button.setIcon(overIcon);
				frame.setVisible(false);
				new StartWindow();
			}
		}
	}
	
	public Window(int row, int column, double rate) {
		this.row = row;
		this.column = column;
		this.rate = rate;
		x = (column + 2) * gridX;
		y = (row + 4) * gridY;
		bombIcon = new ImageIcon(Window.class.getResource("../image/bomb.png"));
		flagIcon = new ImageIcon(Window.class.getResource("../image/flag.png"));
		puzzleIcon = new ImageIcon(Window.class.getResource("../image/puzzle.png"));
		overIcon = new ImageIcon(Window.class.getResource("../image/over.jpg"));
		restartIcon = new ImageIcon(Window.class.getResource("../image/restart.jpg"));
		icon1 = new ImageIcon(Window.class.getResource("../image/1.png"));
		icon2 = new ImageIcon(Window.class.getResource("../image/2.png"));
		icon3 = new ImageIcon(Window.class.getResource("../image/3.png"));
		icon4 = new ImageIcon(Window.class.getResource("../image/4.png"));
		icon5 = new ImageIcon(Window.class.getResource("../image/5.png"));
		icon6 = new ImageIcon(Window.class.getResource("../image/6.png"));
		icon7 = new ImageIcon(Window.class.getResource("../image/7.png"));
		icon8 = new ImageIcon(Window.class.getResource("../image/8.png"));
		buildWindow();
	}
	
	private void buildWindow() {
		frame = new JFrame("É¨À× by Ñî¿¢ÐÛ");
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds((screenX-x)/2, (screenY-y)/2, x, y);
		
		overButton = new JButton(overIcon);
		overButton.addActionListener(new OverActionListener());
		overButton.setBounds(x/2-gridX, gridY/2, gridX * 2, gridY * 2);
		frame.add(overButton);
		
		buildPanel();
		frame.setVisible(true);
	}
	
	private void buildPanel() {
		table = new Table(row, column, rate);
		panel = new JPanel(new GridLayout(row, column));
		for (int i=0; i<row; i++)
			for (int j=0; j<column; j++) {
				JButton button = new JButton();
				table.getGrid(i, j).setButton(button);
				button.addMouseListener(new MyMouseListener(i, j));
				panel.add(button);
			}
		panel.setBounds(gridX, gridY * 3, column * gridX, row * gridY);
		frame.add(panel);
	}
	
	private void gameOver() {
		for (int i=0; i<row; i++)
			for (int j=0; j<column; j++) {
				JButton temp = table.getGrid(i, j).getButton();
				temp.setEnabled(false);
				temp.setBackground(Color.GRAY);
				table.getGrid(i, j).setClicked();
				if (table.getGrid(i, j).isBomb()) {
					temp.setIcon(bombIcon);
					temp.setDisabledIcon(bombIcon);
				}
				else if (table.getGrid(i, j).getNumOfBomb() == 0)
					temp.setIcon(null);
				else
					switch (table.getGrid(i, j).getNumOfBomb()) {
					case 1: temp.setIcon(icon1); temp.setDisabledIcon(icon1); break;
					case 2: temp.setIcon(icon2); temp.setDisabledIcon(icon2); break;
					case 3: temp.setIcon(icon3); temp.setDisabledIcon(icon3); break;
					case 4: temp.setIcon(icon4); temp.setDisabledIcon(icon4); break;
					case 5: temp.setIcon(icon5); temp.setDisabledIcon(icon5); break;
					case 6: temp.setIcon(icon6); temp.setDisabledIcon(icon6); break;
					case 7: temp.setIcon(icon7); temp.setDisabledIcon(icon7); break;
					case 8: temp.setIcon(icon8); temp.setDisabledIcon(icon8); break;
					}
			}
		flag = false;
	}
}
