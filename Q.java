/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osprojectphase1groupnumber3;
class Node{
    private PCB pcb;
    Node next; 
    public Node(PCB pcb){
        this.pcb= pcb;
        this.next= null;
    }
    public PCB getPCB(){
        return pcb;
    }
}
/**
 *
 * @author shatha
 */
public class Q {
    //@
     Node front, rear;

    public Q(){
        front= rear=null;
    }
    // empty? 
    public boolean isEmpty(){
        if(this.rear== null || this.front == null)
            return true; 
        return false; 
    }
    //add in Q ->rear
    public void enqueue(PCB pcb){
        Node temp =new Node(pcb);
        if(isEmpty()){
            this.front = this.rear= temp; 
            return;
        }
        this.rear.next= temp;
        this.rear= temp;
    }
    //remove from Q ->front
    public PCB dequeue(){
        if(isEmpty()){
            return null;
        }
        Node temp = this.front;
        this.front = this.front.next; 
        
        if(isEmpty()){
            this.rear = null;
        }
        return temp.getPCB();
    }

}



