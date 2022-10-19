package services;

import models.Task;
import models.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Validators {
    public static boolean checkRepeatingUser(String email,ArrayList<User> usersList) {
        boolean doesExist=false;
        for (int i = 0; i < usersList.size(); i++) {
            if (email.equalsIgnoreCase(usersList.get(i).getEmail())){
                doesExist=true;
            }
        }
        return doesExist;
    }
    public static boolean validateChoice(int choice, int end) {
        boolean validateChoice = true;
        try {
            if (choice < 0 || choice > end) {
                System.out.println("Choice range can be from 0 to " + end + " including!");
                validateChoice = false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice! Enter digit from 0 to " + end + " including!");
            validateChoice = false;
        }
        return validateChoice;
    }
    public static int validatePriority(String priority) {
        Scanner sc = new Scanner(System.in);
        try {
            if (Integer.parseInt(priority) < 1 || Integer.parseInt(priority) > 5) {
                System.out.println("Priority range can be from 1 to 5 including!");

                System.out.print("Enter Task Priority - > ");
                priority = sc.nextLine();
                validatePriority(priority);
            }
        }catch (NumberFormatException e) {
            System.out.println("Invalid choice! Enter digit from 1 to 5 including!");
            System.out.print("Enter Task Priority - > ");
            priority = sc.nextLine();
            validatePriority(priority);
        }
        return Integer.parseInt(priority);
    }
    public static int validateID(String id, User user, ArrayList<User>usersList) {
        Scanner sc = new Scanner(System.in);
        try {
            if (Integer.parseInt(id) < 1 || Integer.parseInt(id) > user.getTasks().size()) {
                System.out.println("ID range can be from 1 to "+user.getTasks().size()+" including!");

                System.out.print("Enter Task new ID - > ");
                id = sc.nextLine();
                validateID(id,user,usersList);
            }
        }catch (NumberFormatException e) {
            System.out.println("Invalid choice! Enter digit from 1 "+user.getTasks().size()+" including!");
            System.out.print("Enter Task new ID - > ");
            id = sc.nextLine();
            validateID(id,user,usersList);
        }
        return Integer.parseInt(id);
    }
}
