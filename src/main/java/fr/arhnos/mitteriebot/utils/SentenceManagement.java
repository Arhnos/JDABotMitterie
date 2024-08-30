package fr.arhnos.mitteriebot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SentenceManagement {
	private Connection con;
	
	public boolean addSentence(String str) {
		try {
			if(this.con == null || this.con.isClosed()) connect();
			PreparedStatement ps = con.prepareStatement("INSERT INTO phrasesDiscord (content) VALUES (?)");
			ps.setString(1, str);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
	            if (this.con != null && !this.con.isClosed()) this.con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return false;
	}
	
	public boolean removeSentence(int id) {
		try {
			if(this.con == null || this.con.isClosed()) connect();
			PreparedStatement ps = con.prepareStatement("DELETE FROM phrasesDiscord WHERE id = ?");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        try {
	            if (this.con != null && !this.con.isClosed()) this.con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return false;
	}
	
	public Sentence getRandomSentence() {
		Sentence sentence = null;
		try {
			if(this.con == null || this.con.isClosed()) connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM phrasesDiscord ORDER BY RANDOM() LIMIT 1");
			if(rs.next()) {
				this.con.close();
				sentence = new Sentence(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sentence;
	}
	
	public int getIdWithContent(String str) {
		try {
			if(this.con == null || this.con.isClosed()) connect();
			PreparedStatement ps = con.prepareStatement("SELECT id FROM phrasesDiscord WHERE content= ? LIMIT 1");
			ps.setString(1, str);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				this.con.close();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public Sentence findById(int id) {
		Sentence sentence = null;
		try {
			if(this.con == null || this.con.isClosed()) connect();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM phrasesDiscord WHERE id= ? LIMIT 1");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				sentence = new Sentence(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sentence;
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
}
