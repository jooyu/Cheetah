
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>FreeMarker Index</title>
 
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" language="javascript">  

	function oncli(obj) //主程序函数
	{
		
	$(obj).attr("href","/search/process?queryString="+$("#queryString").val());
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
	<a id="myButton" class="input-group-addon btn btn-primary" role="button" onclick="oncli(this)">搜索</a>
	</div>
	</div>

    <!-- Bootstrap core JavaScript -->

	
<#include "/footer.ftl">
   <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

  </body>
</html>