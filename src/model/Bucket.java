package model;

import java.util.ArrayList;
import java.util.Map;

public class Bucket {
	
	private String id;
	private boolean shouldSwapp;
	private ArrayList<Map<String,String>> records = new ArrayList<>();
	
	@Override
	public String toString() {
		return "Bucket [id=" + id + ", shouldSwapp=" + shouldSwapp + ", elements=" + records.size() + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isShouldSwapp() {
		return shouldSwapp;
	}
	public void setShouldSwapp(boolean shouldSwapp) {
		this.shouldSwapp = shouldSwapp;
	}
	public ArrayList<Map<String, String>> getRecords() {
		return records;
	}
	public void setRecords(ArrayList<Map<String, String>> records) {
		this.records = records;
	}
	
	

}
