package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sound.midi.Soundbank;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DaoOperation;
import model.Bucket;
import treeProcessing.DiseaseAnonymiser;
import treeProcessing.RecordSwapper;
import treeProcessing.hierarchy;
import utilities.MyChart;
import utilities.Parameter;

public class test extends JFrame{
	
	
	private JTable table;
	private JTable tableAnony;
	private DefaultTableModel model ;
	private DefaultTableModel modelAnonymized ;
	
	
	
	
	
	
	
	
	
	
	private Panel paneEst=new Panel(new FlowLayout());
	private JButton btnok= new JButton("ok");
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private  JPanel  src = new JPanel(new BorderLayout());
	

	
	private Panel panCenter= new Panel(new GridLayout(2,1));

	
	
	
	
	
	
	
	
	
	
	public static double Total_afterTC;
	public static double Total_beforeTC;
	public  static double  NumberOfRecordChanged;
	
	

	private JButton ncp= new JButton("apply NCP & NRC");

	private JComboBox<String> comboOPT = new JComboBox<>();

	private Object[] columns;
	
	
	
	
	
	public static void main(String args[]) {
		new test();
	}
	
	
	
	public test()  {
		
	


		comboOPT.setModel(new DefaultComboBoxModel<>(new String[] {"max to max","min to min","max to min","min to max"}));
		
		
		

		// filling the original table
//		columns = Parameter.columns;
		
		Object rows[][] =new Object[1][6];
		rows[0][0]="test";
		rows[0][1]="test";
		rows[0][2]="test";
		rows[0][3]="test";
		rows[0][4]="test";
		rows[0][5]="test";
		
		
		Object row[] =new Object[6];
		row[0]="test";
		row[1]="test";
		row[2]="test";
		row[3]="test";
		row[4]="test";
		row[5]="test";
		table= new JTable(new DefaultTableModel(rows,row));
		JScrollPane jScrollPane =new JScrollPane( table);
		
		
		table.setBackground(new Color(245, 222, 179));


		comboOPT.setLightWeightPopupEnabled(false);
		
//		table.setBackground(new Color(245, 222, 179));
		//tableAnony.setVisible(true);
		modelAnonymized = new DefaultTableModel();
		tableAnony= new JTable(modelAnonymized);
		JScrollPane jScrollPane2 =new JScrollPane( tableAnony);
		
		
		
		

		
		
		
		
		
		

		
			
		
//		
//		 
//		 
//		 
//		pan1.add(jScrollPane); 
//		pan1.add(jScrollPane2); 
//
//		
		panCenter.add(jScrollPane);
		panCenter.add(jScrollPane2);

//		
//		
		paneEst.add(comboOPT);
		paneEst.add(btnok);
		paneEst.add(ncp);

//		
		 
		 
		 
	   src.add(paneEst,BorderLayout.NORTH);
	   src.add(panCenter,BorderLayout.CENTER);
	   
	   
	    this.add(src);
	   
		ncp.setEnabled(false);
        setTitle("t-closeness technique");
        setLocationRelativeTo(null);
		setVisible(true);
		this.setSize(500,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		
		
		
		
		//btn listener
		 btnok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
			
		 
		 });
				
				

				
				
				
				

			ncp.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					
					
					
					
				}
			});
			
	}

	
}
