package com.renren.monitor.manager;

import java.util.List;

import net.paoding.rose.jade.annotation.SQLParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.renren.monitor.dao.ShowAlertDAO;
import com.renren.monitor.model.*;
import com.renren.monitor.utils.JsonHelper;

@Service
public class ShowAlertManager {

	@Autowired
	ShowAlertDAO dao;
	
	private static Log logger = LogFactory.getLog(ShowAlertManager.class);
	
	public int getRedAlertNum(){
		
		try{					
			return dao.getRedAlertNum();
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return -1;
	}
	
	public int getOrangeAlertNum(){
		
		try{					
			return dao.getOrangeAlertNum();
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return -1;
	}
	
	public List<StatsData> getOrangeAlert(int page){
		
		try{					
			return dao.getOrangeAlert(page);
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return null;
	}
	
	public List<StatsData> getRedAlert(int start){
		
		try{					
			return dao.getRedAlert(start);
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return null;
	}
	
	public List<DimTag> getDistinctServiceId(){
		
		try{					
			return dao.getDistinctServiceId();
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return null;
	}
	
	public List<DimTag> getDistinctEndPoint(String serviceId){
		
		try{					
			return dao.getDistinctEndPoint(serviceId);
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return null;
	}

	public StatsData getTagAlertValue(String serviceId,String tag){
		try{					
			return dao.getTagAlertValue(serviceId,tag);
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return null;
	}
	
	public List<DimTag> getDistinctTag(String serviceId,String endPoint){
		
		try{					
			return dao.getDistinctTag(serviceId, endPoint);
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return null;
	}
	
	  public List<Alert> getDistinctTagWithoutEndPoint(@SQLParam("serviceId") String serviceId){
		  
		  try{					
				return dao.getDistinctTagWithoutEndPoint(serviceId);
			}
			catch(Exception e){
				logger.error("get clouddeveloperinfo error!");
				logger.error(e.getMessage() != null ? e.getMessage() : e);
			}
			
			return null;
	  }

	
	public DimTime getLatestDimTimeDate(){
		
		try{					
			return dao.getLatestDimTimeDate();
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return null;
	}
	
	public DimTime getOldestDimTimeDate(){
		
		try{					
			return dao.getOldestDimTimeDate();
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return null;
	}
	
	public StatsData getLatestSourceDataDate(){
		
		try{					
			return dao.getLatestSourceDataDate();
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return null;
	}
	
	public int setLatestSourceDataAlert(float alert_value,float orange_alert,float red_alert,String serviceId,String tag){
		try{
			return dao.setLatestSourceDataAlert(alert_value,orange_alert,red_alert,serviceId,tag);
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return -1;
	}
	
	public int setDataFactAlert(float avgMultiWarningValue,float alertValue,int id){
		try{					
			return dao.setDataFactAlert(avgMultiWarningValue,alertValue,id);
		}
		catch(Exception e){
			logger.error("get clouddeveloperinfo error!");
			logger.error(e.getMessage() != null ? e.getMessage() : e);
		}
		
		return -1;
	}
	
	  public List<DimTag> getDimTagId(String serviceId,String tag){
		  try{					
				return dao.getDimTagId(serviceId, tag);
			}
			catch(Exception e){
				logger.error("get clouddeveloperinfo error!");
				logger.error(e.getMessage() != null ? e.getMessage() : e);
			}
			
			return null;
	  }
	  
	  public List<DataFact> getDataFactAvg(int tag_sk){
		  try{					
				return dao.getDataFactAvg(tag_sk);
			}
			catch(Exception e){
				logger.error("get clouddeveloperinfo error!");
				logger.error(e.getMessage() != null ? e.getMessage() : e);
			}
			
			return null;
		  
	  }




}
