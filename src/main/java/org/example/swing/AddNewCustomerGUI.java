package org.example.swing;

import javax.swing.*;
import java.awt.*;

public class AddNewCustomerGUI extends JFrame {
	private JPanel contentPanel;
	private JPanel mainPanel;
	private JPanel buttonsPanel;

	private JButton bt1;

	private JLabel lb1;

	private JTextField tf1;

	private BorderLayout borderLayout;

	public AddNewCustomerGUI() {
		contentPanel = (JPanel) this.getContentPane();

		mainPanel = new JPanel();
		buttonsPanel = new JPanel();

		bt1 = new JButton("Add");

		buttonsPanel.add(bt1);

		lb1 = new JLabel("Customer's Name");

		tf1 = new JTextField(20);

		mainPanel.add(lb1);
		mainPanel.add(tf1);

		borderLayout = new BorderLayout();
		contentPanel.setLayout(borderLayout);

		contentPanel.add(mainPanel, BorderLayout.CENTER);
		contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

}
