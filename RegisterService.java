package org.gelber;

import java.util.*;

class RegisterService {

	private Customer customer;
	private Deque<Customer> queue = new LinkedList<Customer>();
	private boolean isTrainee;
    private int noOfItems, processingTimeForCustomer;
     
   
    RegisterService(boolean isTrainee){
    	this.isTrainee = isTrainee;
    }
   	
	void setIsTrainee(boolean isTrainee){
		this.isTrainee = isTrainee;
	}
	
	public Deque<Customer> getCustomerQueueForRegister() {
	      return queue;
	}
	
	  
	public int getProcessingTimeForCustomer() {
	      return processingTimeForCustomer;
	}

    public void setProcessingTimeForCustomer(int processingTimeForCustomer) {
	     this.processingTimeForCustomer = processingTimeForCustomer;
	}
    
	public int getNoOfItems() {
	      return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
	     this.noOfItems = noOfItems;
	}
	    
	void setCustomer(Customer customer){
		this.customer = customer;
		this.noOfItems = customer.getNoOfItems();
	}
	
	Customer getCustomer(){
		return this.customer;
	}

	void processItems(){
		
		this.processingTimeForCustomer++;
		if(!isTrainee){
		  this.noOfItems--;
	    }else if((processingTimeForCustomer%2)==0){
          this.noOfItems--;
	    }
		
	}

}