package com.renren.monitor.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.sf.json.JSONObject;


import org.springframework.beans.factory.annotation.Autowired;
import com.renren.monitor.service.SourceDataService;

@Path("")
public class ETLController {
		
	@Autowired
    private SourceDataService SourceDataService;
	
	 @Get("etl")
	    public String helloWorld(@Param("start") String start,@Param("end") String end) {
		 

		
		 JSONObject json = null;
		 json = SourceDataService.ETL(start,end);
		 return "r:/monitor/setting?etl=ok";


	    }
}
