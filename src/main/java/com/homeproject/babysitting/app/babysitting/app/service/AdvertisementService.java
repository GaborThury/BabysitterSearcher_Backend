package com.homeproject.babysitting.app.babysitting.app.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

@Service
public class AdvertisementService implements DomainService {

    private static final String ADVERTISEMENT_COLLECTION = "advertisements";
    private static final String ADVERTISEMENT_NAME_KEY = "id";
    private FirestoreService firestoreService;

    public AdvertisementService(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

    @Override
    public List<Map<String, Object>> findAll() throws ExecutionException,
            InterruptedException, IllegalArgumentException {
        return firestoreService.getAllDocumentsFromCollection(ADVERTISEMENT_COLLECTION);
    }

    @Override
    public List<String> findIdS() throws ExecutionException,
            InterruptedException, IllegalArgumentException {
        return firestoreService.getAllDocumentNamesFromCollection(ADVERTISEMENT_COLLECTION);
    }

    @Override
    public Map<String, Object> findById(String id) throws ExecutionException,
            InterruptedException, IllegalArgumentException, NoSuchElementException {
        return firestoreService.getDocumentFields(ADVERTISEMENT_COLLECTION, id);
    }

    @Override
    public void create(Map<String, Object> values) throws ExecutionException,
            InterruptedException, IllegalArgumentException {
        firestoreService.createDocument(ADVERTISEMENT_COLLECTION, values);
    }

    @Override
    public void update(Map<String, Object> values) throws
            IllegalArgumentException,
            ExecutionException, InterruptedException {
        String id;
        try {
            id = values.remove(ADVERTISEMENT_NAME_KEY).toString();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Attribute 'id' cannot be null!");
        }
        if (id.isBlank()) {
            throw new IllegalArgumentException("Attribute 'id' cannot be empty or blank!");
        }
        firestoreService.updateDocument(ADVERTISEMENT_COLLECTION, id, values);
    }

    @Override
    public void delete(String id) throws IllegalArgumentException,
            NoSuchElementException, ExecutionException, InterruptedException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Attribute 'id' cannot be empty, blank or null!");
        }
        firestoreService.deleteDocument(ADVERTISEMENT_COLLECTION, id);
    }

    @Override
    public void deleteFields(Map<String, Object> request) throws IllegalArgumentException,
            NoSuchElementException, ExecutionException, InterruptedException {
        String id;
        try {
            id = request.get(ADVERTISEMENT_NAME_KEY).toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("Attribute 'id' cannot be null!");
        }
        if (id.isBlank()) throw new IllegalArgumentException("Attribute 'id' cannot be empty or blank!");
        List<String> fieldsToDelete = (List<String>) request.get("fieldsToDelete");
        firestoreService.deleteFields(ADVERTISEMENT_COLLECTION, id, fieldsToDelete);
    }
}
