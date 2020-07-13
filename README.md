# Disney Interview Task Of Ksenia Laveeva

Author: Ksenia Laveeva

Purpose: Implementation of test task from Disney

## Structure of repository

* uitest\ - project for Selenium Ui Tests 
* xmlcompare\ - project for XML files comparison

## Uitest project

The project contains Chromedriver that requires a version of Chrome: 83.0.4103.116

### Test Scenario

Based on my understanding of the task, tests will follow the next scenario:
* Register new user
* Save user information into xlsx file (uitest\storage.xlsx)
* Log out
* Log in with user credentials from xlsx file
* Verify that user logged in successfully
* Go to My Account page
* Verify that user full name and email address matches expected from xlsx file

### Test Notes

I found the next issue:
When I go to the Shop page I can see Sign In button immediately. But when I click on this button Sign In component is not displayed.
This is because the page is still loaded. Only after full load Sign in button starts to work. This scenario reproduced when I manually test web site. 
Problem is that the page does not inform the user that page still is not ready yet: there are no spinner or loader icons.

### Hot to run

Project contain Unit Tests at <code>\uitest\src\test\java\com\disney\interview\Tests.java</code>
It contains two test methods:
* whenUserRegistered_thenUsernameShouldBeDisplayed - registers user and Save user information into xlsx file (uitest\storage.xlsx)
* whenPreviouslyRegisteredUserLogsIn_thenAccountDisplaysRegisteredInfo - uses registration info from xlsx file to log in to the site. Then verifies that My Account information matches records. This method should be executed after `whenUserRegistered_thenUsernameShouldBeDisplayed`


## XmlCompare

### Developer Notes
The biggest problem is that provided XML was not valid. Both XML contains the tag `manifest:Experience` that was not closed, so I decided to fix that XML files first. Fixed XML resides in `xmlcompare\file\` folder.

There are 3rd party libraries for comparing XML files in Java like `XMLUnit` (https://www.xmlunit.org/).
But I decided that probably you wanted me to implement a custom algorithm so I did it.

All test methods will print founded differences to Test Run output.
Comparator is able to spot differences in Tag Names, attributes (different set or values of attributes), and text values of XML nodes.
However, it has some limitation and does not support case if XML have different structures. 
* If the same node contains a different count of child nodes comparator would throw an exception. 
* If a node in file1 has child nodes ordered in a different way then in file2  - comparator will falsely mark it as differences. 

### Hot to run
The project contains Unit Tests at <code>\xmlcompare\src\test\java\com\disney\interview\XmlComparatorTest.java</code>
It contains 3 test methods:
* whenCompareDifferentFiles_thenDifferencesFound - compares original XML files with differences and print those differences.
* whenCompareSameFiles_thenDifferencesNotFound - compares two identical XML files for testing of library.
* whenCompareFilesWithMultipleDifferences_thenDifferencesFound - compares two custom XML files that I prepared to debug my algorithm and print that differences.