package com.efounder.JEnterprise.timer;

import java.util.EventListener;

/** Listener which can be notifies when a task finishes.
* @see Task
* @author Jaroslav Tulach
*/
public interface TaskListener extends EventListener {
    /** Called when a task finishes running.
    * @param task the finished task
    */
    public void taskFinished (Task task);
}
