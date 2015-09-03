package trab1_ProcessamentoParaleleo;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BarberShop extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int maxQueueLength;
	private Barber barber;
	private List<Cliente> queue; // Sync this obj
	
	
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
		synchronized(queue){
			return queue.isEmpty() ? null : queue.remove(0);
		}
	}
	
	public void addCustomer(Cliente customer){
		synchronized(queue){
			if(queue.size() < maxQueueLength)
				queue.add(customer);
		}
	}

	@Override
	public void run(){
		while(true){
			Container container = getContentPane();
			container.removeAll();
//			container.setVisible(false);
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.ipadx = 0;

			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 1;
			c.weighty = 1;
			
			Color barberColor = new Color(234, 67, 53), queueColor = new Color(66, 133, 244);
//			Dimension sitSize = new Dimension(150, 50);
			
			JLabel l = new JLabel("Cadeira:");
			l.setOpaque(true);
			l.setBackground(barberColor.brighter());
			l.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, barberColor));
			container.add(l, c);
			
			c.gridx = 1;
			c.gridwidth = 5;
			l = new JLabel("Fila:");
			l.setOpaque(true);
			l.setBackground(queueColor.brighter());
			l.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, queueColor));
			container.add(l, c);
			c.gridwidth = 1;

			c.gridx = 0;
			c.gridy = 1;
			Cliente customer = barber.getCustomer();
			l = customer != null ? customer.toJLabel() : barber.toJLabel();
			l.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, barberColor));
//			l.setSize(sitSize);
			container.add(l, c);
			
			Object customarArr[];
			synchronized(queue){
				customarArr = queue.toArray();
			}
			
			for(int x = 0; x < 5; x++){
				JLabel label;
				if(x < customarArr.length){
					Cliente customer1 = (Cliente) customarArr[x];
					label = customer1.toJLabel();
				}
				else
					label = new JLabel("Cadeira desocupada", SwingConstants.CENTER);
//				label.setSize(sitSize);
				label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, queueColor));
				c.gridx++;
				container.add(label, c);
			}
			
			container.setVisible(true);
			setVisible(true);
			
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
