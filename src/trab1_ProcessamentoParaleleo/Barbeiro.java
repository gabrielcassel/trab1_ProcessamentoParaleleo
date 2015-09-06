package trab1_ProcessamentoParaleleo;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Barbeiro implements Runnable {

	private Barbearia barberShop;
	private Cliente customer;
	
	public Barbeiro(Barbearia bs){
		barberShop = bs;
	}

	@Override
	public void run() {
		while(true){
			customer = barberShop.getNextCustomer();
			int duration = customer != null ? 2000 : 300;
			
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
