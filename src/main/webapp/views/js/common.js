/**
 * Created by stupig on 14-5-11.
 */

var hostURL;
if(location.port)
    hostURL = 'http://' + location.hostname + ':' + location.port;
else
    hostURL = 'http://' + location.hostname;

var communityPicURL = hostURL + '/anjia/communitypic/';
var bulletinPicURL  = hostURL + '/anjia/announcementpic/';
var mailPicURL      = hostURL + '/anjia/mailpic/';
var businessPicURL  = hostURL + '/anjia/businesspic/';
var productPicURL   = hostURL + '/anjia/productspic/';
var couponPicURL    = hostURL + '/anjia/couponpic/';

// 从服务器拉取数据(异步方式)
//var hostURL = 'http://10.1.112.63:8080';

function getDataFromServer(url, condition, doSomething, load, content){
    $.ajax({
        type : 'post',
        url : hostURL + url,
        data : condition,
        dataType : 'json',
        beforeSend:function(XMLHttpRequest){
        	$(load).show();
            $(content).hide();
        },
        success : function(data, status, xhr){
        	$(load).hide();
            $(content).show();
            doSomething(data);
        },
        complete:function(XMLHttpRequest,textStatus){
           $(load).hide();
           $(content).show();
        },
        error : function(xhr, status, error){
            doSomethingAfterServerError();
        }
    });
}

function uploadDataToServer(url, id, condition, doSomething){
    $.ajaxFileUpload({
    	type: 'post',
		url : hostURL + url,
		secureuri : false,
		fileElementId : id,
        data : condition,
        dataType: "json",
        success: function(data) {
        	doSomething(data);
        }
	});
}

function deleteData(url, condition, doSomething){
	$.ajax({
		type: "Post",
        url: hostURL + url,
        dataType: "json",
        data: condition,
        traditional: true,
        success: function(data) {
        	doSomething(data);
        }
	});
}

/**
 * 获取图片路径
 */
function getIMGURL(file) {
	var url = null ; 
    if (window.createObjectURL!=undefined) { // basic
    	url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) { // mozilla(firefox)
    	url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // webkit or chrome
    	url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}

function md5(data) {
	var pwd;
	// TODO 进行MD5加密
	pwd = $.md5(data);
	return pwd;
}
