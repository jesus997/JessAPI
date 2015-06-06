package me.JessHilario.Snippets;

import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable{
	private int timer = 60;
	private int seg = 60;
	private boolean close = true;
	
	public Countdown(int startMin, int startSeg) {
		this.timer = startMin;
		this.seg = startSeg;
	}
	
	public void run() {
		if(close){
			if (timer == 0 && seg == 0) {
				// Codigo cuando termine la cuenta regresiva.
			}
		
			if(seg <= 0){
				timer--;
				seg = 60;
				if(timer < 0){
					timer = 0;
					seg = 0;
				}
			}else{
				seg--;
			}
		}
	}
	
	public String timeToString(){
		return (timer < 10 ? "0" + timer : timer) + ":" + (seg < 10 ? "0" + seg : seg);
	}
}
