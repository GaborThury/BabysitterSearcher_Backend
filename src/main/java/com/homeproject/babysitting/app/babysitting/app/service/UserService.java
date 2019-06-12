package com.homeproject.babysitting.app.babysitting.app.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService implements DomainService {

    private static final String USERS_COLLECTION = "users";
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
    public Map<String, Object> create(String username, Map<String, Object> values) {
        return firestoreService.createFireStoreDocument(USERS_COLLECTION, username, values);
    }

    @Override
    public Map<String, Object> update(String username, Map<String, Object> values) {
        return firestoreService.updateFireStoreDocument(USERS_COLLECTION, username, values);
    }
}

