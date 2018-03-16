import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {

    private static JDA bot;

    public Bot(String token, String domChannel, String selfId) throws LoginException, InterruptedException {
        bot = new JDABuilder(AccountType.CLIENT).setToken(token)
                .addEventListener(
                        new MessageListener(
                                domChannel,
                                selfId,
                                new MessageFilter(Configuration.getSwears())
                        )
                ).buildBlocking();
    }

    public JDA getBot(){
        return bot;
    }

}
