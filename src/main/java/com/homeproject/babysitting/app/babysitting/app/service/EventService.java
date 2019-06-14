package com.homeproject.babysitting.app.babysitting.app.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

@Service
public class EventService implements DomainService {

    private static final String EVENT_COLLECTION = "events";
    private static final String EVENT_NAME_KEY = "id";
    private FirestoreService firestoreService;

    public EventService(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

    @Override
    public List<Map<String, Object>> findAll() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentsFromCollection(EVENT_COLLECTION);
    }

    @Override
    public List<String> findIdS() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentNamesFromCollection(EVENT_COLLECTION);
    }

    @Override
    public Map<String, Object> findById(String eventName)
            throws ExecutionException, InterruptedException, IllegalArgumentException {
        return firestoreService.getDocumentFields(EVENT_COLLECTION, eventName);
    }

    @Override
    public void create(Map<String, Object> values)
            throws ExecutionException, InterruptedException, IllegalArgumentException {
        firestoreService.createDocument(EVENT_COLLECTION, values);
    }

    @Override
    public void update(Map<String, Object> values) throws
            IllegalArgumentException,
            ExecutionException, InterruptedException {
        String id;
        try {
            id = values.remove(EVENT_NAME_KEY).toString();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Attribute 'id' cannot be null!");
        }
        if (id.isBlank()) throw new IllegalArgumentException("Attribute 'id' cannot be empty or blank!");
        firestoreService.updateDocument(EVENT_COLLECTION, id, values);
    }

    @Override
    public void delete(String id) throws IllegalArgumentException,
            NoSuchElementException, ExecutionException, InterruptedException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Attribute 'id' cannot be empty, blank or null!");
        }
        firestoreService.deleteDocument(EVENT_COLLECTION, id);
    }

    @Override
    public void deleteFields(Map<String, Object> request) throws IllegalArgumentException,
            NoSuchElementException, ExecutionException, InterruptedException {
        String id;
        try {
            id = request.get(EVENT_NAME_KEY).toString();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Attribute 'id' cannot be null!");
        }
        if (id.isBlank()) throw new IllegalArgumentException("'Attribute id' cannot be empty or blank!");
        List<String> fieldsToDelete = (List<String>) request.get("fieldsToDelete");
        firestoreService.deleteFields(EVENT_COLLECTION, id, fieldsToDelete);
    }
}
