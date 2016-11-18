package com.millross.spring.boot.vertx.controller;

import com.millross.spring.boot.vertx.SimpleVertxEBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class DemoController {

    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private SimpleVertxEBService service;

    @RequestMapping("/showDemo")
    public String index(Model model) throws Exception {
        final Integer counter = service.invoke();
        model.addAttribute("invocationCount", counter);
        return "showDemo";
    }


}
