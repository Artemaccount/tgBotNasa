package pictureSender;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;

/**
 * Суперкласс для сервисных команд
 */
abstract class OperationCommand extends BotCommand {

    OperationCommand(String identifier, String description) {
        super(identifier, description);
    }

    void sendAnswer(AbsSender absSender, Long chatId, String text) throws IOException {
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