Feature: Validate error responses for Create Realm
As a and end user
I need to be intimated that I have supplied insufficient information
So that I can correct it


Scenario: User does not supply realm name
Given I have not supplied realm name in the request as below and make a create call
|name                         |description                                        |
|                             |Error out as No Name                               |
Then I validate that the error code and description is as below
|code                         |message                                        |
|MissingRealmName             |Realm name is mandatory and must be supplied.      |



Scenario: User supplies existing realm name
Given I have made a call to create a realm as below
|name                                    |description                                        |
|Duplicate Realm Test Error Code Check Final       |This is a test for duplicate realm error           |
When I make a create call with an existing realm name created from above
|name                                    |description                                        |
|Duplicate Realm Test Error Code Check Final       |This is a test for duplicate realm error           |
Then I validate that the error code and description is as below
|code                         |message                                        |
|DuplicateRealmName           |Duplicate realm name [Duplicate Realm Test Error Code Check Final].       |
And I clean up by deleting the realm by id



Scenario: User supplies more  characters in realm name
Given I have supplied more than allowed chars in realm name and make a post call
|name                                                                                                     |description                                        |
|This is a Test for more than 100 characters in realm name this test should fail with an error reason1    |Error out as invalid realm name                    |
Then I validate that the error code and description is as below
|code                         |message                                        |
|InvalidRealmName             |Realm name should not be longer than 100 chars.    |




Scenario: User Supplies more characters in realm description
Given I have supplied excess characters in realm description and make a post call
|name                         |description                                        |
|Valid Name                   |This is a test for more than 255 characters in description this should error out with a reason Realm description should not be more than 255 characters long. Invalid Description is the error code for this scenario and this should throw an error. Fill 255 .                    |
Then I validate that the error code and description is as below
|code                         |message                                        |
|InvalidRealmDescription             |Realm description should not be longer than 255 chars.   |



