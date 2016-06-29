package cpu;

import java.util.LinkedList;

/**
 * Created by Artem on 29.06.16.
 */
public class CPUQueue {
    LinkedList<CPU> processQueue;

    public CPUQueue() {
        processQueue=new LinkedList<>();
    }

    public int getSize(){
        return processQueue.size();
    }

    public boolean add(CPU process){
        try{
            processQueue.add(process);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public CPU getProcess(){
        if(processQueue.size()>0){
            return processQueue.pollFirst();
        }else{
            return null;
        }

    }

}
