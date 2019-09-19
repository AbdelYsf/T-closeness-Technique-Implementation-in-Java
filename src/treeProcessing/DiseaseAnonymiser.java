package treeProcessing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dao.DaoOperation;
import model.Bucket;
import model.Node;

import utilities.Parameter;

public class DiseaseAnonymiser {
	
	

	
	// Number Of record Changed
	
	
	public static double getNRC() {
		
		System.out.println(" Parameter.Nbchaged : "+Parameter.Nbchaged);
		System.out.println(" DaoOperation.getNumber(Parameter.tableName) : "+DaoOperation.getNumber(Parameter.tableName));

		return  Parameter.Nbchaged/(float)DaoOperation.getNumber(Parameter.tableName);
		
		
		
	}
	
	
	
	
	
	// -------------

	// la methode parent qui  fait les appels � tous les m�thodes NCP
	public static  double getNCP (String d1,String d2) {
		
		Node tree= hierarchy.getTree();

		Node parent =getParent(new StringBuffer(d1).reverse().toString(),new StringBuffer(d2).reverse().toString(),tree);
		
		return   getNFeuille(parent)/(float)getNFeuille(tree);
		
	}
	
	// return le node parent commun de deux disase
	public static  Node  getParent (String d1 ,String d2,Node child) {
				
		Node n1 =getNodeFromDiseasePath(child, d1);
		
		Node n2 =getNodeFromDiseasePath(child, d2);
		
		Node parent =null;
		
		while (n1.getParent()!=null && n2.getParent() !=null) {
		
			if(n1.getParent().getId().equals(n2.getParent().getId())) {
			
			return  parent=n1.getParent();
			
			}else {
				
				n1=n1.getParent();
				n2=n2.getParent();
			}
			
		
		}
		
		
		return parent;
		
		
		}
	
	// return nombre de feuilles d'un noeud
	public  static  int getNFeuille(Node child) {
		
	int num = 0;
	
	if(child.getChildren().isEmpty()) {
		return 1;
	}else {
	
		for(Node n : child.getChildren()) {
			 num +=getNFeuille(n);
		}
	}
	
	return num;}
	
	// return  le noeud  correspondant � un code disease passe 	
	private  static Node getNodeFromDiseasePath(Node child, String disease) {
		
		String wieght [] =new StringBuffer(disease).reverse().toString().split("");

		int  i=1;
		
			while(i<wieght.length  ) {
				
			      etq: for(Node n : child.getChildren()) {
			    	  if(n.getPathWieght() == Integer.parseInt(wieght[i])) {
			    		  child=n;
			    		  break etq;
			    	  }
			      }
			
			i++;
			
			
			}
			
			return child;
							
			
		}

	//  -----------------------
		
	
	public  static HashMap<Bucket,Double> getMoyenneEachBucket(ArrayList<Bucket> bucket_avant) {
		
		double  value  ;
		
		HashMap<Bucket, Double> statistiques = new HashMap<Bucket, Double>();
		
		for(Bucket  b   : bucket_avant) {
			
			value =0;
			
			System.out.println("-----------------------------------------");

	
			for(int i=0;i<b.getRecords().size();i++) {

				for(int j=i+1;j<b.getRecords().size();j++) {
				
					
					System.out.println("("+b.getRecords().get(i).get("anonymizedCode") + ","+ b.getRecords().get(j).get("anonymizedCode")+")"
							+ "    Result --> "+getNCP(b.getRecords().get(i).get("anonymizedCode"),b.getRecords().get(j).get("anonymizedCode")));
					
				value+=getNCP(b.getRecords().get(i).get("anonymizedCode"),b.getRecords().get(j).get("anonymizedCode"));
				}
				
				System.out.println("");
			
			
		}
			
			

			statistiques.put(b,value/(float)b.getRecords().size());

		
		}
			

		return statistiques;
	}
	public static  double getTotalMoyenneNCP(Collection <Double> list) {
		
		double somme=0;
		for(Double b : list) {
			somme+=b;
		}
		return somme/(float)list.size();
			
		}
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void anonimizeDisease(List<Bucket> bucketList){
		Node tree= hierarchy.getTree();
		
		String ananymcode="";
		
			for(Bucket b :bucketList) {
			
			for(Map<String, String> ele:b.getRecords()) {
				ananymcode =generateDiseaseCode(ele.get("diseaseid"), tree);
				ele.put("anonymizedCode",ananymcode );
				
			}
		}
	}

	/**
	 * @param code: disease code 
	 * @param tree 
	 * @return
	 */
	//retourne le path Anonyme Correspondant a un code de disease
	private static String generateDiseaseCode(String code,Node tree) {
		
		Node no = traverse(tree, code);
		String s = "";
		Node n = no;
		
		System.out.println("node:" +no +" : "+code);
		while (n.getParent() != null) {
			s += n.getPathWieght();
			n = n.getParent();
		}
		return new StringBuffer(s+"1").reverse().toString();
	}
	
	// recherche un noeud dans l'arbre 
		private  static Node traverse(Node child, String x) {
			if (child.getId()==x) {
				return child;
			} else {
				for (Node n : child.getChildren()) {

					Node res = traverse(n, x);
					if (res != null) {
						return res;
					}
				}
			}
			return null;

		}
	
	
	

}
