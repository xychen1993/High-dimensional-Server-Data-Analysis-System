package com.renren.monitor.manager;

import java.util.List;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.renren.monitor.dao.SourceDataDAO;
import com.renren.monitor.model.*;
import com.renren.monitor.utils.JsonHelper;

@Service
public class SourceDataManager{

	@Autowired
	SourceDataDAO dao;
	
	private static Log logger = LogFactory.getLog(SourceDataManager.class);
	
	public List<StatsData> getSourceDataByTime(long startTime,long stopTime){
		try{					
			 List<StatsData> list=dao.getSourceData(startTime,stopTime);
			 return list;
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		return null;
	
	}
	
	public Alert getAlert(String tag){
		try{					
			 return dao.getAlert(tag);
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		return null;
	
	}
	
	public DimTag getDimTag(String serviceId, String endPoint,String tag){
		try{
							
				return dao.getDimTag(serviceId, endPoint, tag);
				
			}
			catch(Exception e){
				logger.error("get clouddeveloperinfo error!");
				logger.error(e.getMessage() != null ? e.getMessage() : e);
			}
		return null;
	}
	
	public int setDimTag(String serviceId, String endPoint,String tag){
		try{
				return dao.setDimTag(serviceId, endPoint, tag);
			}
			catch(Exception e){
				logger.error("get clouddeveloperinfo error!");
				logger.error(e.getMessage() != null ? e.getMessage() : e);
			}
		return -1;
	}
	
	public int setAlert(String serviceId,String tag){
		try{
				return dao.setAlert(serviceId, tag);
			}
			catch(Exception e){
				logger.error("get clouddeveloperinfo error!");
				logger.error(e.getMessage() != null ? e.getMessage() : e);
			}
		return -1;
	}
	
	public DimTime getDimTime(String startTime){
		try{
							
				return dao.getDimTime(startTime);
				
			}
			catch(Exception e){
				logger.error("get clouddeveloperinfo error!");
				logger.error(e.getMessage() != null ? e.getMessage() : e);
			}
		return null;
	}
	
	public int getDimTime(int year,int month,int day,int hour, int minute){
		try{
				return dao.getDimTime(year,month,day,hour,minute);
				
			}
			catch(Exception e){
				logger.error("get clouddeveloperinfo error!");
				logger.error(e.getMessage() != null ? e.getMessage() : e);
			}
		return -1;
	}
	
	public int setDimTime(String startTime){
		try{
			
			String[] timeDetail = startTime.split(" |-|:");
				return dao.setDimTime(Integer.parseInt(timeDetail[0]),Integer.parseInt(timeDetail[1]),Integer.parseInt(timeDetail[2]),Integer.parseInt(timeDetail[3]),Integer.parseInt(timeDetail[4]),startTime);
			}
			catch(Exception e){
				logger.error("get clouddeveloperinfo error!");
				logger.error(e.getMessage() != null ? e.getMessage() : e);
			}
		return -1;
	}
	
	
	public int updateDataFact(float avg,int count,int tag_sk,int time_sk,float avgMultiWarningValue,float alertValue){
		try{
				return dao.updateDataFact(avg, count,tag_sk,time_sk,avgMultiWarningValue,alertValue);
			}
			catch(Exception e){
				logger.error("get clouddeveloperinfo error!");
				logger.error(e.getMessage() != null ? e.getMessage() : e);
			}
		return -1;
	}
}