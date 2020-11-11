package jamshid.database;

import jamshid.type.TodoItem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Database {
    private Map<Long, List<TodoItem>> todoList = new HashMap<Long, List<TodoItem>>();

public int addTask(Long userID, TodoItem todoItem){
    if (todoList.containsKey(userID)){
        todoList.get(userID).add(todoItem);
        return todoList.get(userID).size();
    }else{
        List<TodoItem> newList = new LinkedList<TodoItem>();
        newList.add(todoItem);
        todoList.put(userID, newList);
        return 1;
    }
}

    public Map<Long, List<TodoItem>> getTodoList() {
        return todoList;
    }

    public void setTodoList(Map<Long, List<TodoItem>> todoList) {
        this.todoList = todoList;
    }
}
