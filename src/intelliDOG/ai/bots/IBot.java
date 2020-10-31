package intelliDOG.ai.bots;

import intelliDOG.ai.evaluators.Evaluator;
import intelliDOG.ai.framework.BotBoard;
import intelliDOG.ai.framework.InformationGatherer;
import intelliDOG.ai.framework.Move;

public interface IBot{	
	
	/**
	 * This method will calculate the next move the bot want's to do.
	 * @return The <class>Move</class> that will be made, or Null if no move is possible.
	 */
	public Move makeMove();
	
	/**
	 * 
	 * @return the bot's BotBoard
	 */
	public BotBoard getBotBoard();
	
	/**
	 * 
	 * @return the bot's InformationGatherer
	 */
	public InformationGatherer getInformationGatherer();

	/**
	 * 
	 * @return the player of the bot (See <class>Players</class>)
	 */
	public byte getPlayer();
	
	/**
	 * set a desired Evaluator
	 * @param eval
	 */
	public void setEvaluator(Evaluator eval);
	
	/**
	 * Exchange a card with the partner
	 * @return the card to exchange
	 */
	public int exchangeCard(); 
	
}
