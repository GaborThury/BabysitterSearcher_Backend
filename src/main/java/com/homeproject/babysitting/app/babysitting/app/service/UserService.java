
package com.homeproject.babysitting.app.babysitting.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    @Autowired
    private FirestoreService firestoreService;

    private static final String USERS_COLLECTION = "users";

/*
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
    }*/

    public List<Map<String, Object>> findAll() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentsFromCollection(USERS_COLLECTION);
    }

    public List<String> findAllUserNames() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentNamesFromCollection(USERS_COLLECTION);
    }

    public Map<String, Object> findUserByName(String username) {
        return firestoreService.getDocumentFields(USERS_COLLECTION, username);
    }

    public Map<String, Object> create(String username, Map<String, Object> values) {
        return firestoreService.createFireStoreDocument(USERS_COLLECTION, username, values);
    }

    public Map<String, Object> update(String username, Map<String, Object> values) {
        return firestoreService.updateFireStoreDocument(USERS_COLLECTION, username, values);
    }
}

