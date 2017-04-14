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
    <script language="javascript" type="text/javascript"  src="views/js/showAlert.js"></script>
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
            <li><a href="count" class="active"><i class="fa fa-map-marker fa-fw"></i>访问次数</a></li>
            <li><a href="setting" ><i class="fa fa-users fa-fw"></i>监控设置</a></li>        
          </ul>  
        </nav>
      </div>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
        
        <div class="templatemo-top-nav-container">
          <div class="row">
            <nav class="templatemo-top-nav col-lg-12 col-md-12">
              <ul class="text-uppercase">
                <li><a class="active">引用次数</a></li>
               
              </ul>  
            </nav> 
          </div>
        </div>
        
        
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive" style="height:700px;width:800">
             	
             	<select id="serviceSelect"  class="form-control" style="margin-top:20px;margin-left:10px;"  onchange="changeSelect(1)">
				</select>
				
				<select id="endPointSelect"  class="form-control" style="margin-top:-34px;margin-left:17%;" onchange="changeSelect(2)">
				</select>
				
				<select id="tagSelect" style="margin-top:-34px;margin-left:33%;" class="form-control">
				</select>
						
                          <input type="text" placeholder="年"  style="margin-top:-34px;margin-left:49%;width:5%" class="form-control" id="year" value="2015" onfocus="WdatePicker({dateFmt:'yyyy'})" />
                          <input type="text" placeholder="月"  style="margin-top:-34px;margin-left:55%;width:5%" class="form-control" id="month" value="" onfocus="WdatePicker({dateFmt:'MM'})" />
                          <input type="text" placeholder="日"  style="margin-top:-34px;margin-left:61%;width:5%" class="form-control" id="day" onfocus="WdatePicker({dateFmt:'dd'})" />
                          <input type="text" placeholder="时"  style="margin-top:-34px;margin-left:67%;width:5%" class="form-control" id="hour" onfocus="WdatePicker({dateFmt:'HH'})" />
                          <input type="text" placeholder="分"  style="margin-top:-34px;margin-left:73%;width:5%" class="form-control" id="minute" onfocus="WdatePicker({dateFmt:'mm'})" />
            
              <input type=button  style="margin-top:-34px;margin-left:79%;background-color:rgb(57,143,180);font-family:verdana;color:white;width:10%" value="提交查询" class="form-control" onclick="get()" />
            
            
            <div id="main" style="height:600px; margin-left:10px;margin-top:20px;" ></div>
    <script type="text/javascript">
     
     	var DimTagList = "${DimTagList}";
    	initSelectStart(DimTagList,"${serviceIdNow}","${endPointNow}","${tagNow}","${yearNow}","${monthNow}","${dayNow}","${hourNow}","${minuteNow}",0);
		var check = "${check}";
		
		if(check != "ok"){
		
			alert("所选时间段内无数据。请检查数据库或在监控设置中进行数据预处理。");
			 window.location.href="count";
		}
		
		function changeSelect(flag){
		
    		initSelect(DimTagList,document.getElementById('serviceSelect').value,document.getElementById('endPointSelect').value,document.getElementById('tagSelect').value,flag);
		
		}

		
		function post() {
    	      
		   var PARAMS={
		   				'serviceId':document.getElementById('serviceSelect').value,
		   				'endPoint':document.getElementById('endPointSelect').value,
		   				'tag':document.getElementById('tagSelect').value,
		   				'year':document.getElementById('year').value,
		   				'month':document.getElementById('month').value,
		   				'day':document.getElementById('day').value,
		   				'hour':document.getElementById('hour').value,
		   				'minute':document.getElementById('minute').value
		   				
		   		};
		    var temp = document.createElement("form");      
		    temp.action = 'count';      
		    temp.method = "post";      
		    temp.style.display = "none";      
		    for (var x in PARAMS) {      
		        var opt = document.createElement("textarea");      
		        opt.name = x;      
		        opt.value = PARAMS[x];      
		        // alert(opt.name)      
		        temp.appendChild(opt);      
		    }      
		    document.body.appendChild(temp);      
		    temp.submit();      
		    return temp;      
		}      
		
		function get() {
    	      
		   var serviceId=document.getElementById('serviceSelect').value;
		   var endPoint = document.getElementById('endPointSelect').value;
		   var tag = document.getElementById('tagSelect').value;
		   var year = document.getElementById('year').value;
		   var month = document.getElementById('month').value;
		   	var day = document.getElementById('day').value;
		   	var hour = document.getElementById('hour').value;
			var minute = document.getElementById('minute').value;
		   				

		 window.location.href="count?serviceId="+serviceId+"&endPoint="+endPoint+"&tag="+tag+"&year1="+year+"&month1="+month+"&day1="+day+"&hour1="+hour+"&minute1="+minute;
		}      

    	
   

        
	  // 路径配置
        require.config({
            paths: {
                echarts: 'views/echarts/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
            	'echarts/chart/line' 
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
				    title : {
				        text: '引用次',
				        subtext: '${title}'
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : ${timeLine}
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value} 次'
				            }
				        }
				    ],
				    series : [
				        {
				            name:'引用次数',
				            type:'line',
				            data: ${count},
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name: '平均值'}
				                ]
				            }
				        },

				    ]
				};
                    
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
        
	

        
    </script>
            </div>                          
          </div>  
          
         </div>  
          
          
           

  </body>
</html>