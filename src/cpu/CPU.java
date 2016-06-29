package cpu;

/**
 * Created by Artem on 29.06.16.
 */
public class CPU implements Runnable {
    private long processTime;
    private String name;

    public CPU() {
        processExecuting();
        name=System.currentTimeMillis()+"";
    }

    public void processExecuting(){
         processTime=(long) (Math.random()*(CPUProcess.upperBorder - CPUProcess.lowerBorder) + CPUProcess.lowerBorder);
    }


    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try{
            System.out.println("Executing:          "+name+ "   Time="+processTime);
            System.out.println();
            synchronized (this){
                wait(processTime);
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
