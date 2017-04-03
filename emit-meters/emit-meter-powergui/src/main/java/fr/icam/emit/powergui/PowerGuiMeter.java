package fr.icam.emit.powergui;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PowerGuiMeter {

	private final static String HOST = "http://localhost/powergui/";
	
    public void setUp() throws Exception { }
    
    public void tearDown() throws Exception { }
    
    public final void doStart() throws Exception {
    	this.batch(true);
    }
    
    public final void doStop() throws Exception {
    	this.batch(false);
    }
    
    
    private void batch (boolean start) throws Exception {
    	CloseableHttpClient client = HttpClients.createDefault();
    	try {
    		HttpGet request = new HttpGet(HOST + (start ? "start.php" : "stop.php"));
    		CloseableHttpResponse response = client.execute(request);
    		HttpEntity entity = response.getEntity();
    		try {
    			InputStream stream = entity.getContent();
    			IOUtils.copy(stream, System.out);
    		} finally {
    			response.close();
    			EntityUtils.consume(entity);
    		}
    	} finally {
    		client.close();
    	}
}

	public void doRetrieve(OutputStream stream) throws Exception {
        URL url = new URL(HOST + "ext/power/resultats/points.csv");
        InputStream input = url.openStream();
        if (stream != null) {
        	IOUtils.copy(input, stream);
        	input.close();
        }
    }
	
}