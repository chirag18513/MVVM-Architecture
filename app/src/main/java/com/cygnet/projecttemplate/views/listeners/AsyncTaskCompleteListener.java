package com.cygnet.projecttemplate.views.listeners;

public interface AsyncTaskCompleteListener<T> {
   public void onTaskComplete(T result);
}