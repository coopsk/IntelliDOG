package intelliDOG.ai.learning.ann.net.exceptions;

/**
 * Spezifische Exceptionklasse für das Teacher-Management.
 * 
 * @author Iseli Andreas
 * @date 23.04.08
 */
public class NeuralNetTeacherNotValidException extends NeuralNetException {
	private static final long serialVersionUID = 1L;
	public NeuralNetTeacherNotValidException() {
		super();
	}
	public NeuralNetTeacherNotValidException(String msg) {
		super(msg);
	}
}
