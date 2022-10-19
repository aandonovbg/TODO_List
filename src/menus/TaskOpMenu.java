package menus;

import models.User;
import services.ReadWriteFile;
import services.TaskServices;
import services.Validators;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskOpMenu {
    public static void taskMenu(User user, ArrayList<User> usersList) {
        System.out.println("---------Task Operations-----------");
        System.out.println("1. Add Task.");
        System.out.println("2. Delete Task.");
        System.out.println("3. Edit Task.");
        System.out.println("4. Check if the List is empty.");
        System.out.println("5. Change Task order.");
        System.out.println("0. < - BACK.");
        System.out.print("Your choice - > ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (Validators.validateChoice(choice, 5)) {
            switch (choice) {
                case 0 -> {
                    ReadWriteFile.saveListToFile(usersList);
                    LoginMenu.menu();
                }
                case 1 -> TaskServices.addTask(user,usersList);
                case 2 -> TaskServices.deleteTask(user,usersList);
                case 3 -> TaskServices.editTask(user,usersList);
                case 4 -> TaskServices.checkTaskListIsEmpty(user,usersList);
                case 5 -> TaskServices.changeTaskOrder(user,usersList);

            }
        }
    }
}
