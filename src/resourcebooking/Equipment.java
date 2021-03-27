
package resourcebooking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;



public class Equipment {
    
    public static String equipmentDirectory = System.getProperty("user.dir") + "\\Equipment.txt";
    
    private String pens;
    
    
    
    
    
    
    
    
    
    
    
    
    public static void writeFile(ArrayList<Catering> equipmentList) {

        try {
            FileWriter writeToFile = new FileWriter(equipmentDirectory, true); //false = overwrite, true = add
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < equipmentList.size(); i++) {
                printToFile.println(equipmentList.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
//    public static ArrayList<Equipment> readFile() {
//
//        ArrayList<Catering> equipmentList = new ArrayList<>();
//        String lineFromFile;
//        try {
//            BufferedReader read = new BufferedReader(new FileReader(equipmentDirectory));
//            while ((lineFromFile = read.readLine()) != null) {
//                String[] equipmentDetails = lineFromFile.split(", ");
//                //String username, string password, int account number                   change values to match equipment above
//                Equipment request = new Equipment(Integer.parseInt(equipmentDetails[0]), equipmentDetails[1], equipmentDetails[2], equipmentDetails[3], equipmentDetails[4], cateringDetails[5], cateringDetails[6], cateringDetails[7]);
//                equipmentList.add(request);
//            }
//            read.close();
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        return equipmentList;
//    }
//    
}
