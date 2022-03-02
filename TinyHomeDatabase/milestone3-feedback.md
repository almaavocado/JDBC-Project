# Team 9
- Score: posted on BB
- Collaboration: Equal
- Changes during grace period
  - penalty: -5
  - Wrote 3 required queries that were missing
  - Changed program significantly as it was not able to run any queries nor delete
  - Wrote Javadoc comments for many methods

## Summary
- **Runtime**: Insert works, delete and queries not implementd. Some runtime errors
- **Queries**: Some of the criteria not satisfied and some have syntax errors
- **Implementation/JPA**:
- **Database model**:Good improvements, though there are some improvements still needed.


## Running project
- **Initial Data**
  - Partially works
  - Some of the insert statements in `seed-data.sql` **do not satisfy the referential integrity constraint**, so that data is not loaded!
  - The errors are reported in the log and should have been fixed
- **Queries** 
  - None implemented. 
    - Attempted during grace period, however, these are running into exceptions
    - Query 1 runtime error: `Internal Exception: java.sql.SQLSyntaxErrorException: Unknown column 't1' in 'field list'`
    - Query 2 runtime exception: `java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax... near 'EXCEPT SELECT funder_name FROM FUNDER JOIN CONTRIBUTION C on FUNDER.PHONE_NUMBER' `
      - Recall that MySQL does not support the `EXCEPT` operator, set difference
    - Query 3 runtime exception: `java.sql.SQLSyntaxErrorException: Unknown column 'f.funderName' in 'field list'`
    - Remaining queries execute without errors
  - Attempt to implement interface to view objects of the different classes
  - Runtime exceptions due to incorrect `toString` methods that used the wrong type specifier in the `format` method. **Fixed during grace period**

- **Insert**
  - Ok, however... the requirements were to allow user to insert one type of object, you've implemented more. 
  - User must know the surrogate key values. This should not be the case. Surrogate keys are for internal implementation, not for users to know.
  - Inserting dates... program expects user to enter a Long!? (ProgramEnrollment)
- **Delete**: Not implemented
  - Implemented during grace period
- **User interface**: Not the expected user interactions
  - The requirement was to allow insertion of just one object, not every object
  - The attempt to implement generically has a lot of problems
  - No appropriate formatting of the results of queries
 
## 6 required queries
- **Executing queries**: 
  - Syntax error in query #2 (see comment on criteria 5)
  - How can the expenses for all houses be the same? Query is incorrect, also, it's not the expenses per house, it's the expense of the community!
  - Syntax error: Query 1 has INNER JOIN without the necessary join condition (ON clause)
- **Criteria 1**: retrieve information from at least 3 tables --> Good
- **Criteria 2**: 2+ use outer joins appropriately
  - Inappropriate use of outer join in queries where it's used.
- **Criteria 3**: 3+ of the queries must aggregate values for groups --> Good
- **Criteria 4**: 3 of the queries must use different aspects of subqueries
  - Uses subqueries in two ways
- **Criteria 5**: use set difference in a non-trivial way
  - Syntax error: MySQL does not support the set difference operator, so you must use an alternative of either (1) outer join or (2) `NOT IN`


## Implementation/JPA
- Well done
- Inappropriate/incorrect implementation
  - `Employment.person` and `AfterStay.person` are each incorrectly implemented as a `@OneToOne` relationship; the diagram states that it should be `@ManyToOne`

- Missing Javadoc comments

### Application
- Missing default case in `switch`
- `viewDataMenu`: No formatting of output

## Database model
- Much better organization/structure of the documentation
- Some of the feedback was addressed
- Some feedback not addressed or addressed incorrectly
- Funder: missing city
- Community : missing state and zipcode
- TinyHome.available is a derived attribute, but not modeled as such
- Feedback was provided that the database should have a historical component of when someone occupied a TinyHome. Such information is necessary, rather than a limited model that can only store who is currently occupying the homes. Your model already says a Person can have many AfterStays, so that means they could occupy more than one TinyHome. None of that is modeled and there's no justification provided.

