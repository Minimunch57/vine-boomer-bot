package me.mw.main;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * 
 * @author Matthew Whitney
 *
 */
public class BotEventListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(!event.getAuthor().isBot()) {
			event.getChannel().sendMessage("Received.");
		}
	}
	
}
