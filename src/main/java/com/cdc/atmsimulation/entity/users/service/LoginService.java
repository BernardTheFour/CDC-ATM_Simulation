package com.cdc.atmsimulation.entity.users.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {    

    public boolean validateLoginInput(String accountNumber, String pin){
        if (accountNumber.length() != 6 || !accountNumber.matches("[0-9]+")){
            return false;
        }

        if (pin.length() != 6 || !pin.matches("[0-9]+")){
            return false;
        }

        return true;
    }
}
