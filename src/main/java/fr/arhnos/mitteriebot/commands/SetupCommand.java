package fr.arhnos.mitteriebot.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Mentions;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SetupCommand extends Command {

	public SetupCommand(MessageReceivedEvent event) {
		super(event);
		// TODO Auto-generated constructor stub
	}
	
	public boolean checkIfCommandValid() {
		Message message = event.getMessage();
		System.out.println(message.getContentRaw());
		Mentions mentions = message.getMentions();
		if(mentions.getChannels().size() !=1 && mentions.getRoles().size() != 1) return false;	
		return true;
	}
	
	public boolean checkIfAuthorIsAdmin() {
		return event.getMember().getPermissions().contains(Permission.ADMINISTRATOR);
	}
	
	@Override
	public String getOutput() {
		if(checkIfAuthorIsAdmin()) {
			if(checkIfCommandValid()) {
				Mentions mentions = event.getMessage().getMentions();
				GuildChannel channel = mentions.getChannels().getFirst();
				Role role = mentions.getRoles().getFirst();
				saveParametersId(role, channel);
				return ":white_check_mark: Les paramètres ont été sauvegardés";
			} else {
				return ":x: La commande est invalide, pour initialiser faites : **!setup #channel @Role** (en les mentionnant)";
			}
		} else {
			return ":hammer: Seul les administrateurs peuvent faire cette commande";
		}
	}
	
	public void saveParametersId(Role r, GuildChannel channel) {
		File f = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "ids.txt");
		try {
			f.createNewFile();
			FileWriter fw = new FileWriter(f);
			fw.append(r.getId() + "\n");
			fw.append(channel.getId());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
