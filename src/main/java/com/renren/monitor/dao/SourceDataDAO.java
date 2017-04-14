package com.renren.monitor.dao;


import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.renren.monitor.model.*;
 
@DAO
public interface SourceDataDAO{
	
	  @SQL("select * from logmonitor_statsdata where starttime >= :startTime and stoptime <:stopTime ")
	   public List<StatsData> getSourceData(@SQLParam("startTime") long startTime, @SQLParam("stopTime") long stopTime);
	  
	  @SQL("select * from alert where tag=:tag ")
	   public Alert getAlert(@SQLParam("tag") String tag);
	  
	  @SQL("select id from dim_tag where serviceId = :serviceId and endPoint = :endPoint and tag =:tag")
	   public DimTag getDimTag(@SQLParam("serviceId") String serviceId, @SQLParam("endPoint") String endPoint, @SQLParam("tag") String tag);
	 
	  @SQL("select id from dim_time where startTime=:startTime")
	   public DimTime getDimTime(@SQLParam("startTime") String startTime);
	  
	  @SQL("insert into dim_tag values(null,:serviceId,:endPoint,:tag)")
	   public int setDimTag(@SQLParam("serviceId") String serviceId, @SQLParam("endPoint") String endPoint, @SQLParam("tag") String tag);
	
	  @SQL("insert into alert values(null,:serviceId,:tag,1000,1.5,1.2)")
	   public int setAlert(@SQLParam("serviceId") String serviceId, @SQLParam("tag") String tag);
	
	  @SQL("insert into dim_time values(null,:year,:month,:day,:hour,:minute,:startTime)")
	   public int setDimTime(@SQLParam("year") int year,@SQLParam("month") int month,@SQLParam("day") int day,@SQLParam("hour") int hour,@SQLParam("minute") int minute,@SQLParam("startTime") String startTime);
	
	  @SQL("select count(*) from dim_time #if(:year > -1) { where year=:year } #if(:month > -1) { and month=:month } #if(:day > -1) { and day=:day } #if(:hour > -1) { and hour=:hour } #if(:minute > -1) { and minute=:minute }")
	   public int getDimTime(@SQLParam("year") int year,@SQLParam("month") int month,@SQLParam("day") int day,@SQLParam("hour") int hour,@SQLParam("minute") int minute);
	
	  @SQL("insert into data_fact values (null,:tag_sk,:time_sk,:avg,:count,:avgMultiWarningValue,:alertValue)")
	   public int updateDataFact(@SQLParam("avg") float avg, @SQLParam("count") int count,@SQLParam("tag_sk") int tag_sk, @SQLParam("time_sk") int time_sk,@SQLParam("avgMultiWarningValue") float avgMultiWarningValue,@SQLParam("alertValue") float alertValue);
	  
	 
	  
	  
}
