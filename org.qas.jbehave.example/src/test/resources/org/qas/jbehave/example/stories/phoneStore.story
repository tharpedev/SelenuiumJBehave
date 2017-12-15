Meta:
@author QASymphony.com
@categories tabularParam outComes
@skip


Narrative:
As a user
I want to perform an action
So that I can achieve a business goal


Scenario: Find product by price
Meta:
@skip
@ignored false
Given product list:
|name|price|
|iPhoneX|1100|
|Nokia 1100|20|
|Sam sung S8+|800|
When search product by price greater or equal 700.0
Then result are:
|name|price|
|iPhoneX|1100|
|Sam sung S8+|800|


Scenario: Find product by name
Given product list:
|name|price|
|iPhoneX|1100|
|Nokia 1100|20|
|Sam sung S8+|800|
|iPhone 4|100|
When search product by name iPhone
Then name of filtered products must contain iPhone
