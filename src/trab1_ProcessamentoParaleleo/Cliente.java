package trab1_ProcessamentoParaleleo;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Cliente{
	
	private static int quantity = 0;
	public static Barbearia barberShop;
	
	private int index;
	
	public Cliente(){
		index = quantity++;
	}
	
	public String toString(){
		return "Cliente " + (index + 1);		
	}
	
	public JLabel toJLabel(){
		JLabel label = new JLabel("<html>" + toString() + "</html>", SwingConstants.CENTER);
		return label;	
	}
	
}
