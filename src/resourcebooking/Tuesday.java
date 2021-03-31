package resourcebooking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Tuesday {
    private String day;
    private String email;
    private String time;
    private String disabled;
    private int xCoord; //roomNo
    private int yCoord; //Time letter    
    private int amountPeople;

    public static String dayDirectory = System.getProperty("user.dir") + "\\Tuesday.txt";
    public static ArrayList<Tuesday> tuesdayBookings = new ArrayList<>();

    public Tuesday(String day, String email, String time, String disabled, int xCoord, int yCoord, int amountPeople) {
        this.day = day;
        this.email = email;
        this.time = time;
        this.disabled = disabled;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.amountPeople = amountPeople;
    }
    
    public String toString() {
        return day + ", " + email + ", " + time + ", " + disabled + ", " + xCoord + ", " + yCoord + ", " + amountPeople;
    }

    public String getDay() {
        return day;
    }

    public String getEmail() {
        return email;
    }

    public String getTime() {
        return time;
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

    public void setDay(String day) {
        this.day = day;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTime(String time) {
        this.time = time;
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
    
    public static void writeFile(ArrayList<Tuesday> tuesdayBookings) {

        try {
            FileWriter writeToFile = new FileWriter(dayDirectory, false); //false = overwrite, true = add
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < tuesdayBookings.size(); i++) {
                printToFile.println(tuesdayBookings.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static ArrayList<Tuesday> readFile() {

        ArrayList<Tuesday> tuesdayBookingList = new ArrayList<>();
        String lineFromFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(dayDirectory));
            while ((lineFromFile = read.readLine()) != null) {
                String[] bookingDetails = lineFromFile.split(", ");
                //String username, string password, int account number
                Tuesday bookings = new Tuesday(bookingDetails[0], bookingDetails[1], bookingDetails[2], bookingDetails[3], Integer.parseInt(bookingDetails[4]), Integer.parseInt(bookingDetails[5]), Integer.parseInt(bookingDetails[6]));
                tuesdayBookingList.add(bookings);
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return tuesdayBookingList;
    }
    
    
}
