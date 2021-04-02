# RestAssuredFramework 

API Automation Framework built using Rest Assured, Java, Maven, TestNG, Extent Reports.

Test Driven Framework

****************************************************************************

How to use this framework?

Clone the repository to your workspace.

Open the testdata.xlsx under the src/test/resources folder

In the RunManager sheet -->Choose the test cases you want to run by choosing yes

In the testdata sheet --->Choose the test data you want to pass to the testcase from excel sheet.

The data from the excel sheet will be passed to the test method as a hashtable.

Run the testng.xml file always.

Note: If you try to run individual tests as testng test, you will get NPE as the listeners are configured in testng.xml

You can add maven surefire plugin to trigger the testng.xml file from pom.xml

******************************************************************************
How the framework works?

AnnotationTransformer class which implements IAnnotationTransformer is reponsible for reading the data from RunManager sheet in testdata.xlsx It sets the annotation of the test methods like description,enabled, priority, dataprovider values read from the excel.

Things to note : Test name in the first column of the excel sheet should match with atleast an @Test available in test classes mentioned in the testng.xml

All the tests will have the same dataprovider in the TestUtils class.

For example the loginTest in RunManager sheet of testdata.xlsx will take the data from TestData sheet which have row where the testname is loginTest. 

If there are multiple rows with loginTest as test name , framework will consider it as this as multiple iterations for a test case.


******************************************************************************
Other tips:

TestCase description given in the excel sheet will be displayed in the extent reports.

Data from excel sheet will be available as Hashtable parameter to your tests. 

You can fetch the value using data.get("columnnameinexcel") Refer the already existing tests for more details.

*******************************************************************************

I have used some dummy API's for testing purpose. 

If you trying to run the BestBuyAPI test cases, make sure you configure as per below docs and make sure that the server is up and running on http://localhost://3030

https://github.com/BestBuy/api-playground

Make sure you have NodeJS installed (we require version 4 or newer).

git clone https://github.com/bestbuy/api-playground/

cd api-playground

npm install

npm start