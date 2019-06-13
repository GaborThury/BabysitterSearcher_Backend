package com.homeproject.babysitting.app.babysitting.app.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
    public Map<String, Object> create(Map<String, Object> values) throws IllegalArgumentException, NullPointerException {
        String userName = values.get(USERNAME_KEY).toString();
        if (userName.isEmpty()) throw new IllegalArgumentException();
        return firestoreService.createDocument(USERS_COLLECTION, userName, values);
    }

    @Override
    public Map<String, Object> update(Map<String, Object> values) throws IllegalArgumentException, NullPointerException {
        String userName = values.get(USERNAME_KEY).toString();
        if (userName.isEmpty()) throw new IllegalArgumentException();
        return firestoreService.updateDocument(USERS_COLLECTION, userName, values);
    }

    @Override
    public void delete(String userName) throws IllegalArgumentException,
            NoSuchElementException, ExecutionException, InterruptedException {
        if (userName == null || userName.isEmpty()) throw new IllegalArgumentException("'userName' cannot be empty null!");
        firestoreService.deleteDocument(USERS_COLLECTION, userName);
    }

    @Override
    public void deleteFields(Map<String, Object> request) throws IllegalArgumentException,
            NoSuchElementException, ExecutionException, InterruptedException {
        String id = request.get(USERNAME_KEY).toString();
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("'id' cannot be empty or null!");
        List<String> fieldsToDelete = (List<String>) request.get("fieldsToDelete");
        firestoreService.deleteFields(USERS_COLLECTION, id, fieldsToDelete);
    }
}

