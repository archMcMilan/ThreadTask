package cpu;

/**
 * Class contains two variable that show Process parameters
 */
public class Process {
    private long processTime;
    private String name;

    public Process() {
        name = System.currentTimeMillis() + "";
    }

    /**
     * Method count time that is needed for process executing
     * @return
     */
    public long processExecuting() {
        processTime = (long) (Math.random() * (Runner.upperBorder - Runner.lowerBorder) + Runner.lowerBorder);
        return processTime;
    }


    public String getProcessName() {
        return name;
    }

    public void execute() {
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
