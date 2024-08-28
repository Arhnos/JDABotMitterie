package fr.arhnos.mitteriebot.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Command {
	protected MessageReceivedEvent event;
	
	public Command(MessageReceivedEvent event) {
		this.event = event;
	}
	
	public String getOriginContentMessage() {
		return this.event.getMessage().getContentRaw();
	}
	
	public String getOriginAuthorMessage() {
		return this.event.getAuthor().getAsMention();
	}
	
	public User getAuthorUser() {
		return this.event.getAuthor();
	}
	
	public Guild getGuild() {
		return this.event.getGuild();
	}
	
	public String getOutput() {
		return "Commande effectuée";
	}
	
	public Member getMember() {
		return this.event.getMember();
	}
}
