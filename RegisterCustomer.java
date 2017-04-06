/**+
 * 
 */
package org.gelber;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
class RegisterCustomer {
	
	public static void main(String[] args){
	   int noOfRegisters = 2;
	   int time =0;
	   Multimap<Integer,Customer> multimap = ArrayListMultimap.create();

	   List<RegisterService> registers = new ArrayList<RegisterService>();
	   List<Customer> customers = new ArrayList<Customer>();
	   multimap.put(1,new Customer(0,1,5));
	   multimap.put(2,new Customer(1,2,1));
	  multimap.put(3,new Customer(0,3,5));
	    multimap.put(5,new Customer(1,5,3));
	      multimap.put(8,new Customer(0,8,2));
	   
//	    customers.add(new Customer(0,1,2));
//	      customers.add(new Customer(0,2,1));
	   
//	   multimap.put(1,new Customer(0,1,2));
//	   multimap.put(1,new Customer(0,1,3));
//	   multimap.put(2,new Customer(0,2,1));
//	   multimap.put(2,new Customer(0,2,1));
	   
//	   customers.add(new Customer(0,1,3));
//	   customers.add(new Customer(0,1,5));
//	  customers.add(new Customer(0,3,1));
//	    customers.add(new Customer(0,4,1));
//	      customers.add(new Customer(0,4,1));
    

	   registers.add(new RegisterService(0));
	   registers.add(new RegisterService(1));

	   for(int i=0;i<customers.size();i++){
		// Customer cust = customers.get(i);
	   	int size =1000;
	   	int itemSize = 1000;
	   	RegisterService r=null;
	   	time++;
	    List<Customer> customer = (List<Customer>)multimap.get(time);
	    for(Customer cust:customer){
         for(RegisterService register: registers){
           if(register.noOfItems==0){
        	 if(register.queue.size()!=0){
             register.setCustomer(register.queue.poll());
             register.minutesProcessed=0;
        	 }
        	 else if(cust.startTime==time && register.queue.size()==0){
        		 register.setCustomer(cust);
        		 
        	 }
        	 r = null;
             break;
           }
           else{
           	 register.decrementItems();
           }
           if(time>2){
           	// if(cust.type ==0){
           	 	if(cust.startTime==time && register.queue.size()<size){
                    r = register;
                    size = register.queue.size();
           	 	}
           	 	else if(cust.startTime==time && register.queue.size()==size){
           	 		
           	 		if(cust.type==0){ r =registers.get(0);System.out.println("eherhe");}
           	 		else {
                       if(register.queue.size() ==0 || register.queue.peekLast().noOfItems < itemSize){
                          r = register;
                       }
           	 		}
           	 	}
           	//}
           }
         }
         if(r!=null)
         r.queue.add(cust);
         if(cust.startTime!=time) 
           i--;
	    }
       }
        int i=0,c=0;
		while(true){
			RegisterService register = registers.get(i);
			
			if(register.noOfItems!=0){
				 register.decrementItems();
				 
				}
			else if(register.queue.size()!=0){
				 register.setCustomer(register.queue.poll());
				 register.minutesProcessed=0;
				 continue;
			}
		    else{
		    	c++;
		    	if(registers.size() == c)
		    		break;
		    }
		    i++;
		    while(i==2){
		    	i=0;time++;
		    }
		}
        
       System.out.println(time);
	}
}