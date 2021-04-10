package stackexchange;
import stackexchange.CLIReader;
import java.util.*;
import stackexchange.*;
import stackexchange.Processor;

public class Controller {
    public  void process(String filePath) {
        //Read Input from the cli for input file
        CLIReader cliReader=new CLIReader();
        ArrayList<Order>orderList= cliReader.read(filePath);

        //Sorting the List on basis of incoming time to maintain order
        Collections.sort(orderList,Order.ListComparator);
        
        //Processing order from orderlist
        Processor processor=new Processor();
        processor.execute(orderList);
        
        //Printing the results
        ArrayList<String>list=processor.getResultSet();
        for(int i=0; i<list.size();i++){
            System.out.println(list.get(i));
        }    
        return ;
        
    }
}    
