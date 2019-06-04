/*
package com.homeproject.babysitting.app.babysitting.app.service;

import com.google.firebase.auth.FirebaseAuth;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    public void fakeSaveUser(String idToken) {
        String uid = getUserIdFromToken(idToken);
        System.out.println("User id: " + uid);
    }

    private String getUserIdFromToken(String idToken) {
        String uid = null;
        try {
            uid = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get().getUid();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return uid;
    }
}
*/
