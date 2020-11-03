package com.framework.core;

public class TestCaseDetails {
	
	public static String scenarioName;
	public static String scenarioLine;
	public static String reportPath;
	public static String featureFileName;

	public static String getFeatureFileName() {
		return featureFileName;
	}

	public static void setFeatureFileName(String featureFileName) {
		TestCaseDetails.featureFileName = featureFileName;
	}

	public static String getScenarioLine() {
		return scenarioLine;
	}

	public static void setScenarioLine(String scenarioLine) {
		TestCaseDetails.scenarioLine = scenarioLine;
	}

	public static String getScenarioName() {
		return scenarioName;
	}

	public static void setScenarioName(String scenarioName) {
		TestCaseDetails.scenarioName = scenarioName;
	}
	
	public static String getReportPath() {
		return reportPath;
	}

	public static void setReportPath(String reportPath) {
		TestCaseDetails.reportPath = reportPath;
	}

}
