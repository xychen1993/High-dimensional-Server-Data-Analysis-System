package com.renren.monitor.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import com.renren.monitor.model.*;

@DAO
public interface ShowAlertDAO {
	
	 
	 @SQL("select count(*) from logmonitor_statsdata a, alert b WHERE a.avg > b.alert_value*b.red_alert AND a.tag=b.tag and a.serviceid=b.serviceId ")
	  public int getRedAlertNum();
	 
	 @SQL("select count(*) from logmonitor_statsdata a, alert b WHERE a.avg > b.alert_value*b.orange_alert AND  a.avg< b.alert_value*b.red_alert AND a.tag=b.tag and a.serviceid=b.serviceId")
	  public int getOrangeAlertNum();
	 
	 @SQL("SELECT a.* FROM logmonitor_statsdata a, alert b WHERE a.avg > b.alert_value*b.orange_alert AND  a.avg< b.alert_value*b.red_alert AND a.tag=b.tag and a.serviceid=b.serviceId limit :start,15 ")
	  public List<StatsData> getOrangeAlert(@SQLParam("start") int start);
	 
	 @SQL("SELECT a.* FROM logmonitor_statsdata a, alert b WHERE a.avg > b.alert_value*b.red_alert AND a.tag=b.tag and a.serviceid=b.serviceId limit :start,15")
	  public List<StatsData> getRedAlert(@SQLParam("start") int start);
	 
	 @SQL("select distinct serviceId from dim_tag ")
	  public List<DimTag> getDistinctServiceId();
	 
	 @SQL("select distinct endPoint from dim_tag where serviceId=:serviceId")
	  public List<DimTag> getDistinctEndPoint(@SQLParam("serviceId") String serviceId);

	 @SQL("select distinct tag from dim_tag where serviceId=:serviceId and endPoint=:endPoint")
	  public List<DimTag> getDistinctTag(@SQLParam("serviceId") String serviceId,@SQLParam("endPoint") String endPoint);
	 
	 @SQL("select * from alert where serviceId=:serviceId")
	  public List<Alert> getDistinctTagWithoutEndPoint(@SQLParam("serviceId") String serviceId);
	 
	 @SQL("select * from logmonitor_statsdata where serviceId=:serviceId and tag=:tag limit 1")
	  public StatsData getTagAlertValue(@SQLParam("serviceId") String serviceId,@SQLParam("tag") String tag);
	  
	 @SQL("select distinct year from dim_time ")
	  public List<DimTime> getDistinctYear();
	 
	 @SQL("select distinct month from dim_time where year=:year")
	  public List<DimTime> getDistinctMonth(@SQLParam("year") String year);
	 
	 @SQL("select distinct day from dim_time where year=:year and month=:month")
	  public List<DimTime> getDistinctDay(@SQLParam("year") String year,@SQLParam("month") String month);
	 
	 @SQL("select distinct hour from dim_time where year=:year and month=:month and day=:day ")
	  public List<DimTime> getDistinctHour(@SQLParam("year") String year,@SQLParam("month") String month,@SQLParam("day") String day);
	 
	 @SQL("select distinct minute from dim_time where year=:year and month=:month and day=:day and hour=:hour")
	  public List<DimTime> getDistinctMinute(@SQLParam("year") String year,@SQLParam("month") String month,@SQLParam("day") String day,@SQLParam("hour") String hour);

	 @SQL("select startTime from dim_time order by startTime desc limit 1 ")
	  public DimTime getLatestDimTimeDate();
	 
	 @SQL("select startTime from dim_time order by startTime limit 1  ")
	  public DimTime getOldestDimTimeDate();
	 
	 @SQL("select starttime from logmonitor_statsdata order by startTime desc limit 1 ")
	  public StatsData getLatestSourceDataDate();
	 
	 @SQL("update alert set alert_value=:alert_value,red_alert=:red_alert,orange_alert=:orange_alert where serviceId=:serviceId and tag=:tag")
	  public int setLatestSourceDataAlert(@SQLParam("alert_value") float alert_value,@SQLParam("orange_alert") float orange_alert,@SQLParam("red_alert") float red_alert,@SQLParam("serviceId") String serviceId,@SQLParam("tag") String tag);

	 @SQL("select id from dim_tag where serviceId=:serviceId and tag=:tag ")
	  public List<DimTag> getDimTagId(@SQLParam("serviceId") String serviceId,@SQLParam("tag") String tag);
	 
	 @SQL("update data_fact set avgMultiWarningValue=:avgMultiWarningValue,alertValue=:alertValue where id=:id")
	  public int setDataFactAlert(@SQLParam("avgMultiWarningValue") float avgMultiWarningValue,@SQLParam("alertValue") float alertValue,@SQLParam("id") int id);

	 @SQL("select id,avg from data_fact where tag_sk=:tag_sk")
	  public List<DataFact> getDataFactAvg(@SQLParam("tag_sk") int tag_sk);
}
