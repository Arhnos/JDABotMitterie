package fr.arhnos.mitteriebot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingCommand extends Command{

	public PingCommand(MessageReceivedEvent event) {
		super(event);
	}
	
	public String getOutput() {
		return "Pong " + this.getOriginAuthorMessage();
	}
}
