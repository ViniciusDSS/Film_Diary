package com.example.af_mobile.config;

import com.google.firebase.auth.FirebaseAuth;


public class ConfigBD {

    private static FirebaseAuth auth;

    public static FirebaseAuth autenticacao(){
        if (auth == null){
                auth =FirebaseAuth.getInstance();
        }
        return auth;
    }
}
