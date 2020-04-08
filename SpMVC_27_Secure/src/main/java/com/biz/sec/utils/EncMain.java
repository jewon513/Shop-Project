package com.biz.sec.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();

		// 환경변수 목록을 가져와서 그 중에서 BIZ.COM으로 등록된 값을 보여라.
		Map<String, String> envList = System.getenv();

		System.out.println(envList.get("BIZ.COM"));

		Scanner scan = new Scanner(System.in);

		System.out.print("MySQL UserName >> ");
		String username = scan.nextLine();

		System.out.print("MySQL Password >> ");
		String password = scan.nextLine();

		/*
		 * 암호화를 하기 위한 설정
		 */

		// 알고리즘과 soltpassword 설정
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(envList.get("BIZ.COM"));

		String encUsername = pbEnc.encrypt(username);
		String encPassword = pbEnc.encrypt(password);

		System.out.println(encUsername);
		System.out.println(encPassword);

		String saveFile = "./src/main/webapp/WEB-INF/spring/db.properties";
		String saveUserName = String.format("mysql.username=ENC(%s)", encUsername);
		String savePassword = String.format("mysql.password=ENC(%s)", encPassword);

		try {
			PrintWriter out = new PrintWriter(saveFile);
			
			out.println(saveUserName);
			out.println(savePassword);
			
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scan.close();
		System.out.println("db.properties 저장 완료!!!");

	}

}
