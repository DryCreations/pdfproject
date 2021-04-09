- [Grading Breakdown](#grading-breakdown)
  * [Team Project](#team-project)
    + [Process (12 Points)](#process-12-points)
    + [Traceability (5 points)](#traceability-5-points)
    + [Test Coverage (8 points)](#test-coverage-8-points)
    + [Product Backlog Items or Requirements (10 points)](#product-backlog-items-or-requirements-10-points)
    + [Design Exists in Formatted Code Documentation (5 points)](#design-exists-in-formatted-code-documentation-5-points)
  * [Individual Contribution](#individual-contribution)
    + [Commits and Pull Requests (10 points)](#commits-and-pull-requests-10-points)
    + [Minutes (5 points)](#minutes-5-points)
    + [Pull Request Reviews (10 points)](#pull-request-reviews-10-points)
    + [Closed Actions (5 points)](#closed-actions-5-points)
  * [Graduate Level](#graduate-level)
    + [Program By Contract (5 points)](#program-by-contract-5-points)
    + [Literate Programming (10 points)](#literate-programming-10-points)

# Grading Breakdown

This document breaks down the grading items on pilot so we can track what items are met, and what artifacts we have as proof of the item being completed.

This document serves primarily as a check list for the grade items, there are separate documents for each graded item, click on the heading to read further.

## Team Project

### [Process](./process.md) (12 Points)

These points are awarded for evidence that the team followed a process, either Waterfall or Agile. Adherence to each process will be judged by your Instructor or Grader though assessment or process artifacts such as "pointed" and assigned tasks, Pull Requests, documents, Minutes, and Actions. See the description of the Traceability grade item for additional information about expected process artifacts.

Assessment: For full credit, each team member must demonstrate adherence to a process.

- [ ] [2 point] Write at least 2 User Stories per team member.
- [ ] [3 point] Identify at least 15 Tasks and place them in the backlog.
- [ ] [5 point] Complete 5 non-trivial programming Tasks per team member including test artifacts, formatted documentation, and acceptance through Pull Request Approval.
- [ ] [2 point] Produce a Software Design Document (SDD) via automatic generation from formatted comments.

### [Traceability](./traceability.md) (5 points)

These points are available for documenting traceability from tests to implementation to design to Requirements or User Stories. These points are all or nothing. There is either traceability or were isn't.

- [ ] There must be documented traceability from every Commit to the pertinent Task, from every Task to the pertinent User Story.
- [ ] There must be documented traceability from every Pull Request to a User Story.
- [ ] There must be documented traceability from every test to a User Story.

### [Test Coverage](./test_coverage.md) (8 points)

You will write code and modify code as part of the implementations of Requirements or User Stories. You must test the code as you write or modify. How do you know when you are done testing? You are done testing when the tests indicate that the Requirements or User Stories have been implemented AND as much of the code written or changed as possible executed during tests. If large amounts of code do not execute under test, you either wrote/changed too much code, or you don't have sufficient tests.

Use an automated code coverage tool to measure the code that executes under test.

To earn theses points, the team must implement at least 5 non-trivial requirements, and provide test coverage for code involved with implementing the requirements.

- [ ] Code is being tested.
- [ ] A code coverage tool is being used to measure the code that executes under a test.
- [ ] the team has implemented at least 5 non-trivial requirements that are demonstrably being covered by tests.

### [Product Backlog Items or Requirements](./backlog.md) (10 points)

Teams using an Agile Process define a backlog of User Stories and Tasks. To earn full credit, there must be at least 10 correctly stated User Stories that each have one or more pointed Tasks.

- [ ] A backlog of user stories and tasks has been defined.
- [ ] 10 User Stories have been correctly stated.
- [ ] Every user story has at least one pointed task

### [Design Exists in Formatted Code Documentation](./design.md) (5 points)

For both Waterfall and Agile, each team must produce an "As-Built" Software Design Document (SDD) that is substantially generated from formatted code documentation. To earn full credit, design documentation must exist for all code that was written or modified by the team.

- [ ] A Software Design Document can be generated from formatted code documentation.
- [ ] Design documentation exists for all code that was written or modified by the team

## Individual Contribution

### [Commits and Pull Requests](./pull_request.md) (10 points)

The sum of your number of meaningful commits and pull requests needs to be greater than or equal to 20 to earn full credit.

- [ ] Hayden
- [ ] Cassandra
- [ ] Parker
- [ ] William
- [ ] Derrick
- [ ] Hunter
- [ ] Charles

### [Minutes](./minutes.md) (5 points)

To earn full credit, you must be identified as "present" in Minutes for at least 5 separate meetings, and the Minutes must document your non-trivial contribution in the Project. Completion of Action Items that are documented in the Minutes can be used to document your contribution.

- [ ] Hayden
- [ ] Cassandra
- [ ] Parker
- [ ] William
- [ ] Derrick
- [ ] Hunter
- [ ] Charles

### [Pull Request Reviews](./reviews.md) (10 points)

To earn full credit, you must provide at least 10 non-trivial items of feedback within Pull Requests made by someone other than yourself. You cannot review your own Pull Requests.

- [ ] Hayden
- [ ] Cassandra
- [ ] Parker
- [ ] William
- [ ] Derrick
- [ ] Hunter
- [ ] Charles

### [Closed Actions](./closed_actions.md) (5 points)

To earn full credit, you must complete and close at least 5 Actions that are correctly stated with objective completion criteria, documented in Minutes, and assigned to you with a due date. You may be required to supply evidence of Action completion.

- [ ] Hayden
- [ ] Cassandra
- [ ] Parker
- [ ] William
- [ ] Derrick
- [ ] Hunter
- [ ] Charles

## Graduate Level

### [Program By Contract](./contract.md) (5 points)

To earn full credit, each Graduate Student must document contracts for at least 5 non-trivial functions, methods, or classes. Contracts must be verified using pre-condition, post-condition, and invariant assertions within implementations.

Undergraduate Students receive these points for free.

- [ ] 5 non-trivial functions, methods, or classes have documented contracts.

### [Literate Programming](./literate.md) (10 points)

To earn full credit, each Graduate Student must demonstrate all of the following characteristics of Literate Programming for at least 5 non-trivial functions or methods:

- Method or function names and arguments must be self documenting.
- Named data types must be used as a form of documentation.
- Contractual elements like acceptable values, preconditions, and postconditions must be documented.
- Formatted documentation must explain WHY each function or method exists.
- Undergraduate Students receive these points for free.

- [ ] 5 non-trivial functions meet the described characteristics of literate programming.
