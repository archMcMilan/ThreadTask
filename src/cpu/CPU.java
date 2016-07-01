package cpu;

/**
 * Created by Artem on 30.06.16.
 */
public class CPU extends Thread {
    private CPUQueue queue1;
    private CPUQueue queue2;
    private int iterationAmount=100;
    private int processTakeCounter=0;
    private int processTakeFrom1QueueCounter=0;

    public CPU(CPUQueue queue1,CPUQueue queue2){
        this.queue1=queue1;
        this.queue2=queue2;
    }


    /**
     * Method takes class Process object from queue which size is bigger. Also in method counted statistics for
     * iterationAmount iterations
     * @return Process object
     */
    public Process takeProcess(){
        if(processTakeCounter>iterationAmount){
            System.out.println("From queue1 has been taken="+ processTakeFrom1QueueCounter+
                    "      All taken process amount=" +processTakeCounter);
            System.out.format("Percentage %2.1f",(double) processTakeFrom1QueueCounter/processTakeCounter*100);
            System.out.println();
            System.exit(0);
        }
        processTakeCounter++;
        System.out.println();
        System.out.println("Queue1 size="+queue1.getSize()+"       Queue2 size="+queue2.getSize());
        if(queue1.getSize()>=queue2.getSize()){
            System.out.print("Take from queue1    ");
            processTakeFrom1QueueCounter++;
            return queue1.getProcess();
        }else{//queue1.getSize()<queue2.getSize()
            System.out.print("Take from queue2    ");
            return queue2.getProcess();
        }
    }

    @Override
    public void run() {
        while(true){
            Process process=takeProcess();
            if(process==null){
                continue;
            }
            System.out.println(process.getProcessName());
            process.execute();
        }

    }
}
