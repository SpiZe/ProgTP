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

public class View extends JFrame
{
	private static final String IMAGE_PATH = "./IMAGES/Connect4/";

	private static final long serialVersionUID = 1L;

	private JButton[] controlButtons;

	private MyImageContainer[][] placeHolders;

	private final JTextField message = new JTextField(20);
	private final JPanel centerPane = new JPanel();
	
	private GameController controller;

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

	private void createMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenuItem resignMenuItem = new JMenuItem("Resign");
		resignMenuItem.addActionListener(new ResignActionHandler());
		gameMenu.add(resignMenuItem);
		menuBar.add(gameMenu);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new AboutActionHandler());
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);

		this.setJMenuBar(menuBar);
	}

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

	private void configureWindow()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(((screenSize.width * 3) / 6), ((screenSize.height * 4) / 7));
		setLocation(((screenSize.width - getWidth()) / 2), ((screenSize.height - getHeight()) / 2));
	}

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

	private class ResignActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			System.out.println("Action on menu");
		}
	}

	private class AboutActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JOptionPane.showMessageDialog(View.this, "GUI for Connect4\n420-520-SF TP1\n\nAuthor: François Gagnon", "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	
	private void askDimension()
	{
		String input = JOptionPane.showInputDialog("Nombre de rangées");
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
