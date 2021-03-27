package resourcebooking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ResourceBooking {

    public static Scanner input = new Scanner(System.in);
    
    public static ArrayList<Catering> cateringRequests = new ArrayList<>();

    public static void main(String[] args) {
        cateringRequests = Catering.readFile();
        view();
        currentTime();
        mainMenu();
        //do user login
        //make a 2d array to book slots, use the grid to represent times, 5 time slots per day, 5 different rooms
        //in 2d array, put an x when a time slot is booked.
        //Or a morning and afternoon slot that last 3 hours and refreshments can be delivered every hour on the hour.

    }

    public static void mainMenu() {
        while (true) {
            try {
                System.out.println("\nChoose an option:");
                System.out.println("1 - Book a room");
                System.out.println("2 - Request refeshments");
                System.out.println("3 - Request equiptment");
                System.out.println("4 - View catering requests");
                System.out.println("5 - view equiptment requests");
                System.out.println("0 - Exit");
                int menuChoice = input.nextInt();
                switch (menuChoice) {
                    case 1:
                        roomBook();
                        break;
                    case 2:
                        reqRefreshments();
                        break;
                    case 3:
                        reqEquiptment();
                        break;
                    case 4:
                        viewCatering();
                        break;
                    case 5:
                        viewEquiptment();
                        break;
                    case 0:
//                        Catering.writeFile(cateringRequests);
                        System.exit(menuChoice);
                }

            } catch (Exception e) {
                System.out.println("Error occured: " + e);
                input.nextLine();

            }
        }

    }

    public static void roomBook() {
        // enter number of poeple in your meeting room and choose the room based on how may poeple fit. eg. a room with 3 poeple can go in rooms 2 - 5.
        // put x in 2d array to signify room taken.
    }

    public static void reqRefreshments() {
        int roomNumber = roomVerif();
        String email = emailVerif();

        System.out.println("Would you like tea?");
        input.nextLine();
        String tea = input.nextLine();

        System.out.println("Would you like coffee?");
        String coffee = input.nextLine();

        System.out.println("Would you like water?");
        String water = input.nextLine();

        System.out.println("Would you like pastries?");
        String pastry = input.nextLine();

        System.out.println("Would you like sandwiches?");
        String sandwich = input.nextLine();

        String requestTime = currentTime();

        Catering newRequest = new Catering(roomNumber, email, tea, coffee, water, pastry, sandwich, requestTime);
        cateringRequests.add(newRequest);
        Catering.writeFile(cateringRequests);
        
        System.out.println(cateringRequests);
        

    }

    public static void reqEquiptment() {

    }

    public static void viewCatering() {
        for (int i = 0; i < cateringRequests.size(); i++) {
            System.out.println(cateringRequests);
            System.out.println(cateringRequests.get(i).toString());            
        }
    }

    public static void viewEquiptment() {

    }

    public static String emailVerif() {
        boolean valid = true;
        String email = "";
        while (valid == true) {
            System.out.println("Please enter your email:");
            email = input.next();

            if (email.contains("@")) {
                if (email.contains(".co.uk") || email.contains(".com") || email.contains(".ac.uk")) {
                    System.out.println("Email valid.");
                }
                valid = false;
            } else {
                System.out.println("Email not valid. Please try again.");
            }

        }
        return email;
    }

    public static int roomVerif() {
        boolean valid = true;
        int room = 0;
        while (valid == true) {
            System.out.println("Enter room number:");
            room = input.nextInt();

            if (room < 6 && room > -1) {
                System.out.println("Room number: " + room);
                valid = false;
            } else {
                System.out.println("Room number not valid. Please try again.");
            }
        }
        return room;
    }

    public static String currentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String convertDate = formatter.format(date);                
        System.out.println(convertDate);
        return convertDate;
    }

    
    
    
    public static void view() {
        for (int i = 0; i < cateringRequests.size(); i++) {
            System.out.println(cateringRequests.get(i).toString());
        }
    }
    
    
    
}