package com.renren.monitor.utils;

import net.sf.json.JSONObject;

public class JsonHelper {
	public static JSONObject getOkJson(){
		JSONObject root = new JSONObject();
		root.put("code", 0);
		root.put("msg", "操作成功");
		return root;
	}
	
	public static JSONObject getErrorJson(){
		JSONObject root = new JSONObject();
		root.put("code", -1);
		root.put("msg", "操作失败");
		return root;
	}
	
	public static JSONObject resultByJson(int code, String msg) {
		JSONObject root = new JSONObject();
		root.put("code", code);
		root.put("msg", msg);
		return root;
	}
}
