package com.renren.monitor.model;

public class StatsData {
	
	private int id;
	private String serviceId;
	private String endPoint;
	private long startTime;
	private long stopTime;
	private String tag;
	private float avg;
	private String std_dev;
	private String min;
	private String max;
	private String modifyTime;
	private int count;



	public int getId(){
		return this.id;
	}
	public String getServiceId(){
		return this.serviceId;
	}
	public String getEndPoint(){
		return this.endPoint;
	}
	public long getStartTime(){
		return this.startTime;
	}
	public long getStopTime(){
		return this.stopTime;
	}
	public String getTag(){
		return this.tag;
	}
	public float getAvg(){
		return this.avg;
	}
	public String getStdDev(){
		return this.std_dev;
	}
	public String getMin(){
		return this.min;	
	}
	public String getMax(){
		return this.max;
	}
	public String getModifyTime(){
		return this.modifyTime;
	}

	public int getCount(){
		return this.count;
	}


	public void setCount(int count){
		this.count = count;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}
	public void setEndPoint(String endPoint){
		this.endPoint = endPoint;
	}
	public void setStartTime(long startTime){
		this.startTime = startTime;
	}
	public void setStopTime(long stopTime){
		this.stopTime = stopTime;
	}
	public void setTag(String tag){
		this.tag = tag;
	}
	public void setAvg(float avg){
		this.avg = avg;
	}
	public void setStdDev(String std_dev){
		this.std_dev = std_dev;
	}
	public void setMin(String min){
		this.min = min;
	}
	public void setMax(String max){
		this.max = max;
	}
	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	
}
