
package osprojectphase1groupnumber3;
//imports
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;


public class  OSProjectPhase1GroupNumber3{
    static int P;//number of process
    static Scanner input = new Scanner(System.in);
    static PCB pcb[];
    
    public static void main(String[] args) {
        enterOption();
       
    }
    public static void printOption(){
        System.out.println("1. Enter process’ information.");
        System.out.println("2. Report detailed information about each process.");
        System.out.println("3. Report the average turnaround time, waiting time, and response time.");
        System.out.println("4. Exit the program.");
    }
    public static void enterOption(){
         int optionNum=0;
        do{
            printOption();
            System.out.println("choose the number of the option: ");
            String str = input.next();
            if(isNum(str)){
                optionNum = parseInt(str);
            
            }
           

        if (optionNum>0 && optionNum<5 ){
            break;
        }else{
            System.out.println("try again the option should be only real number from 1-4");
        }
         }while(true);
         switchOption(optionNum);
    }


    public static void switchOption(int op){
        switch(op){
            case 1:
                processCreation();//stop hear
                enterOption();
                break;
            case 2:
                if(pcb==null){
                    System.out.println("You should enter option 1 before option 2!");
                    enterOption();
                    break;
                }
                else{
                    printInfo PCBInfo = new printInfo();//stop hear!!!!
                    PCBInfo.PCBReport(P,pcb);
//                    printInfo PCBInfo2 = new printInfo();//stop hear!!
//                    PCBInfo2.printPCBInfo(P,pcb);
                    enterOption();
                    break;}
            case 3:
                if(pcb==null){
                    System.out.println("You should enter option 1 before option 3!");
                    enterOption();
                    break;
                }
                else{
                    avg();
                    enterOption();
                    break;
                }
            case 4:
                System.out.println("Have a nice day buy buy!");
                break;
            default: 
                System.out.println("Somting wrong happened!");
           
        }
    }
    public static void processCreation(){

        boolean POk = false; 
        do{
            System.out.println("please Enter the number of processes:");

               String str = input.next() ; 

               if(isNum(str)){
                    P = parseInt(str);
                    if (P>=1)
                    POk = true ; 
                    else{
                      System.out.println("the number of processes should be an int number that is bigger then 0");
                     }
               }else{
                    System.out.println("the number of processes should be an int number that is bigger then 0");
                }
        } while (!POk);
        pcb = new PCB[P];//Todo see
        //pcb.size(P);
        int i=0;
        while(i< P){
            pcb[i]= new PCB();
            pcb[i].setPID(i);
//            pcb[i].setStartTime();
//            pcb.setTerminationTime( 0);
//            pcb.setTurnAroundTime(i, 0);
//            pcb.setWaitingTime(i, 0);
//            pcb.setResponseTime(i, 0);
            System.out.println("\nplease Enter the following information about process "+i+":");
            int cpu=0;
            
            do{
                System.out.print("CPU burst:");
                String str = input.next();

                if(isNum(str)){

                    cpu= parseInt(str);
                    break;
                } 
                else{
                    System.out.println("cpu burst should be a number");
                }
            }while(true);
            
            pcb[i].setCpuBurst(cpu);
            int pr=0;
           
            do{
                System.out.print("priority(1 to 10):");
                String str = input.next();
                if(isNum(str)){
                    pr = parseInt(str);
                    if(pr>0 && pr<11)
                    break;
                    else{
                        System.out.println("priority should be a number from 1 to 10 only");
                    }
                }
                else{
                    System.out.println("priority should be a number from 1 to 10 only");
                }
            }while(true);

            pcb[i].setPriority(i,pr);
            i++;
        }
        //phase2
        int time= 0;
        Q qK = new Q();
        for(int prs=0 ; prs<P; prs++){
            qK.enqueue(pcb[prs]);
        }
        while(!(qK.isEmpty())){
            PCB max = qK.dequeue();
            Q qK2 = new Q();
            while(!(qK.isEmpty())){
                PCB temp = qK.dequeue();
                if(temp.getPriority() >= max.getPriority()){
                    qK2.enqueue(max);
                    max= temp;
                    System.out.println("max, "+max.getPID());
                }

                    else{
                        qK2.enqueue(temp);
                    }
            }
            while(!(qK2.isEmpty())){
                PCB temp = qK2.dequeue();
                qK.enqueue(temp);
            }
            Q q = new Q();
            while(!(qK.isEmpty())){
                PCB temp = qK.dequeue();
                if(temp.getPriority() == max.getPriority()){
                    q.enqueue(temp);
                }else{
                    qK2.enqueue(temp);
                }
            }
            q.enqueue(max);
            while(!(qK2.isEmpty())){
                PCB temp = qK2.dequeue();
                qK.enqueue(temp);
            }
            int tempTime = time; 
            Q q2 = new Q();
            while(!(q.isEmpty())){
                int arrive =0;
                PCB temp= q.dequeue();
                int start = tempTime;
                temp.setStartTime(tempTime);
                int response = start - arrive;
                int burst = temp.getCpuBurst();
                temp.setResponseTime(response);
                temp.setRemainingCpuBurst(burst);
                if (burst>4)
                    tempTime+=4;
                else 
                    tempTime+=burst;
                q2.enqueue(temp);
            }
            while(!(q2.isEmpty())){
                PCB temp= q2.dequeue();
                if(temp.getRemainingCpuBurst()<=4){
//                    Turn Around Time = Completion Time - Arrival Time.
//                    Waiting Time = Turnaround time - Burst Time.
                    int burst = temp.getCpuBurst();
                    int start = temp.getStartTime();
                    int arrive =0;
                    time +=temp.getRemainingCpuBurst();
                    temp.setRemainingCpuBurst(0);
                    temp.setTerminationTime(time);
                    int tat = time +arrive;
                    temp.setTurnAroundTime(tat);
                    temp.setWaitingTime(tat-burst);
//                    prs++;
                }else{//b>4
                    time+=4; 
                    int updateR=  temp.getRemainingCpuBurst() -4 ;
                    temp.setRemainingCpuBurst(updateR);
                    q2.enqueue(temp);
                }
            }
        }
        
        printInfo PCBInfo = new printInfo();//stop hear!!
        PCBInfo.printPCBInfo(P,pcb);  
    }

    public static void avg(){
        double sumTat=0, sumWait=0, sumRes=0;
        double avgTat,avgWait,avgRes;
        for(int i=0;i<P;i++){
            sumTat+= pcb[i].getTurnAroundTime();
            sumWait+= pcb[i].getWaitingTime();
            sumRes+= pcb[i].getResponseTime();
        }
        avgTat = sumTat/P;
        avgWait = sumWait/P;
        avgRes = sumRes/P;
        String PInfo= "Average Turn Around Time: "+avgTat+"\nAverage Waiting Time: "+avgWait+"\nAverage Response Time:"+avgRes;
        System.out.println(PInfo);
        printInfo PCBInfo = new printInfo();//stop hear!!
        PCBInfo.PCBReport2(P,pcb, avgTat,avgWait,avgRes );
    }
    

  public static boolean  isNum(String str) {  
      
      if (str.length()==0) 
          return false ; 
      
    for(int i = 0 ; i < str.length() ; i++ ){  
          // 0 = 48 , 9 = 57
        if( str.charAt(i)<48 ||  str.charAt(i)>57 ) 
            return false ; 
    }
    return true ;  
  }
     
}


class printInfo { 
    
     int P;//number of process
     

    public void printPCBInfo(int p,PCB pcbArray[]) { 
        P= p;
        PCB pcb[] = pcbArray;
        System.out.println("Processes information : ");
        for (int i = 0; i < P; i++) {
            System.out.print("PID:" + pcb[i].getPID() + "\t  cpuBurst:" +pcb[i].getCpuBurst() + "\tpriority:" + pcb[i].getPriority() +
            "\tstartTime:" + pcb[i].getStartTime() +  "\tterminationTime:" +  pcb[i].getTerminationTime() + "\tturnAroundTime:" +  pcb[i].getTurnAroundTime() 
            + "\twaitingTime:" +  pcb[i].getWaitingTime() + "\tresponseTime:"+ pcb[i].getResponseTime() + "\n");
            /*System.out.println( 

            PCB.getPID(i),
            PCB.getCpuBurst(i),
            PCB.getPriority(i),
            PCB.getStartTime(i),
            PCB.getTerminationTime(i),
            PCB.getTurnAroundTime(i),
            PCB.getWaitingTime(i),
            PCB.getResponseTime(i) ) ;*/
        }
    }

    public void PCBReport(int p,PCB pcbArray[])  { //stop hear!!!
        P= p;
        PCB pcb[] = pcbArray;
        try { 
            String PInfo = "";
            //PCB pcb = new PCB() ; 

            for (int i = 0; i < P; i++) {
            PInfo += "PID:" + pcb[i].getPID() + "\t  cpuBurst:" +pcb[i].getCpuBurst() + "\tpriority:" + pcb[i].getPriority() +
            "\tstartTime:" + pcb[i].getStartTime() +  "\tterminationTime" +  pcb[i].getTerminationTime() + "\tturnAroundTime:" +  pcb[i].getTurnAroundTime() 
            + "\twaitingTime:" +  pcb[i].getWaitingTime() + "\tresponseTime:"+ pcb[i].getResponseTime() + "\n";}

            BufferedWriter writer = new BufferedWriter(new FileWriter ("Report1.txt")) ;
            writer.write(PInfo) ;
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(printInfo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void PCBReport2(int p,PCB pcbArray[],double avgTat,double avgWait,double avgRes)  { //stop hear!!!
        P= p;
        PCB pcb[] = pcbArray;
        try { 
            String PInfo= "Average Turn Around Time: "+avgTat+"\nAverage Waiting Time: "+avgWait+"\nAverage Response Time:"+avgRes;
            BufferedWriter writer = new BufferedWriter(new FileWriter ("Report2.txt")) ;
            writer.write(PInfo) ;
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(printInfo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


} 