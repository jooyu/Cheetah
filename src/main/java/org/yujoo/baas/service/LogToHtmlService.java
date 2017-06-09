package org.yujoo.baas.service;

import java.io.File;
import java.util.List;

/**
 *转化log成html文件便于检索和展示(如何做到动态转化，日志是不断产生的，定时任务做到每日生成)
 * @author yu joo
 * yujoo2008@gmail.com
 * 2017年6月8日  下午7:37:27
 */
public class LogToHtmlService {

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

	

	
//	public static List<File> getFileList(String strPath) {
//        File dir = new File(strPath);
//        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
//        if (files != null) {
//            for (int i = 0; i < files.length; i++) {
//                String fileName = files[i].getName();
//                if (files[i].isDirectory()) { // 判断是文件还是文件夹
//                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
//                } else if (fileName.endsWith("log")) { // 判断文件名是否以log结尾
//                    String strFileName = files[i].getAbsolutePath();
//                    System.out.println("---" + strFileName);
//                    filelist.add(files[i]);
//                } else {
//                    continue;
//                }
//            }
//
//        }
//        return filelist;
//    }
	
	public void traverseFolder2(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
	
}
