package thontepu.naveen.baseproject;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockWebServer;
import thontepu.naveen.baseproject.Utilities.Utilities;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        MockWebServer mockWebServer = new MockWebServer();
        Utilities.printLog("the mockWebserver = "+mockWebServer.getHostName());
        Utilities.printLog("the mockWebserver = "+mockWebServer.getPort());
        Utilities.printLog("the mockWebserver = "+mockWebServer.toString());
        Utilities.printLog("the mockWebserver = "+mockWebServer.toProxyAddress().toString());
        assertEquals("thontepu.naveen.baseproject0", appContext.getPackageName());
    }
}
