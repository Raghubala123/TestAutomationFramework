Feature: Sample Cucumber Scripts
@Google
Scenario Outline: Search using Google

Given the user navigates to the web site
When the user enters the text in the search field
And clicks the search button
Then the user will be able to view the search results by verifying the title

Examples:
|LineNumber|
|12        |
|13        |

@Bing
Scenario Outline: Search using Bing

Given the user navigates to the web site
When the user enters the text in the search field
#And clicks the search button
#Then the user will be able to view the search results by verifying the title

Examples:
|LineNumber |
|25         |
 
