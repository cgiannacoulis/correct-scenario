package org.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowAllCustomersGUI extends JFrame {
	private JPanel contentPanel;
	private JPanel buttonsPanel;

	private JButton bt1;
	private JButton bt2;

	private JList list;
	private DefaultListModel<String> listModel;

	private BorderLayout borderLayout;

	public ShowAllCustomersGUI() {
		contentPanel = (JPanel) this.getContentPane();

		buttonsPanel = new JPanel();

		listModel = new DefaultListModel<>();
		listModel.addElement("John");
		listModel.addElement("Ares");
		listModel.addElement("Nikos");

		list = new JList(listModel);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		borderLayout = new BorderLayout();

		contentPanel.setLayout(borderLayout);

		bt1 = new JButton("Add new customer");
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddNewCustomerGUI addNewCustomerGUI = new AddNewCustomerGUI();
				addNewCustomerGUI.pack();
				addNewCustomerGUI.setVisible(true);
			}
		});

		bt2 = new JButton("Exit");
		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		buttonsPanel.add(bt1);
		this.buttonsPanel.add(bt2);

		contentPanel.add(list, BorderLayout.CENTER);
		contentPanel.add(buttonsPanel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
