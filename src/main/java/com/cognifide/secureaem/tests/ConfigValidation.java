package com.cognifide.secureaem.tests;

import com.cognifide.secureaem.AbstractTest;
import com.cognifide.secureaem.CliConfiguration;
import com.cognifide.secureaem.TestConfiguration;
import com.cognifide.secureaem.markers.AuthorTest;
import com.cognifide.secureaem.markers.DispatcherTest;
import com.cognifide.secureaem.markers.PublishTest;

/**
 * This class checks if the SecureAEM configuration looks sane.
 * 
 * @author trekawek
 *
 */
public class ConfigValidation extends AbstractTest implements AuthorTest, DispatcherTest, PublishTest {

	public ConfigValidation(CliConfiguration config, TestConfiguration testConfiguration) {
		super(config, testConfiguration);
	}

	@Override
	public boolean doTest(String url, String instanceName) throws Exception {
		if (safePathExists(url)) {
			addInfoMessage("URL [%s] for instance %s looks OK", url, instanceName);
		} else {
			addErrorMessage("URL [%s] for instance %s doesn't look right", url, instanceName);
			return false;
		}
		return true;
	}

	private boolean safePathExists(String url) {
		try {
			httpHelper.pathExists(url);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
