##Demo Rest API

This project demonstrates implementing a rest API in Java with Spring, Hibernate and MySQL.

Uses:
Spring    4.0.2.RELEASE
Hibernate 4.3.2.Final
MySQL     5.1.29
Jackson   2.3.0

Project includes jUnit integration tests and a testData.sql file (demoRestAPI\src\test\resources) for testing.

This project has 3 objects: Person, Shift, and ShiftAssignment. Persons are assigned to Shifts for a day via ShiftAssignment. Fetch and delete operations can be accessed by id. Save and update operations consume JSON objects.

####/person
- GET     /person       Fetch all people
- GET     /person/1     Fetch person with id of 1
- POST    /person       Save person 
- PUT     /person       Update person
- DELETE  /person/1     Delete person with id of 1


####/shift
- GET     /shift        
- - Fetch all shifts
- GET     /shift/1      Fetch shift with id of 1
- POST    /shift        Save shift 
- PUT     /shift        Update shift
- DELETE  /shift/1      Delete shift with id of 1


####/assign
- GET     /assign/1     Fetch shift assignment with id of 1
- GET     /assign       Fetch shift assignments within date range
-                       {startDtm, endDtTm}
- GET     /assign       Fetch shift assignments for person or shift within date range
-                       {startDtm, endDtTm, shiftId, personId}
- POST    /assign       Save shift assignment
- PUT     /assign       Update shift assignment
- DELETE  /assign/1     Delete shift assignment with id of 1


####Person
- firstName             First name of person (max 30)
- lastName              Last name of person (max 30)
- activeInd             Whether person is active (can deactivate instead of delete, default Y)

####Shift
- label                 Label for shift
- minAssigned           Min assigned threshold (implied, not enforced, default 1)
- maxAssigned           Max assigned threshold (enforced, default 1)
- activeInd             Whether shift is active (can deactivate instead of delete, default Y)

####ShiftAssignment
- personId              Id of person to assign to shift
- shiftId               Id of shift to assign person to
- shiftDt               Date of person/shift assignment
- activeInd             Whether shift assignment is active (can deactivate instead of delete, default Y)
