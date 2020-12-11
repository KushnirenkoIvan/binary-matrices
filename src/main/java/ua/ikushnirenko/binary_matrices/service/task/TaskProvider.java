package ua.ikushnirenko.binary_matrices.service.task;

/**
 * Implemented for test purposes,
 * it avoids implicit task creation in the services,
 * allows mocking for unit testing.
 */
public class TaskProvider {

    public <T extends Task> T get(T t) {
        return t;
    }
}
