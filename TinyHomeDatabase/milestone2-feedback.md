# Team 9
- Grade: TBD
- Contributions : Equal

- **Do not push IDE files to repository**: It seems one of you used Eclipse for the project. There's not a problem with that, but you should not push IDE-specific files to the repository. Update the `.gitignore` file.

- **Be sure project works on both Apache Derby and MySQL**
  - Currently the `persistence.xml` file uses Apache Derby, it needs to work with `MySQL`

## Class Annotations
- **Runtime Exception**: attempting to insert into `Funder`: `A truncation error was encountered trying to shrink VARCHAR '18002738255' to length 10.`
- **More errors after the one above is fixed** You need to **ask for help if you're unable to resolve the errors I'm observing in this submission.
- **Unable to use column name `year`**: This is one attribute cose column you must rename because it's clear from the log that you cannot have a column named `year` becuase it's a reserved keyword in SQL.

### UML class diagram revision and implementation
- **Inconsistent**: UML class diagram has attributes not implemented. E.g.: several attributes in `Person`
- `FunderLocation` is not a good name as it's not descriptive of the information represented. It seems to suggest that it's the location of a funder which it clearly is not. A better name: `Contribution`, as in a funder makes contributions that fund TinyHome locations.
- Enumerated domains
  - These should not be implemented as Java enum's as that makes the enumeration tightly integrated with the source code. Any need to change the enumeration (add, remove, update a value), requires changing the code, recompiling, removing previous data, and recreating it all again. This creates **significant overhead in maintainance**.
  - Instead, enumerated domains need to be implemented as presented when we first learned of them in the DBDesign website. That is, the enumeration is a table and the rows of the table represent the enumerated values. This requires implementing an enumerated domain as a Java class.

### Relationships
- **Missing `mappedBy` attribute in the relationship annotations**. For example, there is only one relationship in the UML class diagram between `Funder` and `FunderLocation`. Yet, the implementation has two unidirectional relationships defined. To implement one relationship (matching the UML class diagram), the `@OneToMany` side has to be defined with the `mappedBy` attribute in the annotation; that indicates that it's the same relationship as defined on the other side as a `@ManyToOne`. Review the video where I presented a JPA example and/or also the JAP Wikibook. 
  - Every 1-to-Many relationship that you're trying to implement bidirectionally is missing the `mappedBy` attribute on the `@ManyToOne` annotation
  - Similar problem with `@OneToOne` relationships, since you're trying to implement bidirectional relationships, you need to use `mappedBy` on one side
- Missing attribute `nullable = false` in relationships. This has to be used when the participation constraint is mandatory to the parent class, which is the case in several of the relationships you have in the UML class diagram.
  - You did this correctly in `FunderLocation` at line 20, but it's missing from other places where it's needed.

### Constraints
- Good... but check the use of `nullable` and be sure you define all candidate keys

## Main program to create/persist objects
- Incorrect object creation
  - Lines 92-98: attempt to create a `Funder` object, but uses a phone number that is too long and does not set a value for `affiliation` even though a value is required due to `nullable = false` on the attribute.


## Miscellaneous
- Code must always have comments! In the case of Java, you're required to use Javadoc comments for classes and methods
- You must use descriptive names. Names such as `emp1, emp2, f1, l1...`, etc are not descriptive. If you're going to have several objects of the same type, use an ArrayList. Thus, instead of three `Expense` objects names `e1, e2, e3`, define one ArrayList object and add the `Expense` objects into it.
- Need to organize code better. In `TinyHomesApp`, you have a method that is about 150 lines long. Each method needs to be short, this is one of many lessons from lower-division classes and you must not abandon them. It's expected that you will apply what you learn in previous classes.
  - In addition, the method is named `createPersonEntity`, when it clearly does a lot more than that. 
