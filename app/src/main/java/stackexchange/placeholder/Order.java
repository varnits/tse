
package stackexchange;
import java.util.*;
class Order{
    String id; 
    String time;
     String stockName; 
     String orderType; 
     float price; 
     int quantity;
public  Order(String id, String time, String stockName, String orderType,  String price, String quantity ){
            this.id=id;
            this.time=time;
            this.stockName=stockName;
            this.orderType=orderType;
            this.price=Float.parseFloat(price);
            this.quantity=Integer.parseInt(quantity);


}


public String getId(){
    return this.id;
}
public String getTime(){
    return this.time;
}
public String getStockName(){
    return this.stockName;
}
public String getOrderType(){
    
    return this.orderType;
}
public float getPrice(){
    return this.price;
}
public Integer getQuantity(){
    return this.quantity;
}
public void setStockName(String stockName){
     this.stockName=stockName;
    return;
}
public void setId(String id){
    this.id=id;
   return;
}
public void setOrderType(String orderType){
    this.orderType=orderType;
   return;
}
public void setPrice(Float price){
    this.price=price;
   return;
}
public void setQuantity(int  quantity){
    this.quantity=quantity;
   return;
}
public void setTime(String time){
    this.time=time;
   return;
}
public static  Comparator<Order> ListComparator = new Comparator<Order>() {

	public int compare(Order order1, Order order2) {
       //
       return order1.time.compareTo(order2.time);

    }
};

public static  Comparator<Order> MinComparator = new Comparator<Order>() {

	public int compare(Order order1, Order order2) {
       //
       
       Float price1 =order1.price;
	   Float price2 = order2.price;
       //ascending order
       if(price1<price2)
        return -1;
       

        else if((price1>price2))
        return 1;

        return order1.time.compareTo(order2.time);
    
        
	   //descending order
	   //return StudentName2.compareTo(StudentName1);
    }
};
public static  Comparator<Order> MaxComparator = new Comparator<Order>() {

	public int compare(Order order1, Order order2) {
       //
       
       Float price1 = order1.price;
	   Float price2 = order2.price;
       //ascending order
       if(price1>price2)
        return -1;
       

        else if((price1<price2))
        return 1;
        return order1.time.compareTo(order2.time);
      
        
	   //descending order
	   //return StudentName2.compareTo(StudentName1);
    }
};
}