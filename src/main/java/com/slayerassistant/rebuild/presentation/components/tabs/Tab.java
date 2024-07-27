package com.slayerassistant.rebuild.presentation.components.tabs;

public interface Tab<T> 
{
    void update(T data);
    void shutDown();
}