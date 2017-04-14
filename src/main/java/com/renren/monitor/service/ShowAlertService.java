package com.renren.monitor.service;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;

import org.olap4j.CellSet;
import org.olap4j.Cell;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.OlapStatement;
import org.olap4j.metadata.Member;
import org.olap4j.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renren.monitor.manager.SourceDataManager;
import com.renren.monitor.manager.ShowAlertManager;
import com.renren.monitor.model.*;
import com.renren.monitor.utils.TimeHelper;

@Service
public class ShowAlertService {

	@Autowired
	ShowAlertManager ShowAlertManager;
	
	@Autowired
	SourceDataManager SourceDataManager;

	
	public List<StatsData> showRedAlert(int page){
		
		
		List<StatsData> redAlertList = ShowAlertManager.getRedAlert((page-1)*15);

		return redAlertList;
	}
	
	public List<StatsData> showOrangeAlert(int page){
		
		
		List<StatsData> orangeAlertList = ShowAlertManager.getOrangeAlert((page-1)*15);

		return orangeAlertList;
	}
	
	public int getRedAlertPage(){
		
		int redNum = ShowAlertManager.getRedAlertNum();
		int page = redNum/15+1;
		if(redNum%15==0){
			page = redNum/15;
		}

		return page;
	}
	
	public int getOrangeAlertPage(){
		
		int orangeNum = ShowAlertManager.getOrangeAlertNum();
		int page = orangeNum/15+1;
		if(orangeNum%15==0){
			page = orangeNum/15;
		}

		return page;
	}
	
    //mdx查询获得count
    public String getCountShow(String paramStr) throws ClassNotFoundException, Exception{
    	String[] param = paramStr.split(",");
		String title = "";

    	Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
		Connection connection= DriverManager.getConnection("jdbc:mondrian:" + 
				        		"Jdbc=jdbc:mysql://127.0.0.1:3306/visualization; JdbcUser=root; JdbcPassword=chenxinyi;" +
				        		"Catalog=G:/forMyEclipse/monitor/src/main/webapp/WEB-INF/dataCube.xml;"+"JdbcDrivers=com.mysql.jdbc.Driver;");
				    //   	"Catalog=C:/Users/WML/Workspaces/MyEclipse 10/monitor/src/main/webapp/WEB-INF/dataCube.xml;"+"JdbcDrivers=com.mysql.jdbc.Driver;");

		OlapConnection olapConnection = connection.unwrap(OlapConnection.class);

	//	String query=" select {[TIME].[hour].[23].[30],[TIME].[hour].[23].[31]} ON COLUMNS, {[TAG].[serviceId].[cloudim]} ON ROWS from dataCube where([Measures].[count])";
		
		
		
		String query = "select {[TIME].[year]";
		
		int[] time = new int[5];
		for(int i=0;i<5;i++){
			time[i] = -1;
		}

		for(int i=3;i<8;i++){		
			
			if(param[i].equals("null")){
				break;
			}
			query+=".["+param[i]+"]";
			time[i-3] = Integer.parseInt(param[i]);
		}
		
	//	System.out.println(time[0]+","+time[1]+","+time[2]+","+time[3]+","+time[4]);
		int dimtime = SourceDataManager.getDimTime(time[0],time[1],time[2],time[3],time[4]);
		if(dimtime==0){
			
			return "no result";
		}
		
		if(!param[0].equals("null")){
			if(time[4]==-1){
				query+=".Children} ON COLUMNS, {[TAG].[serviceId]";

			}else{
				query+="} ON COLUMNS, {[TAG].[serviceId]";

			}
			
			for(int i=0;i<3;i++){
				
				if(param[i].equals("null")){
					break;
				}
				query+=".["+param[i]+"]";
				title+=param[i]+".";
			}
			
			if(param[0].equals("null")){
				title="all";
				query+=".Members} ON ROWS from dataCube where([Measures].[count])";

			}else{

				query+="} ON ROWS from dataCube where([Measures].[count])";

			}

		}else if(param[0].equals("null")){
			if(time[4]==-1){
				query+=".Children} ON COLUMNS, {[Measures].[count]} ON ROWS from dataCube ";

			}else{
				query+="} ON COLUMNS, {[Measures].[count]} ON ROWS from dataCube ";

			}
		}
		
		
	        //System.out.println(query);

		String timeLine = "[";
		String count = "[";
		CellSet cs = null;
			try {
				OlapStatement statement =olapConnection.createStatement();
				cs = statement.executeOlapQuery(query);
			} catch (OlapException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//System.out.println("cs.getAxes().size():"+cs.getAxes().size());
				
			if(cs.getAxes().size()>1){
				for (Position row : cs.getAxes().get(1)) {
				    
				    for (Position column : cs.getAxes().get(0)) {
				       
				        for (Member member : row.getMembers()) {
				            
				         //   System.out.println("rows:"+member.getUniqueName());

				        }
				        for (Member member : column.getMembers()) {
				           
				            
				            String temp= member.getUniqueName();
				         //   System.out.println("columns:"+temp);
				            
				            String[] times= temp.split("\\.|\\[|\\]");
				            int length = times.length;
				            timeLine+="'";
				            for(int i=4;i<length;i++){
				           // 	System.out.println(i+":"+times[i]);
				          		if(times[i].equals("")){
				          			continue;
				          		}				          		
				          		if(i==10){
				          			timeLine+=times[i]+" ";

				          		}else if(i<10&&i!=length-1){
				          			timeLine+=times[i]+"-";
				          		}else if(i<10&&i==length-1){
				          			timeLine+=times[i];
				          		}else if(i>10&&i!=length-1){

				          			timeLine+=times[i]+":";

				          		}else if(i==length-1&&length==17){
				          			if(times[i].length()>1){
					          			timeLine+=times[i];
				          			}else{
					          			timeLine+="0"+times[i];

				          			}

				          		}
				          		else if(i==length-1&&length==14){
				          			timeLine+=times[i]+":00";

				          		}
				            }
				            timeLine+="',";
				            
				        }
				       // System.out.println(column+","+row);
				        Cell cell = cs.getCell(column, row);
				       String temp =""+cell.getValue();
				       // System.out.println("values:"+temp);
				        if(temp.equals("null")){
				        	count+=0+",";
				        }else{
					        count+=temp+",";
				        }
				    //    System.out.println();
				    }
				    }
			}else{
				for(Position column:cs.getAxes().get(0))
				{
					for(Member member:column.getMembers()){
					//	System.out.println("columns:"+member.getUniqueName());
					}
					Cell cell=cs.getCell(column);
				//	System.out.print("values:"+cell.getValue());
				//	System.out.println();
				}
			}


		timeLine=timeLine.substring(0,timeLine.length()-1)+"]";	
		count=count.substring(0,count.length()-1)+"]";	

	//	System.out.println(timeLine);
	//System.out.println(count);
	//	System.out.println(title);

    	return title+"+"+count+"+"+timeLine;
    }
    
    
    //查询avg
    public String getAvgShow(String paramStr) throws ClassNotFoundException, Exception{
    	
    	String[] param = paramStr.split(",");
		String title = "";

    	
   // 	Connection connection=DriverManager.getConnection("Provider=mondrian; Jdbc=jdbc:mysql://10.4.36.39:3306/visualization; JdbcUser=root; JdbcPassword=chenxinyi; Catalog=file:///G:/forMyEclipse/monitor/src/main/webapp/WEB-INF/dataCube.xml; JdbcDriver=com.mysql.jdbc.Driver",null);

  /*
   * Connection connection= DriverManager.getConnection("jdbc:mondrian:" + "Jdbc=jdbc:mysql://127.0.0.1:3306/visualization; JdbcUser=root; JdbcPassword=chenxinyi;" +
				        		"Catalog=G:/forMyEclipse/monitor/src/main/webapp/WEB-INF/dataCube.xml;"+"JdbcDrivers=com.mysql.jdbc.Driver");
			
   */
    	Class.forName("mondrian.olap4j.MondrianOlap4jDriver");

	    Connection connection= DriverManager.getConnection("jdbc:mondrian:" + 
						        		"Jdbc=jdbc:mysql://127.0.0.1:3306/visualization; JdbcUser=root; JdbcPassword=chenxinyi;" +
						        		"Catalog=G:/forMyEclipse/monitor/src/main/webapp/WEB-INF/dataCube.xml;"+"JdbcDrivers=com.mysql.jdbc.Driver;");
									//"Catalog=C:/Users/WML/Workspaces/MyEclipse 10/monitor/src/main/webapp/WEB-INF/dataCube.xml;"+"JdbcDrivers=com.mysql.jdbc.Driver;");

		
		OlapConnection olapConnection = connection.unwrap(OlapConnection.class);

	//	String query=" select {[TIME].[hour].[23].[30],[TIME].[hour].[23].[31]} ON COLUMNS, {[TAG].[serviceId].[cloudim]} ON ROWS from dataCube where([Measures].[count])";
		
		
		
		String query = "select {[TIME].[year]";
		String queryMulti = "select {[TIME].[year]";
		
		int[] time = new int[5];
		for(int i=0;i<5;i++){
			time[i] = -1;
		}

		for(int i=3;i<8;i++){		
			
			if(param[i].equals("null")){
				break;
			}
			query+=".["+param[i]+"]";
			queryMulti+=".["+param[i]+"]";
			time[i-3] = Integer.parseInt(param[i]);
		}
		
		//System.out.println(time[0]+","+time[1]+","+time[2]+","+time[3]+","+time[4]);
		int dimtime = SourceDataManager.getDimTime(time[0],time[1],time[2],time[3],time[4]);
		if(dimtime==0){
			
			return "no result";
		}

		if(!param[0].equals("null")){
			
			if(time[4]==-1){
				query+=".Children} ON COLUMNS, {[TAG].[serviceId]";
				queryMulti+=".Children} ON COLUMNS, {[TAG].[serviceId]";
				
			}else{
				query+="} ON COLUMNS, {[TAG].[serviceId]";
				queryMulti+="} ON COLUMNS, {[TAG].[serviceId]";
				
			}

			

			for(int i=0;i<3;i++){
				
				if(param[i].equals("null")){
					break;
				}
				query+=".["+param[i]+"]";
				queryMulti+=".["+param[i]+"]";
				title+=param[i]+".";
			}
			
			if(param[0].equals("null")){
				title="all";
				query+=".Members} ON ROWS from dataCube where([Measures].[alert_value_multi])";
				queryMulti+=".Members} ON ROWS from dataCube where([Measures].[alert_value])";
			}else{

				query+="} ON ROWS from dataCube where([Measures].[alert_value_multi])";
				queryMulti+="} ON ROWS from dataCube where([Measures].[alert_value])";
			}
		}else{

			if(time[4]==-1){
				query+=".Children} ON COLUMNS,{[Measures].[alert_value_multi]} ON ROWS from dataCube ";
				queryMulti+=".Children} ON COLUMNS, {[Measures].[alert_value]} ON ROWS from dataCube ";
				
			}else{
				query+="} ON COLUMNS,{[Measures].[alert_value_multi]} ON ROWS from dataCube ";
				queryMulti+="} ON COLUMNS, {[Measures].[alert_value]} ON ROWS from dataCube ";
				
			}
			
		}
		


		//System.out.println("avg:"+query);

		String timeLine = "[";
		String count = "[";
		CellSet cs = null;
		CellSet cs2 = null;
			try {
				OlapStatement statement =olapConnection.createStatement();
				cs = statement.executeOlapQuery(query);
				cs2 = statement.executeOlapQuery(queryMulti);
			} catch (OlapException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		//	System.out.println("cs.getAxes().size():"+cs.getAxes().size());
				if(cs.getAxes().size()>1){
				for (Position row : cs.getAxes().get(1)) {
				    
				    for (Position column : cs.getAxes().get(0)) {
				       
				        for (Member member : row.getMembers()) {
				            
				       //     System.out.println("rows:"+member.getUniqueName());

				        }
				        for (Member member : column.getMembers()) {
				           
				            
				            String temp= member.getUniqueName();
				           // System.out.println("columns:"+temp);
				            
				            String[] times= temp.split("\\.|\\[|\\]");
				            int length = times.length;
				            timeLine+="'";
				            for(int i=4;i<length;i++){
				           // 	System.out.println(i+":"+times[i]);
				          		if(times[i].equals("")){
				          			continue;
				          		}				          		
				          		if(i==10){
				          			timeLine+=times[i]+" ";

				          		}else if(i<10&&i!=length-1){
				          			timeLine+=times[i]+"-";
				          		}else if(i<10&&i==length-1){
				          			timeLine+=times[i];
				          		}else if(i>10&&i!=length-1){

				          			timeLine+=times[i]+":";

				          		}else if(i==length-1&&length==17){
				          			if(times[i].length()>1){
					          			timeLine+=times[i];
				          			}else{
					          			timeLine+="0"+times[i];

				          			}

				          		}
				          		else if(i==length-1&&length==14){
				          			timeLine+=times[i]+":00";

				          		}
				            }
				            timeLine+="',";
				            
				        }
				       // System.out.println(column+","+row);
				        Cell cell = cs.getCell(column, row);
				        Cell cell2 = cs2.getCell(column, row);
				        
				       String temp =""+cell.getValue();
				       String temp2 =""+cell2.getValue();
				       
				       
				     //   System.out.println("values:"+temp);
				        if(temp.equals("null")){
				        	count+=0+",";
				        }else{
				          
				        	float tempnum =Float.parseFloat(temp);
						    float tempnum2 =Float.parseFloat(temp2);
					        count+=tempnum/tempnum2+",";
					      //  System.out.println("das:"+tempnum/tempnum2);
				        }
				    //    System.out.println();
				    }
				    }
			}else{
				for(Position column:cs.getAxes().get(0))
				{
					for(Member member:column.getMembers()){
					//	System.out.println("columns:"+member.getUniqueName());
					}
					Cell cell=cs.getCell(column);
				//	System.out.print("values:"+cell.getValue());
				//	System.out.println();
				}
			}


		timeLine=timeLine.substring(0,timeLine.length()-1)+"]";	
		count=count.substring(0,count.length()-1)+"]";	


    	return title+"+"+count+"+"+timeLine;
    }
    
    //获取设置初始值
    public String getSetting() throws ClassNotFoundException, Exception{
    			
		DimTime latestDimTimeDate = ShowAlertManager.getLatestDimTimeDate();
		DimTime oldestDimTimeDate = ShowAlertManager.getOldestDimTimeDate();
		StatsData latestSourceDataDate = ShowAlertManager.getLatestSourceDataDate();
	
    	return latestDimTimeDate.getstartTime()+","+oldestDimTimeDate.getstartTime()+","+TimeHelper.getDateFromSeconds(""+latestSourceDataDate.getStartTime());
    }
    
    //更新alert设置
    public String setSetting(String serviceId,String tag,float alert_value,float orange_alert,float red_alert) throws ClassNotFoundException, Exception{
    	
		//System.out.println(serviceId+","+tag+","+alert_value+","+orange_alert+","+red_alert);

    	List<DimTag> ListdDimTag = ShowAlertManager.getDimTagId(serviceId,tag);
    	
    	int setDataFact=1;
    	
    	for(DimTag dimtag : ListdDimTag){
    		
        	int tag_sk = dimtag.getId();
        	List<DataFact> dataList = ShowAlertManager.getDataFactAvg(tag_sk);
        	
        	for(DataFact data : dataList){
        	//	System.out.println(tag_sk+","+data.getId()+","+data.getAvg()+","+alert_value+","+alert_value*data.getAvg());

        		int flag = ShowAlertManager.setDataFactAlert(alert_value*data.getAvg(),alert_value,data.getId());
            
        	}
        	
    	}
    	
    	
    	int setSourceData =  ShowAlertManager.setLatestSourceDataAlert(alert_value,orange_alert,red_alert,serviceId,tag);
    	
    	if(setSourceData!=0&&setDataFact!=0){
        	return "ok";
    	}else{
    		return "error";
    	}
	
    }


    
    
    
    public String getDistinctDimTag(){
    	
    	List<DimTag> serviceIdSourceId= ShowAlertManager.getDistinctServiceId();
    	String endPoinList = "";
    	String DimTagList = "";
    	String tagList = "";
    	
    	int i=0;
    	for (DimTag dimTag : serviceIdSourceId) {
	    	String serviceId = dimTag.getServiceId();
    		DimTagList+=serviceId+"。";
    		
    		List<DimTag> endPointSourceList = ShowAlertManager.getDistinctEndPoint(serviceId);
    		
    		for (DimTag dimTag2 : endPointSourceList) {
    			String endPoint = dimTag2.getEndPoint();
    			endPoinList+=endPoint+",";
    			
        		List<DimTag> tagSourceList = ShowAlertManager.getDistinctTag(serviceId, endPoint);
        		for (DimTag dimTag3 :  tagSourceList) {
        			
        			String tag = dimTag3.getTag();
        			tagList+=tag+";";
        		}
        		tagList+=",";

    		}
    		tagList+="。";
    		endPoinList+="。";
    	}
    	
    	return DimTagList+"+"+endPoinList+"+"+tagList;
    	
   }
    
    
    public String getDistinctDimTagAlertValue(){
    	
    	List<DimTag> serviceIdSourceId= ShowAlertManager.getDistinctServiceId();
    	String DimTagList = "";
    	String tagList = "";
    	
    	int i=0;
    	for (DimTag dimTag : serviceIdSourceId) {
	    	
    		String serviceId = dimTag.getServiceId();
    		DimTagList+=serviceId+"。";
    		
    		//获得每个serviceId下的alert表里面的tag与alert_value等
    		List<Alert> alertSourceList = ShowAlertManager.getDistinctTagWithoutEndPoint(serviceId);
    		
    		for (Alert Alert : alertSourceList) {
    		
    			tagList+=Alert.getTag()+";"+Alert.getAlertVaule()+";"+Alert.getRedAlert()+";"+Alert.getOrangeAlert()+",";
    		}
    		
    		tagList+="。";
    	
    	}
    	
    	return DimTagList+"+"+tagList;
    	
   }
	
	

}
