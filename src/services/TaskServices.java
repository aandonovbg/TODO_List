package services;

import menus.TaskOpMenu;
import models.Task;
import models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static services.Validators.validateID;
import static services.Validators.validatePriority;

public class TaskServices {
    public static void addTask(User user, ArrayList<User> userList) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Task Title - > ");
        String title = sc.nextLine();
        System.out.print("Enter Task Description - > ");
        String description = sc.nextLine();
        System.out.print("Enter Task Priority - > ");
        String choice = sc.nextLine();

        int priority = validatePriority(choice);
        if (userList.size() == 0){
            user.getTasks().add(new Task(title, description, priority));
            System.out.println("Task successfully added!\n");
            userList.add(user);
            TaskOpMenu.taskMenu(user, userList);
        }else {
            for (int i = 0; i < userList.size(); i++) {
                if (user.getEmail().equalsIgnoreCase(userList.get(i).getEmail())) {
                    userList.get(i).getTasks().add(new Task(title, description, priority));
                    System.out.println("Task successfully added!\n");
                    TaskOpMenu.taskMenu(user, userList);
                } else {
                    user.getTasks().add(new Task(title, description, priority));
                    System.out.println("Task successfully added!\n");
                    userList.add(user);
                    TaskOpMenu.taskMenu(user, userList);
                }
            }
        }
    }

    public static void showTasks(User user) {
        System.out.println("Tasks details: \n");
        for (int i = 0; i < user.getTasks().size(); i++) {
            System.out.println("ID - > " + (i + 1));
            System.out.println("Title - > " + user.getTasks().get(i).getTitle());
            System.out.println("Description - > " + user.getTasks().get(i).getDescription());
            System.out.println("Priority - > " + user.getTasks().get(i).getPriority());
            System.out.println("================================================================");
        }
    }

    public static void deleteTask(User user, ArrayList<User> userList) {
        ArrayList<Task> tempTasks = new ArrayList<Task>();

        showTasks(user);
        System.out.print("Choose a task for deletion - > ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        try {
            if (choice < 1 || choice > user.getTasks().size()) {
                System.out.println("Choice out of range(1-" + user.getTasks().size() + ")!");
                deleteTask(user, userList);
            }
        } catch (NumberFormatException e) {
            System.out.println("Write a digit from 1 to " + user.getTasks().size() + " including!");
            deleteTask(user, userList);
        }

        for (int i = 0; i < user.getTasks().size(); i++) {
            if (choice == i + 1) {
                for (int j = 0; j < user.getTasks().size(); j++) {
                    if (j != i) {
                        tempTasks.add(user.getTasks().get(j));
                    }
                }
            }
            System.out.println("Task number " + (i + 1) + " was successfully deleted");
        }
        user.setTasks(tempTasks);
        showTasks(user);
        System.out.println();

        TaskOpMenu.taskMenu(user, userList);
    }

    public static void editTask(User user, ArrayList<User> userList) {
        showTasks(user);
        System.out.print("Choose ID number of a task to edit - > ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        try {
            if (choice < 1 || choice > user.getTasks().size()) {
                System.out.println("Choice out of range(1-" + user.getTasks().size() + ")!");
                editTask(user, userList);
            }
        } catch (NumberFormatException e) {
            System.out.println("Write a digit from 1 to " + user.getTasks().size() + " including!");
            editTask(user, userList);
        }

        chooseToEdit(user, choice, userList);
    }

    public static void chooseToEdit(User user, int choice, ArrayList<User> userList) {
        for (int i = 1; i <= user.getTasks().size(); i++) {
            if (choice == i) {
                System.out.println("Choose what to edit:");
                System.out.print("1. Title - > " + user.getTasks().get(i - 1).getTitle());
                System.out.print("\n2. Description - > " + user.getTasks().get(i - 1).getDescription());
                System.out.print("\n3. Priority - > " + user.getTasks().get(i - 1).getPriority());
                System.out.println("\n0. < - BACK.");
                System.out.print("Your choice - > ");
                Scanner sc = new Scanner(System.in);
                int choice1 = sc.nextInt();
                try {
                    if (choice1 < 0 || choice1 > 3) {
                        System.out.println("Choice out of range(0-3)!");
                        editTask(user, userList);
                    }
                } catch (Exception e) {
                    System.out.println("Write a digit from 0 to 3 including!");
                    editTask(user, userList);
                }
                switch (choice1) {
                    case 0 -> TaskOpMenu.taskMenu(user, userList);
                    case 1 -> {
                        System.out.print("Type in new Title - > ");
                        sc.nextLine();
                        String title = sc.nextLine();
                        user.getTasks().get(i - 1).setTitle(title);
                        System.out.println("Title successfully edited.");

                        chooseToEdit(user, choice, userList);
                    }
                    case 2 -> {
                        System.out.print("Type in new Description - > ");
                        sc.nextLine();
                        user.getTasks().get(i - 1).setDescription(sc.nextLine());
                        System.out.println("Description successfully edited.");
                        chooseToEdit(user, choice, userList);
                    }
                    case 3 -> {
                        System.out.print("Type in new Priority - > ");
                        sc.nextLine();
                        int priority = validatePriority(sc.nextLine());
                        user.getTasks().get(i - 1).setPriority(priority);
                        System.out.println("Priority successfully edited.");
                        chooseToEdit(user, choice, userList);
                    }
                }
            }
        }
    }

    public static void checkTaskListIsEmpty(User user, ArrayList<User> userList) {
        if (userList.size() == 0) {
            System.out.println("TaskList is empty");
        } else {
            System.out.println("TaskList is NOT empty");
        }
        TaskOpMenu.taskMenu(user, userList);
    }

    public static void changeTaskOrder(User user, ArrayList<User> userList) {
        ArrayList<Task> tempTasks = new ArrayList<Task>();
        if (userList.size() == 0) {
            System.out.println("TaskList is empty");
        } else {
            showTasks(user);
            System.out.print("Choose ID number of the task you want to move - > ");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            try {
                if (choice < 1 || choice > user.getTasks().size()) {
                    System.out.println("Choice out of range(1-" + user.getTasks().size() + ")!");
                    changeTaskOrder(user, userList);
                }
            } catch (NumberFormatException e) {
                System.out.println("Write a digit from 1 to " + user.getTasks().size() + " including!");
                changeTaskOrder(user, userList);
            }
            for (int i = 0; i < user.getTasks().size(); i++) {
                if (choice == i + 1) {
                    System.out.println("Old ID of the task - > " + (i + 1));
                    System.out.print("Specify new ID for the task - > ");
                    sc.nextLine();
                    int newID = validateID(sc.nextLine(), user, userList);
                    Collections.swap(user.getTasks(),i,newID-1);
                }
            }
        }

        showTasks(user);
        System.out.println();
        System.out.println("Press enter to continue");
        try{
            System.in.read();
        } catch(Exception e){}
        TaskOpMenu.taskMenu(user, userList);
    }
}
