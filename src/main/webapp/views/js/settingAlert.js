var serviceIds;
var tagss = new Array();
var tags = new Array(new Array(),new Array());
var tag = new Array();

function initSelect(List,serviceIdNow,tagNow,flag) {
	
	var service=new Array(); 
	service = List.split("+");
	
	serviceIds = service[0];
	tagss = service[1];
	
	var serviceId = new Array();
	serviceId=serviceIds.split("。");
	tags = tagss.split("。");
	
	$('#serviceSelect').html("");
	
	if(serviceIdNow!='null'){
		
		var String = '<option value="'+serviceIdNow+'">'+serviceIdNow;		
	}
	else{
		
		var String = '<option value="null">全部serviceId</option>';		
	}
	
	
	for(var i=0;i<serviceId.length-1;i++){
		
		var serviceIdTemp = serviceId[i];
		
		tag[serviceIdTemp] =tags[i];
	
		if(serviceIdNow!='null'&&serviceIdTemp == serviceIdNow) continue;
		
		String += '<option value="'+serviceIdTemp+'">'+serviceIdTemp+'</option>';
		
	}
	
	
	if(serviceIdNow!='null'){
		
			String += '<option value="null">全部serviceId</option>';	
			getTagList(serviceIdNow,tagNow,flag);

	}else{
		$('#alert_value').attr('value',"报警界限（ms）");
		$('#red_alert').attr('value',"红色报警倍数");
		$('#orange_alert').attr('value',"橙色报警倍数");
		
		$('#tagSelect').html("");
		$('#tagSelect').append('<option value="null">全部tag</option>');

	}

	
	$('#serviceSelect').append(String);
	
	

	
	
}

function getTagList(serviceId,tagNow,flag){
	
	
	$('#tagSelect').html("");
	$('#alert_value').attr('value',"报警界限（ms）");
	$('#red_alert').attr('value',"红色报警倍数");
	$('#orange_alert').attr('value',"橙色报警倍数");
	var String="";	
	var alert_value="报警界限（ms）";
	var red_alert="红色报警倍数";
	var orange_alert="橙色报警倍数";

	if(flag==1){
		String = '<option value="null">全部tag</option>';

	}else{
		String = '<option value="'+tagNow+'">'+tagNow+'</option>';

	}

	var tagShow = new Array();
		tagShow = tag[serviceId].split(",");
		
		for(var i=0;i<tagShow.length-1;i++){
			var alertShow = tagShow[i].split(";");
			
			if(tagNow!='null'&&alertShow[0]==tagNow){
				alert_value = alertShow[1];
				red_alert = alertShow[2];
				orange_alert = alertShow[3];
				continue;
			}
			String += '<option value="'+alertShow[0]+'">'+alertShow[0]+'</option>';
			
			
		}
		
		if(tagNow!='null'&&flag==0){
			String += '<option value="null">全部endPoint</option>';
		}
	
	
		$('#tagSelect').append(String);
		$('#alert_value').attr('value',alert_value);
		$('#red_alert').attr('value',red_alert);
		$('#orange_alert').attr('value',orange_alert);
		
	
	

}

