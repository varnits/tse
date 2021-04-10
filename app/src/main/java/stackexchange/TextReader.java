package stackexchange;

import stackexchange.*;



import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;
public class CLIReader {
    
    public ArrayList <Order> read(String input){
   
        ArrayList<String >list=new ArrayList<>();
        ArrayList<Order >orderList=new ArrayList<>();    

        try {
          File myObj = new File(input);
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            orderList.add(createOrder(data));
        
          }
          int i=0;
          int n=orderList.size();
           
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        return orderList;
    }
    public Order createOrder(String orderString){
   
        String s[]=orderString.split(" ");

            String arr[]=new String [6];
            int i=0;
            int j=0;
            while(i<s.length && j<6){
                if(!s[i].isEmpty())
                    arr[j++]=s[i];
                i++;    
            }
          
        Order order =new Order(arr[0],arr[1], arr[2], arr[3], arr[4], arr[5]);
  //      System.out.println("Id->"+order.getId());
        return order;
    }
}
