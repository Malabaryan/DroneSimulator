/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author edger
 */
public class Report<T> {
    private int actualTime;
    private T value;

    public Report(int actualTime, T value) {
        this.actualTime = actualTime;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public int getActualTime() {
        return actualTime;
    }
    
}
