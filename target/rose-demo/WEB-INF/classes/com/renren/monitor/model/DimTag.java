package com.renren.monitor.model;

public class DimTag {
	
	private int id=-1;
	private String serviceId=null;
	private String endPoint=null;
	private String tag=null;
	
	public int getId(){
		return this.id;
	}
	public String getServiceId(){
		return this.serviceId;
	}
	public String getEndPoint(){
		return this.endPoint;
	}public String getTag(){
		return this.tag;
	}
	
	public void setId(int id){
		this.id = id;
	}
	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}
	public void setEndPoint(String endPoint){
		this.endPoint = endPoint;
	}public void setTag(String tag){
		this.tag = tag;
	}
}
