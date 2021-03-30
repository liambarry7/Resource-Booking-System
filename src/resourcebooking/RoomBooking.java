package resourcebooking;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;



public class RoomBooking {
    private String email;
    private String disabled;
    private int xCoord;
    private int yCoord;
    private int time;
    private int amountPeople;
    
    public static String bookingDirectory = System.getProperty("user.dir") + "\\Booking.txt";

    public RoomBooking(String email, String disabled, int xCoord, int yCoord, int amountPeople) {
        this.email = email;
        this.disabled = disabled;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.amountPeople = amountPeople;
    }
    
    public String toString() {
        return email + ", " + disabled + ", " + xCoord + ", " + yCoord + ", " + amountPeople;
    }

    public String getEmail() {
        return email;
    }

    public String getDisabled() {
        return disabled;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getAmountPeople() {
        return amountPeople;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setAmountPeople(int amountPeople) {
        this.amountPeople = amountPeople;
    }
    
    public static void writeFile(ArrayList<RoomBooking> BookingList) {

        try {
            FileWriter writeToFile = new FileWriter(bookingDirectory, false); //false = overwrite, true = add
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < BookingList.size(); i++) {
                printToFile.println(BookingList.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    
    
}
