/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

/**
 *
 * @author DilanHO
 */
public class TimelineController extends Thread {
    
    private int ActualTime;

    @Override
    public void run() {
        // TODO implement here
        try {
            Thread.sleep(1);
            ActualTime++;
        } 
            catch (InterruptedException ex) {System.out.println("Biiip");}
    }
    
    public int getActualTime() {
        return ActualTime;
    }
    
    
    
    
    
}
