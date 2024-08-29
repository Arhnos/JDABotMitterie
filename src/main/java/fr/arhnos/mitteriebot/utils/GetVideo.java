package fr.arhnos.mitteriebot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

public class GetVideo {
	public Connection con;
	
	public GetVideo() {
		
	}
	
	private void connect() {
		try {
			Properties p = new Properties();
			p.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "db.prop"));
			Class.forName(p.getProperty("driver"));
            String url =  p.getProperty("url");
            String nom = p.getProperty("nom");
            String mdp = p.getProperty("mdp");
            this.con = DriverManager.getConnection(url, nom, mdp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Video getRandomVideo() {
		Video res = null;
		try {
			if(this.con == null || this.con.isClosed()) connect();
			PreparedStatement ps = this.con.prepareStatement("SELECT * FROM videosmitterie ORDER BY RANDOM() LIMIT 1");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {				
				String url = rs.getString(1);
				String name = rs.getString(2);
				LocalDate ld = LocalDate.parse(rs.getString(3));
				LocalTime lt = LocalTime.parse(rs.getString(4));
				res = new Video(url, name, ld, lt);
			}
			this.con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
	
	public Video findFromUrl(String urlSearch) {
		Video res = null;
		try {
			if(this.con == null || this.con.isClosed()) connect();
			PreparedStatement ps = this.con.prepareStatement("SELECT * FROM videosmitterie WHERE url= ? LIMIT 1");
			ps.setString(1, urlSearch);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {				
				String url = rs.getString(1);
				String name = rs.getString(2);
				LocalDate ld = LocalDate.parse(rs.getString(3));
				LocalTime lt = LocalTime.parse(rs.getString(4));
				res = new Video(url, name, ld, lt);
			}
			this.con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
	
}
