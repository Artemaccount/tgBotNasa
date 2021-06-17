import serviceCommands.HelpCommand;
import pictureSender.PicCommand;
import serviceCommands.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public final class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;


    public Bot(String botName, String botToken) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;

        register(new StartCommand("start", "Старт"));
        register(new HelpCommand("help","Помощь"));
        register(new PicCommand("pic","Пик"));

    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
        setAnswer(chatId, "Не является командой");
    }
    /**
     * Отправка ответа
     * @param chatId id чата
     * @param text текст ответа
     */
    private void setAnswer(Long chatId, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
        }
    }




}