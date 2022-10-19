package models;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private ArrayList<Task> tasks;
    private String name, email;


    public User(String name, String email,ArrayList<Task> tasks) {
        this.name = name;
        this.email = email;
        this.tasks = tasks;
    }
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    @Override
    public String toString() {
        return "User info" +
                "\nUser Name - > " + name +
                "\nEmail - > " + email +
                "\nTasks details:" + tasks;
    }


}
