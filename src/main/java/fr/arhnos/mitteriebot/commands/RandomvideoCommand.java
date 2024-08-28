package fr.arhnos.mitteriebot.commands;

import fr.arhnos.mitteriebot.utils.GetVideo;
import fr.arhnos.mitteriebot.utils.Video;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RandomvideoCommand extends Command{

	public RandomvideoCommand(MessageReceivedEvent event) {
		super(event);
	}
	
	public MessageEmbed getOutputEmbed() {
		GetVideo getVideo = new GetVideo();
		Video video = getVideo.getRandomVideo();
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(video.getName());
		eb.setUrl("https://www.youtube.com/watch?v=" + video.getUrl());
		eb.setImage("https://img.youtube.com/vi/" + video.getUrl() +"/0.jpg");
		eb.setDescription("Vidéo sortie le " + video.getDayOut() + " à " + video.getTimeOut());
		return eb.build();
	}
	
}
