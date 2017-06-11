
<!DOCTYPE html>
<html lang="zh-CN">
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
function oncli() //主程序函数
{
  CreateXMLHttpRequest(); //创建对象
  var content=document.getElementById("queryString").value;  
	
  var parm = "queryString=" + content ;//构造URL参数


	$.ajax({  
	     type:'post',  
	     url:'/search/process?queryString='+content,  
	     data:{},  
	     cache:false,  
	     dataType:'json',  
	     success:function(data){     
	      }
	}); 
}
	
    </script>  
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/index">日志查询</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right">
     <!--       <div class="form-group">
              <input type="text" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
     -->
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <div class="jumbotron">
      <div class="container">
        <h1>日志查询</h1>
        <p>这是一个基于lucene的日志检索系统，为了方便对线上日志进行目标检索</p>
      </div>
    </div>


	<div class="container">
	<div class="input-group">
	<input id="queryString" type="text" class="form-control input-lg">
	<a id="myButton" href="" class="input-group-addon btn btn-primary" role="button" onclick="oncli()">搜索</a>
	</div>
	</div>

    <!-- Bootstrap core JavaScript -->


        



  </body>
</html>