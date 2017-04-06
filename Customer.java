package org.gelber;

class Customer {

	private int noOfItems;
    private Type type;
	private int startTime;

	Customer(Type type,int startTime,int noOfItems){
       this.noOfItems = noOfItems;
       this.type = type;
       this.startTime = startTime;
	}
	
    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
	
	
	enum Type{
		A,B
	}

}