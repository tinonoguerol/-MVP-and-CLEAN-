package com.cnoguerol.tinoexamen.libs.base;

/**
 * Created by cnoguerol.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}

