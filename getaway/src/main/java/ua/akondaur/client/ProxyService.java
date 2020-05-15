package ua.akondaur.client;

import org.springframework.stereotype.Component;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.akondaur.db.Goal;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class ProxyService {
    private static final String BACKEND_NAME = "client";

    @Autowired
    private GoalServiceClient goalServiceClient;

    @CircuitBreaker(name = BACKEND_NAME, fallbackMethod = "fallbackGetAll")
    public Map getAllGoals() {
        return goalServiceClient.getAllGoals();
    }

    @CircuitBreaker(name = BACKEND_NAME, fallbackMethod = "fallbackGetOne")
    public Map getGoalById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        return goalServiceClient.getGoalById(id);
    }

    @Retry(name = BACKEND_NAME)
    public Map createGoal(@Valid @RequestBody Goal goal) {
        return goalServiceClient.createGoal(goal);
    }

    @Retry(name = BACKEND_NAME)
    public Map updateGoal(@PathVariable(value = "id") Long id, @Valid @RequestBody Goal goalDetails)
            throws ResourceNotFoundException {

        return goalServiceClient.updateGoal(id, goalDetails);
    }

    @Retry(name = BACKEND_NAME)
    public Map deleteGoal(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {

        return goalServiceClient.deleteGoal(id);
    }

    public Map fallbackGetAll(Throwable e) {
        Map result = new HashMap();

        result.put("instanceId", -1);
        result.put("content", "[]");
        return result;
    }

    public Map fallbackGetOne(Throwable e) {
        Map result = new HashMap();

        result.put("instanceId", -1);
        result.put("content", "{}");
        return result;
    }
}
