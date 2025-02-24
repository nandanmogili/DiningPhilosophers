# Dining Philosophers Problem - Java Implementation
Team Members: Nandan Mogili and Sreekar Kompella

## Overview
This project provides a solution to the classic **Dining Philosophers Problem** using **Java threads and locks**. The solution ensures that:
- **No deadlock** occurs by enforcing an ordered locking mechanism.
- **No starvation** happens, ensuring that all philosophers get a chance to eat.

## How It Works
Each philosopher is modeled as a **thread** that alternates between **thinking** and **eating**. They need two forks (shared resources) to eat. The program ensures proper synchronization using **ReentrantLock**.

### Key Features
- Uses **Locks (ReentrantLock)** to prevent deadlocks.
- Philosophers pick up forks in a specific order to **prevent circular wait**.
- Implements **fair resource allocation** to avoid starvation.

## Files
- **`DiningPhilosophers.java`** - Main execution class that initializes philosophers and forks.
- **`Fork.class`** - Represents a fork with lock-based control.
- **`Philosopher.class`** - Implements a philosopher as a thread.

## How to Run
### 1. Compile the Java Files
```sh
javac DiningPhilosophers.java
```
### 2. Run the Program
```sh
java DiningPhilosophers
```
## Expected Output
You should see logs indicating philosophers thinking and eating in a **fair** manner:
```sh
Philosopher 0 is thinking.
Philosopher 1 is thinking.
Philosopher 2 is eating.
Philosopher 3 is thinking.
Philosopher 4 is eating.
...
```
The program runs indefinitely, simulating philosophers' behavior.
## Stopping the Program
To stop execution manually, press **CTRL+C**.

## License
This project is open-source and free to use.
