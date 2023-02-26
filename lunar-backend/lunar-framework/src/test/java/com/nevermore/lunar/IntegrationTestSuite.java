package com.nevermore.lunar;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import static com.nevermore.lunar.framework.utils.TestSuiteConstantsKt.INTEGRATION_TEST;

/**
 * @author nevermore
 */
@Suite
@SuiteDisplayName("Lunar Integration Test Suite")
@SelectPackages("com.nevermore.lunar")
@IncludeTags(INTEGRATION_TEST)
public class IntegrationTestSuite {
}
