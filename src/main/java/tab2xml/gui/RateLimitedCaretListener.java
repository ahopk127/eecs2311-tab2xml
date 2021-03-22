package tab2xml.gui;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * A {@link CaretListener} that limits how often it executes.
 * 
 * @author Adrien Hopkins
 * @since 2021-03-22
 */
final class RateLimitedCaretListener implements CaretListener {
	private final CaretListener listener;
	private final long rateLimit;
	private long lastExecution;
	
	/**
	 * @param listener  listener to use
	 * @param rateLimit limit for execution in milliseconds
	 * @since 2021-03-22
	 */
	public RateLimitedCaretListener(CaretListener listener, long rateLimit) {
		this.listener = listener;
		this.rateLimit = rateLimit;
		this.lastExecution = System.currentTimeMillis();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * The call will only occur if the time since the last execution is greater
	 * than this listener's rate limit.
	 */
	@Override
	public void caretUpdate(CaretEvent e) {
		final long currentTime = System.currentTimeMillis();
		if (currentTime - this.lastExecution >= this.rateLimit) {
			this.lastExecution = currentTime;
			this.listener.caretUpdate(e);
		}
	}
}
