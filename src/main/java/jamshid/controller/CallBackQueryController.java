package jamshid.controller;

import jamshid.database.Database;
import jamshid.type.MyMessages;
import jamshid.type.TodoItem;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallBackQueryController {
    private Map<Integer, TodoItem> task = new HashMap<Integer, TodoItem>();


    public MyMessages todoControllers(Update update,  Map<Integer, List<TodoItem>> database, Integer key) {
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
                System.out.println("vbbf");
                send_msg.setText("Vazifa titulini yuboring");
                msg.setTypeMessage("sendmessage");
                msg.setSendMessage(send_msg);

                TodoItem todoItem = new TodoItem();
                todoItem.setId(Integer.toString(update.getCallbackQuery().getMessage().getMessageId()));
                if (todoItem.getType() == null) todoItem.setType("title");
                todoItem.setUserId(update.getCallbackQuery().getMessage().getChatId());
                task.put(update.getCallbackQuery().getMessage().getFrom().getId(), todoItem);
            }
            if (commands[2].equals("list")) {
                List<TodoItem> items = database.get(key);
                StringBuilder s = new StringBuilder();
                send_msg.setParseMode("HTML");
                Integer cnt = 1;
                for (TodoItem item: items) {
                    System.out.println(item.getTitle());
                    System.out.println(item.getContent());
                    s.append("\n<b>").append(String.valueOf(cnt)).append(".Title: </b>").append(item.getTitle()).append("\n<b>Content: </b>").append(item.getContent());
                    cnt++;
                }
                msg.setTypeMessage("sendmessage");
                send_msg.setText(String.valueOf(s));
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
