# Delivery Robot

A Java console application that simulates delivery robot routes and analyzes how frequently the robot turns right.
The project demonstrates basic multithreading with `ExecutorService`, concurrent task execution, synchronized access to shared data, random route generation, and frequency analysis with the Java Stream API.

## Features

* Generates random delivery robot routes
* Runs route generation and analysis concurrently
* Counts right-turn commands in every route
* Stores frequency statistics in a shared map
* Finds the most common number of right turns
* Sorts and displays the remaining results by frequency
* Uses synchronization to protect shared mutable state

## Tech Stack

* Java
* Gradle
* Java Concurrency API
* Java Stream API
* JUnit 5

## Route Commands

Each generated route consists of three possible commands:

| Command | Meaning      |
| ------- | ------------ |
| `R`     | Turn right   |
| `L`     | Turn left    |
| `F`     | Move forward |

## How It Works

The application performs the following steps:

1. Creates a fixed thread pool.
2. Submits 1,000 route-generation tasks.
3. Generates a route containing 100 commands in every task.
4. Counts the number of `R` commands in each route.
5. Updates a shared frequency map.
6. Finds the most frequently occurring number of right turns.
7. Prints the complete statistics.




## Main Configuration

The application uses the following constants:

```java
static final int ROUTES_COUNT = 1000;
static final char TARGET_CHAR = 'R';
```

Each route is generated with:

```java
String route = generateRoute("RLRFR", 100);
```

This means that the application generates:

* 1,000 routes
* 100 commands per route
* `R`, `L`, and `F` commands
* Statistics for the `R` command
