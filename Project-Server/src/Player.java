import java.util.ArrayList;
import java.util.Scanner;


public class Player
{
	private ColorEnum color;
	private Board board;

	public Player(ColorEnum color, Board board)
	{
		this.color = color;
		this.board = board;
	}

	public void makeMove()
	{
		Move move;

		//Keep looping and getting moves 'till we get a valid move
		while (true)
		{
			move = getMove();
			//If we're trying to move from positions that are actually in the board
			if (!(move.getA().isOutOfBounds() || move.getB().isOutOfBounds()))
			{
				//If we're trying to move a non-existent piece
				if (board.getBoard()[move.getA().getRow()][move.getA().getCollumn()] != null)
				{
					//If we're trying to move a piece that isn't ours
					if (board.getBoard()[move.getA().getRow()][move.getA().getCollumn()].getColor() == this.color)
					{
						if (PieceCanMove(board.getBoard()[move.getA().getRow()][move.getA().getCollumn()], move, board))
						{
							System.out.println("Move is valid, hooraayy!");
							break;
						}
						else
						{
							System.out.println("Sorry, that piece cannot move there");
						}
					}
					else
					{
						System.out.println("Sorry, that piece is not yours to move");
					}
				}
				else
				{
					System.out.println("Sorry, there is no piece in the specified starting position");
				}
			}
			else
			{
				System.out.println("Sorry, the position isn't within the board");
			}
			System.out.println("You must re-enter your move");
		}
	}

	public Move getMove()
	{
		Move r;
		BoardPosition a;
		BoardPosition b;
		Scanner sc = new Scanner(System.in);
		int row;
		String collumn;

		System.out.print("Enter the row of the position of the piece you would like to move: ");		
		row = sc.nextInt();

		System.out.print("Enter the collumn of the position of the piece you would like to move: ");
		collumn = sc.next();

		a = new BoardPosition(6 - row, getNumForCollumn(collumn));

		System.out.print("Enter the row of the position you would like to move this piece to: ");		
		row = sc.nextInt();

		System.out.print("Enter the collumn of the position you would like to move this piece to: ");		
		collumn = sc.next();

		b = new BoardPosition(row, getNumForCollumn(collumn));

		r = new Move(a, b);

		return r;
	}

	private int getNumForCollumn(String i)
	{
		ArrayList<String> colNames = new ArrayList<String>();

		colNames.add("a");
		colNames.add("b");
		colNames.add("c");
		colNames.add("d");
		colNames.add("e");
		colNames.add("f");

		int r = colNames.indexOf(i);

		return r;
	}

	public ColorEnum getColor()
	{
		return color;
	}

	public boolean PieceCanMove(Piece piece, Move move, Board board)
	{
		boolean r = false;

		//If we're dealing with a knight
		if (piece.getPiece() == PieceEnum.KNIGHT)
		{
			//If we're trying to capture
			if (board.getBoard()[move.getB().getRow()][move.getB().getCollumn()] != null)
			{
				//We can only capture enemy pieces
				if (board.getBoard()[move.getB().getRow()][move.getB().getCollumn()].getColor() != piece.getColor())
				{
					//If this is one of the eight positions the knight can move to
					if(validateKnightMove(move))
					{
						r = true;
					}
					else
					{
						System.out.println("Sorry, you cannot move your knight there");
					}
				}
			}
			else
			{
				if(validateKnightMove(move))
				{
					r = true;
				}
				else
				{
					System.out.println("Sorry, you cannot move your knight there");
				}
			}
		}
		else //We have a pawn
		{
			//If we're not trying to capture
			if (move.getA().getCollumn() == move.getB().getCollumn())
			{
				if (piece.getColor() == ColorEnum.WHITE)
				{
					//White pawns can only move up
					if (move.getA().getRow() == move.getB().getRow() + 1 && board.getBoard()[move.getB().getRow()][move.getB().getCollumn()] == null)
					{
						r = true;
					}
					else
					{
						System.out.println("Sorry, you cannot move your pawn there");
					}
				}
				else //The piece is black
				{
					//Black pawns can only move down
					if (move.getA().getCollumn() == move.getB().getCollumn() - 1 && board.getBoard()[move.getB().getRow()][move.getB().getCollumn()] == null)
					{
						r = true;
					}
					else
					{
						System.out.println("Sorry, you cannot move your pawn there");
					}
				}
			}
			else //We're trying to capture
			{
				//Are we really trying to capture or is the destination empty?
				if (board.getBoard()[move.getB().getRow()][move.getB().getCollumn()] != null)
				{
					//We can only capture enemy pieces
					if (board.getBoard()[move.getB().getRow()][move.getB().getCollumn()].getColor() != piece.getColor())
					{
						//We can only capture diagonally
						if (move.getA().getCollumn() == move.getA().getCollumn() + 1 || move.getA().getCollumn() == move.getA().getCollumn() - 1)
						{
							if (piece.getColor() == ColorEnum.WHITE)
							{
								//White pawns can only move up
								if (move.getA().getRow() == move.getB().getRow() + 1)
								{
									r = true;
								}
								else
								{
									System.out.println("Sorry, you cannot move your pawn there");
								}
							}
							else //The piece is black
							{
								//Black pawns can only move down
								if (move.getA().getCollumn() == move.getB().getCollumn() - 1)
								{
									r = true;
								}
								else
								{
									System.out.println("Sorry, you cannot move your pawn there");
								}
							}
						}
						else
						{
							System.out.println("Sorry, you cannot move your pawn there");
						}
					}
					else
					{
						System.out.println("Sorry, you cannot move your pawn there");
					}
				}
				else
				{
					System.out.println("Sorry, you cannot move your pawn there");
				}
			}
		}

		return r;
	}
	
	public boolean validateKnightMove(Move move)
	{
		return (move.getB().getCollumn() == move.getA().getCollumn() - 2 && (move.getB().getRow() == move.getA().getRow() + 1 || move.getB().getRow() == move.getA().getRow() - 1))
				|| (move.getB().getCollumn() == move.getA().getCollumn() + 2 && (move.getB().getRow() == move.getA().getRow() + 1 || move.getB().getRow() == move.getA().getRow() - 1))
				|| (move.getB().getCollumn() == move.getA().getCollumn() - 1 && (move.getB().getRow() == move.getA().getRow() + 2 || move.getB().getRow() == move.getA().getRow() - 2))
				|| (move.getB().getCollumn() == move.getA().getCollumn() + 1 && (move.getB().getRow() == move.getA().getRow() + 2 || move.getB().getRow() == move.getA().getRow() - 2));
	}
}
