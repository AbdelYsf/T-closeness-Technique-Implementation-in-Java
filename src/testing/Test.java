package testing;

import gui.DBnameEntry;


public class Test {

	public static void main(String[] args) {
	
			try {
				//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*				DaoOperation opt= new DaoOperation("bd");
*///				for(String s:opt.getAllTables()) {
//					System.out.println(s);
//				}
				new DBnameEntry();
			
				//new MainGui("tpjava");
//				Map<String,String> mp=DaoOperation.getAllFields("users");
//				for(Entry entry:mp.entrySet()) {
//					System.out.println(entry.getKey() +" ; "+ entry.getValue());
//				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
	}
}


//for(Bucket b :l) {
//	
//	System.out.println("_______________________");
//	for(Map<String, String> ele:b.getRecords()) {
//		System.out.println("::::::::::::::::");
//		for(Entry<String, String> ee:ele.entrySet()) {
//			System.out.println(ee.getKey()+"   :"+ee.getValue());
//		}
//	}
//}
//for(Bucket b:l) {
//	System.out.println(b);
//}
////Rec
//RecordSwapper.sortDiseaseCode(l);
//RecordSwapper.bucketsClassifier(l);
//RecordSwapper.bucketsShuffler(l, "max");
//s
//System.out.println("#######################");
//for(Bucket b :l) {
//	
//	System.out.println("_______________________");
//	for(Map<String, String> ele:b.getRecords()) {
//		System.out.println("::::::::::::::::");
//		for(Entry<String, String> eeee:ele.entrySet()) {
//			System.out.println(eeee.getKey()+"   :"+eeee.getValue());
//		}
//	}
//}