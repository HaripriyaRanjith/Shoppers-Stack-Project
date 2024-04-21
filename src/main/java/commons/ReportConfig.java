package commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

public class ReportConfig extends BaseTest{

	static String reportPath=System.getProperty("user.dir")+File.separator+"reports"+File.separator;
	static String screenshotPath=System.getProperty("user.dir")+File.separator+"screenshots"+File.separator;
	public static final String DATE_FORMAT_NOW = "yyyy_MM_dd_HH.mm.ss";
	public static String currentTime;
	static ExtentReports extent = new ExtentReports();
	static ExtentSparkReporter spark = new ExtentSparkReporter(reportPath+reportName());
	
	//Extend report generation - To attach STATUS of test steps along with screenshot captured 
	public static void attachReport(String status,String stepName,String stepDetails) throws Exception {

		extent.attachReporter(spark);
		if(status.equalsIgnoreCase("pass")) {
			extent.createTest(stepName)
			.pass(stepDetails, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(stepName)).build());
		}else if(status.equalsIgnoreCase("info")){
			extent.createTest(stepName)
			.info(stepDetails, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(stepName)).build());
		}else if(status.equalsIgnoreCase("fail")){
			extent.createTest(stepName)
			.fail(stepDetails, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(stepName)).build());
		}
	}
	
	//Final creation of extend report method
	public static void finalizeReport() {
		extent.flush();
	}

	//Dynamic report naming method 
	public static String reportName() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		currentTime = sdf.format(cal.getTime());
		return "ExtentReport_"+currentTime+".html";
	}

	//Dynamic screenshot naming method 
	public static String captureScreenshot(String stepName) throws IOException {

		File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshot=screenshotPath+stepName+currentTime+".png";
		Files.copy(srcFile, new File(screenshot));
		return screenshot;

	}
}
