package com.drunkornot.lingudetect.lingu;

import java.util.ArrayList;
import java.util.List;

public class Timer {

    long timeToWait = 5000;
    long timeToFire = 0;

    private List<ITimerNotificationsListener> listeners = new ArrayList<ITimerNotificationsListener>();

    public Timer() {

    }

    public Timer(long timeToWait) {
        this.timeToWait = timeToWait;
    }

    public void AddListener(ITimerNotificationsListener listener) {
        listeners.add(listener);
    }

    public void Start() {
        timeToFire = System.currentTimeMillis() + timeToWait;
        long timeCompleted = timeToFire;
        new Thread() {
            public void run() {
                while(System.currentTimeMillis() <= timeToFire ) {
                }
                ReportCompleted(timeCompleted);
            }
        }.start();
    }

    private void ReportCompleted(long timeCompleted) {
        if(timeCompleted == timeToFire) {
            for (ITimerNotificationsListener listener : listeners) {
                listener.onCountdownEnded();
            }
        }
    }
}
