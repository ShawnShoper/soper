package org.shoper.soper.exception;

/**
 * Created by ShawnShoper on 16/6/20.
 */
public class RPCException extends Throwable {
    public RPCException(String message) {
        super(message);
    }

    public RPCException(Throwable cause) {
        super(cause);
    }
}
