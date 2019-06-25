import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 *  The GUI is constructed in a Panel, which must then added to
 *   a JFrame, a top-level window.  (See main()).
 */
public class TicTacToeGUI extends JPanel implements ActionListener {

    private JLabel statusLabel; 
    private static JButton resetButton = new JButton("Reset");
    private TTTButton[][] gameBoard; 
    private TicTacToe tttModel;   // A reference to the model
  
    /**
     *  Constructor sets up the GUI Panel and intializes the game.
     */
    public TicTacToeGUI() {
	resetButton.addActionListener(this);
    
	initialize();  // Initialize the game (model) and the GUI.
    }

    /**
     *  Initializes the game and the GUI. This is called
     *  when the app starts and when it is reset.
     */
    private void initialize() {
	tttModel = new TicTacToe(this); // Get a fresh model

	removeAll();           // Needed for resetting the view
	setVisible(false);
	setLayout(new BorderLayout());

	// Set up a 3 x 3 grid panel then populate it with
	// the 9 TTTButtons that make up the game board.
	JPanel gridPanel = new JPanel();
	gridPanel.setLayout(new GridLayout(3,3));
	gameBoard = new TTTButton[3][3];        // A 3 x 3 array
	add("Center", gridPanel);

	// Each button needs a listener and needs to remember
	// its row and column.
	for (int i=0; i < gameBoard.length; i++) 
	    for (int j=0; j < gameBoard[i].length; j++) {
		gameBoard[i][j] = new TTTButton(" ");
		gameBoard[i][j].row = i;
		gameBoard[i][j].col = j;
		gameBoard[i][j].addActionListener(this);
		gridPanel.add(gameBoard[i][j]);
	    }

	// Set up and add the status panel to the South
	JPanel statusPanel = new JPanel(); 
	statusLabel = new JLabel("X's move!");
	statusPanel.add(statusLabel);
	statusPanel.add(resetButton);
	this.add("South",statusPanel);
	setVisible(true);
    }

    /**
     * Handles the GUI's button clicks. The reset button resets the
     *  game to its initial state.  
     * The other buttons are the buttons in the TicTacToe board.
     *  They control the moves in the game. When a board button is
     *  clicked, we call the model's guiMove() method, passing it
     *  the button's row and column values. That method updates the
     *  the state of the game and then calls the GUI's updateGUI()
     *  method.
     */
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == resetButton) {
	    initialize();
	} else {
	    TTTButton b = (TTTButton)e.getSource();  // Board button clicked
	    String status="";
	    if(!tttModel.isGameOver()) {
	    	status = tttModel.guiMove(b.row, b.col);
	 	   
		    updateGUI(status);  
		  
	    }
	    
	    if(!tttModel.isGameOver()) {
	    status=tttModel.computerMove();
	   updateGUI(status);  
	    }
	}
    }

    /**
     * This method is called from the TicTacToe object (the model).
     *  It updates the GUI based on the state of the game board and status string.
     * @param status a string reporting the model's state.
     */
    private void updateGUI(String status) {
	if (!status.equals("")) {
	    statusLabel.setText(status);

	    String[][] board = tttModel.getBoard();
	    for (int i = 0; i < board.length; i++)
		for (int j = 0; j < board[i].length; j++)
		    if (board[i][j].equals("X") || board[i][j].equals("O")) {
		    	gameBoard[i][j].setText(board[i][j]);
		    	if (board[i][j].equals("X")) {
		    		gameBoard[i][j].setBackground(Color.green);
				}
		    	else if (board[i][j].equals("O")) {
		    		gameBoard[i][j].setBackground(Color.orange);
				}
		    }
		    else {
			gameBoard[i][j].setText(" ");        
		    }
	}
    }
  
    /**
     * An inner class.  This will be compiled to TicTacToeGUI$TTTButton.
     * 
     * A TTTButton (Tic Tac Toe Button) is a JButton plus
     *  the row and column instance variables, which keep track
     *  of where the button is in the 3 x 3 Tic Tac Toe Board.
     */
    class TTTButton extends JButton {
	public int row;
	public int col;

	/**
	 * Constructor
	 * @param text is the label that appears on the button
	 */
	TTTButton(String text) {
	    super(text);
	}
    }

    /**
     *  Creates a top-level windoe (JFrame) and adds
     *   the TicTacToeGUI to it.  
     */
    public static void main(String argv[]) {
	TicTacToeGUI gui = new TicTacToeGUI();    // Our app's GUI
	resetButton.setBackground(Color.RED);
	JFrame window = new JFrame("Tic Tac Toe"); // Top-level window
	window.setLocation(0,0);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// Add the GUI to the top-level window and display it.
	Container contentPane = window.getContentPane();
	contentPane.removeAll();
	contentPane.setLayout(new BorderLayout());
	contentPane.add((JPanel) gui);             // Add the GUI
	window.pack();    
	window.setSize(200,200);
	window.setVisible(true);    
    }
}