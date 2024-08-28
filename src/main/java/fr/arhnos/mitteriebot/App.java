package fr.arhnos.mitteriebot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.arhnos.mitteriebot.commands.ManCommand;
import fr.arhnos.mitteriebot.commands.PingCommand;
import fr.arhnos.mitteriebot.commands.RandomvideoCommand;
import fr.arhnos.mitteriebot.commands.SubscribeCommand;
import fr.arhnos.mitteriebot.commands.UnsubscribeCommand;
import fr.arhnos.mitteriebot.utils.ImportToken;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class App extends ListenerAdapter {
	private static String token = new ImportToken().getToken();
	private static Collection<GatewayIntent> perms = getListOfPermissions();
	
	public static void main(String[] args) {
		JDABuilder bot = JDABuilder.createLight(token, perms);
		bot.setActivity(Activity.playing("la roulette russe"));
		bot.addEventListeners(new App());
		bot.build();
		
	}
	
	public static Collection<GatewayIntent> getListOfPermissions(){
		ArrayList<GatewayIntent> perms = new ArrayList<GatewayIntent>();
		perms.add(GatewayIntent.GUILD_MESSAGES);
		perms.add(GatewayIntent.MESSAGE_CONTENT);
		return perms;
	}
	
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(event.getAuthor().isBot()) return;
		
		String message = event.getMessage().getContentRaw();
		
		if(message.equalsIgnoreCase("!ping")) {
			System.out.println("Commande PING lancée");
			PingCommand pingCommand = new PingCommand(event);
			event.getChannel().sendMessage(pingCommand.getOutput()).queue();
		} else if(message.equalsIgnoreCase("!subscribe")) {
			System.out.println("Commande SUBSCRIBE lancée");
			SubscribeCommand subscribeCommand = new SubscribeCommand(event);
			event.getChannel().sendMessage(subscribeCommand.getOutput()).queue();
		} else if(message.equalsIgnoreCase("!unsubscribe")) {
			System.out.println("Commande UNSUBSCRIBE lancée");
			UnsubscribeCommand unsubscribeCommand = new UnsubscribeCommand(event);
			event.getChannel().sendMessage(unsubscribeCommand.getOutput()).queue();
		} else if(message.equalsIgnoreCase("!man")) {
			System.out.println("Commande MAN lancée");
			ManCommand manCommand = new ManCommand(event);
			event.getChannel().sendMessageEmbeds(manCommand.getOutputEmbed()).queue();
		} else if(message.equalsIgnoreCase("!randomvideo")) {
			System.out.println("Commande RANDOMVIDEO lancée");
			RandomvideoCommand randomvideoCommand = new RandomvideoCommand(event);
			event.getChannel().sendMessageEmbeds(randomvideoCommand.getOutputEmbed()).queue();
		}
	}
}
