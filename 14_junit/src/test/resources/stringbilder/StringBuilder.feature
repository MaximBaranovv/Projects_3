Feature: StringBuilder testing

  Scenario: Testing an initial capacity of empty StringBuilder
    When Creating a StringBuilder object with an empty constructor
    Then The result has to be sixteen

  Scenario: Testing the StringBuilder(CharSequence constructor)
    Given A CharSequence object
    When Creating a StringBuilder object and passing the CharSequence object to it
    Then Getting CharSequence object

  Scenario: Testing the StringBuilder with the -1 initial capacity
    When Creating a StringBuilder object with the initial capacity = -1
    Then A NegativeArraySizeException should be thrown

  Scenario: Testing the StringBuilder by passing the null argument to it's constructor
    When Passing null object to the new StringBuilder object
    Then A NullPointerException should be thrown

  Scenario: Testing append() method
    Given StringBuilder object with "Hello " string
    When Appending a "World" string to the object
    Then Result is Hello world

  Scenario: Testing insert() method
    Given StringBuilder object with "Hello" string for insert
    When Inserting a "World" string to the object with the offset = 3
    Then Result is HelWorldlo

  Scenario: Testing replace() method
    Given StringBuilder object with "Hello" string for replace
    When Inserting a "World" string to the object with the start = 3 and end = 4
    Then Result is HelWorldo

  Scenario: Testing delete() method
    Given StringBuilder object with "Hello_World" string for delete
    When Delete a string from 3 and and to 6
    Then Result is HelWorld

  Scenario: Testing reverse() method
    Given StringBuilder object with "Hello_World" string for reverse
    When Reverse a string
    Then Result is dlroW_olleH

  Scenario: Testing capacity() method
    Given StringBuilder object
    When Appending object with "Small String " string
    Then Capacity = sixteen
    But Appending object with another "is now becoming large" string
    Then Capacity = thirty for

  Scenario: Testing length() method
    Given Empty StringBuilder object
    When Appending object with "Small String" string for appending
    Then Length = twelve

  Scenario: Testing charAt() method
    Given StringBuilder obj with "Hello_World" string
    When Passing an index that equals 4
    Then Chart equals o

  Scenario: Testing indexOf() method
    Given StringBuilder obj
    When Appending a "Cucumber" string
    Then Result is five