package com.homeproject.babysitting.app.babysitting.app.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface DomainService {

    List<Map<String, Object>> findAll() throws ExecutionException, InterruptedException;

    List<String> findAllNames() throws ExecutionException, InterruptedException;

    Map<String, Object> findById(String id);

    Map<String, Object> create(String name, Map<String, Object> values);

    Map<String, Object> update(String name, Map<String, Object> values);
}
