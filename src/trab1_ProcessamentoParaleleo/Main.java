package trab1_ProcessamentoParaleleo;

public class Main {
	
	public static void main(String args[]){
		if(args.length == 0){
			System.out.println("Você precisa passar como argumento um dos programas abaixo:");
			System.out.println("- problema-dos-filosofos");
			System.out.println("- barbeiro-dorminhoco");
			return;
		}
		
		switch(args[0]){
		case "problema-dos-filosofos":
			boolean solidarity = args.length >= 2 && args[1].equals("1");
			startMeal(solidarity);
		break;
		case "barbeiro-dorminhoco":
			startBarberShop();
		break;
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
	
	public static void startBarberShop(){
		Barbearia.maxQueueLength = 5;
		
		Barbearia barberShop = new Barbearia();
		Barbeiro barber = new Barbeiro(barberShop);
		barberShop.setBarber(barber);
		
		Thread barberShopThread = new Thread(barberShop);
		barberShopThread.start();
		Thread barberThread = new Thread(barber);
		barberThread.start();
		
		Runnable generator = () -> {
			while(true){
				barberShop.addCustomer(new Cliente());
				
				try {
					Thread.sleep((long) (Math.random() * 4000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Thread generatorThread = new Thread(generator);
		generatorThread.start();
	}
	
}
