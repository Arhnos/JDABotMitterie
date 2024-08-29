package fr.arhnos.mitteriebot.commands;

import fr.arhnos.mitteriebot.utils.EmbedVideo;
import fr.arhnos.mitteriebot.utils.GetVideo;
import fr.arhnos.mitteriebot.utils.Video;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RandomvideoCommand extends Command{

	public RandomvideoCommand(MessageReceivedEvent event) {
		super(event);
	}
	
	public MessageEmbed getOutputEmbed() {
		GetVideo getVideo = new GetVideo();
		Video video = getVideo.getRandomVideo();
		return EmbedVideo.renderVideo(video);
	}
	
}
