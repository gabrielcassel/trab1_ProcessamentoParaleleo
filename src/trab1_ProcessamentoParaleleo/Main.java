package trab1_ProcessamentoParaleleo;

public class Main {
	
	public static void main(String args[]){
		if(args.length == 0){
			System.out.println("Você precisa passar como argumento um dos programas abaixo:");
			System.out.println("- problema-dos-filosofos");
			return;
		}
		
		if(args[0].equals("problema-dos-filosofos")){
			boolean solidarity = args.length >= 2 && args[1].equals("1");
			Main.startMeal(solidarity);
		}
	}
	
	public static void startMeal(boolean solidarity){
		int quantity = 5;
		Filosofo.solidarity = solidarity;
		
		Garfo.generate(quantity);
		Filosofo.generate(quantity);
		
		Thread t = new Thread(new Mesa());
		t.start();		
	}
	
}
