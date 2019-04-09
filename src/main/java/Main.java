import content.gameRPS;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import thirdParty.mongo.WorkingWithMongo;
//import org.w3c.dom.events.Event;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        final String token = "NTUzMjgyNDk2NTE5ODY0MzYz.D2L0Qw.C7xTylEU4oFjRa8J-zyrkXbars4";
        builder.setToken(token);
        builder.addEventListeners(new Main());
        builder.build();
    }

    //ti pidor
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        WorkingWithMongo mongo = new WorkingWithMongo();
        System.out.println("We received a message from " +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay()
        );
        //==============================================================================================================
        if(event.getMessage().getContentRaw().equals("!ping")) {
            event.getChannel().sendMessage("Иди ко мне, мой сладкий. Поиграем.").queue();
        }
        if(event.getMessage().getContentRaw().toLowerCase().equals("ножницы") ||
                event.getMessage().getContentRaw().toLowerCase().equals("камень") ||
                event.getMessage().getContentRaw().toLowerCase().equals("бумага")) {
            event.getChannel().sendMessage(gameRPS.winner(event.getMessage().getContentRaw().toLowerCase())).queue();
        }
        if(event.getMessage().getContentRaw().toLowerCase().equals("db")) {
            mongo.test();
            event.getChannel().sendMessage("Wooooooooooooooo-Hooooooooooooooooo").queue();
        }
        if(event.getMessage().getContentRaw().toLowerCase().contains("мамаша")) {
            event.getChannel().sendMessage("Здравствуй, пупсик.").queue();
        }
    }
}
