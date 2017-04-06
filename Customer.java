package org.gelber;
class Customer {

	int noOfItems;
    int type;
	int startTime;

	Customer(int type,int startTime,int noOfItems){
       this.noOfItems = noOfItems;
       this.type = type;
       this.startTime = startTime;
	}

}