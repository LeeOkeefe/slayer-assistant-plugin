package com.slayerassistant.rebuild.domain;

public interface Tab<T> 
{
    void update(T data);
    void shutDown();
}