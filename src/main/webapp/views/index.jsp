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
          <li><a  href="index" class="active"><i class="fa fa-home fa-fw"></i>监控警报</a></li>
            <li><a href="avg-time"><i class="fa fa-database fa-fw"></i>平均时长</a></li>
            <li><a href="count" ><i class="fa fa-map-marker fa-fw"></i>访问次数</a></li>
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
                <li><a href="index" class="active">红色预警</a></li>
                <li><a href="index-orange">橙色预警</a></li>
               
              </ul>  
            </nav> 
          </div>
        </div>
        
        
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
             <table class="table table-striped table-bordered templatemo-user-table-red">
                <thead>
                  <tr>
                    <td><a href="" class="white-text templatemo-sort-by">Id<span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Service ID <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">End Point <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Start Time<span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Stop Time<span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Tag<span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">AVG Runtime<span class="caret"></span></a></td>
                   
                  </tr>
                </thead>
                <tbody>
                
                  <tr>
                   <td>${id1}</td>
                    <td>${serviceId1}</td>
                    <td>${endPoint1 }</td>
                    <td>${startTime1 }</td>
                    <td>${stopTime1 }</td>
                    <td>${tag1 }</td>
                    <td>${avg1 }</td>
                   </tr>
                  <tr>
                   <td>${id2}</td>
                    <td>${serviceId2}</td>
                    <td>${endPoint2 }</td>
                    <td>${startTime2 }</td>
                    <td>${stopTime2 }</td>
                    <td>${tag2 }</td>
                    <td>${avg2 }</td>
                   </tr>
                  <tr>
                   <td>${id3}</td>
                    <td>${serviceId3}</td>
                    <td>${endPoint3 }</td>
                    <td>${startTime3 }</td>
                    <td>${stopTime3 }</td>
                    <td>${tag3 }</td>
                    <td>${avg3 }</td>
                   </tr>
                  <tr>
                   <td>${id4}</td>
                    <td>${serviceId4}</td>
                    <td>${endPoint4 }</td>
                    <td>${startTime4 }</td>
                    <td>${stopTime4 }</td>
                    <td>${tag4 }</td>
                    <td>${avg4 }</td>
                   </tr>
                  <tr>
                   <td>${id5}</td>
                    <td>${serviceId5}</td>
                    <td>${endPoint5}</td>
                    <td>${startTime5 }</td>
                    <td>${stopTime5 }</td>
                    <td>${tag5 }</td>
                    <td>${avg5 }</td>
                   </tr>
                  <tr>
                   <td>${id6}</td>
                    <td>${serviceId6}</td>
                    <td>${endPoint6 }</td>
                    <td>${startTime6 }</td>
                    <td>${stopTime6 }</td>
                    <td>${tag6 }</td>
                    <td>${avg6 }</td>
                   </tr>
                  <tr>
                   <td>${id7}</td>
                    <td>${serviceId7}</td>
                    <td>${endPoint7 }</td>
                    <td>${startTime7 }</td>
                    <td>${stopTime7 }</td>
                    <td>${tag7 }</td>
                    <td>${avg7 }</td>
                   </tr>
                 <tr>
                   <td>${id8}</td>
                    <td>${serviceId8}</td>
                    <td>${endPoint8 }</td>
                    <td>${startTime8 }</td>
                    <td>${stopTime8 }</td>
                    <td>${tag8 }</td>
                    <td>${avg8 }</td>
                   </tr>
                  <tr>
                   <td>${id9}</td>
                    <td>${serviceId9}</td>
                    <td>${endPoint9 }</td>
                    <td>${startTime9 }</td>
                    <td>${stopTime9 }</td>
                    <td>${tag9 }</td>
                    <td>${avg9 }</td>
                   </tr>
                  <tr>
                   <td>${id10}</td>
                    <td>${serviceId10}</td>
                    <td>${endPoint10 }</td>
                    <td>${startTime10 }</td>
                    <td>${stopTime10 }</td>
                    <td>${tag10 }</td>
                    <td>${avg10 }</td>
                   </tr>  
                <tr>
                   <td>${id11}</td>
                    <td>${serviceId11}</td>
                    <td>${endPoint11 }</td>
                    <td>${startTime11 }</td>
                    <td>${stopTime11 }</td>
                    <td>${tag11 }</td>
                    <td>${avg11 }</td>
                   </tr>
                   
                   <tr>
                   <td>${id12}</td>
                    <td>${serviceId12}</td>
                    <td>${endPoint12 }</td>
                    <td>${startTime12 }</td>
                    <td>${stopTime12 }</td>
                    <td>${tag12 }</td>
                    <td>${avg12 }</td>
                   </tr>
                   <tr>
                   <td>${id13}</td>
                    <td>${serviceId13}</td>
                    <td>${endPoint13 }</td>
                    <td>${startTime13 }</td>
                    <td>${stopTime13 }</td>
                    <td>${tag13 }</td>
                    <td>${avg13 }</td>
                   </tr>
                   <tr>
                   <td>${id14}</td>
                    <td>${serviceId14}</td>
                    <td>${endPoint14 }</td>
                    <td>${startTime14 }</td>
                    <td>${stopTime14 }</td>
                    <td>${tag14 }</td>
                    <td>${avg14 }</td>
                   </tr>
                   <tr>
                   <td>${id15}</td>
                    <td>${serviceId15}</td>
                    <td>${endPoint15 }</td>
                    <td>${startTime15 }</td>
                    <td>${stopTime15 }</td>
                    <td>${tag15 }</td>
                    <td>${avg15 }</td>
                   </tr>
                 
                                
                </tbody>
              </table>    
            </div>                          
          </div>  
            <script type="text/javascript">
            var pageNum = ${pageNum};
			var currentPage = ${currentPage};
			
			if(pageNum<=5){
				if(currentPage ==1){
							
					document.write("<div class=\"joggerRed\"><span class=\"disabled\"> < </span><span class=\"current\">1</span>");
				
				}
				else{
					var page1=currentPage-1;
					document.write("<div class=\"joggerRed\"><a href=\"?page="+page1+"\"> < </a><a href=\"?page=1\">1</a>");
			
				}
				
				for(var i=2;i<=pageNum;i++){
				
					if(i==currentPage){
					
						document.write("<span class=\"current\">"+i+"</span>");
						continue;
					}
					
					document.write("<a href=\"?page="+i+"\">"+i+"</a>");
				}
			if(currentPage!=pageNum){
			
					var page1=currentPage+1;
			document.write("<a href=\"?page="+page1+"\"> > </a>");
			}
	
		
			}
			
			
			else{
			
			if(currentPage==1){
							
					document.write("<div class=\"joggerRed\"><span class=\"disabled\"> < </span><span class=\"current\">1</span>");
				
				}
				else{
					var page1=currentPage-1;
					document.write("<div class=\"joggerRed\"><a href=\"?page="+page1+"\"> < </a><a href=\"?page=1\">1</a>");
			
				}
				
				for(var i=2;i<=pageNum-2;i++){
				
					if(i==currentPage-1){
					
						document.write("<a href=\"?page="+i+"\">"+i+"</a>");
						continue;
					}
					if(i==currentPage){
					
						document.write("<span class=\"current\">"+i+"</span>");
						continue;
					}if(i==currentPage+1){
					
						document.write("<a href=\"?page="+i+"\">"+i+"</a>");
						break;
					}if(i<currentPage-1&&currentPage>2){
						document.write("...");
					   continue;
					}	
			
			}
			
							
			var pageNum1=pageNum-1;
			var pageNum2=pageNum-2;
			var currentPage1=currentPage+1;
				
			
			if(currentPage == pageNum1){
			
				document.write("<span class=\"current\">"+pageNum1+"</span></a><a href=\"?page="+pageNum+"\">"+pageNum+"</a><a href=\"?page="+currentPage1+"\"> > </a>");
			
			}else if(currentPage == pageNum){

				document.write("<a href=\"?page="+pageNum1+"\">"+pageNum1+"</a><span class=\"current\">"+pageNum+"</span><span class=\"disabled\"> > </span>");
				
				
			}else if(i==pageNum1){
			
				document.write("<a href=\"?page="+pageNum1+"\">"+pageNum1+"</a><a href=\"?page="+pageNum+"\">"+pageNum+"</a><a href=\"?page="+ currentPage1+"\"> > </a>");
			
			}else if(i!=pageNum2){

				document.write("...<a href=\"?page="+pageNum1+"\">"+pageNum1+"</a><a href=\"?page="+pageNum+"\">"+pageNum+"</a><a href=\"?page="+currentPage1+"\"> > </a>");
			
			}else{
					
				document.write("<a href=\"?page="+pageNum1+"\">"+pageNum1+"</a><a href=\"?page="+pageNum+"\">"+pageNum+"</a><a href=\"?page="+currentPage1+"\"> > </a>");
				
			}
			
		}
			
           
           </script> 
          
         </div>  
          
          
           

  </body>
</html>