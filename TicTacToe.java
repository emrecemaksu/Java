import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

class TicTacToe {

    // Instance variables. Each object has its own
    // copy of these. They are usually private, meaning
    // they can only be referenced within the class definition.

    /**
     *  A library object for doing keyboard I/O.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     *  This is our game board, a 2-d array of Strings.
     */
    private String board[][];

    /**
     * Represents the player, either "X" or "O".
     */
    private String player = "O";
  
    /**
     * A reference to the GUI.
     */ 
    private TicTacToeGUI tttGUI; 

    /**
     *  Keeps track of the number of legal plays and
     *  used to determine when the game is over.
     */
    private int guiPlays = 0;
    
    public Random r;

    /**
     * Flag to keep track of whether game is over.
     */
    private boolean gameOver = false;

    public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	// The Constructors
    /**
     *  The constructor is usually public, which means it can be called
     *   from outside the class definition.  Constructors have no return
     *   type.  Here it just calls makeBoard() to create the board.
     */
    public TicTacToe() {
	board = makeBoard();
    }

    /**
     *  The constructor used with the GUI.
     */
    public TicTacToe(TicTacToeGUI gui) {
	tttGUI = gui;
	board = makeBoard();
    }

    // Public and private methods. Public methods can be called from
    //  outside the class, private methods only inside the class.
    /**
     *  This method creates the 2-dimensional array that represents
     *   the tic-tac-toe board. Its cells are numbered 0 through 8.
     *  @return a 2-dimensional array of strings.
     */
    private String[][] makeBoard() {
	String[][] board = new String[3][3];
	int cell = 0;
	for (int i = 0; i < board.length; i++) 
	    for (int j = 0; j < board.length; j++) {
		board[i][j] = "" + cell;
		cell += 1;
	    }
	return board;
    }

    /**
     * Returns a reference to the tic tac toe board.
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     *  Prints the tic-tac-toe board. Should be public.
     */
    public void printBoard() {
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board.length; j++) 
		System.out.print(board[i][j] + " ");
	    System.out.println();
	}
    }

    /**
     * Used to test legal move for GUI.
     * @param row the row index of the move on a 2-d board.
     * @param col the column index of the move on a 2-d board.
     * @return true iff the square is not alreay occupied.
     */
    private boolean isLegalMove(int row, int col) {
	return (row >= 0 && row <= 2 && col >= 0 && col <= 2) &&
	    ! board[row][col].equals("X") && ! board[row][col].equals("O");
    }

    /**
     * Call back method used by the GUI after each click on a game square.
     *  This updates the state of the game and calls updateGUI() so the
     *  GUI can update its display.
     * @param row the row of the square
     * @param col the column of the square
     */
    public String guiMove(int row, int col) {
	String status = "";
	if (!gameOver && isLegalMove(row, col)) {
	    status = "X's move!";
	   
	    board[row][col] = "X";
	    ++guiPlays;
	    if (gameWon()) {
		status =  "X wins!";
		gameOver = true;
	    }
	    else if (guiPlays == 9) {
		status = "A tie!";
		gameOver = true;
	    }
	}
	return status;
    }

    /**
     *  Counts the numbers of "X"s or "O"s in the nth row.
     *  Returns a number between -3 and +3. The number will
     *  be +3 if there are 3 Xs and -3 if there are 3 Os.
     * @param n, the row number,  0 through 2
     * @return a value between -3 and +3
     */
    private int checkRow(int n) {
	int sum = 0;
	for (int i = 0; i < board[n].length; i++) 
	    if (board[n][i].equals("X"))
		sum += 1;
	    else if (board[n][i].equals("O"))
		sum -= 1;
	return sum;
    }

    /**
     *  Counts the numbers of "X"s or "O"s in the nth column.
     *  Returns a number between -3 and +3. The number will
     *  be +3 if there are 3 Xs and -3 if there are 3 Os.
     * @param n, the column number,  0 through 2
     * @return a value between -3 and +3
     */
    private int checkColumn(int n) {
	int sum = 0;
	for (int i = 0; i < board.length; i++) 
	    if (board[i][n].equals("X"))
		sum += 1;
	    else if (board[i][n].equals("O"))
		sum -= 1;
	return sum;
    }

    private int checkLeftDiagonal() {
	int sum = 0;
	for (int i = 0; i < board.length; i++) 
	    if (board[i][i].equals("X"))
		sum += 1;
	    else if (board[i][i].equals("O"))
		sum -= 1;
	return sum;
    }

    private int checkRightDiagonal() {
	int sum = 0;
	for (int i = 0; i < board.length; i++) 
	    if (board[i][board.length-i-1].equals("X"))
		sum += 1;
	    else if (board[i][board.length-i-1].equals("O"))
		sum -= 1;
	return sum;
    }

    /**
     * Return true iff some row, column or diagonal
     *  contains 3 Xs or 3 Os or all 9 squares are
     *  filled and it ends in a draw.
     */
    public boolean gameWon() {
	int score = 0;
	for (int i = 0; i < board.length; i++) {
	    score = checkRow(i);
	    if (score == 3 || score == -3)
		return true;
	}
	for (int i = 0; i < board.length; i++) {
	    score = checkColumn(i);
	    if (score == 3 || score == -3)
		return true;
	}
	score = checkLeftDiagonal();
	if (score == 3 || score == -3)
	    return true;
	score = checkRightDiagonal();
	if (score == 3 || score == -3)
	    return true;
	return false;      
    }

    /**
     * Alternates turns between player X and player O
     *  until the game is over.
     */
    public void play() {
	int plays = 0;
	while (plays < 9 && !gameWon()) {
	    player = (player.equals("X")) ? "O" : "X";
	    plays += 1;
	    System.out.println("It is " + player + "'s turn");
	    System.out.println("What cell do you play : ");
	    String input = scanner.next();                       
	    int cell = Integer.parseInt(input);
            while (! isLegalMove(cell / 3, cell % 3)) {
		System.out.println("Illegal Move. Try again.");
  	        System.out.println("What cell do you play : ");
	        input = scanner.next();                       
                cell = Integer.parseInt(input);
    	    }
            board[cell/3][cell%3] = player;          
            printBoard();
	}    
	System.out.println("Game over!");
	if (plays < 9)
	    System.out.println("The winner is " + player);
	else
	    System.out.println("That's a draw!");
    }
    
    public String computerMove() {
    	//Randomly create x, row
    	//randomly create y , ccolumn between 0-3
    	
    	//control it is in the legal move
    	String status = "";
    	//then put O
    	Random rand = new Random();
    	int o_row=0;
    	int o_column=0;
    	boolean isFound = false;
    	while(!isFound){
    		o_row = rand.nextInt(3);
        	
        	o_column = rand.nextInt(3);
        	
        	if(!gameOver && isLegalMove(o_row, o_column)) 
        	{
        		board[o_row][o_column]="O";
        		isFound=true;
        		
        		status = "X's move!";
       		 	++guiPlays;
       		    if (gameWon()) {
       			status =  "Computer wins!";
       			System.out.println(status);
       			gameOver = true;
       		    }
       		    else if (guiPlays == 9) {
       			status = "A tie!";
       			gameOver = true;
       		    }
        	}
    	}
    	
    		
    		
    	return status;
    	
    }
    

    public static void main(String argv[]) {
	TicTacToe ttt = new TicTacToe();
	ttt.printBoard();
	ttt.play();
    }
}