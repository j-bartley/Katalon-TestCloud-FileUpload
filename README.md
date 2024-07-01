# Katalon-TestCloud-FileUpload
A small project to show how to place a file in your project for use of uploading the file during a Katalon TestCloud execution.

'''
I have a local file that works fine when running the test in Katalon Studio, but when I try to execute the test on TestCloud it cannot locate the file:

Reason: java.io.FileNotFoundException: File '' does not exist
'''

**Two requirements are needed in order for an additional file to be available for a test run that is executed via TestCloud:**

1. The file must be located within the project (for example it could be placed into the “Data Files” folder) as well as the file must be included on the Git Repository that is linked with the project.

2. An additional piece of code is required that identifies the webdriver and creates a Local File Detector.

**Required Functionality Code:**

`import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.LocalFileDetector
import org.openqa.selenium.support.events.EventFiringWebDriver
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.selenium.driver.CRemoteWebDriver

WebUI.openBrowser('')

// This code must be executed AFTER the browser is opened.
EventFiringWebDriver driver = DriverFactory.getWebDriver()

WebDriver wrappedDriver = driver.getWrappedDriver()

if (wrappedDriver.class == CRemoteWebDriver) {

wrappedDriver.setFileDetector(new LocalFileDetector())

}`

**File Upload Code:**

Once the required code has been established, the following code can be used as the commands to upload the actual file (the path in this example expects the file “uploadSample.txt” to be located in the “Data Files” folder of the project, and uses an example Object (Page_File Upload) as the upload pointer).

`String filePath = new File(RunConfiguration.getProjectDir() + '/' + 'Data Files/uploadSample.txt').getCanonicalPath()

WebUI.uploadFile(findTestObject('Page_File Upload/input_File to uploadNotes about the file to_4f2f05'), filePath)`

'''
Once this code is implemented, the file upload will work correctly whether executed locally on Katalon Studio / KRE and remotely with a KRE / Agent or TestCloud.
'''


**Additional Notes:**

Another time this error may occur is if there is a mismatch between local folder/file names and the folder/file names on the Git Repository. This is true for any folder/file located within the Katalon Studio project. 

For example, if the user has a test case folder named “Web” on their local project but the same folder name is “web” it will throw the “Reason: java.io.FileNotFoundException: File '' does not exist” error since it is unable to find the files due to the mismatch in names.

