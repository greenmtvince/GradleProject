package com.udacity.gradle.builditbigger;

/**
 * Created by Vinnie on 1/11/2018.
 */

public interface TaskCompleteListener<T> {
    public void onTaskComplete(T result);
}
