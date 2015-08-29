package trab1_ProcessamentoParaleleo;

import java.sql.Timestamp;
import java.util.Date;

public class Filosofo implements Runnable{
	
	public static Filosofo all[];
	public static String[] states = {"Meditando", "Comendo"};
	public static int defaultStarvation = 0;
	public static int starvationLimit = 100;
	public static int starvationIncreaseRate = 10;
	public static int starvationDecreaseRate = 15;

	private int index;
	private int state;
	private int starvation;
	private long lastExecution; 
	
	public static void generate(int quantity){
		all = new Filosofo[quantity];
		for(int x = 0; x < quantity;)
			all[x] = new Filosofo(++x);
	}
	
	public static long currentTimestamp(){
		Date date = new Date();
		return date.getTime();
	}
	
	public Filosofo(int num){
		index = num;
		state = 0;
		starvation = defaultStarvation;
		lastExecution = currentTimestamp();
		
		Thread thread = new Thread(this);
	    thread.start();
	}
	
	@Override
	public void run(){
		while(true){
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			long now = currentTimestamp(), diff = now - lastExecution;
			lastExecution = now;
			
			switch(state){
			case 0:
				starvation += diff / 1000.00 * starvationIncreaseRate;
			break;
			}
		}
	}

	@Override
	public String toString(){
		return "Filosofo " + index + ": " + states[state] + ". Fome: " + starvation; 
	}

}
