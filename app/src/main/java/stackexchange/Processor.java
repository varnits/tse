package stackexchange;

import stackexchange.*;



import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;

public class Processor {
    
    ArrayList<Transaction> transactionSet;
   
   public Processor(){
       this.transactionSet= new ArrayList<Transaction> ();
   
   }
    
    public void execute(ArrayList<Order> orderList){
      
        HashMap<String,Stock> map=new HashMap<>();
     // for(listel: )
       
       for (Order o : orderList) { 		      
            	            
            if(!map.containsKey(o.getStockName())){
             
                Stock stock=new Stock(o.getStockName());
                map.put(o.getStockName(),stock);
                
            }
            
            PriorityQueue<Order> sellQ    =map.get(o.getStockName()).getSellQ();
            PriorityQueue<Order> buyQ   =map.get(o.getStockName()).getBuyQ();
            //make ordertype enum
            if( o.orderType.equals("buy")){

                if(map.containsKey(o.getStockName())){
                
                    int left=executeTillCantBuy(sellQ,o);
                    if(left>0){
                        o.setQuantity(left);
                        buyQ.add(o);
                    }
                }
            }
            if( o.orderType.equals("sell")){

                if(map.containsKey(o.getStockName())){

                    int left=executeTillCantSell(buyQ,o);
                    if(left>0){
                        o.setQuantity(left);
                        sellQ.add(o);
                    }
                }
            }
        }


    }
    
      public int  executeTillCantBuy(PriorityQueue<Order> sellQ,Order o){
          
        int left=o.getQuantity();
        
        while(!sellQ.isEmpty() && left>0){
              Order sellQOrder=sellQ.peek();
                if(sellQOrder.getPrice()>o.getPrice())
                    return left;
              
                if(sellQOrder.getPrice()<=o.getPrice() && sellQOrder.getQuantity()>o.getQuantity()){
                    
                    left=0;
                    Order q=sellQ.poll();

                  
                    // Create Transaction
                    transactionSet.add(new Transaction(o.getId(),q.getPrice(), o.getQuantity(), q.getId()));

                    q.setQuantity(q.getQuantity()-o.getQuantity());
                    sellQ.add(q);
  
                }
                else if(sellQOrder.getPrice()<=o.getPrice() && sellQOrder.getQuantity()<=o.getQuantity()){
                    Order q=sellQ.poll();
                    
                    left=o.getQuantity()-q.getQuantity();

                    transactionSet.add(new Transaction(o.getId(),q.getPrice(), o.getQuantity(), q.getId()));
                   
                    o.setQuantity(left);                          
                       
                }
               

            }
      
        return left;
      }
      public int  executeTillCantSell(PriorityQueue<Order> buyQ,Order o){
        
        int left=o.getQuantity();
        
        while(!buyQ.isEmpty() && left>0){
        
            Order buyQOrder=buyQ.peek();
            if(buyQOrder.getPrice()<o.getPrice())
                return left;
            if(buyQOrder.getPrice()>=o.getPrice() && buyQOrder.getQuantity()>o.getQuantity()){
                left=0;
               
                Order q=buyQ.poll();

                transactionSet.add(new Transaction(q.getId(),o.getPrice(), o.getQuantity(), o.getId()));

                q.setQuantity(q.getQuantity()-o.getQuantity());
                buyQ.add(q);
                    
            }
            else if(buyQOrder.getPrice()>=o.getPrice() && buyQOrder.getQuantity()<=o.getQuantity()){
             
                Order q=buyQ.poll();
                left=o.getQuantity()-q.getQuantity();
                
                transactionSet.add(new Transaction(q.getId(),o.getPrice(), q.getQuantity(), o.getId()));

                
                o.setQuantity(left);    
                  
            }
          
        }
  
    return left;
  }
  
public ArrayList<Transaction>  getTranasactionSet(){
    return this.transactionSet;
}
}    