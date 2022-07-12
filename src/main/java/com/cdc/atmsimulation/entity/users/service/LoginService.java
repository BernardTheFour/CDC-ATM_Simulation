package com.cdc.atmsimulation.entity.users.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {    

    public  void validateLoginInput(String accountNumber, String pin) throws Exception{
        if (accountNumber.length() != 6 || pin.length() != 6){
            throw new Exception("must have 6 digit length");
        }

        if (!accountNumber.matches("[0-9]+") || !pin.matches("[0-9]+")){
            throw new Exception("must be a number");
        }
    }
}
