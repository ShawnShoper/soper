package org.shoper.soper.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ShawnShoper on 16/6/23.
 */
@RestController
public class TestController {
    @RequestMapping(value = "/say")
    public User say(User user) {
        return user;
    }
}
