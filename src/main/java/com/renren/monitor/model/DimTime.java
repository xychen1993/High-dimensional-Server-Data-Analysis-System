package com.renren.monitor.model;


public class DimTime {
	private int id;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String startTime;

	public int getId(){
		return this.id;
	}public int getYear(){
		return this.year;
	}public int getMonth(){
		return this.month;
	}public int getDay(){
		return this.day;
	}public int getHour(){
		return this.hour;
	}public int getMinute(){
		return this.minute;
	}public String getstartTime(){
		return this.startTime;
	}
	
	
	public void setstartTime(String startTime){
		this.startTime = startTime;
	}public void setId(int id){
		this.id = id;
	}public void setYear(int year){
		this.year = year;
	}public void setMonth(int month){
		this.month = month;
	}public void setDay(int day){
		this.day = day;
	}public void setHour(int hour){
		this.hour = hour;
	}public void setMinute(int minute){
		this.minute = minute;
	}
}
