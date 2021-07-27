package com.pthon.renue;

import com.pthon.renue.controllers.MainController;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class startup {
    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() throws URISyntaxException, IOException {
        MainController mainController = new MainController();
        mainController.startParser();
    }
}
