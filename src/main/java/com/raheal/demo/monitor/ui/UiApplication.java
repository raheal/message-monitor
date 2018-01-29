package com.raheal.demo.monitor.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UiApplication implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(UiApplication.class);
	
	public static final DefaultTableModel defaultTableModel = new DefaultTableModel();
	
	public void showUi() {
		logger.info("Loading UI");
		JFrame frame = new JFrame("Message Monitor");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(showTable(),  BorderLayout.CENTER);
		frame.setSize(700, 600);
		frame.setVisible(true);
	}
	
	
	public JScrollPane showTable() {
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("Event Name");
		defaultTableModel.addColumn("DML_Type");
		defaultTableModel.addColumn("Commit Date");
		defaultTableModel.addColumn("Payload");
		JTable table = new JTable(defaultTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}


	@Override
	public void run() {
		showUi();
	}
	
}
