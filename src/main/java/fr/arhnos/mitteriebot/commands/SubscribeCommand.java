package fr.arhnos.mitteriebot.commands;

import java.awt.Color;
import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SubscribeCommand extends Command {
	public SubscribeCommand(MessageReceivedEvent event) {
		super(event);
	}
	
	public void createRole() {
		Guild guild = this.getGuild();
		guild.createRole()
			.setName("Frères de mites")
			.setColor(Color.PINK)
			.queue();
	}
	
	public Role searchRole() {
		Guild guild = this.getGuild();
		List<Role> roles = guild.getRolesByName("Frères de mites", true);
		if(roles.isEmpty()) return null;
		return roles.get(0);
	}
	
	public void attachRoleToMember() {
		if(searchRole() == null) {
			createRole();
		}
		Role role = searchRole();
		this.getGuild().addRoleToMember(getMember(), role).queue();
	}
	
	@Override
	public String getOutput() {
		if(this.getGuild() == null) return "Cette commande n'est disponible seulement sur un serveur";
		if(userIsAlreadySubscribe()) return "Vous êtes déjà abonné ! Pour vous désabonner, faites **!unsubscribe**";
		attachRoleToMember();
		return "🎉 " + this.getOriginAuthorMessage() + " s'est abonné aux Mites !!!";
	}
	
	public boolean userIsAlreadySubscribe() {
		Member member = this.getMember();
		return member.getRoles().contains(searchRole());
	}

}
