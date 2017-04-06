package org.gelber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.gelber.Customer.Type;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
class RegisterCustomers {
   static int registerSize;
   Multimap<Integer,Customer> multimap;
   List<RegisterService> registers;
   int queueSize = Integer.MAX_VALUE;
   int itemSize = Integer.MAX_VALUE;
   
   private boolean checkIfNoMoreItemsAndEmptyQueue(){
	   boolean itemRemaining = false;
	   for(RegisterService register:registers){
		   if(register.getCustomerQueueForRegister().size()!=0 || register.getNoOfItems()!=0){
			   itemRemaining = true;
			   break;
		   }
	   }
	 return itemRemaining;
   }
   
   
   private void processItemsOnRegister(){
	   for(RegisterService register: registers){
		   if(register.getNoOfItems()==0){
		       	 if(register.getCustomerQueueForRegister().size()!=0){
		            register.setCustomer(register.getCustomerQueueForRegister().poll());
		            register.setProcessingTimeForCustomer(0);
		            register.processItems();
		         }
	        }
	        else{
	          	 register.processItems();
	        }
			
	   }
   }
   
   private void assignCustomerToRegisterQueue(Customer customer){

	   	RegisterService registerService=null;
	    	if(customer.getType()==Type.A){
		    	for(RegisterService register:registers){
		    		if(register.getCustomerQueueForRegister().size()<queueSize){
		                   registerService = register;
		                   queueSize = register.getCustomerQueueForRegister().size();
		          	}
		    		else if(register.getCustomerQueueForRegister().size()==queueSize){
		          	 	   registerService =registers.get(0);
		          	}
		    	}
	    	}
	    	else{
	    	   for(RegisterService register:registers){
		    		if(register.getCustomerQueueForRegister().size()==0){
		    			register.getCustomerQueueForRegister().add(customer);
		    	    }
		    	    else{
		    	    	if(register.getCustomerQueueForRegister().peekLast().getNoOfItems()<itemSize){
		    	    		registerService = register;
		    	    		itemSize = register.getCustomerQueueForRegister().peekLast().getNoOfItems();
		    	    	}
		    	    }
	    	   }
	    	 
	    	}
	    	  if(registerService!=null)
    		      registerService.getCustomerQueueForRegister().add(customer);
   }
   
   private void checkIfCustomerHasCome(int time){
	   // Get a list of all customers who have come at particular time
	   List<Customer> customerListForTime = (List<Customer>)multimap.get(time);
	   //Lets assign each customer to a register
	   for(Customer customer : customerListForTime){	
		  assignCustomerToRegisterQueue(customer); 
	   }
	   processItemsOnRegister();
   }
   private void startTimer(){
	    for(int i=1;;i++){
			  checkIfCustomerHasCome(i);
		      boolean itemsRemaining = checkIfNoMoreItemsAndEmptyQueue();
		    
		      if(!itemsRemaining){
		    	  System.out.println("Finished at: t="+i+" minutes");
				 break;
			   }			 
	    }		   
   }
   
   static RegisterCustomers initializeData(){
	   Scanner scanner = new Scanner(System.in);
	   RegisterCustomers registerCustomers = new RegisterCustomers();
	   registerCustomers.multimap = ArrayListMultimap.create();
	   registerSize = Integer.parseInt(scanner.nextLine());
	   while(scanner.hasNextLine()){
		   String[] input = scanner.nextLine().split(" ");
		   if(input[0].charAt(0)=='A')
		   registerCustomers.multimap.put(Integer.parseInt(input[1]),new Customer(Type.A,Integer.parseInt(input[1]),Integer.parseInt(input[2])));
		   else
		   registerCustomers.multimap.put(Integer.parseInt(input[1]),new Customer(Type.B,Integer.parseInt(input[1]),Integer.parseInt(input[2])));
	   }
	   registerCustomers.registers = new ArrayList<RegisterService>();
	   for(int i=0;i<registerSize;i++){
		   if(i==(registerSize-1))
		   registerCustomers.registers.add(new RegisterService(true));
	   }
	   scanner.close();
	   return registerCustomers;
   }
	
	public static void main(String[] args){
	
	   RegisterCustomers registerCustomers = RegisterCustomers.initializeData();
	   registerCustomers.startTimer();

	}
}