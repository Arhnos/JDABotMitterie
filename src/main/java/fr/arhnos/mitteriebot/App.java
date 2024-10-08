package fr.arhnos.mitteriebot;
import java.util.ArrayList;
import java.util.Collection;

import fr.arhnos.mitteriebot.commands.AddsentenceCommand;
import fr.arhnos.mitteriebot.commands.LastfiveCommand;
import fr.arhnos.mitteriebot.commands.ManCommand;
import fr.arhnos.mitteriebot.commands.PingCommand;
import fr.arhnos.mitteriebot.commands.RandomvideoCommand;
import fr.arhnos.mitteriebot.commands.RemovesentenceCommand;
import fr.arhnos.mitteriebot.commands.SentenceCommand;
import fr.arhnos.mitteriebot.commands.SetupCommand;
import fr.arhnos.mitteriebot.commands.SubscribeCommand;
import fr.arhnos.mitteriebot.commands.UnsubscribeCommand;
import fr.arhnos.mitteriebot.utils.ImportToken;
import fr.arhnos.mitteriebot.utils.PollingDatabase;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class App extends ListenerAdapter {
	public static JDA jda;
	private static String token = new ImportToken().getToken();
	private static Collection<GatewayIntent> perms = getListOfPermissions();
	
	public static void main(String[] args) {
		JDABuilder bot = JDABuilder.createLight(token, perms);
		bot.setActivity(Activity.playing("la roulette russe"));
		bot.addEventListeners(new App());
		jda = bot.build();
		new PollingDatabase(jda);
		
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
		} else if(message.startsWith("!setup")) {
			System.out.println("Commande SETUP lancée");
			SetupCommand setupCommand = new SetupCommand(event);
			event.getChannel().sendMessage(setupCommand.getOutput()).queue();
		} else if(message.equalsIgnoreCase("!last5")) {
			System.out.println("Commande LAST5 lancée");
			LastfiveCommand lastfiveCommand = new LastfiveCommand(event);
			event.getChannel().sendMessageEmbeds(lastfiveCommand.getOutputEmbed()).queue();
		} else if(message.equalsIgnoreCase("!sentence")) {
			System.out.println("Commande SENTENCE lancée");
			SentenceCommand sentenceCommand = new SentenceCommand(event);
			event.getChannel().sendMessage(sentenceCommand.getOutput()).queue();
		} else if(message.startsWith("!addsentence")) {
			System.out.println("Commande ADDSENTENCE lancée");
			AddsentenceCommand addsentenceCommand = new AddsentenceCommand(event);
			event.getChannel().sendMessage(addsentenceCommand.getOutput()).queue();
		} else if(message.startsWith("!removesentence")) {
			System.out.println("Commande REMOVESENTENCE lancée");
			RemovesentenceCommand removesentenceCommand = new RemovesentenceCommand(event);
			event.getChannel().sendMessage(removesentenceCommand.getOutput()).queue();
		}
	}
}
