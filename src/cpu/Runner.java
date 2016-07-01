package cpu;

/**
 * Created by Artem on 01.07.16.
 */
public class Runner {
    public static int lowerBorder=50;
    public static int upperBorder=100;

    public static void main(String[] args) {
        CPUQueue queue1=new CPUQueue();
        CPUQueue queue2=new CPUQueue();

        CPUProcess cpuProcess=new CPUProcess(queue1,queue2);
        cpuProcess.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CPU cpu=new CPU(queue1,queue2);
        cpu.start();

    }

}
