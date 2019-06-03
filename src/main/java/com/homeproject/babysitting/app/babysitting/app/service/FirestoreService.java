package com.homeproject.babysitting.app.babysitting.app.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    @Autowired
    private Firestore db;

    private final String collectionPath = "sampleData";
    private final String documentPath = "test";


    public Map<String, Object> getQuoteFromFirestore() {
        //DocumentReference docRef = db.collection("sampleData").document("test");
        DocumentReference documentReference = db.collection(collectionPath).document(documentPath);
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

    public Map<String, Object> saveToFireStore(Map<String, Object> values) {
        db
                .collection(collectionPath)
                .document(documentPath)
                .update(values);
        return values;
    }
}

