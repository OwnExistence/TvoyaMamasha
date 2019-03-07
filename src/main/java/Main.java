import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class Main implements IListener<MessageReceivedEvent>{
    /*
     * Ключ чат-бота, который нужно получить после регистрации бота
     * тут: https://discordapp.com/developers/applications/me
     */
    public static final String ECHO_BOT_TOKEN = "NTUzMjgyNDk2NTE5ODY0MzYz.D2L0Qw.C7xTylEU4oFjRa8J-zyrkXbars4";

    private IDiscordClient dscordClient;	// Сущность клиента discord
    private boolean isConnected;

    public Main(){
        this.isConnected = false;
    }

    private void regBot(){
        /*
         * Получаем ссылку на диспетчер событий.
         */
        EventDispatcher dispatcher = dscordClient.getDispatcher();
        /*
         * Регистрируем этого бота в диспетчере.
         */
        dispatcher.registerListener(this);
    }

    public void login() throws DiscordException{
        ClientBuilder cBuilder = new ClientBuilder();
        cBuilder.withToken(Main.ECHO_BOT_TOKEN);
        /*
         * В случае неудачи подключения произойдет
         * исключение DiscordException.
         */
        dscordClient = cBuilder.login();
        regBot();

        this.isConnected = true;
    }

    public void handle(MessageReceivedEvent event){
        /*
         * Получаем объект пришедшего сообщения из объекта события.
         */
        IMessage message = event.getMessage();
        /*
         * Получаем объект канала пришедшего сообщения.
         */
        IChannel channel = message.getChannel();

        /*
         * Достаем содержание сообщения.
         */
        String inputMsgStr = message.getContent();

        try{
            /*
             * Отправляем полученное сообщение inputMsgStr обратно
             * в discord в указанный канал channel через клиент
             * dscordClient.
             */
            new MessageBuilder(this.dscordClient).withChannel(channel).withContent(inputMsgStr).build();
        }
        catch(RateLimitException e){
            System.err.println("Ошибка при отправке сообщения: " + e.getMessage());
            e.printStackTrace();
        }
        catch(DiscordException e){
            System.err.println("Ошибка при отправке сообщения: " + e.getMessage());
            e.printStackTrace();
        }
        catch(MissingPermissionsException e){
            System.err.println("Ошибка при отправке сообщения: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        // создаем бота
        Main echoDiscordBot = new Main();

        try{
            // подключаем бот к серверу Discord
            echoDiscordBot.login();
            System.out.println("Бот успешно подключен.");
        }
        catch(DiscordException e){
            System.err.println("Ошибка при подключении бота к Discord: " + e.getMessage());
        }
    }
}