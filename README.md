# Kotlin Memory Model Research intro: lock-free stack

## Implementation

Implementation can be found [here](src/main/kotlin/org/jub/solovev/lockfreestack), see files: `BasicStack.kt`
and `LockFreeStackImpl.kt`.

## Tests

Test sources can be found [here](src/test/kotlin/org/jub/solovev/lockfreestack), see files:

* `BasicStackTest.kt` for simple single-threaded tests;
* `LinearizabilityTest.kt` for linearizability tests implemented via _**lincheck**_. They could take some time to pass (1-2
  minutes on my computer).

Tests can be executed via **_Gradle_**. To run them execute the following command from the project root:

```bash
./gradlew test
```
* To run tests on Windows use `gradlew.bat` instead.
* To make `gradlew` executable, `sudo chmod +x gradlew` can be used.