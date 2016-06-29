package cpu;

/**
 * Created by Artem on 29.06.16.
 */
public class CPUProcess extends Thread{
    private CPUQueue queue1;
    private CPUQueue queue2;
    public static int lowerBorder=50;
    public static int upperBorder=100;

    private int processTakeCounter=0;
    private int processTakeFrom1QueueCounter=0;

    public CPUProcess() {
        this.queue1 = new CPUQueue();
        this.queue2 = new CPUQueue();
    }

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

    public CPU takeProcess(){
        if(processTakeCounter>100){
            System.out.println(processTakeFrom1QueueCounter+" "+processTakeCounter);
            System.out.println((double) processTakeFrom1QueueCounter/processTakeCounter*100);
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
