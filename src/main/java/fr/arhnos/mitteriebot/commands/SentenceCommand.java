package fr.arhnos.mitteriebot.commands;

import fr.arhnos.mitteriebot.utils.Sentence;
import fr.arhnos.mitteriebot.utils.SentenceManagement;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SentenceCommand extends Command {

	public SentenceCommand(MessageReceivedEvent event) {
		super(event);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getOutput() {
		SentenceManagement management = new SentenceManagement();
		Sentence sentence = management.getRandomSentence();
		
		if(sentence == null) return ":warning: Aucune phrase n'a été enregistrée faites **!addsentence <phrase>**";
		
		return sentence.getContent() + " (id: " + sentence.getId() + ")";
	}

}
