package trab1_ProcessamentoParaleleo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BarberShop extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int maxQueueLength;
	private Barber barber;
	private List<Cliente> queue;
	
	
	public BarberShop(){
		super();
		
		queue = new ArrayList<Cliente>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setBounds(300, 300, 800, 120);
	}

	public void setBarber(Barber b){
		barber = b;
	}
	
	public Cliente getNextCustomer(){
		return queue.isEmpty() ? null : queue.remove(0);
	}
	
	public void addCustomer(Cliente customer){
		if(queue.size() < maxQueueLength)
			queue.add(customer);
	}

	@Override
	public void run(){
		while(true){
			Container container = getContentPane();
			container.removeAll();
			container.setVisible(false);
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.ipadx = 0;

			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 1;
			c.weighty = 1;
			
			JLabel l = new JLabel("Cadeira:");
			l.setBackground(Color.RED);
			l.setOpaque(true);
			container.add(l, c);
			c.gridx = 1;
			c.gridwidth = 5;
			l = new JLabel("Fila:");
			l.setBackground(Color.YELLOW);
			l.setOpaque(true);
			container.add(l, c);
			c.gridwidth = 1;

			c.gridx = 0;
			c.gridy = 1;
			Cliente customer = barber.getCustomer();
			l = customer != null ? customer.toJLabel() : barber.toJLabel();
			l.setBackground(Color.RED);
			container.add(l, c);
			
			for(Cliente cl: queue){
				Component label = cl.toJLabel();
				c.gridx++;
				container.add(label, c);
			}
			
			container.setVisible(true);
			setVisible(true);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
