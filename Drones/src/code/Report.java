/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

/**
 *
 * @author DilanHO
 */
public class Report {
    
    int BeginReport;
    int FinishReport;
    int TravelsFinished;
    
    void beginReport(int time){
        BeginReport = time;
    }
    
    void finishReport(int time, int travels){
        BeginReport = time;
        TravelsFinished = travels;
    }
    
    
    
}
