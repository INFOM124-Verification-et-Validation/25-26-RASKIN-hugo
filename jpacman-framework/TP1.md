# TP 1 - Beginning of the project

## Exercice 1
Open `board.DirectionTest` and create additional test methods for e.g., the south, east, and west directions.

## Exercice 2
Open the `board.OccupantTest` class and implement the 3 empty tests:
1. `noStartSquare()` test asserts that a unit has no square to start with, i.e., a unit "has no square" at the beginning.
2. `testOccupy()` test verifies that the unit indeed has the target square as its base after occupation. In other words, if a unit is occupied by a(ny) basic square, one should contain the other.
3. `testReoccupy()` test verifies that the unit indeed has the target square as its base after double occupation.

## Exercice 3
Open the `board.BoardTest` class and implement the following tests :
1. `verifyWidth()` verifies that the board's width is **equal to** its max width.
2. `verifyHeight()` verifies that the board's height is **equal to** its max height.
3. `testSquareAt` test asserts that X Y coordinates are indeed on the board.

**Note : Board is already declared in the test class (see variable `board`), use it to verify your assertions
Also, maybe you'll have to import the needed assertions from AssertJ module like :`import static org.junit.jupiter.api.Assertions.__`
Here is a link for AssertJ documentation: https://assertj.github.io/doc/#assertj-core-assertions-guide
and for JUnit : https://docs.junit.org/5.0.1/api/org/junit/jupiter/api/Assertions.html**