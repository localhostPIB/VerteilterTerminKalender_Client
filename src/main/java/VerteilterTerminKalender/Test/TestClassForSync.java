package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.util.Sync;

public class TestClassForSync {

    public static void main(String[] args) {
        TestClassForSync testClassForSync = new TestClassForSync();
        testClassForSync.testInitiateConnection();

    }

    private void testInitiateConnection(){
        MainApp mainApp = new MainApp();
        Sync.initiateConnection(mainApp,"165");
    }
}
