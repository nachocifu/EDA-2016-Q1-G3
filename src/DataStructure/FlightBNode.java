package DataStructure;

import FlightAssistant.Flight;

public class FlightBNode {
    private Flight left;
    private Flight middle;
    private Flight right;
    private FlightBNode parent;
    private FlightBNode leftChild;
    private FlightBNode middleChild;
    private FlightBNode rightChild;

    public FlightBNode(FlightBNode parent, Flight leftValue, Flight rightValue){
    	this.parent = parent;
    	this.left = leftValue;
    	this.right = rightValue;
    }
    
    public boolean isLeaf(){
    	if(leftChild == null && middleChild == null && rightChild == null)
    		return true;
    	return false;
    }
    
    public void add(){
    	
    }
    
    public boolean isFull(){
    	if(this.left != null && this.right != null && this.middle != null) {
    		return true;
    	}
    	return false;
    }
    	
    public Flight getLeft(){
    	return this.left;
    }
    
    public Flight getMiddle(){
    	return this.middle;
    }
    
    public Flight getRight(){
    	return this.right;
    }
    
    public BTreeNode split(FlightBNode node){
    	FlightBNode current =  node;
    	Flight leftAux;
    	Flight middleAux;
    	Flight rightAux;
    	while(current.isFull()){
    			leftAux = current.getLeft();
    			rightAux = current.getRight();
    			middleAux = current.getMiddle();
    			current = current.parent;
    			
    	}
    	
    }
    
}