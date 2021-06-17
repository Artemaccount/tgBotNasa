package pictureSender;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import utils.Utils;

import java.io.IOException;

/**
 * Команда "Старт"
 */
public class PicCommand extends OperationCommand {

    public PicCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);
        try {
            sendAnswer(absSender, chat.getId(), userName, "Загружаю для тебя пикчу\uD83D\uDC68\u200D\uD83D\uDCBB" +
                    "\nОдин момент!");
            sendPicAndDesc(absSender, chat.getId(), userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }