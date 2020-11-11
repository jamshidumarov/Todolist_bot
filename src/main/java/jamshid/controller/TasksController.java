package jamshid.controller;

import jamshid.type.MyMessages;
import jamshid.type.TodoItem;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

public class TasksController {

    public MyMessages controlTask(Update update, Map<Integer, TodoItem> task, int key){
        MyMessages my_msg = new MyMessages();
        SendMessage send_msg = new SendMessage();
        send_msg.setChatId(update.getMessage().getChatId());
        send_msg.setParseMode("Markdown");

        if(task.containsKey(key)){
            System.out.println("yed shekilli");
            send_msg.setText("*Title*: " + update.getMessage().getText() + "\nYaxshi! Endi vazifa kontentini yuboring:");
            my_msg.setSendMessage(send_msg);
            my_msg.setTypeMessage("sendmessage");
        } else System.out.println("yemadi");
        return my_msg;
    }
}
