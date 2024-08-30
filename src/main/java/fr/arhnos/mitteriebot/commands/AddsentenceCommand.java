package fr.arhnos.mitteriebot.commands;

import fr.arhnos.mitteriebot.utils.SentenceManagement;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class AddsentenceCommand extends Command {

	public AddsentenceCommand(MessageReceivedEvent event) {
		super(event);
		// TODO Auto-generated constructor stub
	}
	
	
	public String getOutput() {
		SentenceManagement management = new SentenceManagement();
		if(checkIfValid(event.getMessage().getContentRaw())) {
			String content = event.getMessage().getContentRaw().replace("!addsentence ", "");
			if(management.addSentence(content)) {
				int id = management.getIdWithContent(content);
				return ":white_check_mark: Phrase enregistrée avec l'id : " + id;
			}
			return ":warning: Un problème a eu lieu lors de l'insertion, Philippe vient nous aider";
		}
		return ":x: La commande est invalide ! Faites **!addsentence <phrase>**";
	}


	private boolean checkIfValid(String contentRaw) {
		if(contentRaw.equalsIgnoreCase("!addsentence")) return false;
		String content = contentRaw.replace("!addsentence ", "");
		if(content.isEmpty()) return false;
		return true;
	}

}
