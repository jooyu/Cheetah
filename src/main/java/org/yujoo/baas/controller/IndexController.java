package org.yujoo.baas.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yujoo.baas.service.QueryStringService;
import org.yujoo.baas.util.FileUtil;
import org.yujoo.baas.util.LuceneUtil;

@Controller

public class IndexController {
@Autowired
	public QueryStringService queryStringService;

	

	
	/**
	 * 搜索界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"","/","search/query"})
	
	public String queryString( HttpServletRequest request) {

		return "search";
	}

	
	
	/**
	 * 搜索界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"","/","search/process"})
	
	public String result( HttpServletRequest request,Model model) {

		String queryString =request.getParameter("queryString");
		//设置全局唯一变量queryString
		FileUtil.setQueryString(queryString);
		List<String> resultList =new ArrayList<String>();
		if(queryString==null)
		{
			return "error";
		}
		System.out.println("queryString"+queryString);
		
		 resultList=queryStringService.getSearchResult(queryString);

		for (String string : resultList) {
			System.out.println(string);
		}
		 model.addAttribute("resultList",resultList);
		  return "result"; 
	}
	
	
//	/**
//	 * 搜索界面
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value={"","/","search/process"})
//
//	public String result( HttpServletRequest request,Model model) {
//
//		String queryString =request.getParameter("queryString");
//		//设置全局唯一变量queryString
//		FileUtil.setQueryString(queryString);
//		List<String> resultList =new ArrayList<String>();
//		if(queryString==null)
//		{
//			return "error";
//		}
//		System.out.println("queryString"+queryString);
//		
//		 resultList=queryStringService.getSearchResult(queryString);
//
//		for (String string : resultList) {
//			System.out.println(string);
//		}
//		 model.addAttribute("resultList",resultList);
//		  return "result "; 
//
//	}
//
	@RequestMapping(value={"","/","search/getContent"})

	public String getContent( HttpServletRequest request,Model model)
	{
		
		String filePath =request.getParameter("fileContentPath");
		
		System.out.println("filePath="+filePath);
		
		String contents=FileUtil.readFileByLines(filePath);
//		response.setContentType("application/msword");    
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + filePath + "\"");    
//        OutputStream out;  
//       
//        try {  
//            out = response.getOutputStream();  
//            byte[] buf = new byte[1024];  
//            InputStream in = new FileInputStream(new File(filePath));    
//            int tempbyte;  
//            while ((tempbyte = in.read(buf)) != -1) {  
//                out.write(buf);  
//            }  
//            out.flush();  
//            out.close();  
//            in.close();
//            
//        } catch (IOException e1) {  
//            e1.printStackTrace();  	
//        }  
//        System.out.println(FileUtil.readFileByLines(filePath));
		if(contents.substring(0, 4).equals("null"))
		{
			contents=contents.substring(4, contents.length());
			
		}
		
		System.out.println(contents);
   	 	model.addAttribute("contents",contents);
//   	 	model.addAttribute("contents","111");
		System.out.println("111");
        return "detail";  
		
	
		
	}
}