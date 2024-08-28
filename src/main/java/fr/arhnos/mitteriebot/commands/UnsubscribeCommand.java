package fr.arhnos.mitteriebot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UnsubscribeCommand extends Command {
	private final SubscribeCommand subscribeCommand = new SubscribeCommand(event);

	public UnsubscribeCommand(MessageReceivedEvent event) {
		super(event);
	}
	
	@Override
	public String getOutput() {
		if(!subscribeCommand.userIsAlreadySubscribe()) return "Vous n'êtes pas abonné aux Mites... Pour vous abonner, faites **!subscribe**";
		removeRole();
		return ":crying_cat_face: " + this.getOriginAuthorMessage() + " s'est désabonné des Mites... (bad ending)";
	}
	
	public void removeRole() {
		this.getGuild().removeRoleFromMember(this.getMember(), subscribeCommand.searchRole()).queue();
	}

}
