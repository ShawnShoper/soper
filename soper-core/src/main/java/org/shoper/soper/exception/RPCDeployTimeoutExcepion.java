package org.shoper.soper.exception;

import org.hyperic.sigar.RPC;

/**
 * Created by ShawnShoper on 16/6/20.
 */
public class RPCDeployTimeoutExcepion extends RPCException{
    public RPCDeployTimeoutExcepion(String msg){
        super(msg);
    }
    public RPCDeployTimeoutExcepion(Throwable e){
        super(e);
    }
}
