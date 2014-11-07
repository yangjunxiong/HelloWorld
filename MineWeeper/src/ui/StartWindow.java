package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StartWindow {
	private JFrame frame;
	private JPanel panel, topPanel, endPanel;
	private JButton button;
	private JComboBox row, column;
	private JTextField rate;
	private JLabel info, rowLabel, columnLabel, rateLabel;
	private final int screenX = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	private final int screenY = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int noRow = row.getSelectedIndex() + 3;
			int noColumn = column.getSelectedIndex() + 3;
			new Window(noRow, noColumn, Double.parseDouble(rate.getText()));
			frame.setVisible(false);
		}
	}
	
	public StartWindow() {
		frame = new JFrame("扫雷 by 杨竣雄");
		frame.setBounds((screenX-500)/2, (screenY-500)/2, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		panel = new JPanel(new GridLayout(3, 3));
		topPanel = new JPanel();
		endPanel = new JPanel();
		frame.add(panel, BorderLayout.CENTER);
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(endPanel, BorderLayout.SOUTH);
		
		info = new JLabel("请在下面选择地雷阵的行列数，并输入地雷产生几率");
		rowLabel = new JLabel("行：");
		columnLabel = new JLabel("列：");
		rateLabel = new JLabel("地雷产生几率（建议0.1到0.4之间）");
		button = new JButton("开始游戏！");
		button.addActionListener(new MyActionListener());
		row = new JComboBox();
		row.setBackground(Color.WHITE);
		column = new JComboBox();
		column.setBackground(Color.WHITE);
		for (int i=3; i<16; i++)
			row.addItem(i);
		for (int i=3; i<25; i++)
			column.addItem(i);
		row.setSelectedIndex(7);
		column.setSelectedIndex(7);
		rate = new JTextField("0.2");
		panel.add(rowLabel);
		panel.add(row);
		panel.add(columnLabel);
		panel.add(column);
		panel.add(rateLabel);
		panel.add(rate);
		topPanel.add(info);
		endPanel.add(button);
		frame.setVisible(true);
	}
}
