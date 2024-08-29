package fr.arhnos.mitteriebot.commands;

import java.util.ArrayList;
import java.util.List;

import fr.arhnos.mitteriebot.utils.EmbedVideo;
import fr.arhnos.mitteriebot.utils.GetVideo;
import fr.arhnos.mitteriebot.utils.Video;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class LastfiveCommand extends Command {

	public LastfiveCommand(MessageReceivedEvent event) {
		super(event);
		// TODO Auto-generated constructor stub
	}
	
	public List<MessageEmbed> getOutputEmbed() {
		List<MessageEmbed> result = new ArrayList<MessageEmbed>();
		List<Video> videos = new GetVideo().getLastFiveVideos();
		for (Video video : videos) {
			result.add(EmbedVideo.renderVideo(video));
		}
		return result;
	}

}
