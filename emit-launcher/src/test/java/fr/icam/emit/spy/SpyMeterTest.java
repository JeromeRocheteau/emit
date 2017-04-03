package fr.icam.emit.spy;

import fr.icam.emit.spy.SpyMeter;

public class SpyMeterTest {

    public static void main(String[] args) throws Exception {
    	SpyMeter app = new SpyMeter();
    	app.setUp();
    	app.doLaunch(0L, "ls", "-l", "/home/jerome");
    	app.doRetrieve(System.out);
    	app.tearDown();
    }

}