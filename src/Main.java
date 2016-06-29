import cpu.CPU;
import cpu.CPUProcess;

/**
 * Created by Artem on 26.06.16.
 */
public class Main {
    public static void main(String[] args) {
        CPUProcess cpuProcess=new CPUProcess();
        cpuProcess.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true){
            CPU cpu=cpuProcess.takeProcess();
            System.out.println(cpu.getName());
            cpu.run();
        }

    }
}
