package menus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import models.User;
import services.Validators;

import static services.RegisterNewUser.regNewUser;

public class StartMenu {
    public static void menu() {
        ArrayList<User>usersList=new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("ProgramFiles/UsersList.txt"));
            if (br.readLine() == null) {
                System.out.println("File \"ProgramFiles/UsersList.txt\" is empty.");
                System.out.println("1. Register NEW user.");
                System.out.println("0. EXIT.");
                System.out.print("Your choice - > ");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                Validators.validateChoice(choice, 1);
                switch (choice) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> regNewUser();
                }
            } else LoginMenu.menu();
        } catch (FileNotFoundException e) {
            System.out.println("File \"ProgramFiles/UsersList.txt\" NOT found!!!");
        } catch (IOException e) {
            System.out.println("Unable to read \"ProgramFiles/UsersList.txt\".");
        }
    }

}
