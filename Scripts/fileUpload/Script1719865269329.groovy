import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
// Start Test Code Imports
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.LocalFileDetector as LocalFileDetector
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.selenium.driver.CRemoteWebDriver as CRemoteWebDriver
import com.kms.katalon.core.webui.driver.WebUIDriverType as WebUIDriverType

// End Test Code Imports
WebUI.openBrowser('')

// Start Test Code
EventFiringWebDriver driver = DriverFactory.getWebDriver()

WebDriver wrappedDriver = driver.getWrappedDriver()

if (wrappedDriver.class == CRemoteWebDriver) {
	wrappedDriver.setFileDetector(new LocalFileDetector())
}

// End Test Code
WebUI.navigateToUrl('https://cgi-lib.berkeley.edu/ex/fup.html')

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_File Upload/h1_Sample File Upload Form'), 0)

String filePath = new File((RunConfiguration.getProjectDir() + '/') + 'Data Files/uploadSample.txt').getCanonicalPath()

WebUI.uploadFile(findTestObject('Page_File Upload/input_File to uploadNotes about the file to_4f2f05'), filePath)

WebUI.setText(findTestObject('Object Repository/Page_File Upload/input_File to uploadNotes about the file to_b892c2'), 'asdf')

WebUI.click(findTestObject('Object Repository/Page_File Upload/input_submit'))

WebUI.verifyElementText(findTestObject('Page_File Upload/blockquote_asdf'), 'asdf')

WebUI.delay(2)

WebUI.closeBrowser()

