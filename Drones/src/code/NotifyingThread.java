/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author Carlos
 */
public abstract class NotifyingThread extends Thread {
  private final Set<ThreadCompleteListener> listeners
                   = new CopyOnWriteArraySet<ThreadCompleteListener>();
  public final void addListener(final ThreadCompleteListener listener) {
    listeners.add(listener);
  }
  public final void removeListener(final ThreadCompleteListener listener) {
    listeners.remove(listener);
  }
  private final void notifyListeners(long stime) {
    for (ThreadCompleteListener listener : listeners) {
      listener.notifyOfThreadComplete(this, stime);
    }
  }
  @Override
  public final void run() {
    long time = 0;
    try {
      time = doRun();
    } finally {
      notifyListeners(time);
    }
  }
  public abstract long doRun();
}