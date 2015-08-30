package trab1_ProcessamentoParaleleo;

public class Garfo {
	
	private static Garfo all[];
	
	private Filosofo filosofo;
	
	public static void generate(int quantity){
		all = new Garfo[quantity];
		for(int x = 0; x < quantity; x++)
			all[x] = new Garfo();
	}
	
	public static Garfo getForkAtIndex(int index){
		int forkLen = all.length;
		index = (index + forkLen) % forkLen;
		return all[index];
	}
	
	public synchronized boolean get(Filosofo f){
		if(filosofo == null){
			filosofo = f;
			return true;
		}		
		return false;
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

}
