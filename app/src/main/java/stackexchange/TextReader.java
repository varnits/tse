package stackexchange;

import stackexchange.*;



import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;
public class TextReader {
    //invalid Input exception
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
        //create new exception invalid input
          e.printStackTrace();
        }
        return orderList;
    }
    public Order createOrder(String orderString){
   
        String s[]=orderString.split(" ");
// create list instead of array
//invalid input file exception
            ArrayList<String> arrList=new ArrayList<>();
            int i=0;
            int j=0;
            while(i<s.length && j<6){
                
                if(!s[i].isEmpty()){
                    arrList.add(s[i]);
                  j++;
                  }    
                i++; 

            }
          if(arrList.size()<6)
            throw new InputRuntimeException();

        Order order =new Order(arrList.get(0),arrList.get(1),arrList.get(2)
                      , arrList.get(3),arrList.get(4),arrList.get(5));
  //      System.out.println("Id->"+order.getId());
        return order;
    }
}
