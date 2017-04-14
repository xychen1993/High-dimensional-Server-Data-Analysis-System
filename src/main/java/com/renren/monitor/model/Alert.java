package com.renren.monitor.model;

public class Alert {
	private int id;
	private String tag;
	private String serviceId;
	private float alert_vaule;
	private float orange_alert;
	private float red_alert;
	
	public int getId(){
		return this.id;
	}public String getTag(){
		return this.tag;
	}public float getAlertVaule(){
		return this.alert_vaule;
	}public float getOrangeAlert(){
		return this.orange_alert;
	}public float getRedAlert(){
		return this.red_alert;
	}public String getServiceId(){
		return this.serviceId;
	}
	
	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}
	
	public void setId(int id){
		this.id = id;
	}public void setTag(String tag){
		this.tag = tag;
	}public void setAlertValue(float alert_vaule){
		this.alert_vaule = alert_vaule;
	}
	public void setOrangeAlert(float orange_alert){
		this.orange_alert = orange_alert;
	}
	public void setRedAlert(float red_alert){
		this.red_alert = red_alert;
	}

}
