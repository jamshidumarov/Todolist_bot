package jamshid.controller;

import jamshid.type.MyMessages;
import jamshid.util.InlineButtonUtil;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMembersCount;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.ChatMember;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.LinkedList;
import java.util.List;

public class GeneralController {

    public MyMessages controllerlar(User user, String text, Long chatId, Integer messageId, Integer userID) {
        MyMessages message = new MyMessages();

        SendMessage sendMessage;
        sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("HTML");

        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setParseMode("Markdown");

        if (text.equals("/start")) {

            sendMessage.setText("Assalomu Aleykum " + user.getFirstName() + "! Botga xush kelibsiz!!!" +
                    " \nBotdan to'liq foydalanish uchun kanalimizga obuna bo'ling\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47");

            InlineKeyboardButton channel1 = new InlineButtonUtil().button("Kanalga o'tish", "channel",
                    "https://t.me/yangiqorgon_yangiliklari");
            InlineKeyboardButton tasdiqlash = new InlineButtonUtil().button("Tasdiqlash✅", "tasdiqlash");
            List<InlineKeyboardButton> buttonsRow1 = new InlineButtonUtil().buttonsRow(channel1);
            List<InlineKeyboardButton> buttonsRow2 = new InlineButtonUtil().buttonsRow(tasdiqlash);
            sendMessage.setReplyMarkup(new InlineButtonUtil().keyboardMarkup(new InlineButtonUtil().rowsCollection(buttonsRow1, buttonsRow2)));
            message.setSendMessage(sendMessage);
            message.setTypeMessage("sendmessage");
        }
        if (text.equals("/help")) {
            sendMessage.setText("Sizga qanday yordam bera olaman. Yozib qoldiring \uD83D\uDC49 " +
                    "<a href='https://t.me/jamshidumarov_official'>Jamshid Umarov</a>");
            sendMessage.disableWebPagePreview();
            message.setSendMessage(sendMessage);


            SendVideo sendVideo = new SendVideo();
            sendVideo.setVideo("dwedwd");
            sendVideo.setCaption("Bu videoni ko'rishnni tavsiya qilaman");
            sendVideo.setChatId(chatId);
            message.setSendVideo(sendVideo);
            message.setTypeMessage("message_video");
        }
        if (text.equals("/settings")) {
            sendMessage.setText("Tilni tanlang \uD83D\uDC47");

            InlineKeyboardButton engButton = new InlineButtonUtil().button("Eng\uD83C\uDDFA\uD83C\uDDF8", "eng");
            InlineKeyboardButton uzButton = new InlineButtonUtil().button("Uz\uD83C\uDDFA\uD83C\uDDFF", "uz");
            InlineKeyboardButton ruButton = new InlineButtonUtil().button("Ru\uD83C\uDDF7\uD83C\uDDFA", "ru");

            List<InlineKeyboardButton> language = new InlineButtonUtil().buttonsRow(engButton, uzButton, ruButton);
            List<List<InlineKeyboardButton>> rowsLanguage = new InlineButtonUtil().rowsCollection(language);
            sendMessage.setReplyMarkup(new InlineButtonUtil().keyboardMarkup(rowsLanguage));
            message.setSendMessage(sendMessage);
            message.setTypeMessage("sendmessage");
        }
        if (text.equals("menu")) {
            sendMessage.setText("Asosiy menudasiz");
            InlineKeyboardButton todoList = new InlineButtonUtil().button("Vazifalarni ko'rish", "/todo/list", ":eyes:");
            InlineKeyboardButton addTask = new InlineButtonUtil().button("Vazifa qo'shish", "/todo/createNew",":heavy_plus_sign:");

            List<InlineKeyboardButton> taskList = new InlineButtonUtil().buttonsRow(todoList);
            List<InlineKeyboardButton> addTodo = new InlineButtonUtil().buttonsRow(addTask);
            List<List<InlineKeyboardButton>> rowsLanguage = new InlineButtonUtil().rowsCollection(taskList, addTodo);
            sendMessage.setReplyMarkup(new InlineButtonUtil().keyboardMarkup(rowsLanguage));
            message.setSendMessage(sendMessage);
            message.setTypeMessage("sendmessage");
            message.setSendMessage(sendMessage);
            message.setTypeMessage("sendmessage");
        }
        if (text.equals("tasdiqlash")) {
            editMessageText.setText("Assalomu Aleykum " + user.getFirstName() + "! Botga xush kelibsiz!!!" +
                    " \nBotdan to'liq foydalanish uchun kanalimizga obuna bo'ling\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47");

            InlineKeyboardButton channel1 = new InlineButtonUtil().button("Kanalga o'tish", "channel",
                    "https://t.me/yangiqorgon_yangiliklari");
            InlineKeyboardButton tasdiqlash = new InlineButtonUtil().button("Menu", "menu", ":clipboard:");
            List<InlineKeyboardButton> buttonsRow1 = new InlineButtonUtil().buttonsRow(channel1);
            List<InlineKeyboardButton> buttonsRow2 = new InlineButtonUtil().buttonsRow(tasdiqlash);
            editMessageText.setReplyMarkup(new InlineButtonUtil().keyboardMarkup(new InlineButtonUtil().rowsCollection(buttonsRow1, buttonsRow2)));


            message.setEditMessageText(editMessageText);
            message.setTypeMessage("editmessage");
        }
        return message;
    }
}
