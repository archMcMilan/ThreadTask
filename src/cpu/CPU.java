package cpu;

/**
 * Class contains two variable that show CPU parameters
 */
public class CPU implements Runnable {
    private long processTime;
    private String name;

    public CPU() {
        name = System.currentTimeMillis() + "";
    }

    /**
     * Method count time that is needed for process executing
     * @return
     */
    public long processExecuting() {
        processTime = (long) (Math.random() * (CPUProcess.upperBorder - CPUProcess.lowerBorder) + CPUProcess.lowerBorder);
        return processTime;
    }


    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            System.out.println("Executing:          " + name + "   Time=" + processExecuting());
            System.out.println();
            synchronized (this) {
                wait(processTime);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
