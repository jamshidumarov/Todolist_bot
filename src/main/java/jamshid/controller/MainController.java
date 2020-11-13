package jamshid.controller;

import jamshid.service.GetFileInfo;
import jamshid.settings.SetLanguage;
import jamshid.type.MyMessages;
import jamshid.type.TodoItem;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MainController extends TelegramLongPollingBot {

    CallBackQueryController todoController = new CallBackQueryController();
    Map<Integer, List<TodoItem>> database = new LinkedHashMap<Integer, List<TodoItem>>();
    List<TodoItem> listItems = new LinkedList<TodoItem>();
    int key = 0;


    public String getBotUsername() {
        return "todol1st_bot";
    }

    public String getBotToken() {
        return "1470958658:AAEHGwOE6h5yU9BR-XVGZWS2o4INGDU6ejE";
    }

    public void onUpdateReceived(Update update) {
        MyMessages mymessages = new MyMessages();
        Message message = update.getMessage();
        System.out.println(key);


        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String callbackdata = callbackQuery.getData();

            String language;
            if (callbackdata.equals("eng") || callbackdata.equals("ru") || callbackdata.equals("uz")) {
                language = callbackdata;
                mymessages = new SetLanguage().setlanguage(language,
                        callbackQuery.getMessage().getMessageId(),
                        callbackQuery.getMessage().getChatId());
            } else if (callbackdata.equals("/todo/list") || callbackdata.equals("/todo/createNew")) {
                if (callbackdata.endsWith("createNew")) {
                    key = callbackQuery.getMessage().getFrom().getId();
                }
                    mymessages = todoController.todoControllers(update, database, key);
            } else {
                mymessages = new GeneralController().controllerlar(callbackQuery.getFrom(),
                        callbackdata,
                        callbackQuery.getMessage().getChatId(),
                        callbackQuery.getMessage().getMessageId(),
                        callbackQuery.getFrom().getId());
            }
        }
        else {
            String text = message.getText();

            if (text != null) {

                if (text.equals("/settings") || text.equals("/help") || text.equals("/start")) {
                    mymessages = new GeneralController().controllerlar(update.getMessage().getFrom(),
                            text,
                            message.getChatId(),
                            message.getMessageId(),
                            message.getFrom().getId());
                }else if (todoController.getTask().containsKey(key)) {
                    if (todoController.getTask().get(key).getType() == null) todoController.getTask().get(key).setType("title");
                    mymessages = new TasksController().controlTask(update, todoController.getTask(), key);
                    if (database.get(key) != null && mymessages.getListTodo() != null){

                        database.get(key).add(mymessages.getListTodo());
//                        System.out.println("ifeni ichi database: " + database.toString());
                    }
                    else if(mymessages.getListTodo() != null) {

                        listItems.add(mymessages.getListTodo());
                        database.put(key, listItems);
//                        System.out.println("elseni ichi database: " + database.toString());
                    }
                }
            } else {
                GetFileInfo getFileInfo = new GetFileInfo();
                mymessages = getFileInfo.fileInfo(update);
            }
        }

        try {
            if (mymessages.getTypeMessage().equals("editmessage")) {
                execute(mymessages.getEditMessageText());
            }
            if (mymessages.getTypeMessage().equals("sendmessage")) {
                execute(mymessages.getSendMessage());
            }
            if (mymessages.getTypeMessage().equals("message_video")) {
                execute(mymessages.getSendMessage());
                execute(mymessages.getSendVideo());
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
