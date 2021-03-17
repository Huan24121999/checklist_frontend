package org.example.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.Timer;

public class ListItemViewModel {
    private Timer timer;

    public ListItemViewModel() {
        this.timer=new Timer();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    @Command("setTime")
    public void setTime(){
        timer.setDelay(5000);
    }

    public String getTime(){
        return "hahahah";
    }

    @Init
    public void init(){
        timer=new Timer();
    }

}
