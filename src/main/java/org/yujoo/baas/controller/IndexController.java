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

@Controller

public class IndexController {
@Autowired
	private QueryStringService queryStringService;

	
	/**
	 * 根据指定的目录展示内容
	 * @author eaves.zhu
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/downloadTemplate")  
    public byte[] downloadTemplate(HttpServletRequest request,HttpServletResponse response){  

        String fileName = request.getParameter("src");    
        response.setContentType("application/document");    
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");    
        OutputStream out;  
        try {  
            out = response.getOutputStream();  
            URL base = this.getClass().getResource(""); //先获得本类的所在位置，如/home/popeye/testjava/build/classes/net/  
            String path = new File(base.getFile(), fileName).getCanonicalPath();  
            System.out.println("path"+path);
            byte[] buf = new byte[1024];  
            InputStream in = new FileInputStream(new File(path));    
            int tempbyte;  
            while ((tempbyte = in.read(buf)) != -1) {  
                out.write(buf);  
            }  
            out.flush();  
            out.close();  
        } catch (IOException e1) {  
            e1.printStackTrace();  
        }  
        return null;  

    }  
	
	/**
	 * 搜索界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"","","/search/query"})
	public String qyeryString( HttpServletRequest request) {

		return "search";
	}

	
	/**
	 * 搜索界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"","","/search/result"})
	public String result( HttpServletRequest request,Model model) {

		String queryString =request.getParameter("queryString");
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

	@RequestMapping(value={"","","/search/getContent"})
	public String getContent( HttpServletRequest request,HttpServletResponse response)
	{
		
		String filePath =request.getParameter("path");
		//FileUtil.getFiles(filePath);
		response.setContentType("application/document");    
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filePath + "\"");    
        OutputStream out;  
        try {  
            out = response.getOutputStream();  
            byte[] buf = new byte[1024];  
            InputStream in = new FileInputStream(new File(filePath));    
            int tempbyte;  
            while ((tempbyte = in.read(buf)) != -1) {  
                out.write(buf);  
            }  
            out.flush();  
            out.close();  
            in.close();
        } catch (IOException e1) {  
            e1.printStackTrace();  
        }  
        return null;  
		
	
		
	}
}