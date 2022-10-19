package services;

import models.User;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteFile {
    public static void saveListToFile(ArrayList<User> usersList) {
        try {
            FileOutputStream fileOut=new FileOutputStream("ProgramFiles/UsersList.txt");
            ObjectOutputStream objectOut=new ObjectOutputStream(fileOut);
            objectOut.writeObject(usersList);
            objectOut.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static ArrayList<User> loadListFromFile(){
        ArrayList<User> usersList=new ArrayList<>();
        try {
            FileInputStream fi = new FileInputStream("ProgramFiles/UsersList.txt");
            ObjectInputStream reader=new ObjectInputStream(fi);
            usersList= (ArrayList<User>) reader.readObject();
            reader.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }
}