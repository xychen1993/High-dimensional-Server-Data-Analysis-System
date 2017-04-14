package com.renren.monitor.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import org.springframework.beans.factory.annotation.Autowired;

import com.renren.monitor.model.StatsData;
import com.renren.monitor.service.ShowAlertService;
import com.renren.monitor.utils.TimeHelper;

@Path("")
public class AlertController {
	@Autowired
    private ShowAlertService ShowAlertService;
	
	@Get("index")
	public String showRedAlert(Invocation inv,@Param("page") String pageStr){
		
		int page;
		if(pageStr == null){
			page=1;
		}
		else{
			page = Integer.parseInt(pageStr);
		}
		
		@SuppressWarnings("unchecked")
		List<StatsData> redAlertList = ShowAlertService.showRedAlert(page);
		int redAlertPage = ShowAlertService.getRedAlertPage();
		
		int i=1;
		for (StatsData StatsData : redAlertList) {
			
			inv.addModel("id"+i, StatsData.getId());
			inv.addModel("serviceId"+i, StatsData.getServiceId());
			inv.addModel("endPoint"+i, StatsData.getEndPoint());
			inv.addModel("startTime"+i, TimeHelper.getDateFromSeconds(""+StatsData.getStartTime()));
			inv.addModel("stopTime"+i, TimeHelper.getDateFromSeconds(""+StatsData.getStopTime()));
			inv.addModel("tag"+i, StatsData.getTag());
			inv.addModel("avg"+i, StatsData.getAvg());
			
			i++;
		}
		inv.addModel("pageNum", redAlertPage);
		inv.addModel("currentPage",page);

		return "index";
	}
	

	
	
	@Get("index-orange")
	public String showOrangeAlert(Invocation inv,@Param("page") String pageStr){
		
		int page=-1;
		if(pageStr == null){
			page=1;
		}
		else{
			page = Integer.parseInt(pageStr);
		}
		
		List<StatsData> orangeAlertList = ShowAlertService.showOrangeAlert(page);
		int orangeAlertPage = ShowAlertService.getOrangeAlertPage();
		
		int i=1;
		for (StatsData StatsData : orangeAlertList) {
			
			inv.addModel("id"+i, StatsData.getId());
			inv.addModel("serviceId"+i, StatsData.getServiceId());
			inv.addModel("endPoint"+i, StatsData.getEndPoint());
			inv.addModel("startTime"+i, TimeHelper.getDateFromSeconds(""+StatsData.getStartTime()));
			inv.addModel("stopTime"+i, TimeHelper.getDateFromSeconds(""+StatsData.getStopTime()));
			inv.addModel("tag"+i, StatsData.getTag());
			inv.addModel("avg"+i, StatsData.getAvg());
			
			i++;
		}
		inv.addModel("pageNum", orangeAlertPage);
		inv.addModel("currentPage",page);

		return "index-orange";
	}
	
	@Get("count")
	public String showCountTime(Invocation inv,@Param("serviceId") String serviceId,@Param("endPoint") String endPoint,@Param("tag") String tag,@Param("year1") String year1,@Param("month1") String month1,@Param("day1") String day1,@Param("hour1") String hour1,@Param("minute1") String minute1) throws ClassNotFoundException, Exception{

		String[] timeShow = new String[5]; 
		if(serviceId==null){
			serviceId="null";
		}if(endPoint==null){
			endPoint="null";
		}if(tag==null){
			tag="null";
		}
		String year,month,day,hour,minute;
		if(year1==null||year1.equals("")){
			year="2015";
			inv.addModel("yearNow",year);

		}else{
			year=""+Integer.parseInt(year1);
			inv.addModel("yearNow",year);

		}
		if(month1==null||month1.equals("")){
				month="null";
				inv.addModel("monthNow","");

		}else{
			month=""+Integer.parseInt(month1);
			inv.addModel("monthNow",month);

		}
		
		if(day1==null||day1.equals("")){
			day="null";
			inv.addModel("dayNow","");

		}else{
			day=""+Integer.parseInt(day1);
			inv.addModel("dayNow",day);

		}
		
		if(hour1==null||hour1.equals("")){
			hour="null";
			inv.addModel("hourNow","");

		}else{
			hour=""+Integer.parseInt(hour1);
			inv.addModel("hourNow",hour);

		}
		if(minute1==null||minute1.equals("")){
			minute="null";
			inv.addModel("minuteNow","");

		}else{
			minute=""+Integer.parseInt(minute1);
			inv.addModel("minuteNow",minute);

		}
		
		String resultStr=null;
		
			//System.out.println(serviceId+","+endPoint+","+tag+","+year1+","+month1+","+day1+","+hour1+","+minute1);
		resultStr = ShowAlertService.getCountShow(serviceId+","+endPoint+","+tag+","+year+","+month+","+day+","+hour+","+minute);
	//	resultStr = ShowAlertService.getCountShow("null,null,null,2015,null,null,null,null");

		String[] result = new String[3];
		String check;
		if(resultStr.equals("no result")){
			result[0]="null";
			result[1]="null";
			result[2]="null";
			
			check="no result";
		}else{
			result = resultStr.split("\\+");
			check="ok";

		}
		
		String DimTagList = ShowAlertService.getDistinctDimTag();
		
		inv.addModel("check",check);
		inv.addModel("title",result[0]);
		inv.addModel("count", result[1]);
		inv.addModel("timeLine", result[2]);
		inv.addModel("DimTagList",DimTagList);
		inv.addModel("serviceIdNow",serviceId);
		inv.addModel("endPointNow",endPoint);
		inv.addModel("tagNow",tag);


		//System.out.println(serviceId+endPoint+tag);
		return "count";
	}
	
	@Get("avg-time")
	public String showAvg(Invocation inv,@Param("serviceId") String serviceId,@Param("endPoint") String endPoint,@Param("tag") String tag,@Param("year1") String year1,@Param("month1") String month1,@Param("day1") String day1,@Param("hour1") String hour1,@Param("minute1") String minute1) throws ClassNotFoundException, Exception{

		String[] timeShow = new String[5]; 
		if(serviceId==null){
			serviceId="null";
		}if(endPoint==null){
			endPoint="null";
		}if(tag==null){
			tag="null";
		}
		String year,month,day,hour,minute;
		if(year1==null||year1.equals("")){
			year="2015";
			inv.addModel("yearNow",year);

		}else{
			year=""+Integer.parseInt(year1);
			inv.addModel("yearNow",year);

		}
		if(month1==null||month1.equals("")){
				month="null";
				inv.addModel("monthNow","");

		}else{
			month=""+Integer.parseInt(month1);
			inv.addModel("monthNow",month);

		}
		
		if(day1==null||day1.equals("")){
			day="null";
			inv.addModel("dayNow","");

		}else{
			day=""+Integer.parseInt(day1);
			inv.addModel("dayNow",day);

		}
		
		if(hour1==null||hour1.equals("")){
			hour="null";
			inv.addModel("hourNow","");

		}else{
			hour=""+Integer.parseInt(hour1);
			inv.addModel("hourNow",hour);

		}
		if(minute1==null||minute1.equals("")){
			minute="null";
			inv.addModel("minuteNow","");

		}else{
			minute=""+Integer.parseInt(minute1);
			inv.addModel("minuteNow",minute);

		}
		String resultStr=null;
		
		//	System.out.println(serviceId+","+endPoint+","+tag+","+year+","+month+","+day+","+hour+","+minute1);
		resultStr = ShowAlertService.getAvgShow(serviceId+","+endPoint+","+tag+","+year+","+month+","+day+","+hour+","+minute);
	//	resultStr = ShowAlertService.getCountShow("null,null,null,2015,null,null,null,null");

		String[] result = new String[3];
		String check;
		if(resultStr.equals("no result")){
			result[0]="null";
			result[1]="null";
			result[2]="null";
			
			check="no result";
		}else{
			result = resultStr.split("\\+");
			check="ok";

		}
		
		String DimTagList = ShowAlertService.getDistinctDimTag();
		
		inv.addModel("check",check);
		inv.addModel("title",result[0]);
		inv.addModel("count", result[1]);
		inv.addModel("timeLine", result[2]);
		inv.addModel("DimTagList",DimTagList);
		inv.addModel("serviceIdNow",serviceId);
		inv.addModel("endPointNow",endPoint);
		inv.addModel("tagNow",tag);
		
		//System.out.println(result[2]+","+result[1]);
		return "avg-time";
	}
	
	@Get("setting")
	public String Setting(Invocation inv,@Param("serviceId") String serviceId,@Param("tag") String tag,@Param("alert_value") float alert_value,@Param("red_alert") float red_alert,@Param("orange_alert") float orange_alert,@Param("etl") String etl) throws ClassNotFoundException, Exception{
		
		String resultStr="not set";

		if(serviceId!=null){
			
			resultStr = ShowAlertService.setSetting(serviceId,tag,alert_value,orange_alert,red_alert);
			//System.out.println(serviceId+","+tag+","+alert_value+","+orange_alert+","+red_alert);
		}
		else{
			serviceId="null";
			if(tag==null){
				tag="null";
			}
		}

		String settingShowStr = ShowAlertService.getSetting();
		String[] settingShow = settingShowStr.split(",");
		
		String DimTagList = ShowAlertService.getDistinctDimTagAlertValue();
		String[] latestDimTimeDate = settingShow[0].split(" ");
		String[] oldestDimTimeDate = settingShow[1].split(" ");
		String[] latestSourceDataDate = settingShow[2].split(" ");

		if(etl!=null&&etl.equals("ok")){
			resultStr="etl ok";
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate=null;
       
        startDate=sdf.parse(latestDimTimeDate[0]+" 00:00:00");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(startDate);
    	rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天
    	Date date=rightNow.getTime();
    	date=rightNow.getTime();
    	String reStr = sdf.format(date);
    	
    	String[] latestDimTimeDate1 = reStr.split(" ");
    	
    	inv.addModel("latestDimTimeDate",latestDimTimeDate[0]);
    	inv.addModel("latestDimTimeDate1",latestDimTimeDate1[0]);
		inv.addModel("oldestDimTimeDate", oldestDimTimeDate[0]);
		inv.addModel("latestSourceDataDate",latestSourceDataDate[0]);
		inv.addModel("DimTagList",DimTagList);
		inv.addModel("result",resultStr);
		inv.addModel("serviceIdNow",serviceId);
		inv.addModel("tagNow",tag);

		
		//System.out.println(settingShow[0]+","+settingShow[1]+","+settingShow[2]+resultStr);
		//System.out.println(DimTagList);
		return "setting";
	}
	
	@Get("test")
	public String test(){
		String temp = TimeHelper.getDateFromSeconds("1437381480000");
		return "@ok:"+temp;
	}

}
