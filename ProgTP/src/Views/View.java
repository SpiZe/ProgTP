package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controllers.GameController;
/**
 * 
 * @author Keven, Olivier, Samuel
 *
 */
public class View extends JFrame
{
	private static final String IMAGE_PATH = "./IMAGES/Connect4/";

	private static final long serialVersionUID = 1L;

	private JButton[] controlButtons;

	private MyImageContainer[][] placeHolders;

	private final JTextField message = new JTextField(20);
	private final JPanel centerPane = new JPanel();
	
	private GameController controller;

	/**
	 * 
	 * @param controller Le GameController de l'application est pas� � la vue pour lui indiquer quoi afficher.
	 */
	public View(GameController controller)
	{
		this.controller = controller;
		
		askDimension();


		this.setTitle("Connect4");

		this.configureWindow();

		this.setLayout(new BorderLayout());
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		panelNorth.add(this.message);
		this.message.setEditable(false);
		this.message.setText("Welcome!");
		this.add(panelNorth, BorderLayout.NORTH);
		this.createMenu();
		this.setVisible(true);
	}
	/**
	 * Fonction appel� lorsque la partie fini et que l'utilisateur veut rejouer.
	 */
	public void Reset()
	{
		askDimension();
	}
	
	/**
	 * Demande � l'utilisateur s'il veut refaire une partie.
	 * @param winner Le nom de la personne qui a gagn�.
	 */
	public void GameIsWon(String winner)
	{
		String message = winner + " a gagn�! Voulez vous rejouer?";
		int reply = JOptionPane.showConfirmDialog(null, message, "F�licitations", JOptionPane.YES_NO_OPTION);
	    if (reply == JOptionPane.NO_OPTION)
	    {
	      System.exit(0);
	    }
	    if (reply == JOptionPane.YES_OPTION)
	    {
	    	controller.reset();
	    }
	}

	/**
	 * Cr�e la barre de menu dans le haut de la fen�tre de l'application.
	 */
	private void createMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenuItem resetMenuItem = new JMenuItem("Reset");
		resetMenuItem.addActionListener(new ResetActionHandler());
		gameMenu.add(resetMenuItem);
		menuBar.add(gameMenu);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new AboutActionHandler());
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);

		this.setJMenuBar(menuBar);
	}
	
	/**
	 *  Cette fonction permet d'initiliser la grille de jeu.
	 * @param nbRows Le nombre de rang�e � mettre dans la grille de jeu
	 * @param nbColumns Le nombre de colonnes � mettre dans la grille de jeu.
	 */
	public void initBoard(int nbRows, int nbColumns)
	{		
		this.centerPane.removeAll();
		this.placeHolders = new MyImageContainer[nbRows][nbColumns];
		this.controlButtons = new JButton[nbColumns];

		centerPane.setLayout(new GridLayout(nbRows + 1, nbColumns));

		for (int i = 0; i < nbColumns; i++)
		{
			JButton button = new JButton("T");
			this.controlButtons[i] = button;
			button.addActionListener(new ButtonHandler(i));
			centerPane.add(button);
		}

		for (int row = nbRows - 1; row >= 0; row--)
		{
			for (int column = 0; column < nbColumns; column++)
			{
				MyImageContainer button = new MyImageContainer();
				button.setOpaque(true);
				placeHolders[row][column] = button;
				button.setImageIcon(new ImageIcon("src/Images/empty.jpg"));
				centerPane.add(button);
			}
		}
		this.add(centerPane, BorderLayout.CENTER);
		this.revalidate();
	}

	/**
	 * Cette fonction permet d'arranger la grandeur de la fen�tre et sa place dans l'�cran.
	 */
	private void configureWindow()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(((screenSize.width * 3) / 6), ((screenSize.height * 4) / 7));
		setLocation(((screenSize.width - getWidth()) / 2), ((screenSize.height - getHeight()) / 2));
	}

	/**
	 *Cette classe est l'�couteur d'�v�nement pour les boutons qui placent les jetons dans la grille de jeu.
	 */
	private class ButtonHandler implements ActionListener
	{
		private final int columnIndex;

		private ButtonHandler(int columnIndex)
		{
			this.columnIndex = columnIndex;
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			System.out.println("Action on button: " + columnIndex);
			
			controller.add(columnIndex);
		}
	}
	/**
	 * Cette classe g�re les �v�nements du bouton reset
	 */
	private class ResetActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			controller.reset();
			System.out.println("Action on menu");
		}
	}
/**
 * Cette classe g�re les �v�nements du bouton reset.
 */
	private class AboutActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JOptionPane.showMessageDialog(View.this, "GUI for Connect4\n420-520-SF TP1\n\nAuthor: Fran�ois Gagnon", "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Fonction qui demande � l'utilisateur les informations concernants les colonnes, les rang�es et le nombre de jeton pour gagner.
	 */
	private void askDimension()
	{
		String input = JOptionPane.showInputDialog("Nombre de rang�es");
		try
		{
			if (input != null)
			{
				controller.setNbRow(Integer.parseInt(input));
			}			
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}

		input = JOptionPane.showInputDialog("Nombre de colonnes");
		try
		{
			if (input != null)
			{
				controller.setNbColumns(Integer.parseInt(input));
			}
			
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		input = JOptionPane.showInputDialog("Nombre de jeton en ligne pour gagner");
		try
		{
			if (input != null)
			{
				controller.setNbTokenToWin(Integer.parseInt(input));
			}
			
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
	}
/**
 * Cette fonction est appeler pour placer un jeton sur la grille de jeu. 
 * @param i Position X du jeton
 * @param j Position Y du jeton
 * @param isPlayer1 V�rifie si c'est le 1er joueur ou le second pour savoir quel image afficher.
 */
	public void UpdateToken(int i, int j, boolean isPlayer1) 
	{
		if(isPlayer1)
		{
			placeHolders[i][j].setImageIcon(new ImageIcon("src/Images/shrek.jpg"));
		}
		else
		{
			placeHolders[i][j].setImageIcon(new ImageIcon("src/Images/nicolas.jpg"));
		}
		
	}

}
