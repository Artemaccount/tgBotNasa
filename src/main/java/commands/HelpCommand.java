package commands;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import utils.Utils;

/**
 * Команда "Помощь"
 */
public class HelpCommand extends ServiceCommand {

    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Я умею отправлять фото и описание к нему с сайта NASA\uD83C\uDF0C" +
                        "\n Картинка обновляется раз в день" +
                        "\n /pic - получить картинку и описание\uD83D\uDE42" +
                        "\n описание на английском, " +
                        "потому что я пока не умею переводить текст\uD83E\uDD37\u200D♂️");
    }
}