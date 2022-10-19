package services;
import menus.LoginMenu;
import models.User;
import java.util.ArrayList;
import java.util.Scanner;
import static menus.TaskOpMenu.taskMenu;
import static services.RegisterNewUser.regNewUser;

public class Login {
        public static void userLogin(ArrayList<User> usersList) {
        System.out.print("User Email - > ");
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();
        if (Validators.checkRepeatingUser(email,usersList)) {
            for (int i = 0; i < usersList.size(); i++) { //if user is already registered
                if (email.equalsIgnoreCase(usersList.get(i).getEmail())) {
                    System.out.println(usersList.get(i).getName() + " account was successfully authenticated");
                    taskMenu(usersList.get(i),usersList);
                }
            }
        } else {
            System.out.println("There is NOT any User registered with that email.");
            System.out.println("Please Register NEW User:");
            LoginMenu.menu();
        }
    }
}
