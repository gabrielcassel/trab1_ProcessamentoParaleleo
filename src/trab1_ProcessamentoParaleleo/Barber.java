package trab1_ProcessamentoParaleleo;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Barber implements Runnable {

	private BarberShop barberShop;
	private Cliente customer;
	
	public Barber(BarberShop bs){
		barberShop = bs;
	}

	@Override
	public void run() {
		while(true){
			customer = barberShop.getNextCustomer();
			int duration;
			
			if(customer != null)
				duration = 2000;
			else
				duration = 300;
			
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Cliente getCustomer() {
		return customer;
	}
	
	public JLabel toJLabel(){
		JLabel label = new JLabel("<html>Barbeiro</html>", SwingConstants.CENTER);
		label.setOpaque(true);
		return label;
	}
	
}
