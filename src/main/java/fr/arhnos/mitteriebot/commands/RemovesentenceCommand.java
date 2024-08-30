package fr.arhnos.mitteriebot.commands;

import fr.arhnos.mitteriebot.utils.SentenceManagement;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RemovesentenceCommand extends Command {

	public RemovesentenceCommand(MessageReceivedEvent event) {
		super(event);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getOutput() {
		SentenceManagement management = new SentenceManagement();
		if(checkIfValid(event.getMessage().getContentRaw())) {
			Integer id = Integer.parseInt(event.getMessage().getContentRaw().replace("!removesentence ", ""));
			if(management.findById(id) == null) return ":warning: Cet id n'existe pas !";
			if(!management.removeSentence(id)) return ":warning:Il y a eu un soucis dans le SQL, appellons Philippe";
			return ":white_check_mark: La phrase a été supprimée";
		}
		return ":x: La commande est invalide. Faites **!removesentence <id>**";
	}
	
	private boolean checkIfValid(String contentRaw) {
		if(contentRaw.equalsIgnoreCase("!removesentence")) return false;
		String content = contentRaw.replace("!removesentence ", "");
		try {
			Integer.parseInt(content);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
