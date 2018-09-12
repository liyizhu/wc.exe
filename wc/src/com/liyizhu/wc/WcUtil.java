package com.liyizhu.wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JMenu;

public class WcUtil {
	/*
	 * 计算行数
	 */
	public static void lineNumber(String fileName) {
		//判断文件是否存在且是否为c文件
		if(new File(fileName).isFile()&&!fileName.endsWith(".c")) {
			System.out.println("不支持该文件类型");
			return;
		}
		int lineNumber = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((br.readLine())!=null) {
				lineNumber++;
			}
			br.close();
			System.out.println("文件："+fileName+"  行数："+lineNumber);
		}catch (FileNotFoundException e1) {
			System.out.println("错误：文件不存在");
			return;
			//e1.printStackTrace();
		}catch(IOException e2) {
			System.out.println("错误：文件读取出错");
			//e2.printStackTrace();
		}
		
	}
	
	/*
	 * 计算字符数
	 */
	public static void charNumber(String fileName) {
		if(new File(fileName).isFile()&&!fileName.endsWith(".c")) {
			System.out.println("不支持该文件类型");
			return;
		}
		int charNumber = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String currentLine = null;
			String[] currentLineStrings = null;
			while((currentLine=br.readLine())!=null) {
				currentLineStrings = currentLine.trim().split("\\s+");
				for(int i=0;i<currentLineStrings.length;i++) {
					charNumber = charNumber+currentLineStrings[i].length();
					//System.out.println(currentLineStrings[i]+currentLineStrings[i].length());
				} 
			}
			br.close();
			System.out.println("文件："+fileName+"  字符数数："+charNumber);
		} catch (FileNotFoundException e1) {
			System.out.println("错误：文件不存在");
			return;
			//e1.printStackTrace();
		}catch(IOException e2) {
			System.out.println("错误：文件读取出错");
			//e2.printStackTrace();
		}
		
	}
	
	/*
	 * 计算单词数
	 */
	public static void wordNumber(String fileName) {
		if(new File(fileName).isFile()&&!fileName.endsWith(".c")) {
			System.out.println("不支持该文件类型");
			return;
		}
		//单词数
		int wordNumber = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			//当前行字符串
			String currentLine = null;
			//当前行切分后的数组
			String[] currentLineStrings = null;
			
			Pattern p = Pattern.compile("[_a-zA-Z0-9]+");
			
			Matcher m = null;
			while((currentLine=br.readLine())!=null) {
				//将当前字符串中非字母下划线的字符全部替换为空格
				currentLine = currentLine.replaceAll("[^_a-zA-Z]"," ");
				currentLineStrings = currentLine.trim().split("\\s+");
				for(int i=0;i<currentLineStrings.length;i++) {
					m = p.matcher(currentLineStrings[i]);
					if(m.matches()) {
						wordNumber++;
					}
					//System.out.println(currentLineStrings[i]);
				} 
			}
			br.close();
			System.out.println("文件："+fileName+"  单词数："+wordNumber);
		} catch (FileNotFoundException e1) {
			System.out.println("错误：文件不存在");
			return;
			//e1.printStackTrace();
		}catch(IOException e2) {
			System.out.println("错误：文件读取出错");
			//e2.printStackTrace();
		}
		
	}
	/*
	 * 返回更复杂的数据（代码行，空行，注释行）
	 */
	public static void complexData(String fileName) {
		if(new File(fileName).isFile()&&!fileName.endsWith(".c")) {
			System.out.println("不支持该文件类型");
			return;
		}
		//代码行
		int codeLineNumber = 0;
		//空行
		int blankLineNumber = 0;
		//注释行
		int annotationLine = 0;
		//记录多行注释是否结束
		boolean annotationBegin = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String currentLine = null;
			while((currentLine=br.readLine())!=null) {
				if(annotationBegin) {
					annotationLine++;
					//多行注释结束
					if(currentLine.contains("*/")) {
						annotationBegin = false;
					}
					//多行注释开始
				}else if(currentLine.contains("/*")){
					annotationLine++;
					annotationBegin = true;
				}else if(currentLine.contains("//")) {
					annotationLine++;
				}else if(currentLine.trim().length()>1) {
					codeLineNumber++;
				}else {
					blankLineNumber++;
				}
				
			}
			br.close();
			System.out.print("文件："+fileName+"  ");
			System.out.println("代码行："+codeLineNumber+" 空行："+blankLineNumber+" 注释行："+annotationLine);
		}catch (FileNotFoundException e1) {
			System.out.println("错误：文件不存在");
			return;
			//e1.printStackTrace();
		}catch(IOException e2) {
			System.out.println("错误：文件读取出错");
			//e2.printStackTrace();
		}
		
	}
	/*
	 * 递归处理目录下的所有文件
	 */
	public static void recurseHandleAllFiles(String directory,String wildcardChar) {
		Pattern p = Pattern.compile(wildcardChar);
		Matcher m = null;
		File file = new File(directory);
		if(file.isDirectory()) {
			if(file.listFiles()!=null) {
				for(File item:file.listFiles()) {
					if(item.isDirectory()) {
						recurseHandleAllFiles(directory+"\\"+item.getName(),wildcardChar);
					}else {
						m = p.matcher(item.getName());
						if(m.matches()&&item.getName().endsWith(".c")) {
							complexData(directory+"\\"+item.getName());
						}
					}
				}
			}
		}
	}
	/*
	 * 显示帮助文档
	 */
	public static void showHelpDoc() {
		System.out.println("本程序仅支持c程序文件，即.c文件");
		System.out.println("wc.exe -c file.c 返回文件 file.c 的字符数");
		System.out.println("wc.exe -w file.c 返回文件 file.c 的单词数");
		System.out.println("wc.exe -l file.c 返回文件 file.c 的行数");
		System.out.println("wc.exe -a file.c 返回文件 file.c 的代码行数、空行数、注释行数");
		System.out.println("wc.exe -s directory *.c (支持通配符，*代表多个任意字符，？代表一个任意字符） 返回directory路径下所有符合条件的文件的代码行数、空行数、注释行数");
		System.out.println("wc.exe -x 显示图形界面，选取单个文件，显示文件的字符数、行数等全部统计信息");
	}
}
