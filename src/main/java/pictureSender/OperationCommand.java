package pictureSender;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pictureSender.GetPicture;
import utils.Utils;

import java.io.IOException;
import java.net.URL;

/**
 * Суперкласс для сервисных команд
 */
abstract class OperationCommand extends BotCommand {

    OperationCommand(String identifier, String description) {
        super(identifier, description);
    }

    private SendPhoto createPhoto(Long chatId, String url) throws IOException {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(chatId.toString());
        photo.setPhoto(new InputFile(url));
        return photo;
    }

    /**
     * Отправка ответа пользователю
     */
    void sendPicAndDesc(AbsSender absSender, Long chatId, String userName) throws IOException {
        GetPicture getPicture = new GetPicture();
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(getPicture.getExplanation());
        try {
            absSender.execute(createPhoto(chatId, getPicture.getUrl()));
            absSender.execute(message);
        } catch (IOException | RuntimeException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    void sendAnswer(AbsSender absSender, Long chatId, String userName, String text) throws IOException {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            absSender.execute(message);
        } catch (RuntimeException | TelegramApiException e) {
            e.printStackTrace();
        }
    }
}