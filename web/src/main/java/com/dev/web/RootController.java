package com.dev.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/")
public class RootController {

    @RequestMapping(method = RequestMethod.GET)
    public String root(){
        return "redirect:books";
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String books(){
        return "books";
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String users(){
        return "users";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(ModelMap model, @RequestParam(value = "error", required = false) boolean error){
        model.put("error", error);
        return "login";
    }
}
