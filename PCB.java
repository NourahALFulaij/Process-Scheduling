

package osprojectphase1groupnumber3;

import java.util.*;
import java.io.IOException;

public class PCB {
     int P;//number of processes 
     int priority;
     int cpuBurst;
     String PID;//process ID
     int startTime;//start time 
     int terminationTime;// end time 
     int turnAroundTime;//Turn around time 
     int waitingTime;//Waiting time.
     int responseTime; //Response time
     int remainingCpuBurst;
     Scanner input = new Scanner(System.in);

//    public void size(int p){
//        P = p;
////        priority = new int[p];
////        cpuBurst = new int[p];
////        PID = new String[p];
////        startTime = new int[p];
////        terminationTime = new int[p];
////        turnAroundTime = new int[p];
////        waitingTime = new int[p];
////        responseTime = new int[p];
//        
//    }

    
    public int getRemainingCpuBurst(){
        return remainingCpuBurst; 
    }

    public void setRemainingCpuBurst(int rcb){
        remainingCpuBurst = rcb; 
    }
    
    public int getP() { 
        return P;
    }
    public String getPID() {
        return PID;
     }

     public void setPID(int id) {
        PID = "P" + id;
     }

     public int getPriority() {
         return priority;
     }

     public void setPriority(int id, int pr) {
        while (pr > 10 || pr < 1) {
        System.out.println("Enter the priority of process "+id+" (1 to 10) : ");
        pr = input.nextInt();
     }
     priority = pr;
     }

     public int getCpuBurst() {
     return cpuBurst;
     }
     
     public void setCpuBurst(int burst) {
         cpuBurst = burst;
     }

     public int getStartTime() {
         return startTime;
     }

     public void setStartTime(int start) {
         startTime = start;
     }

     public int getTerminationTime() {
         return terminationTime;
     }

     public void setTerminationTime( int ter) {
         terminationTime = ter;
     }

      public int getTurnAroundTime() {
         return turnAroundTime;
       }

     public void setTurnAroundTime(int tat) {
         turnAroundTime = tat;
         }

    public int getWaitingTime() {
         return waitingTime;
     }

     public void setWaitingTime( int wait) {
         waitingTime = wait;
     }
     
     public int getResponseTime() {
         return responseTime;
     }
    
     public void setResponseTime( int res) {
         responseTime = res;
     }
     


}
