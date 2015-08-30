package trab1_ProcessamentoParaleleo;

import java.util.Date;

public class Filosofo implements Runnable{
	
	public static Filosofo all[];
	public static int defaultStarvation = 0;
	public static int starvationLimit = 100;
	public static int starvationIncreaseRate = 10;
	public static int starvationDecreaseRate = 15;

	private int index;
	private int state;
	private int starvation;
	private long lastExecution;
	
	private Garfo leftFork;
	private Garfo rightFork;

	public static void generate(int quantity){
		all = new Filosofo[quantity];
		for(int x = 0; x < quantity; x++)
			all[x] = new Filosofo(x);
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
	
	private Garfo getForkReference(int num){
		return Garfo.getForkAtIndex(index + num);		
	}
	
	private boolean getLeftFork(){
		Garfo fork = getForkReference(1);
		boolean gotFork = fork.get(this);
		if(gotFork)
			leftFork = fork;
		return gotFork;
	}
	
	private boolean getRightFork(){
		Garfo fork = getForkReference(0);
		boolean gotFork = fork.get(this);
		if(gotFork)
			rightFork = fork;
		return gotFork;
	}
	
	private void dropForks(){
		if(leftFork != null)
			leftFork.drop(this);
		if(rightFork != null)
			rightFork.drop(this);
		
		leftFork = rightFork = null;
	}
	
	public boolean hasBothForks(){
		return leftFork != null && rightFork != null;
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
			
			long now = currentTimestamp();
			float diff = (float) ((now - lastExecution) / 1000.00);
			lastExecution = now;
			
			switch(state){
			case 0:
				starvation += diff * starvationIncreaseRate;

//				Se estiver com fome, tenta pegar garfos e comer
				if(starvation >= starvationLimit){
					getLeftFork();
					getRightFork();
					if(hasBothForks())
						state = 1;
				}
					
			break;
			case 1:
				if(hasBothForks()){
					starvation -= diff * starvationDecreaseRate;
					
					if(starvation <= defaultStarvation){
						starvation = defaultStarvation;
						state = 0;						
						dropForks();
					}
				}
			break;
			}
		}
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("Filósofo ");
		str.append(index + 1);
		str.append(": ");
		
		if(state == 0)
			str.append(starvation < starvationLimit ? "Meditando" : "Esperando");
		else
			str.append("Comendo");
		
		str.append(" - Nível de fome: ");
		str.append(starvation);
		str.append('%');
		
		str.append(" - Garfos: (");
		if(rightFork != null)
			str.append(rightFork);
		if(leftFork != null){
			if(rightFork != null)
				str.append(", ");
			str.append(leftFork);
		}
		str.append(')');
		return str.toString(); 
	}

}
