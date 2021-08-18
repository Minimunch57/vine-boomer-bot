package me.mw.main;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

/**
 * 
 * @author Matthew Whitney
 *
 */
public class Bot {

	/** The <tt>JDA</tt> responsible for handling the Discord bot. */
	private net.dv8tion.jda.api.JDA jda;
	
	//	Main Method
	public static void main(String[] args) {
		//	Create and Start Discord Bot Instance
		new Bot();
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>Bot</i></b>
	 * <p>	<code>public Bot()</code>
	 * <p>	Creates an instance of the Discord bot.
	 * </ul>
	 */
	public Bot() {
		connectBot();
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>connectBot</i></b>
	 * <p>	<code>public void connectBot()</code>
	 * <p>	Sets up an instance of the Discord bot and logs in, making it online.
	 * </ul>
	 */
	public void connectBot() {
		final String token = System.getenv("DISCORD_BOT_TOKEN");
		
		if(token == null) {
			System.out.println("<!> Connection failed. Unable to retrieve token.");
		}
		else {
			try {
				jda = JDABuilder.createDefault(token)
						.setChunkingFilter(ChunkingFilter.ALL)
						.setMemberCachePolicy(MemberCachePolicy.ALL)
						.enableIntents(GatewayIntent.GUILD_MEMBERS)
						.setActivity(Activity.playing("Vine Boom Sound Effect")).build();
				jda.addEventListener(new BotEventListener());
			} catch (LoginException e) {
				System.err.println("<!> Error logging in with the Discord bot.");
			}
		}
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>disconnectBot</i></b>
	 * <p>	<code>public void disconnectBot(boolean force)</code>
	 * <p>	Calls for the Discord bot to log off, forcing shutdown and ignoring pending tasks if desired.
	 * @param force - a <code>boolean</code> for whether or not to force the disconnect and cancel pending tasks.
	 * </ul>
	 */
	public void disconnectBot(boolean force) {
		if(force) {
			jda.shutdownNow();
		}
		else {
			jda.shutdown();
		}
	}
}
