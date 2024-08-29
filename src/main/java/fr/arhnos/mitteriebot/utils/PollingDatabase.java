package fr.arhnos.mitteriebot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class PollingDatabase {
	private JDA jda;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public PollingDatabase(JDA jda) {
		super();
		this.jda = jda;
		start();
	}
	
	public void start() {
		Runnable task = () -> {
			System.out.println("Polling");
			try (Connection con = connect()) {
				List<Video> last5Videos = new ArrayList<Video>();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM videosMitterie ORDER BY datesortie DESC LIMIT 5");
				while(rs.next()) {
					last5Videos.add(new Video(rs.getString(1), rs.getString(2), LocalDate.parse(rs.getString(3)), LocalTime.parse(rs.getString(4))));
				}
				con.close();
				List<Video> videos = checkIfNewVideo(last5Videos);
				if(!videos.isEmpty()) sendEmbeds(videos);			
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
		
		scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.MINUTES);
	}
	
	private void sendEmbeds(List<Video> videos) {
		TextChannel channel = getChannel();
		Role r = getRole();
		
		if(channel == null || r == null) {
			System.out.println("Les informations n'ont pas été spécifiés : faites !setup");
			return;
		}
		
		if(videos.size() == 1) {
			channel.sendMessage(r.getAsMention() + " Nouvelle vidéo sur Mitterie :man_dancing:").queue();
		} else {			
			channel.sendMessage(r.getAsMention() + videos.size() + " nouvelles vidéos sur Mitterie !").queue();
		}
		List<MessageEmbed> embeds = new ArrayList<MessageEmbed>();
		for (Video video : videos) {
			embeds.add(EmbedVideo.renderVideo(video));
		}
		channel.sendMessageEmbeds(embeds).queue();
		
	}

	private List<Video> checkIfNewVideo(List<Video> videos) {
		List<Video> result = new ArrayList<Video>();
		Video lastVideo = lastVideoSend();
		
		if(lastVideo == null || !videos.contains(lastVideo)) {
			saveLastVideoSend(videos.getFirst().getUrl());
			return videos;
		}
		
		if(lastVideo.equals(videos.getFirst())) return result;
		
		for (int i = 0; i < videos.indexOf(lastVideo); i++) {
			result.add(videos.get(i));
		}
		
		saveLastVideoSend(result.getFirst().getUrl());
		return result;
	}
	
	private Video lastVideoSend() {
		File f = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "lastvideo.txt");
		if(f.exists()) {
			try {
				Scanner sc = new Scanner(f);
				String url = sc.next();
				sc.close();
				return new GetVideo().findFromUrl(url);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void saveLastVideoSend(String url) {
		File f = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "lastvideo.txt");
		try {
			f.createNewFile();
			FileWriter fw = new FileWriter(f);
			fw.write(url);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Connection connect() {
		try {
			Properties p = new Properties();
			p.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "db.prop"));
			Class.forName(p.getProperty("driver"));
            String url =  p.getProperty("url");
            String nom = p.getProperty("nom");
            String mdp = p.getProperty("mdp");
            return DriverManager.getConnection(url, nom, mdp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private TextChannel getChannel() {
		File f = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "ids.txt");
		if(f.exists()) {
			try {
				Scanner sc = new Scanner(f);
				sc.next();
				String id = sc.next();
				sc.close();
				return jda.getTextChannelById(id);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private Role getRole() {
		File f = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "ids.txt");
		if(f.exists()) {
			try {
				Scanner sc = new Scanner(f);
				String id = sc.next();
				sc.close();
				return jda.getRoleById(id);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
