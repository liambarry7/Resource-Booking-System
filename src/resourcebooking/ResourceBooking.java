package resourcebooking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import static resourcebooking.Catering.cateringDirectory;

public class ResourceBooking {

    public static Scanner input = new Scanner(System.in);
    
    public static ArrayList<RoomBooking> bookings = new ArrayList<>();
    public static ArrayList<Catering> cateringRequests = new ArrayList<>();
    public static ArrayList<Equiptment> equipmentRequests = new ArrayList<>();
    private static String[][] board = new String[3][5]; //3 = rows, 5 = columns
    private static String[] letters = {"A","B","C"};

    public static void main(String[] args) {
        cateringRequests = Catering.readFile();

        currentTime();
        mainMenu();
        //do user login
        //make a 2d array to book slots, use the grid to represent times, 5 time slots per day, 5 different rooms
        //in 2d array, put an x when a time slot is booked.
        //Or a morning and afternoon slot that last 3 hours and refreshments can be delivered every hour on the hour.
        //3 sessions, morning, lunch, afternoon

    }

    public static void mainMenu() {
        while (true) {
            cateringRequests = Catering.readFile();
            try {
                System.out.println("\nChoose an option:");
                System.out.println("1 - Book a room");
                System.out.println("2 - Request refeshments");
                System.out.println("3 - Request equipment");
                System.out.println("4 - View catering requests");
                System.out.println("5 - view equipment requests");
                System.out.println("6 - Remove booking");
                System.out.println("0 - Exit");
                int menuChoice = input.nextInt();
                switch (menuChoice) {
                    case 1:
                        System.out.println("");
                        roomBook();
                        break;
                    case 2:
                        System.out.println("");
                        reqRefreshments();
                        break;
                    case 3:
                        System.out.println("");
                        reqEquipment();
                        break;
                    case 4:
                        System.out.println("");
                        viewCatering();
                        break;
                    case 5:
                        System.out.println("");
                        viewEquipment();
                        break;
                    case 6:
                        System.out.println("");
                        removeBooking();
                        break;
                    case 0:
                        Catering.writeFile(cateringRequests);
                        System.exit(menuChoice);
                }

            } catch (Exception e) {
                System.out.println("Error occured: " + e);
                input.nextLine();

            }
        }

    }

    public static void roomBook() {
        initialiseBoard();

        try {
            
            String email = emailVerif();
            String access = "";
            int columnNo = 0;
            
            System.out.println("A - Morning session   (09:00 - 11:30)");
            System.out.println("B - Lunch session     (12:00 - 14:30)");
            System.out.println("C - Afternoon session (15:00 - 17:30)\n");
            refreshBoard();

            while (true) {
                System.out.println("\nDo you need disabled access? y/n");
                access = input.next();
                if (access.equalsIgnoreCase("y")) {
                    columnNo = 4;
                    break;
                } else if (access.equalsIgnoreCase("n")) {
                    columnNo = getColumn();
                    break;
                } else {
                    System.out.println("Please enter a valid answer.");
                }
            }
            
            int rowNo = getRow();
            //System.out.println(rowNo);

            board[rowNo][columnNo] = "[X]";
            printBoard();

            //room 5 = 15<x<=50
            //room 4 = 8<x<=15 
            //room 3 = 4<x<=8
            //room 2 = 2<x<=4
            //room 1 = 2=x
            
            
            System.out.println("\nEnter the amount of people:");
            int amountPeople = input.nextInt();
            

            if (access.equalsIgnoreCase("y")) {
                System.out.println("valid Room 5");
            } else if ((amountPeople == 2) && (columnNo == 0))  {
                System.out.println("Valid. Room 1");
            } else if ((2 < amountPeople) && (amountPeople < 5) && columnNo == 1) {
                System.out.println("Valid. Room 2");
            } else if ((4 < amountPeople) && (amountPeople < 9) && columnNo == 2) {
                System.out.println("Valid. Room 3");
            } else if ((8 < amountPeople) && (amountPeople < 16) && columnNo == 3) {
                System.out.println("Valid. Room 4");
            } else if ((15 < amountPeople) && (amountPeople < 51) && columnNo == 4) {
                System.out.println("Valid. Room 5");
            } else if ((amountPeople > 50) || (amountPeople < 2)) {
                System.out.println("Not valid.");
            } else {
                System.out.println("Not valid.");
            }
            
            
            
            RoomBooking newBooking = new RoomBooking(email, access, columnNo, rowNo, amountPeople);
            bookings.add(newBooking);
            
            RoomBooking.writeFile(bookings);
            //make object with every input, then when program restarts, fill in grid with information. can also store email, no of people, time and roomNo (for coords), etc
            
            refreshBoard();
            
            
        } catch (Exception e) {
            System.out.println("Error occurred.\nBooking cancelled. " + e);
        }
        
        
        
    }

        public static void initialiseBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = "[ ]";                
            }
        }
    }
    
    public static void printBoard() {
        System.out.println("    1  2  3  4  5");
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + letters[i] + " ");
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j]);                
            } 
            System.out.println("");
        }
    }
    
    public static int getColumn() {
        int roomNo = 0;
        while (true) {
                System.out.println("Enter Room Number:");
                roomNo = input.nextInt();
                if ((roomNo < 1) || (roomNo > 5)) {
                    System.out.println("Room number not valid.");                    
                } else {
                    //System.out.println("Valid.");
                    roomNo = roomNo - 1;
                    break;
                }
            }
        return roomNo;
    }
    
    public static int getRow() {
        int rowNo = 0;        
        boolean valid = false;
        while (valid == false) {
            System.out.println("Enter session letter:");
            String sessionLetter = input.next();

            for (int i = 0; i < letters.length; i++) {
//                System.out.println(i);
                if (sessionLetter.equalsIgnoreCase(letters[i])) {
                    rowNo = i;                    
                    valid = true;
                } else {
                    System.out.print("");
                }
            }
        }
        return rowNo;
    }
    
    public static void refreshBoard() {
        for (int i = 0; i < bookings.size(); i++) {
            board[bookings.get(i).getyCoord()][bookings.get(i).getxCoord()] = "[x]";            
        }        
        printBoard();
        
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

    public static void reqEquipment() {
        int roomNumber = roomVerif();
        String email = emailVerif();
        
        System.out.println("Would you like any pens?");
        input.nextLine();
        String pen = input.nextLine();
               
        System.out.println("Would you like any paper?");
        String paper = input.nextLine();
        
        System.out.println("Would you like whiteboard and pens?");
        String whiteboard = input.nextLine();
        
        System.out.println("Would you like a projector?");
        String projector = input.nextLine();
        
        System.out.println("Would you like a printer?");
        String printer = input.nextLine();
        
        
    }

    public static void viewCatering() {
        for (int i = 0; i < cateringRequests.size(); i++) {            
            System.out.println(cateringRequests.get(i).toString());            
        }
    }

    public static void viewEquipment() {
        for (int i = 0; i < equipmentRequests.size(); i++) {
            System.out.println(equipmentRequests.get(i).toString());
            
        }
    }

    public static String emailVerif() {
        boolean valid = true;
        String email = "";
        while (valid == true) {
            System.out.println("Please enter your email:");
            email = input.next();

            if (email.contains("@")) {
                if (email.contains(".co.uk") || email.contains(".com") || email.contains(".ac.uk")) {
                    System.out.println("\n");
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
    
    public static void removeBooking() {
        
    }

    
    

    
    
}
