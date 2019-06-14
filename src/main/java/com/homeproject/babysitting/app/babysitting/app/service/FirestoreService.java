package com.homeproject.babysitting.app.babysitting.app.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class FirestoreService {

    private Firestore db;

    public FirestoreService(Firestore db) {
        this.db = db;
    }

    List<Map<String, Object>> getAllDocumentsFromCollection(String collectionName)
            throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(collectionName).get();
        List<DocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream()
                .map(DocumentSnapshot::getData)
                .collect(Collectors.toList());
    }

    List<String> getAllDocumentNamesFromCollection(String collectionName)
            throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(collectionName).get();
        return future.get().getDocuments().stream()
                .map(DocumentSnapshot::getId)
                .collect(Collectors.toList());
    }

    Map<String, Object> getDocumentFields(String collectionName, String documentName)
            throws ExecutionException, InterruptedException, IllegalArgumentException {
        if (collectionName == null || documentName == null) {
            throw new IllegalArgumentException("Parameters cannot be null!");
        }
        ApiFuture<DocumentSnapshot> future = db.collection(collectionName).document(documentName).get();
        DocumentSnapshot document = future.get();

        return document.exists() ? document.getData() : null;
    }

    void createDocument(String collectionName, String documentName, Map<String, Object> values)
            throws ExecutionException, InterruptedException, IllegalArgumentException {
        if (collectionName == null || documentName == null || values == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        db
                .collection(collectionName)
                .document(documentName)
                .create(values)
                .get();
    }

    void createDocument(String collectionName, Map<String, Object> values) throws
            InterruptedException, ExecutionException, IllegalArgumentException {
        if (collectionName == null || values == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        db.collection(collectionName)
                .document()
                .set(values)
                .get();
    }

    void updateDocument(String collectionName, String documentName, Map<String, Object> values)
            throws ExecutionException, InterruptedException, IllegalArgumentException {
        if (collectionName == null || documentName == null || values == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        db
                .collection(collectionName)
                .document(documentName)
                .update(values)
                .get();
    }


    void deleteDocument(String collectionName, String documentName)
            throws IllegalArgumentException, NoSuchElementException,
            InterruptedException, ExecutionException {
        if (collectionName == null || documentName == null) {
            throw new IllegalArgumentException("Parameters 'collectionName' and/or 'documentName' cannot be null!");
        }
        if (isDocumentExists(collectionName, documentName)) {
            db
                    .collection(collectionName)
                    .document(documentName)
                    .delete()
                    .get();
        } else {
            throw new NoSuchElementException("The given document does not exists, hence it cannot be deleted!");
        }
    }

    void deleteFields(String collectionName, String documentName, List<String> idS)
            throws IllegalArgumentException, NoSuchElementException,
            InterruptedException, ExecutionException {
        if (collectionName == null || documentName == null) {
            throw new IllegalArgumentException("Parameters 'collectionName' and/or 'documentName' cannot be null!");
        }
        if (isDocumentExists(collectionName, documentName)) {
            Map<String, Object> fieldsToDelete = new HashMap<>();
            idS.forEach(id -> fieldsToDelete.put(id, FieldValue.delete()));
            db
                    .collection(collectionName)
                    .document(documentName)
                    .update(fieldsToDelete)
                    .get();
        } else {
            throw new NoSuchElementException("The given document does not exists, hence it cannot be deleted!");
        }
    }

    private boolean isDocumentExists(String collectionName, String documentName) throws ExecutionException, InterruptedException {
        return db.collection(collectionName).document(documentName).get().get().exists();
    }

}

