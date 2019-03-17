package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.util.Sync;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TestClassForSync {

    public static void main(String[] args) {
        TestClassForSync testClassForSync = new TestClassForSync();
        testClassForSync.testInitiateConnection();

    }

    private void testInitiateConnection(){
        MainApp mainApp = new MainApp();
        Sync.initiateConnection(mainApp,"165");

        while (true){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(mainApp.getEventInvitesList());
            System.out.println(mainApp.getEventInvitesList().size());

        }
    }
}
