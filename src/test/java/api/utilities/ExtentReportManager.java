package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {


    public ExtentSparkReporter sparkReporter;   //UI of the report
    public ExtentReports extent;    //Populate common info of the report
    public ExtentTest test; //Creating test cases entries in the report and update status of the test methods


    String repName;

    public void onStart(ITestContext context) {


        String timeStamp = new SimpleDateFormat("yyyy,.MM.dd.HH.mm.ss").format(new Date()); //Time Stamp
        repName = "Test-Report-" + timeStamp + ".html";


        //initialize ExtentReports and attach the SparkReporter
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);   //Specify the location of the report
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");   //Title of the report
        sparkReporter.config().setReportName("Pet Store Users API"); //Name of the report
        sparkReporter.config().setTheme(Theme.DARK);


        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("UserName", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Md");
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        //Log the test as passed
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test passed");
    }


    @Override
    public void onTestFailure(ITestResult result) {
        //Log the test as failed
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());

        // Optionally, take a screenshot and attach it to the report
        // String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
        // test.addScreenCaptureFromPath(screenshotPath);
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        //Log the test as skipped
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Failed");
        test.log(Status.SKIP, result.getThrowable().getMessage());

    }


    @Override
    public void onFinish(ITestContext context) {
        //Flush the report at the end
        extent.flush();
    }
}
