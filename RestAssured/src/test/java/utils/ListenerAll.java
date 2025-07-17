package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerAll implements ITestListener{
	
	private LoggerManager logger = new LoggerManager();

	@Override
	public void onTestStart(ITestResult result) {
		logger.logInfo("🟡 Test started with itestListener : " + result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.logInfo("✅ Test Passed: " + result.getMethod().getMethodName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.logError("❌ Test Failed: " + result.getMethod().getMethodName() +"\n the throwable is \n", result.getThrowable());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.logWarn("⚠️ Test Skipped: " + result.getName());

	}

	
	

	@Override
	public void onStart(ITestContext context) {
		logger.logInfo("🏁 Suite Finished:  " + context.getName());

	}

	@Override
	public void onFinish(ITestContext context) {
		logger.logInfo("🏁 Suite Finished:  " + context.getName());
	}

	
	
	
	
	

}
