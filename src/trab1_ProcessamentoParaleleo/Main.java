package trab1_ProcessamentoParaleleo;

public class Main {
	
	public static void main(String args[]){
		Main.startMeal();
	}
	
	public static void startMeal(){
		int quantity = 5;

//		Garfo.generate(quantity);
		Filosofo.generate(quantity);
		
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
