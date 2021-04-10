package stackexchange;
import java.util.*;


class Stock{
        String stockName;
        PriorityQueue<Order> buyQ;
        PriorityQueue<Order> sellQ;

public  Stock(String stockName ){
           
            this.stockName=stockName;
            this.buyQ=new PriorityQueue<>(Order.MaxComparator);
            this.sellQ=new PriorityQueue<>(Order.OrderComparator);

}
public PriorityQueue<Order>  getBuyQ(){
    return this.buyQ;
}
public PriorityQueue<Order>  getSellQ(){
    return this.sellQ;
}


}