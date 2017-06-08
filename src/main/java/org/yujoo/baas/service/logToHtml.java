package org.yujoo.baas.service;

/**
 *转化log成html文件便于检索和展示(如何做到动态转化，日志是不断产生的，定时任务做到每日生成)
 * @author yu joo
 * yujoo2008@gmail.com
 * 2017年6月8日  下午7:37:27
 */
public class logToHtml {

/*
 * 功能实现每日定时cp日志文件夹下面的文件到指定的目录，并给予lucene建立搜索索引
 * 
 * 2017.6.8
 * 浏览器可以打开.log文件
 */
	
	
	//log写html如何做换行
	public void copyFileToLocation(String Path)
	{
		
		
		
	}
	
	
	
	//建立索引完毕后删除，便于下一次同步
	public void  deleteFileAtLocation(String Path)
	{
		
	}

	
	
	
	
	
}
