package com.homeproject.babysitting.app.babysitting.app.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService implements DomainService {

    private static final String USERS_COLLECTION = "users";
    private static final String USERNAME_KEY = "userName";
    private FirestoreService firestoreService;

    public UserService(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

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

    @Override
    public List<Map<String, Object>> findAll() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentsFromCollection(USERS_COLLECTION);
    }

    @Override
    public List<String> findAllNames() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentNamesFromCollection(USERS_COLLECTION);
    }

    @Override
    public Map<String, Object> findById(String username) {
        return firestoreService.getDocumentFields(USERS_COLLECTION, username);
    }

    @Override
    public Map<String, Object> create(Map<String, Object> values) {
        String userName;
        try {
            userName = values.get(USERNAME_KEY).toString();
            if (userName.isEmpty()) throw new IllegalArgumentException();
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        }
        return firestoreService.createFireStoreDocument(USERS_COLLECTION, userName, values);
    }

    @Override
    public Map<String, Object> update(Map<String, Object> values) {
        String userName;
        try {
            userName = values.get(USERNAME_KEY).toString();
            if (userName.isEmpty()) throw new IllegalArgumentException();
        } catch (NullPointerException e) {
            throw e;
        }
        return firestoreService.updateFireStoreDocument(USERS_COLLECTION, userName, values);
    }
}

