package com.homeproject.babysitting.app.babysitting.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class EventService {

    @Autowired
    private FirestoreService firestoreService;

    private static final String EVENT_COLLECTION = "events";


    public List<Map<String, Object>> findAll() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentsFromCollection(EVENT_COLLECTION);
    }

    public List<String> findAllEventNames() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentNamesFromCollection(EVENT_COLLECTION);
    }

    public Map<String, Object> findEventByName(String eventName) {
        return firestoreService.getDocumentFields(EVENT_COLLECTION, eventName);
    }

    public Map<String, Object> create(String eventName, Map<String, Object> values) {
        return firestoreService.createFireStoreDocument(EVENT_COLLECTION, eventName, values);
    }

    public Map<String, Object> update(String eventName, Map<String, Object> values) {
        return firestoreService.updateFireStoreDocument(EVENT_COLLECTION, eventName, values);
    }
}
