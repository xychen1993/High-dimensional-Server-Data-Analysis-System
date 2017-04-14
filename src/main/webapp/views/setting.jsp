<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>可视化系统 </title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <!-- 
    Visual Admin Template
    http://www.templatemo.com/preview/templatemo_455_visual_admin
    -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="views/css/font-awesome.min.css" rel="stylesheet">
    <link href="views/css/bootstrap.min.css" rel="stylesheet">
    <link href="views/css/templatemo-style.css" rel="stylesheet">
    <script language="javascript" type="text/javascript" src="views/js/WdatePicker.js"></script>
    <script language="javascript" type="text/javascript"  src="views/js/settingAlert.js"></script>
       <script language="javascript" type="text/javascript" src="views/js/common.js"></script>
    <script language="javascript" type="text/javascript"  src="views/js/ajaxfileupload.js"></script>
        <script src="views/echarts/build/dist/echarts.js"></script>   
    <script src="views/js/jquery-1.11.2.min.js"></script>
     
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>
  <body>  
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
          <div class="square"></div>
          <h1>监控系统</h1>
        </header>
        <div class="profile-photo-container">
          <img src="views/images/profile-photo.jpg" alt="Profile Photo" class="img-responsive">  
          <div class="profile-photo-overlay"></div>
        </div>      
        <!-- Search box -->

        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
          </div>
        <nav class="templatemo-left-nav">          
          <ul>
          <li><a  href="index" ><i class="fa fa-home fa-fw"></i>监控警报</a></li>
            <li><a href="avg-time"><i class="fa fa-database fa-fw"></i>平均时长</a></li>
            <li><a href="count" ><i class="fa fa-map-marker fa-fw"></i>访问次数</a></li>
            <li><a href="setting"  class="active"><i class="fa fa-users fa-fw"></i>监控设置</a></li>        
          </ul>  
        </nav>
      </div>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
        
        <div class="templatemo-top-nav-container">
          <div class="row">
            <nav class="templatemo-top-nav col-lg-12 col-md-12">
              <ul class="text-uppercase">
                <li><a href="index" class="active">监控设置</a></li>
               
              </ul>  
            </nav> 
          </div>
        </div>
        <div class="templatemo-content-container" >
         <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
 	<div class="templatemo-content-container" style="margin-top:10%">
          <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
             <table class="table table-striped table-bordered templatemo-user-table-original">
                <thead>
                  <tr>
                    <td><a href="" class="white-text templatemo-sort-by">原始数据最新时间<span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">处理后数据最新时间 <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">处理后数据最早时间 <span class="caret"></span></a></td>                       
                    <td><a href="" class="white-text templatemo-sort-by">数据更新预处理 <span class="caret"></span></a></td>                       
                   
                  </tr>
                </thead>
                <tbody>
                
                  <tr>
                   <td id="latestSourceDataDate">${latestSourceDataDate}</td>
                    <td id="latestDimTimeDate">${latestDimTimeDate}</td>
                    <td id="oldestDimTimeDate">${oldestDimTimeDate}</td>
                    <td id="etlButton"></td>

                   </tr>
                 
                                
                </tbody>
              </table>    
            </div>                          
          </div>  
          
           	<select id="serviceSelect"  class="form-control" style="margin-top:5%;margin-left:10%;"  onchange="changeSelect(1)">
				</select>
				<select id="tagSelect" style="margin-top:-34px;margin-left:27%;" class="form-control" style="margin-top:20px;margin-left:10px;"  onchange="changeSelect(2)">
				</select>
						
       			<input type="text" value=""   style="margin-top:-34px;margin-left:43%;width:12%" class="form-control" id="alert_value" ></input>
                <input type="text" value=""  style="margin-top:-34px;margin-left:56%;width:10%" class="form-control" id="red_alert"  ></input>
                <input type="text" value=""  style="margin-top:-34px;margin-left:67%;width:10%" class="form-control" id="orange_alert"></input>
                         
              <input type=button  style="margin-top:-34px;margin-left:78%;background-color:rgb(57,143,180);font-family:verdana;color:white;width:10%" value="提交设置" class="form-control" onclick="get()" />
                       <div id="main" style="height:100px; margin-left:10px;margin-top:20px;" ></div>
           
    <script type="text/javascript">
     
     	var DimTagList = "${DimTagList}";
		var latestSourceDataDate= "${latestSourceDataDate}";
		var latestDimTimeDate= "${latestDimTimeDate}";
		var latestDimTimeDate1= "${latestDimTimeDate1}";
		
		
    	initSelect(DimTagList,"${serviceIdNow}","${tagNow}",0);
    	$('#etlButton').html("");
		$('#etlButton').append('<a onclick="etl()" class="templatemo-edit-btn">点击处理</a>');
    	
		var check = "${result}";

		if(check=='error'){
				alert("更新失败！");
		
		}else if(check=='ok'){
				alert("更新成功！");
		
		}else if(check=='etl ok'){
	
			alert("预处理成功！");
		}
		
		
		
		function changeSelect(flag){
		
    		initSelect(DimTagList,document.getElementById('serviceSelect').value,document.getElementById('tagSelect').value,flag);
		
		}
		
		function etl(){
		
		if(latestDimTimeDate==latestSourceDataDate||latestDimTimeDate1==latestSourceDataDate){
					alert("已是最新数据，无需处理！");
		}else{
		$('#etlButton').html("");
		$('#etlButton').append('<img src="views/images/loading.gif">');
		 window.location.href="etl?start="+latestDimTimeDate+"&end="+latestSourceDataDate;
		}
		
		}

		
		
		function get() {
    	      
		   var serviceId=document.getElementById('serviceSelect').value;
		   var tag = document.getElementById('tagSelect').value;
		   	var alert_value = document.getElementById('alert_value').value;
		   	var red_alert = document.getElementById('red_alert').value;
			var orange_alert = document.getElementById('orange_alert').value;
			
			if(serviceSelect=='null'||tag=='null'){
			
				alert("service ID与tag 不能为空！");
			}else if(alert_value==''||red_alert==''||orange_alert==''||alert_value=='报警界限（ms）'||red_alert=='红色报警倍数'||orange_alert=='橙色报警倍数'){
				alert("报警界限，报警倍数均不能为空！");
			}else{
				 window.location.href="setting?serviceId="+serviceId+"&tag="+tag+"&alert_value="+alert_value+"&red_alert="+red_alert+"&orange_alert="+orange_alert;
				
			}
		   				
		
		}      

    	
   

        
    </script>

          
         </div>  
          
          
           

  </body>
</html>