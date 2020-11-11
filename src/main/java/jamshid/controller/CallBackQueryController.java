package jamshid.controller;

import jamshid.type.MyMessages;
import jamshid.type.TodoItem;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public class CallBackQueryController {
    private Map<Integer, TodoItem> task = new HashMap<Integer, TodoItem>();

    public MyMessages todoControllers(Update update) {
        MyMessages msg = new MyMessages();

        SendMessage send_msg = new SendMessage();
        send_msg.setParseMode("Markdown");

        EditMessageText edited_msg = new EditMessageText();

        edited_msg.setChatId(update.getCallbackQuery().getMessage().getChatId());
        edited_msg.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        send_msg.setChatId(update.getCallbackQuery().getMessage().getChatId());
        if (update.getCallbackQuery().getData().startsWith("/todo/")){
            String[] commands = update.getCallbackQuery().getData().split("/");

            if (commands[2].equals("createNew")) {
                System.out.println(commands[2]);
                TodoItem todoItem = new TodoItem();
                todoItem.setId(Integer.toString(update.getCallbackQuery().getMessage().getMessageId()));
//                todoItem.setUserId(update.getCallbackQuery().getMessage().getChatId());
                task.put(update.getCallbackQuery().getMessage().getFrom().getId(), todoItem);
                System.out.println("salomlar");
                send_msg.setText("Vazifa titulini yuboring");
                msg.setTypeMessage("sendmessage");
                msg.setSendMessage(send_msg);
            }
            if (commands[2].equals("list")) {
                send_msg.setText("blablabla");
                msg.setTypeMessage("sendmessage");
                msg.setSendMessage(send_msg);
            }
        }
        return msg;
    }

    public Map<Integer, TodoItem> getTask() {
        return task;
    }

    public void setTask(Map<Integer, TodoItem> task) {
        this.task = task;
    }
}
