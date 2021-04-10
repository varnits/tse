package stackexchange;

import stackexchange.*;



import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;

public class Processor {
    ArrayList<String> resultSet;
   
   public Processor(){
       this.resultSet=new ArrayList<String>();
   }
    
    public void execute(ArrayList<Order> orderList){
      
        HashMap<String,Stock> map=new HashMap<>();
     // for(listel: )
       
       int n=orderList.size();
       int i=0;
        while(i<n){
            
            Order o=orderList.get(i);
            
            if(!map.containsKey(o.getStockName())){
             
                Stock stock=new Stock(o.getStockName());
                map.put(o.getStockName(),stock);
                
            }
            
            PriorityQueue<Order> sellQ    =map.get(o.getStockName()).getSellQ();
            PriorityQueue<Order> buyQ   =map.get(o.getStockName()).getBuyQ();
            //make ordertype enum
            if( o.orderType.equals("buy")){
          //      System.out.println("stko ->"+" buy"+o.getStockName());

                if(map.containsKey(o.getStockName())){
                
                    int left=executeTillCantBuy(sellQ,o);
                    if(left>0){
                        o.setQuantity(left);
                        buyQ.add(o);
                    }
                }
            }
            if( o.orderType.equals("sell")){
   //             System.out.println("stko -> "+" sold"+o.getStockName());

                if(map.containsKey(o.getStockName())){

                    int left=executeTillCantSell(buyQ,o);
                    if(left>0){
                        o.setQuantity(left);
                        sellQ.add(o);
                    }
                }
            }
        i++;
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

                    System.out.println("1");
                    resultSet.add( o.getId()+" "+String.format("%.02f", q.getPrice())+" "+o.getQuantity()+" "+q.getId() );


                    q.setQuantity(q.getQuantity()-o.getQuantity());
                    sellQ.add(q);
  
                }
                else if(sellQOrder.getPrice()<=o.getPrice() && sellQOrder.getQuantity()<o.getQuantity()){
                    Order q=sellQ.poll();
                    
                    left=o.getQuantity()-q.getQuantity();

                    System.out.println("2");
                    resultSet.add( o.getId()+" "+String.format("%.02f", q.getPrice())+" "+q.getQuantity()+" "+q.getId() );

                    o.setQuantity(left);                          
                       
                }
                else if(sellQOrder.getPrice()<=o.getPrice() ){
                  
                    Order q=sellQ.poll();

                    System.out.println("3");
                    resultSet.add( o.getId()+" "+String.format("%.02f", q.getPrice())+" "+q.getQuantity()+" "+q.getId() );

                    return 0;

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

                System.out.println("4");
                resultSet.add( q.getId()+" "+String.format("%.02f", o.getPrice())+" " +o.getQuantity()+" "+o.getId() );

                q.setQuantity(q.getQuantity()-o.getQuantity());
                buyQ.add(q);
                    
            }
            else if(buyQOrder.getPrice()>=o.getPrice() && buyQOrder.getQuantity()<o.getQuantity()){
             
                Order q=buyQ.poll();
                left=o.getQuantity()-q.getQuantity();
                
                System.out.println("5");
                resultSet.add( q.getId()+" "+String.format("%.02f", o.getPrice())+" " +q.getQuantity()+" "+o.getId() );
                
                o.setQuantity(left);    
                  
            }
            else if(buyQOrder.getPrice()>=o.getPrice()){

                Order q=buyQ.poll();

                System.out.println("6");

                resultSet.add( q.getId()+" "+String.format("%.02f", o.getPrice())+" " +o.getQuantity()+" "+o.getId() );

                return 0;

            }

        }
  
    return left;
  }
  public ArrayList<String>  getResultSet(){
    return this.resultSet;
}
    
}    