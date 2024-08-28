package fr.arhnos.mitteriebot.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class Video {
	private String url;
	private String name;
	private LocalDate dayOut;
	private LocalTime timeOut;
	
	public Video(String url, String name, LocalDate dayOut, LocalTime timeOut) {
		super();
		this.url = url;
		this.name = name;
		this.dayOut = dayOut;
		this.timeOut = timeOut;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDayOut() {
		return dayOut;
	}

	public void setDayOut(LocalDate dayOut) {
		this.dayOut = dayOut;
	}

	public LocalTime getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(LocalTime timeOut) {
		this.timeOut = timeOut;
	}	
}
