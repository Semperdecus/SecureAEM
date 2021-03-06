package com.cognifide.secureaem.tests;

import com.cognifide.secureaem.AbstractTest;
import com.cognifide.secureaem.CliConfiguration;
import com.cognifide.secureaem.TestConfiguration;
import com.cognifide.secureaem.markers.DispatcherTest;

/**
 * Check if the WCM debug filter is enabled.
 * 
 * @author trekawek
 *
 */
public class WcmDebugTest extends AbstractTest implements DispatcherTest {

	public WcmDebugTest(CliConfiguration config, TestConfiguration testConfiguration) {
		super(config, testConfiguration);
	}

	@Override
	public boolean doTest(String url, String instanceName) throws Exception {
		String testUrl = httpHelper.getBasePath(url, false) + "?debug=layout";
		if (httpHelper.pageContainsString(testUrl, "<br>cell=")) {
			addErrorMessage("WCM debug filter is not disabled at [%s]", testUrl);
			return false;
		} else {
			addInfoMessage("WCM debug filter is disabled at [%s]", testUrl);
			return true;
		}
	}
}
