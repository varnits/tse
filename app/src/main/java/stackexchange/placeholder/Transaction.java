package stackexchange;
import java.util.*;
class Transaction{
    String buyId; 
    float sellingPrice;
    int quantity; 
    String sellerId; 
    
    public  Transaction(String buyId,   float sellingPrice,int quantity, String sellerId ){
                    this.buyId=buyId;
                    this.sellingPrice=sellingPrice;
                    this.quantity=quantity;
                    this.sellerId=sellerId;
    }
    
    @Override
    public String toString(){
        String stringTransaction= (this.buyId+" "+String.format("%.02f", this.sellingPrice)+" "+this.quantity+" "+this.sellerId);
        return stringTransaction;
    }

}