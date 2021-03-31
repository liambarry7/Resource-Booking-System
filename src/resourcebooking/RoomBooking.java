package resourcebooking;

import java.util.Scanner;



public class RoomBooking {
    
    public static Scanner input = new Scanner(System.in);
    
    public static void roomBook(String day) { //work out where/when to refresh board
        
        ResourceBooking.initialiseBoard(); //create board
        
        
        String email = ResourceBooking.emailVerif(); //gets user email
        String time = "";
        String disabled = "y";
        int columnNo = 0;
        
        System.out.println("Room 1 - 2 people");
        System.out.println("Room 2 - up to 4 people");
        System.out.println("Room 3 - up to 8 people");
        System.out.println("Room 4 - up to 15 people (disabled access)");
        System.out.println("Room 5 - up to 50 people\n");
        
        System.out.println("A - Morning session   (09:00 - 11:30)");
        System.out.println("B - Lunch session     (12:00 - 14:30)");
        System.out.println("C - Afternoon session (15:00 - 17:30)\n");
        Monday.refresh(); //display board
        
        System.out.println("\nDo you need disabled access?\n1 - yes\n2 - No");
        int access = input.nextInt();
        System.out.println("");
        while (true) {
            if (access == 1) {
                columnNo = 3; //room 4 has disabled access
                break;
            } else if (access == 2) {
                columnNo = ResourceBooking.getColumn(); //any room
                break;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
        
        int rowNo = ResourceBooking.getRow(); //gets row Number
        
        //refreshBoard(); //refreshes board to display any taken slots
                
        if (rowNo == 0) {
            time = "0900 - 11:30";
        } else if (rowNo == 1) {
            time = "13:00 - 14:30";
        } else if (rowNo == 2) {
            time = "15:00 - 17:30";
        } else {
            System.out.print("");
        }
        
        ResourceBooking.board[rowNo][columnNo] = "[X]";
        ResourceBooking.printBoard();
        
        System.out.println("How many people will be in the meeting room?");
        int amountPeople = input.nextInt();
        
        
        boolean valid = peopleCheck(amountPeople, columnNo);
        
        
        if (valid == true) {
            Monday newMondayBooking = new Monday(day, email, time, disabled, rowNo, columnNo, amountPeople);
            Monday.mondayBookings.add(newMondayBooking);
            Monday.writeFile(Monday.mondayBookings);
            Monday.refresh();
        } else {
            System.out.println("Error - Please choose the correct size room.");
        }
        
        
    }
    
    public static boolean peopleCheck(int amountPeople, int columnNo) {
        boolean validation = true;
        
        if ((amountPeople == 2) && (columnNo == 0)) {
            System.out.println("Room 1 valid");
        } else if ((1 < amountPeople) && (amountPeople < 5) && (columnNo == 1)) {
            System.out.println("Room 2 valid");
        } else if ((1 < amountPeople) && (amountPeople < 9) && (columnNo == 2)) {
            System.out.println("Room 3 valid");
        } else if ((1 < amountPeople) && (amountPeople < 16) && (columnNo == 3)) {
            System.out.println("Room 4 valid");
        } else if ((1 < amountPeople) && (amountPeople < 51) && (columnNo == 4)) {
            System.out.println("Room 5 valid");
        } else if ((amountPeople < 2) || (amountPeople > 50)) {
            System.out.println("Not valid");
            validation = false;
        } else {
            System.out.println("Not valid");
            validation = false;
        }
        
        return validation;
    }
    
    public static void updateBoard() {
        for (int i = 0; i < Monday.mondayBookings.size(); i++) {
            ResourceBooking.board[Monday.mondayBookings.get(i).getyCoord()][Monday.mondayBookings.get(i).getxCoord()] = "[X]";
        }
        ResourceBooking.printBoard();
    }
    

    
    
    
    
}
