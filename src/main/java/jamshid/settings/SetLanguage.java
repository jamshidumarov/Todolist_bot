package jamshid.settings;

import jamshid.type.MyMessages;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class SetLanguage {


    public MyMessages setlanguage(String language, Integer messageId, Long chatId) {
        MyMessages myMessages = new MyMessages();
        myMessages.setTypeMessage("editmessage");
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setParseMode("Markdown");

        if (language.equals("eng")) editMessageText.setText("Eng\uD83C\uDDFA\uD83C\uDDF8 is selected");
        if (language.equals("uz")) editMessageText.setText("Uz\uD83C\uDDFA\uD83C\uDDFF tanlandi");
        if (language.equals("ru")) editMessageText.setText("Ru\uD83C\uDDF7\uD83C\uDDFA выбрано");

        myMessages.setEditMessageText(editMessageText);
        return myMessages;
    }
}
