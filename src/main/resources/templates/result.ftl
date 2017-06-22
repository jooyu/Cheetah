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

	 function getContent(obj) //主程序函数
	{
	var fileContentPath=$(obj).attr("value"); 
  	var parm = "fileContentPath=" + encodeURI(fileContentPath) ;//构造URL参数
		
	$(obj).attr("href","/search/getContent?"+parm);

	}
	
    </script> 
  </head>


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

      </div>
    </nav>

    <div class="jumbotron">
      <div class="container">
        <h1>查询列表</h1>
        <p>这是一个基于lucene的日志检索系统，为了方便对线上日志进行目标检索</p>
      </div>
    </div>


	<div class="container">
	<#list resultList as val>  
	     <a   value="${val}" onclick="getContent(this);" />${val}</br>  
	</#list> 
	</div>

<#include "/footer.ftl">

</br>
</br>

 </body> 
</html>