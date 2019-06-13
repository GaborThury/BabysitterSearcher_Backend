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
    public List<Map<String, Object>> findAll() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentsFromCollection(ADVERTISEMENT_COLLECTION);
    }

    @Override
    public List<String> findAllNames() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentNamesFromCollection(ADVERTISEMENT_COLLECTION);
    }

    @Override
    public Map<String, Object> findById(String id) {
        return firestoreService.getDocumentFields(ADVERTISEMENT_COLLECTION, id);
    }

    @Override
    public Map<String, Object> create(Map<String, Object> values) {
        return firestoreService.createDocument(ADVERTISEMENT_COLLECTION, values);
    }

    @Override
    public Map<String, Object> update(Map<String, Object> values) throws IllegalArgumentException, NullPointerException {
        String id = values.remove(ADVERTISEMENT_NAME_KEY).toString();
        if (id.isEmpty()) throw new IllegalArgumentException();
        return firestoreService.updateDocument(ADVERTISEMENT_COLLECTION, id, values);
    }

    @Override
    public void delete(String id) throws IllegalArgumentException,
            NoSuchElementException, ExecutionException, InterruptedException {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("'id' cannot be empty or null!");
        firestoreService.deleteDocument(ADVERTISEMENT_COLLECTION, id);
    }

    @Override
    public void deleteFields(Map<String, Object> request) throws IllegalArgumentException,
            NoSuchElementException, ExecutionException, InterruptedException {
        String id = request.get(ADVERTISEMENT_NAME_KEY).toString();
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("'id' cannot be empty or null!");
        List<String> fieldsToDelete = (List<String>) request.get("fieldsToDelete");
        firestoreService.deleteFields(ADVERTISEMENT_COLLECTION, id, fieldsToDelete);
    }
}
