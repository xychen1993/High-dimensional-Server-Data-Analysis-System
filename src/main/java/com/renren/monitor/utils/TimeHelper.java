package com.renren.monitor.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {

	//毫秒转化为时间
    public static String getDateFromSeconds(String seconds){
        if(seconds==null)
            return " ";
        else{
            Date date=new Date();
            try{
                date.setTime(Long.parseLong(seconds));
                }
            catch(NumberFormatException nfe){
                nfe.printStackTrace();
                return "Input string:"+seconds+"not correct,eg:2011-01-20";
                }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            return sdf.format(date);
            }
        }
    
    //将指定日期转化为秒数
    public static long getSecondsFromDate(String expireDate){
        if(expireDate==null||expireDate.trim().equals(""))
            return 0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try{
            date=sdf.parse(expireDate);
            return (long)(date.getTime());
            }
        catch(ParseException e)
        {
            e.printStackTrace();
            return 0;
            }
        }

  
}
