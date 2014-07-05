package com.trajan.seed.webapp.rest.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-05
 */
@RestController
@RequestMapping("/")
public class InfoController {

    @Value("${env}")
    private String environment;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getInfo() {
        return environment;
    }

}
