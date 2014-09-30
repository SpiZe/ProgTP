package Views;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controllers.GameController;
import Models.Token;

public class GameView extends JFrame implements Observer
{
	private Token token;
	private GameController controller;
	//private Model model;
	private int nbRangees;
	private int nbColonnes;
	private JPanel jPanel;
	private GridLayout grid;
	
	GameView(GameController controller, Token token) 
	{
		InputBoardSize();
		
		this.jPanel = new JPanel();
		
	}
	
	private void InputBoardSize()
	{
		String input = JOptionPane.showInputDialog("Nombre de rangées");
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
		
		input = JOptionPane.showInputDialog("Nombre de colonnes");
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
	
	private void InitGrid()
	{
		this.grid = new GridLayout(this.nbRangees, this.nbColonnes);
		this.jPanel.setLayout(grid);
		int nbOfElements = this.nbRangees*this.nbColonnes;
		
		for (int i = 0; i < nbOfElements; i++)
		{
			
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
