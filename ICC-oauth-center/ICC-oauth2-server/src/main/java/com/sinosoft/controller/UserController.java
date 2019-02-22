package com.sinosoft.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class UserController {
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/token_key")
    public String token_key(){
            return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApc1SzM4ZiU1EcBdYtgpr\n" +
                    "GjFQJpN+VVwSm79koU+6NE7hym9jE0IgSCsdOEPvuBij6uEC0BhNQy+R73R0qc38\n" +
                    "/AOP5at7bYZyEcZduQr8vDfNpX4TYMHzcjbyXygGvnjNDJvW1RIWonLUbpk/STNe\n" +
                    "N5OYrn030sWYkAiT4hRtdeki7CwDlVOApleovVKXavGLJl5KWRG1bYm4hZLdl22H\n" +
                    "/uCJCVfP/0qYyjhK4ZQeVe+88o6ogHIi41ymrxQwcYSJZ8e4Py9fLpoDHJ0nn7ZS\n" +
                    "fykJyUJ58tyhSJXijt1ZaHbdKgHbbTzl8EZd1hCGaFDiogeLGn7JoaQMUgbmEEqj";
    }
}
