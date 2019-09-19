package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class MainGui extends JFrame{
	
	
	private JTable table;
	private JTable tableAnony;
	private DefaultTableModel model ;
	private DefaultTableModel modelAnonymized ;

	private Panel panCenter= new Panel(new GridLayout(2, 1));
	private Panel panBtn=new Panel(new GridLayout(3,1));
	private Panel paneEst=new Panel();
	private JButton btnok= new JButton("ok");
	
	
	
	public static double Total_afterTC;
	public static double Total_beforeTC;
	public  static double  NumberOfRecordChanged;
	
	

	private JButton ncp= new JButton("apply NCP & NRC");

	private JComboBox<String> comboOPT= new JComboBox<String>(new String[] {"max to max","min to min","max to min","min to max"});

	private Object[] columns;
	
	public MainGui(DaoOperation dboparation) throws HeadlessException, ClassNotFoundException, SQLException {
		
		
		
		
		panBtn.add(comboOPT ); panBtn.add(btnok);
		panBtn.add(ncp);
	
		// filling the original table
		columns = Parameter.columns;
		model = new DefaultTableModel(DaoOperation.getResultSet(Parameter.tableName),columns);
		table= new JTable(model);
		JScrollPane jScrollPane =new JScrollPane( table);
		table.setBackground(new Color(245, 222, 179));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		
		
		
		ncp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				new MyChart(dboparation);
				Parameter.Nbchaged=0;
				dispose();
				
				
				
			}
		});
		
		
		//btn listener
		 btnok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboOPT.getSelectedItem());
				ncp.setEnabled(true);
				ArrayList<Bucket> l= DaoOperation.getAllBuckets();
				
				hierarchy.setDiseaseId(l);
				DiseaseAnonymiser.anonimizeDisease(l);
				RecordSwapper.sortDiseaseCode(l);
				
				
				

				for(Bucket b :l) {
					
					System.out.println("_______________________");
					for(Map<String, String> ele:b.getRecords()) {
						System.out.println("::::::::::::::::");
						for(Entry<String, String> ee:ele.entrySet()) {
							System.out.println(ee.getKey()+"   :"+ee.getValue());
						}
					}
				}
				
				
				
				
				
				RecordSwapper.bucketsClassifier(l);
				

				
				
				HashMap<Bucket, Double> bukects_avant=DiseaseAnonymiser.getMoyenneEachBucket(l);
				
				for(Entry<Bucket,Double> b :  bukects_avant.entrySet()) {
					System.out.println("#######################");
					System.out.println(b+" : "+b.getValue());
					
				}
				
				System.out.println("Total Moyenne NCP AVANT :"+DiseaseAnonymiser.getTotalMoyenneNCP(bukects_avant.values())) ;
				
				Total_beforeTC=DiseaseAnonymiser.getTotalMoyenneNCP(bukects_avant.values());
				
				if(comboOPT.getSelectedItem()=="max to max") {
					RecordSwapper.bucketsShuffler(l, "max");
				}else if(comboOPT.getSelectedItem()=="min to min") {
					RecordSwapper.bucketsShuffler(l, "min");
				}else if(comboOPT.getSelectedItem()=="max to min") {
					RecordSwapper.bucketsShuffler(l, "maxtomin");
				}else if(comboOPT.getSelectedItem()=="min to max") {
					RecordSwapper.bucketsShuffler(l, "mintomax");
				}			
				
				
				
				
				
				
				
				
				

				System.out.println("##############  APRES   #########");

				



				for(Bucket b :l) {
					
					System.out.println("_______________________");
					for(Map<String, String> ele:b.getRecords()) {
						System.out.println("::::::::::::::::");
						for(Entry<String, String> ee:ele.entrySet()) {
							System.out.println(ee.getKey()+"   :"+ee.getValue());
						}
					}
				}
				
				
				
				
				System.out.println(".....................");
				
				
				HashMap<Bucket, Double> bukects_apres=DiseaseAnonymiser.getMoyenneEachBucket(l);
				
				for(Entry<Bucket,Double> b :  bukects_apres.entrySet()) {
					System.out.println("#######################");
					System.out.println(b+" : "+b.getValue());
					
				}
				
				System.out.println("Total Moyenne NCP APRES :"+DiseaseAnonymiser.getTotalMoyenneNCP(bukects_apres.values())) ;
					
				Total_afterTC=DiseaseAnonymiser.getTotalMoyenneNCP(bukects_apres.values());
				
				
				
				
				NumberOfRecordChanged=DiseaseAnonymiser.getNRC();
				
				DaoOperation.insertResult(l);
				//show the tab
				tableAnony.setModel(new DefaultTableModel(DaoOperation.getResultSet(Parameter.resultTable),columns));
				
				
				
				
				

				
				
				
				
				
				
//			
//				for(Bucket b :l) {
//					
//					System.out.println("_______________________");
//					for(Map<String, String> ele:b.getRecords()) {
//						System.out.println("::::::::::::::::");
//						for(Entry<String, String> ee:ele.entrySet()) {
//							System.out.println(ee.getKey()+"   :"+ee.getValue());
//						}
//					}
//				}
//				for(Bucket b:l) {
//					System.out.println(b);
//				}
////				//Rec
////				RecordSwapper.sortDiseaseCode(l);
////				RecordSwapper.bucketsClassifier(l);
////				RecordSwapper.bucketsShuffler(l, "min");
////				
////				System.out.println("#######################");
////				for(Bucket b :l) {
////					
////					System.out.println("_______________________");
////					for(Map<String, String> ele:b.getRecords()) {
////						System.out.println("::::::::::::::::");
////						for(Entry<String, String> eeee:ele.entrySet()) {
////							System.out.println(eeee.getKey()+"   :"+eeee.getValue());
////						}
////					}
////				}
//				
//			}
				
			}
		}); 
		
		
		
		 
		 
		 comboOPT.setLightWeightPopupEnabled(false);
		comboOPT.setPreferredSize(new Dimension(90, 30));
		
		table.setBackground(new Color(245, 222, 179));
		//tableAnony.setVisible(true);
		modelAnonymized = new DefaultTableModel();
		tableAnony= new JTable(modelAnonymized);
		JScrollPane jScrollPane2 =new JScrollPane( tableAnony);
		tableAnony.setPreferredScrollableViewportSize(table.getPreferredSize());
		tableAnony.setFillsViewportHeight(true);
		
		
		
		
		paneEst.add(panBtn);
		this.add(paneEst,BorderLayout.EAST);
		
		
		
		
		panCenter.add(jScrollPane);
		panCenter.add(jScrollPane2);

		
		
		
		
		
		
		
		this.add(panCenter);
	
		
		

		ncp.setEnabled(false);
        setTitle("t-closeness technique");
        setLocationRelativeTo(null);
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setSize(600,500);

		
		
	}
	
	
}
