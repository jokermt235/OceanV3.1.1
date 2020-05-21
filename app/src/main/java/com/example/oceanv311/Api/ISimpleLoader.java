package com.example.oceanv311.Api;

import com.example.oceanv311.Callbacks.OnSavedResult;

import java.util.Map;

public interface ISimpleLoader {
    void save(final  String collection, Map<String, Object> data, final OnSavedResult result);
}
