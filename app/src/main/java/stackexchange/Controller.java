package stackexchange;
import stackexchange.TextReader;
import java.util.*;
import stackexchange.*;
import stackexchange.Processor;

public class Controller {
   
    public  void process(String filePath) {
   
        //Read Input from the cli for input file
        TextReader textReader=new TextReader();
        ArrayList<Order>orderList= textReader.read(filePath);

        //Sorting the List on basis of incoming time to maintain order
        Collections.sort(orderList,Order.ListComparator);
        
        for (Order o : orderList) { 		
            System.out.println(o.getId()+" "+o.getTime());
        }

        //Processing order from orderlist
        Processor processor=new Processor();
        processor.execute(orderList);
        
        //Printing the results
        ArrayList<Transaction>resultList=processor.getTranasactionSet();

        Result result=new Result();
        result.printResults(resultList);
        
        return ;
        
    }
    
}    
