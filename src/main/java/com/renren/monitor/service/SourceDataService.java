package com.renren.monitor.service;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.renren.monitor.manager.SourceDataManager;
import com.renren.monitor.model.*;
import com.renren.monitor.utils.JsonHelper;
import com.renren.monitor.utils.TimeHelper;

@Service
public class SourceDataService {
	protected static final Log logger = LogFactory.getLog(SourceDataService.class);

	@Autowired
	SourceDataManager SourceDataManager;


	
	public JSONObject ETL(String startETLDate,String endETLDate){

	try{
		

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate=null;
       
        startDate=sdf.parse(startETLDate+" 00:00:00");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(startDate);
    	rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天

        for(int i=0;;i++){
            
        	Date date=rightNow.getTime();
        	long startTime = TimeHelper.getSecondsFromDate(sdf.format(date));
        	rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天
        //rightNow.add(Calendar.MINUTE,-1);//时间减掉一分钟
        	date=rightNow.getTime();
        	String reStr = sdf.format(date);
        	long stopTime = TimeHelper.getSecondsFromDate(reStr);
             
             

         	List<StatsData> list = SourceDataManager.getSourceDataByTime(startTime,stopTime);

            System.out.println(list.size());
            
         	for (StatsData StatsData : list) {
         		
         		String serviceId = StatsData.getServiceId();
         		String endPoint = StatsData.getEndPoint();
         		String tag = StatsData.getTag();
         		float avg = StatsData.getAvg();
         		int count = StatsData.getCount();         		
         		
         		float alertValue=(float) 1000.0;
         		float orangeAlert=(float) 1.2;
         		float redAlert=(float) 1.5;

         		String startTime2 = TimeHelper.getDateFromSeconds(""+StatsData.getStartTime());
         		
         		int tag_sk = 0;
         		int time_sk=0;


         		DimTag DimTag = SourceDataManager.getDimTag(serviceId, endPoint, tag);
         		DimTime DimTime = SourceDataManager.getDimTime(startTime2);
         		
         		if(DimTag==null){			
        		
         			int res = SourceDataManager.setDimTag(serviceId, endPoint, tag);
         			int res2 = SourceDataManager.setAlert(serviceId, tag);
         			tag_sk = SourceDataManager.getDimTag(serviceId, endPoint, tag).getId();
         			
         		}else{         			
         			tag_sk = DimTag.getId();

         		}
         		
         		if(DimTime==null){			
         		
         			int res = SourceDataManager.setDimTime(startTime2);
             		time_sk= SourceDataManager.getDimTime(startTime2).getId();

         			
         		}else{         			
        			time_sk = DimTime.getId();

         		}	
         		
         		//update data_fact
            		int res = SourceDataManager.updateDataFact(avg, count, tag_sk, time_sk,avg*alertValue,alertValue);		
   		
         	}
         	
         	
         	if(reStr.equals(endETLDate+" 00:00:00")){
            	 break;
             }

        }
       
        
        
		

		}
		catch(Exception e){			logger.error("getUserById error! ");
			logger.error(e.getMessage() != null ? e.getMessage():e);
			return null;
		}
		return JsonHelper.resultByJson(0, "保存成功");
		
	}
	

}
