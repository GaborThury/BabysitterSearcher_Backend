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
    public List<Map<String, Object>> findAll() throws ExecutionException,
            InterruptedException, IllegalArgumentException {
        return firestoreService.getAllDocumentsFromCollection(USERS_COLLECTION);
    }

    @Override
    public List<String> findIdS() throws ExecutionException,
            InterruptedException, IllegalArgumentException {
        return firestoreService.getAllDocumentNamesFromCollection(USERS_COLLECTION);
    }

    @Override
    public Map<String, Object> findById(String username)
            throws ExecutionException, InterruptedException,
            IllegalArgumentException, NoSuchElementException {
        return firestoreService.getDocumentFields(USERS_COLLECTION, username);
    }

    @Override
    public void create(Map<String, Object> values) throws IllegalArgumentException,
            ExecutionException, InterruptedException {
        String userName;
        try {
            userName = values.get(USERNAME_KEY).toString();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Attribute 'userName' cannot be null!");
        }
        if (userName.isBlank()) {
            throw new IllegalArgumentException("Attribute 'userName' cannot be empty or blank!");
        }
        firestoreService.createDocument(USERS_COLLECTION, userName, values);
    }

    @Override
    public void update(Map<String, Object> values) throws IllegalArgumentException,
            ExecutionException, InterruptedException {
        String userName;
        try {
            userName = values.get(USERNAME_KEY).toString();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Attribute 'userName' cannot be null!");
        }
        if (userName.isBlank()){
            throw new IllegalArgumentException("Attribute 'userName' empty or blank!");
        }
        firestoreService.updateDocument(USERS_COLLECTION, userName, values);
    }

    @Override
    public void delete(String userName) throws IllegalArgumentException,
            NoSuchElementException, ExecutionException, InterruptedException {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("'userName' cannot be empty, blank or null!");
        }
        firestoreService.deleteDocument(USERS_COLLECTION, userName);
    }

    @Override
    public void deleteFields(Map<String, Object> request) throws IllegalArgumentException,
            NoSuchElementException, ExecutionException, InterruptedException {
        String id = request.get(USERNAME_KEY).toString();
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("'id' cannot be empty, blank or null!");
        }
        List<String> fieldsToDelete = (List<String>) request.get("fieldsToDelete");
        firestoreService.deleteFields(USERS_COLLECTION, id, fieldsToDelete);
    }
}

