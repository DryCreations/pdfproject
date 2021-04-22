# Test Coverage

## Code is being tested.

Code is being tested using JUnit, you can find the tests in the directory [here](../src/test)

## A code coverage tool is being used to measure the code that executes under a test.

JaCoCo is being used as a code coverage tool, the results can be found by downloading the code coverage results under the relavent [github action](https://github.com/DryCreations/pdfproject/actions/workflows/test.yml)

you can read about the JaCoCo implementation in it's relevent [pull request](https://github.com/DryCreations/pdfproject/pull/52)

## The team has implemented at least 5 non-trivial requirements that are demonstrably being covered by tests.

### Requirement 1

Task 10.2 was implemented as the [MoveStack](../src/main/java/com/groupseven/pdfproject/MoveStack.java) class, this class is being tested [here](src/test/java/com/groupseven/pdfproject/MoveStackTest.java). 

Other testing associated with this task is described [here](../Test_Artifacts/test_artifact_Action26_and_Action27.md)

### Requirement 2

Task 14.1 required implementation of a canvas to display a pdf. You can see a description of our testing [here](../Test_Artifacts/test_artifact_Action28.md)

### Requirement 3

Task 14.2 required implementation of a tool ribbon to organize our tool. You can see a description of our testing [here](../Test_Artifacts/test_artifact_Action29.md)

### Requirement 4

Task 14.3 required implementation of a tool box to organize various buttons that would allow us to interact with a canvas. You can see a description of our testing [here](../Test_Artifacts/test_artifact_Action30.md)

### Requirement 5

Task 18.1 required us to "Demonstrate a way to select an element on the page" you can find a description of our testing [here](../Test_Artifacts/test_artifact_Action33.md)
