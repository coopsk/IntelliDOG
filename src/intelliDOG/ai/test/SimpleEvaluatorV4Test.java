package intelliDOG.ai.test;

import intelliDOG.ai.evaluators.SimpleEvaluatorV4;
import intelliDOG.ai.evaluators.SimpleEvaluatorV5;
import intelliDOG.ai.framework.BotBoard;
import intelliDOG.ai.framework.InformationGatherer;
import intelliDOG.ai.framework.Move;
import intelliDOG.ai.utils.DebugMsg;
import static org.junit.Assert.*; 

import java.util.List;
import org.junit.Before;
import org.junit.Test;
/**
 * tests some situations with the simpleEvaluatorV4
 */
public class SimpleEvaluatorV4Test 
{
	private byte[] board = null;
	private SimpleEvaluatorV4 eval = new SimpleEvaluatorV4(); 
	static DebugMsg msg = DebugMsg.getInstance();
	
	/**
	 * initialize
	 */
	@Before
	public void setUp()
	{
		board = new byte[80];
		//msg.addItemForWhiteList(eval);
	}
	/**
	 * tests if the player really helps his ally or have some wrong
	 * goals
	 */
	@Test
	public void testPlayWithAlly() throws Exception 
	{
		byte player = 1;
		board[64] = 5;
		board[65] = 5;
		board[66] = 5;
		board[67] = 5;
		board[40] = 3;
		board[58] = 2;
		board[30] = 2;
		int[] mycards = new int[]{3,12,11,-1,-1,-1};
		InformationGatherer ig = new InformationGatherer(player);
		ig.setCardsForPlayer(mycards, player);
		BotBoard b = new BotBoard(board, ig);
		List<Move> possible = b.getAllPossibleMoves(player);
		int highest = 0; 
		for(int i = 0; i < possible.size(); i++)
		{
			b.makeMove(possible.get(i), player);
			int result = eval.evaluate(b.getBoard(), player, mycards, 1);
			if(highest < result)
			{
				highest = result;
			}
			b.undoMove(player);
		}
		assertEquals(2803,highest);
	}
	
	/**
	 * test case for the problem with the not attractive bank field
	 */
	@Test
	public void testMoveOnBank() throws Exception
	{
		byte player = 1;
		board[62] = 1;
		int[] mycards = new int[]{2,12,2,-1,-1,-1};
		InformationGatherer ig = new InformationGatherer(player);
		ig.setCardsForPlayer(mycards, player);
		BotBoard b = new BotBoard(board, ig);
		List<Move> possible = b.getAllPossibleMoves(player);
		int highest = 0; 
		for(int i = 0; i < possible.size(); i++)
		{
			b.makeMove(possible.get(i), player);
			int result = eval.evaluate(b.getBoard(), player, mycards, 1);
			if(highest < result)
			{
				highest = result;
			}
			b.undoMove(player);
		}
		assertEquals(637,highest);
	}
	/**
	 * tests if the move into the heaven is attractive even when 
	 * the ally lays back. 
	 */
	@Test
	public void testEnterHeaven() throws Exception
	{
		byte player = 1;
		board[0] = 1;
		board[65] = 5;
		board[66] = 5;
		board[67] = 5; 
		int[] mycards = new int[]{100,12,2,-1,-1,-1};
		InformationGatherer ig = new InformationGatherer(player);
		ig.setCardsForPlayer(mycards, player);
		BotBoard b = new BotBoard(board, ig);
		List<Move> possible = b.getAllPossibleMoves(player); 
		int highest = 0; 
		for(int i = 0; i < possible.size(); i++)
		{
			b.makeMove(possible.get(i), player);
			int result = eval.evaluate(b.getBoard(), player, mycards, 1);
			if(highest < result)
			{
				highest = result;
			}
			b.undoMove(player);
		}
		assertEquals(2765,highest);
	}
	


	@Test
	   public void testEnemyAllInHeaven() throws Exception
	   {
		  msg.addItemForWhiteList(new SimpleEvaluatorV5());
	       byte player = 3;
	       board[64] = 5;// P1
	       board[67] = 5;
	       board[75] = 5;// P3
	       board[68] = 5;
	       board[69] = 5;
	       board[70] = 5;
	       board[71] = 5;
	       board[76] = 5;
	       board[77] = 5;
	       board[78] = 5;
	       board[79] = 5;
	                   int[] mycards = new int[]{100,12,2,-1,-1,-1};
	       InformationGatherer ig = new InformationGatherer(player);
	       ig.setCardsForPlayer(mycards, player);
	       BotBoard b = new BotBoard(board, ig);
	       SimpleEvaluatorV5 eval5 = new SimpleEvaluatorV5();
	       int result = eval5.evaluate(b.getBoard(), player, mycards, 1);
	       System.out.println("result: " + result);
	   } 
}
