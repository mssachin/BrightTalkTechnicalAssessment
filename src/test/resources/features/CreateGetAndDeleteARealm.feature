Feature: Registration and authentication of  user
As an end user
I need to be able to register and authenticate
So that I can use the application



Scenario Outline: Register and authenticate users to the application
Given when I post a request to register and authenticate request using the "<name>" and "<description>"
When I get a response for the create request
Then I validate the response is echoed with id and key
And I get the realm based on the realm id created from above
And I validate the values of the realm
And I clean up by deleting the realm


Examples:
|name                                 | description                   |
|Sachin Mylavarapu Test UserName1     |This is a test description     |







