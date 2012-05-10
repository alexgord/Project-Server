
public class Game
{
	
	private Board board;
	
	private Player player1;
	private Player player2;
	
	public Player getPlayer1()
	{
		return player1;
	}

	public void setPlayer1(Player player1)
	{
		this.player1 = player1;
	}

	public Player getPlayer2()
	{
		return player2;
	}

	public void setPlayer2(Player player2)
	{
		this.player2 = player2;
	}

	public Game()
	{
		board = new Board();
		player1 = new Player(ColorEnum.WHITE, board);
		player2 = new Player(ColorEnum.BLACK, board);
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public void makeMoveForCurrentPlayer()
	{
		player1.makeMove();
	}
}
