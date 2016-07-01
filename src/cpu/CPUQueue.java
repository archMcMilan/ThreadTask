package cpu;

import java.util.LinkedList;

/**
 * Class contains LinkedList which acts as queue
 */
public class CPUQueue {
    private LinkedList<Process> processQueue;

    public CPUQueue() {
        processQueue=new LinkedList<>();
    }

    /**
     * Method wrapper to get processQueue size
     * @return processQueue elements amount
     */
    public synchronized int getSize(){
        return processQueue.size();
    }

    /**
     * Method wrapper to add element to the queue
     * @param process
     * @return  true if adding was successful, otherwise false
     */
    public synchronized boolean add(Process process){
        try{
            processQueue.add(process);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * Method wrapper to get process from processQueue
     * @return Process object if processQueue has elements, otherwise null
     */
    public synchronized Process getProcess(){
        if(processQueue.size()>0){
            return processQueue.pollFirst();
        }else{
            return null;
        }

    }

}
