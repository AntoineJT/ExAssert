# ExAssert
An assertion system in Java throwing exceptions

## Introduction

When I program, I'm used to check preconditions with assertions.
In Java, we are often using exceptions for this purpose.
In order to do cleaner code, I tinkered this little library.

This library uses [Gradle](https://gradle.org/), which makes
building it and using it as a dependency easy.

## Tips

To check behaviors in loops and other performance-critical sections of code
where the check is optional, you may use Java built-in assertions.
Just don't forget to run your software with `-ea` flag when you
want them to not be skipped by the JVM.

## How to build?

Just run the following command in the root project folder:
```
gradlew build
```

## How to run tests?

*Now, the tests are useless, but in the future I plan to
improve this*

Just run the following command in the root project folder:
```
gradlew test
```

## How to use it as a dependency?
### Gradle

To use it as a dependency, you need to use [jitpack](https://jitpack.io/).
You need to add this to your `repositories` block:
```Groovy
maven {
    // other custom maven repositories can go here too
    url 'https://jitpack.io'
}
```

Then add this line to your `dependencies` block:
```Groovy
implementation 'com.github.AntoineJT:ExAssert:master'
```
