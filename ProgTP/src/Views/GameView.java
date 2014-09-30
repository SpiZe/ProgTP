package Views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controllers.GameController;

public class GameView extends JFrame implements Observer
{
	private GameController controller;
	//private Model model;
	private int nbRangees;
	private int nbColonnes;
	private JPanel jPanel;
	
	GameView(GameController controller) 
	{
		InputBoardSize();
		
		this.jPanel = new JPanel();
		
	}
	
	private void InputBoardSize()
	{
		String input = JOptionPane.showInputDialog("Nombre de bouton");
		try 
		{
			if (input == null) 
			{
				System.exit(0);
			}
			this.nbRangees = Integer.parseInt(input);
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		}
		
		input = JOptionPane.showInputDialog("Nombre de bouton");
		try 
		{
			if (input == null) 
			{
				System.exit(0);
			}
			this.nbColonnes = Integer.parseInt(input);
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		}	
	}

	private void InitWindow()
	{
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
