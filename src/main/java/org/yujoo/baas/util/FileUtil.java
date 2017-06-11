package org.yujoo.baas.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtil {
	public static ArrayList<String> filelist = new ArrayList<String>();

	 public static void main(String[] args) throws Exception {
	
	 String filePath =
	 "C://Users//eaves.zhu//Documents//GitHub//Cheetah//src//main//resources//log";
	 getFiles(filePath);
	 }



	/*
	 * 通过递归得到某一路径下所有的目录及其文件
	 */
	public static void getFiles(String filePath) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getFiles(file.getAbsolutePath());
				filelist.add(file.getAbsolutePath());
				//读取文件
				readFileByLines(file.getAbsolutePath());
				System.out.println("显示当前下所有子目录及其文件" + file.getAbsolutePath());
			} else {
				System.out.println("显示当前下所有子目录及其文件" + file.getAbsolutePath());
			}
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static void  readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}