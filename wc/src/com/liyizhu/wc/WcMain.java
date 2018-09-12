package com.liyizhu.wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class WcMain {
	/*
	 *  ������
	 */
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//��ǰ����
		String currentCommand = null;
		//��ǰ�����зֺ������
		String[] currentCommandStrings = null;
		while(true) {
			try {
				//��ȡ����
 				if((currentCommand=br.readLine())!=null) {
					currentCommandStrings = currentCommand.split(" ");
					//�����ַ������鳤��С�ڶ����߳��ȴ����ģ�Ϊ��������
					if(currentCommandStrings.length<2||currentCommandStrings.length>4) {
						//������ֱ������
						if(currentCommandStrings.length==1&&"".equals(currentCommandStrings[0])) {
							
						}else {
							System.out.println("��������");
							System.out.println("����\"wc.exe help\"��\"wc.exe ��\"�鿴�����ĵ�");
						}
						
					}else {
						//�����һ���ֶα���Ϊwc.exe
						if("wc.exe".equals(currentCommandStrings[0])){
							//�鿴��������
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
									System.out.println("��������");
									System.out.println("����\"wc.exe help\"��\"wc.exe ��\"�鿴�����ĵ�");
									break;
								}
							}else if(currentCommandStrings.length==2) {
								switch (currentCommandStrings[1]) {
								case "help":
								case "?":
									WcUtil.showHelpDoc();
									break;
								default:
									System.out.println("��������");
									System.out.println("����\"wc.exe help\"��\"wc.exe ��\"�鿴�����ĵ�");
									break;
								}
							}else if(currentCommandStrings.length==4&&"-s".equals(currentCommandStrings[1])) {
								String currentDirectory = currentCommandStrings[2];
								//·���Ƿ����
								if(new File(currentDirectory).isDirectory()) {
									String wildcardChar = currentCommandStrings[3].replaceAll("\\*","\\.+").replaceAll("\\?","\\.");
									//System.out.println(wildcardChar);
									WcUtil.recurseHandleAllFiles(currentDirectory,wildcardChar);
									System.out.println("��������");
								}else {
									System.out.println("�����Ҳ���ָ��·��");
								}
							}else {
								System.out.println("��������");
								System.out.println("����\"wc.exe help\"��\"wc.exe ��\"�鿴�����ĵ�");
							}
						}else {
							System.out.println("��������");
							System.out.println("����\"wc.exe help\"��\"wc.exe ��\"�鿴�����ĵ�");
						}
					}
				}else {
					System.out.println("��������");
					System.out.println("����\"wc.exe help\"��\"wc.exe ��\"�鿴�����ĵ�");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
