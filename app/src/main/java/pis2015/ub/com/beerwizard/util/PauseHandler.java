package pis2015.ub.com.beerwizard.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.Vector;

/**
 * Message Handler class that supports buffering up of messages when the
 * activity is paused i.e. in the background.
 * <p/>
 * Adapted to this project
 */
public abstract class PauseHandler extends Handler {
    final static Vector<Message> messageQueueBuffer = new Vector<Message>();
    protected final Activity activity;
    private boolean paused;


    public PauseHandler(Activity activity, Looper looper) {
        super(looper);
        this.activity = activity;
    }

    /**
     * Resume the handler
     */
    final public void resume() {
        paused = false;

        while (messageQueueBuffer.size() > 0) {
            final Message msg = messageQueueBuffer.elementAt(0);
            messageQueueBuffer.removeElementAt(0);
            sendMessage(msg);
        }
    }

    final public void removeAllMessages() {
        messageQueueBuffer.clear();
    }

    /**
     * Pause the handler
     */
    final public void pause() {
        paused = true;
    }

    protected abstract void processMessagePaused(Message message);

    /**
     * Notification message to be processed. This will either be directly from
     * handleMessage or played back from a saved message when the activity was
     * paused.
     *
     * @param message the message to be handled
     */
    protected abstract void processMessage(Message message);

    /**
     * {@inheritDoc}
     */
    @Override
    final public void handleMessage(Message msg) {
        if (paused) {
            Message msgCopy = new Message();
            msgCopy.copyFrom(msg);
            messageQueueBuffer.add(msgCopy);
            processMessagePaused(msg);
        } else {
            processMessage(msg);
        }
    }
}