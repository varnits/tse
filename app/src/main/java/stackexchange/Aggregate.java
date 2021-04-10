package stackexchange;

import stackexchange.*;



import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;

public class Aggregate {
    ArrayList<Order> orderList;
    
    public Aggregate( ArrayList<Order> orderList){
        this.orderList=orderList;
    } 
public  HashMap<String, ArrayList<Order> > aggregate(){
    HashMap<String, ArrayList<Order>> map=new HashMap<>();

    int n=orderList.size();
    for(int i=0;i<n;i++){
      Order o=  orderList.get(i);
     
        if(map.containsKey(o.stockName)){
            ArrayList<Order> oL=map.get(o.stockName);
            oL.add(o);
            map.put(o.stockName, oL);

        }
        else{
            ArrayList<Order> oL=new ArrayList<>();
            oL.add(o);
            System.out.println("Stk -> "+o.stockName);
            map.put(o.stockName, oL);

        }

    }
    return map; 

}
}   