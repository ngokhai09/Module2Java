package module2.controller;

import module2.model.ManageStaff;
import module2.model.ManageUser;
import module2.model.Staff;
import module2.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    static ManageStaff manageStaff = new ManageStaff();
    static ManageUser manageUser = new ManageUser();
    static User user = new User();

    public void printMenu() {
        String menu = """
                -----------Employee Management----------
                1. Add Staff
                2. Find Staff By Name
                3. Check Staff's Status
                4. Edit Staff
                5. Change Staff's Status
                6. Account Infomation
                7. Log Out
                8. Exit
                ----------------------------------------
                """;
        System.out.println(menu);
    }

    public void userMenu() {
        boolean isLogin = false;
        String menu = """
                -----------User Management----------
                1. Login
                2. Register
                ----------------------------------------
                """;

        int n = 0;

        while (!isLogin) {
            do {
                System.out.println(menu);
                try {
                    System.out.println("Enter function");
                    n = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error Format");
                }
            } while (1 > n || 2 < n);
            switch (n) {
                case 1:
                    System.out.println("Input User Name:");
                    user.setUserName(sc.nextLine());
                    System.out.println("Input Password:");
                    user.setUserPassword(sc.nextLine());
                    if (manageUser.login(user)) {
                        isLogin = true;
                    } else user = null;
                    break;
                case 2:
                    manageUser.addUser();
                    break;
            }
        }
        if (user != null) {
            selectMenu();
        }
    }

    public void selectMenu() {
        int n = 0;
        String name;
        String id;
        while (n != 8) {
            do {
                printMenu();
                try {
                    System.out.println("Please Enter function!\n");
                    n = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error Format");
                    continue;
                }
                if (n < 1 || n > 9) {
                    System.out.println("You must type function from 1 to 7");
                }
            } while (1 > n || 8 < n);
            switch (n) {
                case 1:
                    manageStaff.addStaff();
                    break;
                case 2:
                    System.out.println("Input Staff's name:");
                    name = sc.nextLine();
                    List<Staff> staffList = manageStaff.searchStaff(name);
                    if (staffList.size() == 0) {
                        System.out.println("No Staff Name: " + name);
                    } else {
                        System.out.println("List Staff Name " + name + " : ");
                        for (Staff staff : staffList) {
                            System.out.println(staff.toString());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Input Staff's name: ");
                    name = sc.nextLine();
                    String res = manageStaff.checkStaffByName(name);
                    if (Objects.equals(res, "")) {
                        System.out.println("No Staff Name " + name);
                    } else {
                        System.out.println("Staff " + name + " is " + res);
                    }

                    break;
                case 4:
                    System.out.println("Input id: ");
                    id = sc.nextLine();
                    int pos = manageStaff.findStaff(id);
                    if (pos > 0) {
                        Staff staff = new Staff();
                        while (true) {
                            System.out.print("Fullname: ");
                            staff.setFullName(sc.nextLine());
                            System.out.print("Address: ");
                            staff.setHomeAddress(sc.nextLine());
                            System.out.print("Phone Number: ");
                            staff.setPhoneNumber(sc.nextLine());
                            System.out.print("Year of Birth: ");
                            staff.setYearOfBirth(sc.nextLine());
                            while (true) {
                                System.out.print("Staff's Status(true/false): ");
                                String status = sc.nextLine();
                                if (Objects.equals(status, "true") || Objects.equals(status, "false")) {
                                    staff.setWork(status.equals("true"));
                                    break;
                                } else {
                                    System.out.println("Please enter Status true or false");
                                }
                            }
                            if (manageStaff.validateInput(staff.getFullName(), staff.getHomeAddress(), staff.getPhoneNumber(), staff.getYearOfBirth())) {
                                break;
                            }
                        }
                        manageStaff.editStaff(pos, staff);
                    } else {
                        System.out.println("No Staff");
                    }

                    break;
                case 5:
                    System.out.println("Input  Id:");
                    id = sc.nextLine();
                    manageStaff.editStaffStatus(id);
                    break;
                case 6:
                    manageUser.showUser(user.getUserName());
                    break;
                case 7:
                    user = null;
                    userMenu();
                    break;
            }
            System.out.println("Press ENTER to continue");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
