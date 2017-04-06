/**+
 * 
 */
package org.gelber;

import java.util.*;
class RegisterCustomers {
	
	public static void main(String[] args){
	   int noOfRegisters = 2;
	   int time =1;
	//   Multimap<String,String> multimap = ArrayListMultimap.create();

	   List<RegisterService> registers = new ArrayList<RegisterService>();
	   List<Customer> customers = new ArrayList<Customer>();
//	   customers.add(new Customer(0,1,5));
//	   customers.add(new Customer(1,2,1));
//	  customers.add(new Customer(0,3,5));
//	    customers.add(new Customer(1,5,3));
//	      customers.add(new Customer(0,8,2));
	   
	    customers.add(new Customer(0,1,2));
	      customers.add(new Customer(0,2,1));
	   
//	    customers.add(new Customer(0,1,2));
//	    customers.add(new Customer(0,1,3));
//	   customers.add(new Customer(0,2,1));
//	   customers.add(new Customer(0,2,1));
	   
//	   customers.add(new Customer(0,1,3));
//	   customers.add(new Customer(0,1,5));
//	  customers.add(new Customer(0,3,1));
//	    customers.add(new Customer(0,4,1));
//	      customers.add(new Customer(0,4,1));
    

	  // registers.add(new RegisterService(0));
	   registers.add(new RegisterService(1));

	   for(int i=0;i<customers.size();i++){
		Customer customer = customers.get(i);
	   	int queueSize =1000;
	   	int itemSize = 1000;
	   	RegisterService registerService=null;
	   	boolean flag = true;
	   //	time++;
	   	if(i!=0&& (customer.startTime != customers.get(i-1).startTime)){
        	//System.out.println(customer.startTime+" "+customers.get(i+1).startTime);
        	 time++;
	   	}else{
	   		flag=false;
	   	}
         for(RegisterService register: registers){
           if(register.noOfItems==0){
        	 if(register.queue.size()!=0){
             register.setCustomer(register.queue.poll());
             register.minutesProcessed=0;
        	 }
        	 else if(customer.startTime==time && register.queue.size()==0){
        		 register.setCustomer(customer);
        		 
        	 }
        	 registerService = null;
             break;
           }
           else{
        	   if(flag==true)
           	 register.decrementItems();
           }
           if(time>1){
           	// if(customer.type ==0){
           	 	if(customer.startTime==time && register.queue.size()<queueSize){
                    registerService = register;
                    queueSize = register.queue.size();
           	 	}
           	 	else if(customer.startTime==time && register.queue.size()==queueSize){
           	 		
           	 		if(customer.type==0){ registerService =registers.get(0);System.out.println("eherhe");}
           	 		else {
                       if(register.queue.size() ==0 || register.queue.peekLast().noOfItems < itemSize){
                          registerService = register;
                       }
           	 		}
           	 	}
           	//}
           }
         }
         if(registerService!=null)
         registerService.queue.add(customer);
         if(customer.startTime!=time) 
           i--;
       }
        int i=0,c=0;
		while(true){
			RegisterService register = registers.get(i);
			
			if(register.noOfItems!=0){
				 register.decrementItems();
				 c=0;
				}
			else if(register.queue.size()!=0){
				 register.setCustomer(register.queue.poll());
				 register.minutesProcessed=0;
				 i++;
				 if(i==1)
					 i--;
				 c=0;
				 continue;
			}
		    else{
		    	c++;
		    	if(registers.size() == c)
		    		break;
		    }
		    i++;
		    while(i==1){
		    	i=0;time++;
		    }
		}
        
       System.out.println(time);
	}
}