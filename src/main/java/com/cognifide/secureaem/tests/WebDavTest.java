package com.cognifide.secureaem.tests;

import java.net.URI;

import com.cognifide.secureaem.TestConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cognifide.secureaem.AbstractTest;
import com.cognifide.secureaem.CliConfiguration;
import com.cognifide.secureaem.markers.PublishTest;

/**
 * Check if given instance supports WebDAV.
 * 
 * @author trekawek
 *
 */
public class WebDavTest extends AbstractTest implements PublishTest {

	public WebDavTest(CliConfiguration config, TestConfiguration testConfiguration) {
		super(config, testConfiguration);
	}

	@Override
	public boolean doTest(String url, String instanceName) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpUriRequest request = new HttpPropFind(new URI(url));
		HttpResponse response = client.execute(request);
		EntityUtils.consume(response.getEntity());
		if (response.getStatusLine().getStatusCode() == 405) {
			addInfoMessage("WebDAV is disabled at %s", instanceName);
			return true;
		} else {
			addErrorMessage("WebDAV is enabled at %s", instanceName);
			return false;
		}
	}

	private static class HttpPropFind extends HttpEntityEnclosingRequestBase {
		public HttpPropFind(URI uri) {
			this.setURI(uri);
		}

		@Override
		public String getMethod() {
			return "PROPFIND";
		}
	}
}
