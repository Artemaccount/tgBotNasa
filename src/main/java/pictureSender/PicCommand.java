package pictureSender;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Map;

/**
 * Команда "/pic"
 */
public class PicCommand extends OperationCommand {

    private static final Map<String, String> getenv = System.getenv();

    public PicCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {
            sendAnswer(absSender, chat.getId(), "Загружаю для тебя пикчу\uD83D\uDC68\u200D\uD83D\uDCBB" +
                    "\nОдин момент!");
            sendPicAndDesc(absSender, chat.getId(), new PictureSender(getenv.get("NASA_KEY")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SendPhoto createPhoto(Long chatId, String url) throws IOException {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(chatId.toString());
        photo.setPhoto(new InputFile(url));
        return photo;
    }

    /**
     * Отправка картинки и описания пользователю
     */
    void sendPicAndDesc(AbsSender absSender, Long chatId, PictureSender pictureSender) throws IOException {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(pictureSender.getExplanation());
        try {
            absSender.execute(createPhoto(chatId, pictureSender.getUrl()));
            absSender.execute(message);
        } catch (IOException | RuntimeException | TelegramApiException e) {
            e.printStackTrace();
        }
    }
    }