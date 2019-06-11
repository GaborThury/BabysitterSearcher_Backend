package com.homeproject.babysitting.app.babysitting.app.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class FirestoreService {

    @Autowired
    private Firestore db;

    private static final String DEFAULT_COLLECTION_NAME = "sampleData";

    public List<Map<String, Object>> getAllDocumentsFromCollection(String collectionName) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(collectionName).get();
        List<DocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream()
                .map(DocumentSnapshot::getData)
                .collect(Collectors.toList());
    }

    public List<String> getAllDocumentNamesFromCollection(String collectionName) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(collectionName).get();
        return future.get().getDocuments().stream()
                .map(DocumentSnapshot::getId)
                .collect(Collectors.toList());
    }

    public Map<String, Object> getDocumentFields(String collectionName, String documentName) {
        //DocumentReference docRef = db.collection("sampleData").document("test");
        String collection = collectionName;
        if (collection == null) {
            collection = DEFAULT_COLLECTION_NAME;
        }

        DocumentReference documentReference = db.collection(collection).document(documentName);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = null;


        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }

        if (document.exists()) {
            return document.getData();
        } else {
            return null;
        }
    }

    public Map<String, Object> createFireStoreDocument(String collectionName, String documentName, Map<String, Object> values) {
        if (collectionName == null || documentName == null || values == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        db
                .collection(collectionName)
                .document(documentName)
                .create(values);
        return values;
    }

    public Map<String, Object> updateFireStoreDocument(String collectionName, String documentName, Map<String, Object> values) {
        if (collectionName == null || documentName == null || values == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        db
                .collection(collectionName)
                .document(documentName)
                .update(values);
        return values;
    }

}

