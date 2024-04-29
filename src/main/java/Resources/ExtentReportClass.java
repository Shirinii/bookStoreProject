package Resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportClass {

	static ExtentReports extent;

	public static ExtentReports getReport() {

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String currentDate = format.format(date);
		String path = System.getProperty("user.dir") + "//report//" + currentDate + ".html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("TestReport");
		reporter.config().setReportName("selenium");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;

	}
//public static ExtentReports getReport() {
}
