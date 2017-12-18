Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: Search for the hascode.com website on Google

Given The Google homepage
When I search for "site:qasymphony.com"
Then the text "Software Testing, Test Case Management & QA Tools Built For Agile" is present
When I click the link "Software Testing, Test Case Management & QA Tools Built For Agile"
Then the text "Work Smarter, Test Faster" is present
And the page's URL should be "https://www.qasymphony.com/"