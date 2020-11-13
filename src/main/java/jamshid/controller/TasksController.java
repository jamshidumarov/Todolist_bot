package jamshid.controller;

import jamshid.type.MyMessages;
import jamshid.type.TodoItem;
import jamshid.util.InlineButtonUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.Map;

public class TasksController {

    public MyMessages controlTask(Update update, Map<Integer, TodoItem> task, int key){
        MyMessages my_msg = new MyMessages();
        SendMessage send_msg = new SendMessage();
        send_msg.setChatId(update.getMessage().getChatId());
        send_msg.setParseMode("Markdown");

        if(task.containsKey(key)){
           if (task.get(key).getType().equals("title")){
               task.get(key).setTitle(update.getMessage().getText());
               send_msg.setText("*Title*: " + task.get(key).getTitle() + "\nYaxshi! Endi vazifa kontentini yuboring:");
               my_msg.setTypeMessage("sendmessage");
               task.get(key).setType("content");
               my_msg.setSendMessage(send_msg);
           }
           else if (task.get(key).getType().equals("content")){
               task.get(key).setContent(update.getMessage().getText());
               send_msg.setText("Vazifa muvaffaqiyatli qo'shildi"+"\n*Title*: " + task.get(key).getTitle() +"\n*Content*: "+task.get(key).getContent());
               InlineKeyboardButton menu = new InlineButtonUtil().button("Menu", "menu", ":clipboard:");
               List<InlineKeyboardButton> buttonsRow2 = new InlineButtonUtil().buttonsRow(menu);
               send_msg.setReplyMarkup(new InlineButtonUtil().keyboardMarkup(new InlineButtonUtil().rowsCollection(buttonsRow2)));
               my_msg.setSendMessage(send_msg);
               task.get(key).setType("addToDatabase");
               my_msg.setTypeMessage("sendmessage");
               my_msg.setListTodo(task.get(key));
           }
        } else System.out.println("yemadi");
        return my_msg;
    }
}
