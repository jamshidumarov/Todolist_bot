package jamshid.util;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InlineButtonUtil {
    public static InlineKeyboardButton button(String text, String callbackData) {
        return new InlineKeyboardButton().setText(text).setCallbackData(callbackData);
    }
    public static InlineKeyboardButton button(String text, String callbackData, String emojiURL) {
        if (emojiURL.startsWith("http")){
            return new InlineKeyboardButton().setText(text).setCallbackData(callbackData).setUrl(emojiURL);
        }
        else{
            String emojiText = EmojiParser.parseToUnicode(emojiURL + " " + text);
            return new InlineKeyboardButton().setText(emojiText).setCallbackData(callbackData);
        }
    }

    public static List<InlineKeyboardButton> buttonsRow(InlineKeyboardButton... button) {
        List<InlineKeyboardButton> listButtons = new LinkedList<InlineKeyboardButton>();
        listButtons.addAll(Arrays.asList(button));
        return listButtons;
    }

    public static List<List<InlineKeyboardButton>> rowsCollection(List<InlineKeyboardButton>... rows){
        List<List<InlineKeyboardButton>> rowList = new LinkedList<List<InlineKeyboardButton>>();
        rowList.addAll(Arrays.asList(rows));
        return rowList;
    }

    public static InlineKeyboardMarkup keyboardMarkup(List<List<InlineKeyboardButton>> markup){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(markup);
        return inlineKeyboardMarkup;
    }
}
