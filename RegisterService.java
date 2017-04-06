package org.gelber;

import java.util.*;

class RegisterService {

	int minutesProcessed;
	Customer c;
	Deque<Customer> queue = new LinkedList<Customer>();
	private int isTrainee;
    int noOfItems;
   
    RegisterService(int isTrainee){
    	this.isTrainee = isTrainee;
    }
   	
	void setIsTrainee(int b){
		this.isTrainee = b;
	}

	void setCustomer(Customer c){
		this.c = c;
		this.noOfItems = c.noOfItems;
	}

	void decrementItems(){
		//checkIfItemsAreZero();
	    this.minutesProcessed++;
		if(isTrainee!=1){
		  this.noOfItems--;
	    }else if((minutesProcessed%2)==0){
          this.noOfItems--;
	    }
	}

//	void checkIfItemsAreZero(){
//		if(this.noOfItems==0){
//			if(queue.peek()!=null){
//			 this.c = queue.poll();
//			 this.noOfItems = c.noOfItems;
//			 this.minutesProcessed = 0;
//			}
//		}
//	}
}