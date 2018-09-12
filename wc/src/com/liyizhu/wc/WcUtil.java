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
	 * ��������
	 */
	public static void lineNumber(String fileName) {
		//�ж��ļ��Ƿ�������Ƿ�Ϊc�ļ�
		if(new File(fileName).isFile()&&!fileName.endsWith(".c")) {
			System.out.println("��֧�ָ��ļ�����");
			return;
		}
		int lineNumber = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((br.readLine())!=null) {
				lineNumber++;
			}
			br.close();
			System.out.println("�ļ���"+fileName+"  ������"+lineNumber);
		}catch (FileNotFoundException e1) {
			System.out.println("�����ļ�������");
			return;
			//e1.printStackTrace();
		}catch(IOException e2) {
			System.out.println("�����ļ���ȡ����");
			//e2.printStackTrace();
		}
		
	}
	
	/*
	 * �����ַ���
	 */
	public static void charNumber(String fileName) {
		if(new File(fileName).isFile()&&!fileName.endsWith(".c")) {
			System.out.println("��֧�ָ��ļ�����");
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
			System.out.println("�ļ���"+fileName+"  �ַ�������"+charNumber);
		} catch (FileNotFoundException e1) {
			System.out.println("�����ļ�������");
			return;
			//e1.printStackTrace();
		}catch(IOException e2) {
			System.out.println("�����ļ���ȡ����");
			//e2.printStackTrace();
		}
		
	}
	
	/*
	 * ���㵥����
	 */
	public static void wordNumber(String fileName) {
		if(new File(fileName).isFile()&&!fileName.endsWith(".c")) {
			System.out.println("��֧�ָ��ļ�����");
			return;
		}
		//������
		int wordNumber = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			//��ǰ���ַ���
			String currentLine = null;
			//��ǰ���зֺ������
			String[] currentLineStrings = null;
			
			Pattern p = Pattern.compile("[_a-zA-Z0-9]+");
			
			Matcher m = null;
			while((currentLine=br.readLine())!=null) {
				//����ǰ�ַ����з���ĸ�»��ߵ��ַ�ȫ���滻Ϊ�ո�
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
			System.out.println("�ļ���"+fileName+"  ��������"+wordNumber);
		} catch (FileNotFoundException e1) {
			System.out.println("�����ļ�������");
			return;
			//e1.printStackTrace();
		}catch(IOException e2) {
			System.out.println("�����ļ���ȡ����");
			//e2.printStackTrace();
		}
		
	}
	/*
	 * ���ظ����ӵ����ݣ������У����У�ע���У�
	 */
	public static void complexData(String fileName) {
		if(new File(fileName).isFile()&&!fileName.endsWith(".c")) {
			System.out.println("��֧�ָ��ļ�����");
			return;
		}
		//������
		int codeLineNumber = 0;
		//����
		int blankLineNumber = 0;
		//ע����
		int annotationLine = 0;
		//��¼����ע���Ƿ����
		boolean annotationBegin = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String currentLine = null;
			while((currentLine=br.readLine())!=null) {
				if(annotationBegin) {
					annotationLine++;
					//����ע�ͽ���
					if(currentLine.contains("*/")) {
						annotationBegin = false;
					}
					//����ע�Ϳ�ʼ
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
			System.out.print("�ļ���"+fileName+"  ");
			System.out.println("�����У�"+codeLineNumber+" ���У�"+blankLineNumber+" ע���У�"+annotationLine);
		}catch (FileNotFoundException e1) {
			System.out.println("�����ļ�������");
			return;
			//e1.printStackTrace();
		}catch(IOException e2) {
			System.out.println("�����ļ���ȡ����");
			//e2.printStackTrace();
		}
		
	}
	/*
	 * �ݹ鴦��Ŀ¼�µ������ļ�
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
	 * ��ʾ�����ĵ�
	 */
	public static void showHelpDoc() {
		System.out.println("�������֧��c�����ļ�����.c�ļ�");
		System.out.println("wc.exe -c file.c �����ļ� file.c ���ַ���");
		System.out.println("wc.exe -w file.c �����ļ� file.c �ĵ�����");
		System.out.println("wc.exe -l file.c �����ļ� file.c ������");
		System.out.println("wc.exe -a file.c �����ļ� file.c �Ĵ�����������������ע������");
		System.out.println("wc.exe -s directory *.c (֧��ͨ�����*�����������ַ���������һ�������ַ��� ����directory·�������з����������ļ��Ĵ�����������������ע������");
		System.out.println("wc.exe -x ��ʾͼ�ν��棬ѡȡ�����ļ�����ʾ�ļ����ַ�����������ȫ��ͳ����Ϣ");
	}
}
