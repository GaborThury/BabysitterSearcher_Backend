package com.homeproject.babysitting.app.babysitting.app.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class EventService implements DomainService {

    private static final String EVENT_COLLECTION = "events";
    private FirestoreService firestoreService;

    public EventService(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

    @Override
    public List<Map<String, Object>> findAll() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentsFromCollection(EVENT_COLLECTION);
    }

    @Override
    public List<String> findAllNames() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentNamesFromCollection(EVENT_COLLECTION);
    }

    @Override
    public Map<String, Object> findById(String eventName) {
        return firestoreService.getDocumentFields(EVENT_COLLECTION, eventName);
    }

    @Override
    public Map<String, Object> create(String eventName, Map<String, Object> values) {
        return firestoreService.createFireStoreDocument(EVENT_COLLECTION, eventName, values);
    }

    @Override
    public Map<String, Object> update(String eventName, Map<String, Object> values) {
        return firestoreService.updateFireStoreDocument(EVENT_COLLECTION, eventName, values);
    }
}
