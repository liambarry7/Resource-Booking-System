package resourcebooking;


public class Tuesday {
    private String day;
    private String email;
    private int time;
    private String disabled;
    private int xCoord; //roomNo
    private int yCoord; //Time letter    
    private int amountPeople;

    public static String dayDirectory = System.getProperty("user.dir") + "\\Tuesday.txt";

    public Tuesday(String day, String email, int time, String disabled, int xCoord, int yCoord, int amountPeople) {
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

    public int getTime() {
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

    public void setTime(int time) {
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
    
    
}
