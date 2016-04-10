package com.lecture.finalproject.model;

public class ModelCount implements Comparable<ModelCount>{
	private int index;
	private int count;

	public ModelCount() {
		super();
	}
	
	
	public ModelCount(int index, int count) {
		super();
		this.index = index;
		this.count = count;
	}


	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

	@Override
	public String toString() {
		return "ModelCount [index=" + index + ", count=" + count + "]";
	}
	
	@Override
	public int compareTo(ModelCount o) {
		// TODO Auto-generated method stub
	
		if(this.count > o.getCount())
			return 1;
		else if(this.count < o.getCount())
			return -1;
		else
			return 0;
	}
	
}
