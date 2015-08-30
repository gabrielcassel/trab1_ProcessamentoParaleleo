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
	
	public synchronized boolean get(Filosofo f){
		if(filosofo == null)
			filosofo = f;
		return f == filosofo;
	}
	
	public boolean drop(Filosofo f){
		if(f == filosofo){
			filosofo = null;
			return true;
		}		
		return false;
	}
	
	public boolean belongsTo(Filosofo f){
		return filosofo == f;
	}	

	@Override
	public String toString(){
		return "Garfo " + (index + 1);
	}

}
