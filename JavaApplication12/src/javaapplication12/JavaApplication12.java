/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.util.concurrent.Executors.callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import javafx.concurrent.Task;

/**
 *
 * @author Frank
 */
public class JavaApplication12 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        Task task = new Task<String>() {
            @Override
            protected String call() throws Exception {
                String test = "dit komt terug.";
                updateMessage(test);
                updateProgress(1, 10); // 1 van 10
                return test;
            }
        };   
       
        Thread t = new Thread(task);
        t.start();
        
        //bar.progressProperty().bind(task.progressProperty());

    }
}
