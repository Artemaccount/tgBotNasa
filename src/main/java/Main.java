import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;
//botsApi.registerBot(new Bot("ArtemMadeBot",
//                        "1877519914:AAElBGhd_TGuLSY2k_RwQJ136Thq2Og1vTg" ));

public class Main {
    private static final Map<String, String> getenv = System.getenv();
    public static void main(String[] args) {
            try {
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                botsApi.registerBot(new Bot(getenv.get("BOT_NAME"), getenv.get("BOT_TOKEN")));
                System.out.println("Бот запущен!");
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
