package com.homeproject.babysitting.app.babysitting.app.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface DomainService {

    List<Map<String, Object>> findAll() throws ExecutionException, InterruptedException;

    List<String> findAllNames() throws ExecutionException, InterruptedException;

    Map<String, Object> findById(String id);

    Map<String, Object> create(Map<String, Object> values);

    Map<String, Object> update(Map<String, Object> values) throws IllegalArgumentException, NullPointerException;

    void delete(String id) throws ExecutionException, InterruptedException;

    void deleteFields(Map<String, Object> request) throws ExecutionException, InterruptedException;
}
