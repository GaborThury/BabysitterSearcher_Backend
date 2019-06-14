package com.homeproject.babysitting.app.babysitting.app.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface DomainService {

    List<Map<String, Object>> findAll() throws ExecutionException, InterruptedException;

    List<String> findIdS() throws ExecutionException, InterruptedException;

    Map<String, Object> findById(String id) throws ExecutionException, InterruptedException;

    void create(Map<String, Object> values) throws ExecutionException, InterruptedException;

    void update(Map<String, Object> values) throws IllegalArgumentException, NullPointerException, ExecutionException, InterruptedException;

    void delete(String id) throws ExecutionException, InterruptedException;

    void deleteFields(Map<String, Object> request) throws ExecutionException, InterruptedException;
}
