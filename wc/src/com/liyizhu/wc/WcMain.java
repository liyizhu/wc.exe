package com.liyizhu.wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class WcMain {
	/*
	 *  主方法
	 */
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//当前命令
		String currentCommand = null;
		//当前命令切分后的数组
		String[] currentCommandStrings = null;
		while(true) {
			try {
				//读取命令
 				if((currentCommand=br.readLine())!=null) {
					currentCommandStrings = currentCommand.split(" ");
					//命令字符串数组长度小于二或者长度大于四，为错误命令
					if(currentCommandStrings.length<2||currentCommandStrings.length>4) {
						//空命令直接跳过
						if(currentCommandStrings.length==1&&"".equals(currentCommandStrings[0])) {
							
						}else {
							System.out.println("错误命令");
							System.out.println("输入\"wc.exe help\"或\"wc.exe ？\"查看帮助文档");
						}
						
					}else {
						//命令第一个字段必须为wc.exe
						if("wc.exe".equals(currentCommandStrings[0])){
							//查看具体命令
							if(currentCommandStrings.length==3) {
								switch (currentCommandStrings[1]) {
								case "-c":
									WcUtil.charNumber(currentCommandStrings[2]);
									break;
								case "-w":
									WcUtil.wordNumber(currentCommandStrings[2]);
									break;
								case "-l":
									WcUtil.lineNumber(currentCommandStrings[2]);
									break;
								case "-a":
									WcUtil.complexData(currentCommandStrings[2]);
									break;
								
								default:
									System.out.println("错误命令");
									System.out.println("输入\"wc.exe help\"或\"wc.exe ？\"查看帮助文档");
									break;
								}
							}else if(currentCommandStrings.length==2) {
								switch (currentCommandStrings[1]) {
								case "help":
								case "?":
									WcUtil.showHelpDoc();
									break;
								default:
									System.out.println("错误命令");
									System.out.println("输入\"wc.exe help\"或\"wc.exe ？\"查看帮助文档");
									break;
								}
							}else if(currentCommandStrings.length==4&&"-s".equals(currentCommandStrings[1])) {
								String currentDirectory = currentCommandStrings[2];
								//路径是否存在
								if(new File(currentDirectory).isDirectory()) {
									String wildcardChar = currentCommandStrings[3].replaceAll("\\*","\\.+").replaceAll("\\?","\\.");
									//System.out.println(wildcardChar);
									WcUtil.recurseHandleAllFiles(currentDirectory,wildcardChar);
									System.out.println("遍历结束");
								}else {
									System.out.println("错误：找不到指定路径");
								}
							}else {
								System.out.println("错误命令");
								System.out.println("输入\"wc.exe help\"或\"wc.exe ？\"查看帮助文档");
							}
						}else {
							System.out.println("错误命令");
							System.out.println("输入\"wc.exe help\"或\"wc.exe ？\"查看帮助文档");
						}
					}
				}else {
					System.out.println("错误命令");
					System.out.println("输入\"wc.exe help\"或\"wc.exe ？\"查看帮助文档");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
