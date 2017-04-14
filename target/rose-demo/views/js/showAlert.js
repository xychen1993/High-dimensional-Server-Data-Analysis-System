var serviceIds;
var endPoints = new Array();
var endPoint= new Array();
var tagss = new Array();
var tags = new Array(new Array(),new Array());
var tag = new Array();
function initSelectStart(List,serviceIdNow,endPointNow,tagNow,yearNow,monthNow,dayNow,hourNow,minuteNow,flag) {
	
	$('#year').attr('value',yearNow);
	$('#month').attr('value',monthNow);
	$('#day').attr('value',dayNow);
	$('#hour').attr('value',hourNow);
	$('#minute').attr('value',minuteNow);
	initSelect(List,serviceIdNow,endPointNow,tagNow,flag);

}

function initSelect(List,serviceIdNow,endPointNow,tagNow,flag) {
	
	var service=new Array(); 
	service = List.split("+");
	
	serviceIds = service[0];
	endPoints = service[1].split("。");
	tagss = service[2].split("。");
	
	var serviceId = new Array();
	serviceId=serviceIds.split("。");
	
	$('#serviceSelect').html("");
	
	if(serviceIdNow!='null'){
		
		var String = '<option value="'+serviceIdNow+'">'+serviceIdNow;		
	}
	else{
		
		var String = '<option value="null">全部serviceId</option>';		
	}
	
	
	for(var i=0;i<serviceId.length-1;i++){
		
		var serviceIdTemp = serviceId[i];
		
		endPoint[serviceIdTemp] = endPoints[i];
		
		var endPointShow = endPoint[serviceIdTemp].split(",");
		tags[serviceIdTemp] =tagss[i].split(",");

		for(j=0;j<endPointShow.length-1;j++){
			
			tag[serviceIdTemp+","+endPointShow[j]] = tags[serviceIdTemp][j];			
		}
	
		if(serviceIdNow!='null'&&serviceIdTemp == serviceIdNow) continue;
		
		String += '<option value="'+serviceIdTemp+'">'+serviceIdTemp+'</option>';
		
	}
	
	
	if(serviceIdNow!='null'){
		
			String += '<option value="null">全部serviceId</option>';		
	}

	
	$('#serviceSelect').append(String);
	
	
	if(serviceIdNow!='null'){
		
		getEndPointList(serviceIdNow,endPointNow,flag);

	}
	else{
		
		$('#endPointSelect').html("");
		$('#endPointSelect').append('<option value="null">全部endPoint</option>');

	}
	
	if(serviceIdNow!='null'&&endPointNow!='null'){

		getTagList(serviceIdNow,endPointNow,tagNow,flag);

	}
	else{
		$('#tagSelect').html("");
		$('#tagSelect').append('<option value="null">全部tag</option>');

	}
	


		
	
}

function getEndPointList(serviceId,endPointNow,flag){
	
	var endPointShow = endPoint[serviceId].split(",");

	$('#endPointSelect').html("");
	
	var String;

	if(endPointNow =='null'||flag==1){
		String = '<option value="null">全部endPoint</option>';
		
	}
	else if(flag==0||flag==2){
		
		String = '<option value="'+endPointNow+'">'+endPointNow+'</option>';

	}
		
		for(var i=0;i<endPointShow.length-1;i++){
			
			if(endPointNow!='null'&&endPointShow[i]==endPointNow) continue;

			String +='<option value="'+endPointShow[i]+'">'+endPointShow[i]+'</option>';
		}

		if(endPointNow!='null'&&flag!=1){
			String += '<option value="null">全部endPoint</option>';
		}
		
		$('#endPointSelect').append(String);
	
}


function getTagList(serviceId,endPoint,tagNow,flag){
	
	
	$('#tagSelect').html("");
	var String="";		
	if(tagNow!='null'&&flag==0){
		String = '<option value="'+tagNow+'">'+tagNow+'</option>';
	}else{
		String = '<option value="null">全部tag</option>';
	}
	
	if(flag!=1){
		
		var tagShow = new Array();
		tagShow = tag[serviceId+","+endPoint].split(";");
		
		for(var i=0;i<tagShow.length-1;i++){
			
			if(tagNow!='null'&&tagShow[i]==tagNow) continue;
			String += '<option value="'+tagShow[i]+'">'+tagShow[i]+'</option>';
		}
		
		if(tagNow!='null'&&flag==0){
			String += '<option value="null">全部endPoint</option>';
		}
	}
	
	
	
	$('#tagSelect').append(String);

}

