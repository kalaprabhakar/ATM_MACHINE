package com.icici.atm.factory;

import com.icici.atm.source.atm;

public class atmFactory {
    public static atm getatmobj(){
        return  new atm();
    }
}
