package com.slayerassistant.domain;

public interface Tab<T> 
{
    void update(T data);
    void shutDown();
}