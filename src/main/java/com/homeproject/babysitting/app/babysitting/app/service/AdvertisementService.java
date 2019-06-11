package com.homeproject.babysitting.app.babysitting.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class AdvertisementService {

    @Autowired
    private FirestoreService firestoreService;

    private static final String ADVERTISEMENT_COLLECTION = "advertisements";


    public List<Map<String, Object>> findAll() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentsFromCollection(ADVERTISEMENT_COLLECTION);
    }

    public List<String> findAllAdvertisementIdS() throws ExecutionException, InterruptedException {
        return firestoreService.getAllDocumentNamesFromCollection(ADVERTISEMENT_COLLECTION);
    }

    public Map<String, Object> findAdvertisementById(String name) {
        return firestoreService.getDocumentFields(ADVERTISEMENT_COLLECTION, name);
    }

    public Map<String, Object> create(String name, Map<String, Object> values) {
        return firestoreService.createFireStoreDocument(ADVERTISEMENT_COLLECTION, name, values);
    }

    public Map<String, Object> update(String name, Map<String, Object> values) {
        return firestoreService.updateFireStoreDocument(ADVERTISEMENT_COLLECTION, name, values);
    }

}
