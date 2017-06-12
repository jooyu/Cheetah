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

<#list resultList as val>  
     <a   value="${val}" onclick="getContent(this);" />${val}</br>  
</#list> 
</br>
</br>

 </body> 
</html>