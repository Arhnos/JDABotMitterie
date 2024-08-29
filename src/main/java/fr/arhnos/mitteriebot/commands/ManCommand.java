package fr.arhnos.mitteriebot.commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ManCommand extends Command {

	public ManCommand(MessageReceivedEvent event) {
		super(event);
	}
	
	
	public MessageEmbed getOutputEmbed() {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Manuel des commandes de MitterieBot");
		eb.setColor(Color.PINK);
		eb.setFooter("Ce manuel vous ai proposé par l'association française des Mites");
		eb.addField(new Field("!man", "Donne toutes les commandes du bot", false));
		eb.addField(new Field("!ping", "Répond pong si le bot est en ligne", false));
		eb.addField(new Field("!subscribe", "Permet d'acquérir le role \"Frères de Mites\" pour avoir les notifications en cas de nouvelle vidéo", false));
		eb.addField(new Field("!unsubscribe", "L'inverse de !subscribe du coup", false));
		eb.addField(new Field("!randomvideo", "Donne une vidéo aléatoire", false));
		eb.addField(new Field("!setup <#channel> <@role>", "Permet d'initialiser le channel et le rôle où seront diffusées les notifications (Permissions admins)", false));
		eb.setUrl("http://sysser.fr/Mitterie");
		
		
		return eb.build();
	}
	
}
