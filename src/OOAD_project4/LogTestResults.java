package OOAD_project4;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter; 
import java.io.IOException;


/*
Using TestWatcher here to capture output
From http://www.javabyexamples.com/monitor-tests-with-junit-test-rule
*/
public class LogTestResults extends TestWatcher {

    public void logInput(String input){
        File log = new File("TestResults.txt");
        try{
            if(log.exists() == false){
                log.createNewFile();
            }
            PrintWriter writer = new PrintWriter(new FileWriter(log, true)); //create file called Logger-n.txt
            writer.println(input);
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error in logging");
        }
    }

    /**
     * Invoked when a test succeeds
     */
    @Override
    protected void succeeded(Description description) {
        String inputStr = String.format("%s succeeded!", description.getMethodName());
        this.logInput(inputStr);
    }

    /**
     * Invoked when a test fails
     */
    @Override
    protected void failed(Throwable e, Description description) {
        String inputStr = String.format("%s failed with %s", description.getMethodName(), e);
        this.logInput(inputStr);
    }

    /**
     * Invoked when a test is skipped due to a failed assumption.
     */
    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        String inputStr = String.format("%s skipped%n", description.getMethodName());
        this.logInput(inputStr);
    }

    /**
     * Invoked when a test is about to start
     */
    @Override
    protected void starting(Description description) {
        String inputStr = String.format("%s is starting", description.getMethodName());
        this.logInput(inputStr);
    }

    /**
     * Invoked when a test method finishes (whether passing or failing)
     */
    @Override
    protected void finished(Description description) {
        String inputStr = String.format("%s finished%n", description.getMethodName());
        this.logInput(inputStr);
    }
}