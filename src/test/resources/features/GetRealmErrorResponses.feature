Feature: Validate error responses for Get Realm
As a and end user
I need to be intimated that I have supplied invalid information
So that I can correct it




Scenario Outline: User inputs invalid realm id in get call
Given I have made a get realm call using invalid realm "<id>"
When I extract the response for the get call
Then I validate that the "<code>" and "<description>" is valid

Examples:
|id       |code                 |description                                    |
|10000    |InvalidRealmId       |Invalid realm id [10000].                      |
|text     |InvalidRealmId       |Invalid realm id [text].                       |
|*(@#     |InvalidRealmId       |Invalid realm id [*(@#].                       |
|-100    |InvalidRealmId        |Invalid realm id [-100].                       |




Scenario Outline: User inputs a realm id that does not exist
Given I have made a get call with an "<id>" that does not exist ensured by deleting it
When I extract the response for the get call
Then I validate that the "<code>" and "<description>" is valid

Examples:
|id       |code                 |description                                    |
|589      |RealmNotFound        |Realm [589] not found.                         |



