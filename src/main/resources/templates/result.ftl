<html> 
<body>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>FreeMarker Index</title>
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" language="javascript">  

var xmlobj; //定义XMLHttpRequest对象
function CreateXMLHttpRequest()
{
if(window.ActiveXObject)
//如果当前浏览器支持Active Xobject，则创建ActiveXObject对象
{
  //xmlobj = new ActiveXObject("Microsoft.XMLHTTP");
  try {
      xmlobj = new ActiveXObject("Msxml2.XMLHTTP");
      } catch (e) {
     try {
       xmlobj = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
          xmlobj = false;
         }
        }
       }
else if(window.XMLHttpRequest)
//如果当前浏览器支持XMLHttp Request，则创建XMLHttpRequest对象
{
  xmlobj = new XMLHttpRequest();
}
}
function getContent(obj) //主程序函数
{
  CreateXMLHttpRequest(); //创建对象
  alert(obj);
  
  var fileContentPath=obj.value; 
  alert(fileContentPath); 
  var parm = "fileContentPath=" + fileContentPath ;//构造URL参数
	alert(parm);
	$.ajax({  
	     type:'post',  
	     url:'/search/getContent?fileContentPath='+fileContentPath,  
	     data:{},  
	     cache:false,  
	     dataType:'json',  
	     success:function(data){     
	      }
	}); 
}
	
    </script>  
  </head>

<#list resultList as val>  
     <a   value="${val}" onclick="getContent(this);return false;" />${val}</br>  
</#list> 
 </body> 
</html>