package trab1_ProcessamentoParaleleo;

public class Garfo {
	
	private static Garfo all[];

	private int index;
	private Filosofo filosofo;
	
	public static void generate(int quantity){
		all = new Garfo[quantity];
		for(int x = 0; x < quantity; x++)
			all[x] = new Garfo(x);
	}
	
	public static Garfo getForkAtIndex(int index){
		int forkLen = all.length;
		index = (index + forkLen) % forkLen;
		return all[index];
	}
	
	public Garfo(int num){
		index = num;
	}
	
	public boolean belongsTo(Filosofo f){
		return filosofo == f;
	}
	
	public synchronized boolean catchIt(Filosofo f){
		if(filosofo == null)
			filosofo = f;
		return belongsTo(f);
	}
	
	public synchronized boolean dropIt(Filosofo f){
		if(belongsTo(f)){
			filosofo = null;
			return true;
		}
		return false;
	}

	@Override
	public String toString(){
		return "Garfo " + (index + 1);
	}

}
