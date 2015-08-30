package trab1_ProcessamentoParaleleo;

public class Main {
	
	public static void main(String args[]){
		if(args.length == 0){
			System.out.println("Você precisa passar como argumento um dos programas abaixo:");
			System.out.println("- problema-dos-filosofos");
			return;
		}
		
		if(args[0].equals("problema-dos-filosofos")){
			boolean solidarity = args.length >= 2 &&  args[1].equals("1");
			Main.startMeal(solidarity);
		}
	}
	
	public static void startMeal(boolean solidarity){
		int quantity = 5;
		Filosofo.solidarity = solidarity;
		
		Garfo.generate(quantity);
		Filosofo.generate(quantity);
		
//		Filosofo[0].getForkReference(1);
		
		Runnable r = () -> {
			while(true){
				try {
					Thread.sleep(250);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.print('\f');
				for(Filosofo filosofo : Filosofo.all)
					System.out.println(filosofo);
			}
		};
		Thread t = new Thread(r);
		t.start();
		
		
	}
	
}
