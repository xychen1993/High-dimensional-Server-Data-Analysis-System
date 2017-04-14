package com.renren.monitor.model;

public class DataFact {

	private int id;
	private int time_sk;
	private int tag_sk;
	private float avg;
	private int count;
	private float avgMultiWarningValue;
	private float alertValue;

	public int getId(){
		return this.id;
	}
	public float getAvg(){
		return this.avg;
	}public int getCount(){
		return this.count;
	}public int getTimeSk(){
		return this.time_sk;
	}public int getTagSk(){
		return this.tag_sk;
	}public float getAvgmultiWarningValue(){
		return this.avgMultiWarningValue;
	}public float getAlertValue(){
		return this.alertValue;
	}

	
	public void setId(int id){
		this.id = id;
	}
	public void setAvg(float avg){
		this.avg = avg;
	}public void setCount(int count){
		this.count = count;
	}public void setTimeSk(int time_sk){
		this.time_sk = time_sk;
	}public void setTagSk(int tag_sk){
		this.tag_sk = tag_sk;
	}public void setAvgmultiWarningValue(float avgMultiWarningValue){
		this.avgMultiWarningValue=avgMultiWarningValue;
	}public void setAlertValue(float alertValue){
		this.alertValue=alertValue;
	}

}
