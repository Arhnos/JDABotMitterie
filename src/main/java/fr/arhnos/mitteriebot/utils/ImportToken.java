package fr.arhnos.mitteriebot.utils;

import java.io.File;
import java.util.Scanner;

public class ImportToken {
	private final File file;
	
	public ImportToken() {
		System.out.println(System.getProperty("user.dir"));
		this.file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "token.txt");
	}
	
	public ImportToken(String path) {
		this.file = new File(path);
	}
	
	public String getToken() {
		String token = null;
		try {
			Scanner sc = new Scanner(file);
			token = sc.next();
			sc.close();
		} catch (Exception e) {
			System.out.println(e.getCause());
		}
		return token;
	}
}