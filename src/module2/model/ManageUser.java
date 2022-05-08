package module2.model;

import module2.controller.Read_Write;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageUser {
    List<User> userList;
    Read_Write<User> read_write = new Read_Write<>();
    static Scanner sc = new Scanner(System.in);
    final String PATH = "D:\\Work\\CodeGym\\Code\\src\\CaseStudy\\Data\\User.txt";
    public ManageUser(){
        this.userList = new ArrayList<>();
        List<String> list = read_write.readFile(PATH);
        for(String string : list){
            String []a = string.split("-");
            userList.add(new User(a[0], a[1]));
        }
    }
    public User inputUser(){
        User user = new User();
        System.out.println("Input User Name:");
        user.setUserName(sc.nextLine());
        System.out.println("Input Password:");
        user.setUserPassword(sc.nextLine());
        return user;
    }
    public void writeUser(){
        System.out.println(userList);
        if (read_write.wirteFile(PATH, userList)) {
            System.out.println("Add Success!");
        } else {
            System.out.println("Add Failed");
        }
    }
    public void addUser(){
        User user = inputUser();
        userList.add(user);
        writeUser();
    }
    public User findUser(String userName){
        for (User user : userList){
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }
    public boolean login(User user){
        User signIn = findUser(user.getUserName());
        if(signIn == null){
            return false;
        }
        return signIn.getUserPassword().equals(user.getUserPassword());
    }
    public void showUser(String userName){
        User user = findUser(userName);
        System.out.println(user.toString());
    }
}
