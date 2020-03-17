import content.gameRPS;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import thirdParty.mongo.DiscordToMongo;
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
    public void onMessageReceived(MessageReceivedEvent event) {
        WorkingWithMongo mongo = new WorkingWithMongo();
        DiscordToMongo dtmongo = new DiscordToMongo();
        System.out.println("We received a message from " +
                event.getAuthor().getName() + "(" +
                event.getChannel().getId() + "): " +
                event.getMessage().getContentDisplay()
        );
        //==============================================================================================================
        if (event.getMessage().getContentRaw().equals("!ping")) {
            event.getChannel().sendMessage("Иди ко мне, мой сладкий. Поиграем.").queue();
        }
        if (event.getMessage().getContentRaw().toLowerCase().equals("ножницы") ||
                event.getMessage().getContentRaw().toLowerCase().equals("камень") ||
                event.getMessage().getContentRaw().toLowerCase().equals("бумага")) {
            event.getChannel().sendMessage(gameRPS.winner(event.getMessage().getContentRaw().toLowerCase())).queue();
        }
        if (event.getMessage().getContentRaw().toLowerCase().equals("db")) {
            mongo.test();
            event.getChannel().sendMessage("Wooooooooooooooo-Hooooooooooooooooo").queue();
        }
        if (event.getMessage().getContentRaw().toLowerCase().contains("привет")) {
            event.getChannel().sendMessage("Здравствуй, пупсик.").queue();
        }
        if (event.getMessage().getContentRaw().toLowerCase().contains("/add")) {
            String newMsg = event.getMessage().getContentRaw().replace("/add ", "");
            event.getChannel().sendMessage(dtmongo.addLink(newMsg)).queue();
        }
        if (event.getMessage().getContentRaw().toLowerCase().equals("/show")) {
            event.getChannel().sendMessage(dtmongo.showLink()).queue();
        }
        if (event.getMessage().getContentRaw().toLowerCase().contains("/delete")) {
            String newMsg = event.getMessage().getContentRaw().replace("/delete ", "");
            event.getChannel().sendMessage(dtmongo.deleteLink(newMsg)).queue();
        }
        if (event.getMessage().getContentRaw().toLowerCase().contains("нахуй")) {
            event.getChannel().sendMessage("https://www.youtube.com/watch?v=Vw_46nfGxNY").queue();
        }
        if (event.getMessage().getContentRaw().equals("!play")) {
            event.getChannel().sendMessage("Задавай свой вопрос, я постараюсь ответить на него предельно ясно.").queue();
        }

    }
}
//sas