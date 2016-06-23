package org.shoper.soper.api;

import org.shoper.soper.rpc.RPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * spring context started listener...
 *
 * @author ShawnShoper
 */
@Component
public class StartedListener
        implements
        ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    RPC provider;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            provider.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
