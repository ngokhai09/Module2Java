package module2.model;

import module2.controller.Read_Write;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ManageStaff {
    List<Staff> staffList;
    Read_Write<Staff> read_write = new Read_Write();
    static Scanner sc = new Scanner(System.in);
    final String PATH = "D:\\Work\\CodeGym\\Code\\src\\CaseStudy\\Data\\Employee.txt";

    public ManageStaff() {
        this.staffList = new ArrayList<>();
        List<String> list = read_write.readFile(PATH);
        for (String string : list){
            String[]a = string.split("-");
            staffList.add(new Staff(a[0], a[1], a[2], a[3], a[4], a[5].equals("Working")));
        }
    }

    public void printStaff() {
        if (staffList.size() == 0) {
            System.out.println("No Staff");
            return;
        }
        for (Staff staff : staffList) {
            System.out.println(staff);
        }
    }

    public void writeStaff() {
        if (read_write.wirteFile(PATH, staffList)) {
            System.out.println("Add Success!");
        } else {
            System.out.println("Add Failed");
        }
    }
    public boolean validateInput(String fullName, String homeAddress, String phoneNumber, String yearOfBirth){
        boolean ok = true;
        if (fullName.equals("")) {
            System.out.println("You must  enter Fullname");
            ok = false;
        }
        if (homeAddress.equals("")) {
            System.out.println("You must enter Home Address");
            ok = false;
        }
        if (phoneNumber.equals("")) {
            System.out.println("You must enter Phone Number");
            ok = false;
        }
        if (yearOfBirth.equals("")) {
            System.out.println("You must enter Year of Birth");
            ok = false;
        }
        return ok;
    }
    public Staff inputStaff() {
        Staff staff = new Staff();
        while (true) {
            int ok = 1;
            System.out.print("Id: ");
            staff.setId(sc.nextLine());
            for (Staff staff1 : staffList) {
                if (Objects.equals(staff1.getId(), staff.getId())) {
                    System.out.println("Id exists");
                    ok = 0;
                    break;
                }
            }
            if (ok == 1) {
                break;
            }
        }
        while (true) {
            int ok = 1;
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
                }
                else{
                    System.out.println("Please enter Status true or false");
                }
            }
            if(validateInput(staff.getFullName(), staff.getHomeAddress(), staff.getPhoneNumber(), staff.getYearOfBirth())){
                break;
            }
        }
        return staff;
    }

    public void addStaff() {
        Staff staff = inputStaff();
        staffList.add(staff);
        writeStaff();
    }

    public int findStaff(String id) {
        int pos = -1;
        int len = staffList.size();
        for (int i = 0; i < len; i++) {
            Staff st = staffList.get(i);
            if (Objects.equals(st.getId(), id)) {
                pos = staffList.indexOf(st);
                break;
            }
        }
        return pos;
    }

    public List<Staff> searchStaff(String name) {
        List<Staff> search = new ArrayList<>();
        for (Staff staff : staffList) {
            if (staff.getFullName().contains(name)) {
                search.add(staff);
            }
        }
        return search;
    }

    public void editStaff(int pos, Staff staff) {
        staffList.set(pos, staff);
        if (read_write.wirteFile("Employee.txt", staffList)) {
            System.out.println("Update Success!");
        } else {
            System.out.println("Update failed");
        }
    }

    public void deleteStaff(String id) throws IOException {
        int pos = 0;
        int len = staffList.size();
        for (int i = 0; i < len; i++) {
            Staff st = staffList.get(i);
            if (Objects.equals(st.getId(), id)) {
                pos = staffList.indexOf(st);
                break;
            }
        }
        if (pos > 0) {
            staffList.remove(pos);
            if (read_write.wirteFile("Employee.txt", staffList)) {
                System.out.println("Delete Success!");
            } else {
                System.out.println("Delete Failed");
            }
        } else {
            System.out.println("No Staff!");
        }
    }

    public String checkStaffByName(String name) {
        for (Staff staff : staffList) {
            if (staff.getFullName().equals(name)) {
                return staff.isWork() ? "Working" : "Not Working";
            }
        }
        return "";
    }

    public void editStaffStatus(String id) {
        Staff st = null;
        int len = staffList.size();
        for (int i = 0; i < len; i++) {
            if (Objects.equals(staffList.get(i).getId(), id)) {
                st = staffList.get(i);
                break;
            }
        }
        if (st != null) {
            if (st.isWork()) {
                st.setWork(false);
                System.out.println(st.getFullName() + " is Not Working");
            } else {
                st.setWork(true);
                System.out.println(st.getFullName() + " is Working");
            }
            if (read_write.wirteFile("Employee.txt", staffList)) {
                System.out.println("Update Success!");

            } else {
                System.out.println("Update failed");
            }
        } else {
            System.out.println("No Staff!");
        }
    }
}
