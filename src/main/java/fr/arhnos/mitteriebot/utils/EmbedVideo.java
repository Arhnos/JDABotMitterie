package fr.arhnos.mitteriebot.utils;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class EmbedVideo {
	public static MessageEmbed renderVideo(Video video) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(video.getName());
		eb.setColor(Color.RED);
		eb.setUrl("https://www.youtube.com/watch?v=" + video.getUrl());
		eb.setImage("https://img.youtube.com/vi/" + video.getUrl() +"/0.jpg");
		
		LocalDate ld = video.getDayOut();
		LocalTime lt = video.getTimeOut();
		
		eb.setDescription("Vidéo sortie le " + ld.getDayOfMonth() + " " + ld.getMonthValue() + " " + ld.getYear() + " à " + lt.getHour() + ":" + lt.getMinute());
		return eb.build();
	}
}
