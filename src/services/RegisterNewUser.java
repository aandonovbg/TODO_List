package services;

import menus.LoginMenu;
import menus.TaskOpMenu;
import models.Task;
import models.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RegisterNewUser {
    public static void regNewUser() {
        ArrayList<User> usersList = ReadWriteFile.loadListFromFile();
        Scanner sc = new Scanner(System.in);
        System.out.print("User Name - > ");
        String name = sc.nextLine();
        System.out.print("User Email - > ");
        String email = sc.nextLine();

        if (usersList.size() == 0) {
            User newUser = new User(name, email);
            System.out.println(name + " is successfully registered!");
            TaskOpMenu.taskMenu(newUser, usersList);
        }else if (Validators.checkRepeatingUser(email,usersList)){
            System.out.println("User with email - > "+email+" is already registered");
            LoginMenu.menu();
        }

    }
}
