package cpu;

/**
 * Class contains two queues and two static variables that are borders for time waiting and three variable for
 * statistics counting and method to generate process and take process from queue
 */
public class CPUProcess extends Thread{
    private CPUQueue queue1;
    private CPUQueue queue2;

    public CPUProcess(CPUQueue queue1,CPUQueue queue2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    /**
     * Method generates new process- create class Process object than decide in which queue put it on the size of
     * this queue. Put in queue which size is less
     * @return true if putting was successful, otherwise false
     */
    public boolean generateProcess(){
        Process process=new Process();
        synchronized (process){
            if(queue1.getSize()<=queue2.getSize()){
                try{
                    queue1.add(process);
                    System.out.print("Generated in queue1 "+process.getProcessName());
                }catch (Exception e){
                    return false;
                }
            }else {//queue1.getSize()>queue2.getSize()
                try{
                    queue2.add(process);
                    System.out.print("Generated in queue2 "+process.getProcessName());
                }catch (Exception e){
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void run() {
        while(true){
            try{
                generateProcess();
                synchronized (this){
                    long time=(long) (Math.random()*(Runner.upperBorder - Runner.lowerBorder) + Runner.lowerBorder);
                    System.out.println("   Time interval="+time);
                    wait(time);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
