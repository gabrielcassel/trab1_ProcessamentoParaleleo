package trab1_ProcessamentoParaleleo;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Mesa extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Mesa(){
		super();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, Filosofo.all.length));
		setBounds(300, 300, 800, 120);
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Container container = getContentPane();
			container.removeAll();
			for(Filosofo filosofo : Filosofo.all){
				Component label = new JLabel("<html>" + filosofo.toString("<br />") + "</html>", SwingConstants.CENTER);
				container.add(label);
			}
			setVisible(true);				
		}
	}
	
}
