import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a fork used by philosophers. Each fork is a shared resource
 * that can be picked up and put down using locks to avoid race conditions.
 */
class Fork {
    private final Lock lock = new ReentrantLock();

    /**
     * Attempts to pick up the fork. Returns true if successful, false otherwise.
     */
    public boolean pickUp() {
        return lock.tryLock();
    }

    /**
     * Releases the fork by unlocking it.
     */
    public void putDown() {
        lock.unlock();
    }

    public static void main(String[] args) {
        System.out.println("Fork class loaded successfully.");
    }
}

/**
 * Represents a philosopher in the dining philosophers problem.
 * Each philosopher alternates between thinking and eating.
 */
class Philosopher extends Thread {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;

    /**
     * Constructor for a philosopher.
     * id : The philosopher's unique identifier.
     * leftFork : The left fork assigned to the philosopher.
     * rightFork : The right fork assigned to the philosopher.
     */
    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    /**
     * Simulates the philosopher thinking for a random duration.
     */
    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    /**
     * Simulates the philosopher eating for a random duration.
     */
    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                if (id % 2 == 0) { // Ensuring an ordered lock acquisition to prevent deadlock
                    if (leftFork.pickUp()) {
                        if (rightFork.pickUp()) {
                            eat();
                            rightFork.putDown();
                        }
                        leftFork.putDown();
                    }
                } else {
                    if (rightFork.pickUp()) {
                        if (leftFork.pickUp()) {
                            eat();
                            leftFork.putDown();
                        }
                        rightFork.putDown();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * Main class to execute the Dining Philosophers simulation.
 * It creates multiple philosophers and forks and starts the simulation.
 */
public class DiningPhilosophers {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Fork[] forks = new Fork[numPhilosophers];

        // Initialize forks
        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Fork();
        }

        // Initialize philosophers and assign forks
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % numPhilosophers]);
            philosophers[i].start();
        }
    }
}