package cpu;

/**
 * Class contains two queues and two static variables that are borders for time waiting and three variable for
 * statistics counting and method to generate process and take process from queue
 */
public class CPUProcess extends Thread{
    private CPUQueue queue1;
    private CPUQueue queue2;
    public static int lowerBorder=50;
    public static int upperBorder=100;

    private int iterationAmount=100;
    private int processTakeCounter=0;
    private int processTakeFrom1QueueCounter=0;

    public CPUProcess() {
        this.queue1 = new CPUQueue();
        this.queue2 = new CPUQueue();
    }

    /**
     * Method generates new process- create class CPU object than decide in which queue put it on the size of
     * this queue. Put in queue which size is less
     * @return true if putting was successful, otherwise false
     */
    public boolean generateProcess(){
        CPU process=new CPU();
        synchronized (process){
            if(queue1.getSize()<=queue2.getSize()){
                try{
                    queue1.add(process);
                    System.out.print("Generated in queue1 "+process.getName());
                }catch (Exception e){
                    return false;
                }
            }else {//queue1.getSize()>queue2.getSize()
                try{
                    queue2.add(process);
                    System.out.print("Generated in queue2 "+process.getName());
                }catch (Exception e){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method takes class CPU object from queue which size is bigger. Also in method counted statistics for
     * iterationAmount iterations
     * @return CPU object
     */
    public CPU takeProcess(){
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
            try{
                generateProcess();
                synchronized (this){
                    long time=(long) (Math.random()*(CPUProcess.upperBorder - CPUProcess.lowerBorder) + CPUProcess.lowerBorder);
                    System.out.println("   Time interval="+time);
                    wait(time);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
