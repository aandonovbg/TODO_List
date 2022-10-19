package menus;

import models.User;
import services.Validators;
import services.ReadWriteFile;
import java.util.ArrayList;
import java.util.Scanner;

import static services.Login.userLogin;
import static services.RegisterNewUser.regNewUser;

public class LoginMenu {
    public static void menu() {
        ArrayList<User> usersList=ReadWriteFile.loadListFromFile();
        System.out.println("    -----TODO List-------    ");
        System.out.println("---------MAIN Menu-----------");
        System.out.println("1. Login.");
        System.out.println("2. Register New User.");
        System.out.println("0. EXIT/ SAVE.");
        System.out.print("Your choice - > ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (Validators.validateChoice(choice, 2)) {
            switch (choice) {
                case 0 -> {
                    ReadWriteFile.saveListToFile(usersList);
                    return;
                }
                case 1 -> userLogin(usersList);
                case 2 -> regNewUser();
            }
        }
    }
}
