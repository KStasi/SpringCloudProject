package ua.akondaur.client;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.akondaur.db.Goal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
class GoalController {
	@Autowired
	private ProxyService goalServiceClient;

	@Autowired
	ConfigClientAppConfiguration configClientAppConfiguration;

	@GetMapping("/config")
	public Map getConfig() {
		Map config = new HashMap<String, String>();
		config.put("property1", configClientAppConfiguration.getProperty1());
		config.put("property2", configClientAppConfiguration.getProperty2());
		config.put("property3", configClientAppConfiguration.getProperty3());
		config.put("property4", configClientAppConfiguration.getProperty4());
		config.put("property5", configClientAppConfiguration.getProperty5());
		config.put("property6", configClientAppConfiguration.getProperty6());
		return config;
	}

	@GetMapping("/goals")
	public Map getAllGoals() {
		return goalServiceClient.getAllGoals();
	}

	@GetMapping("/goals/{id}")
	public Map getGoalById(@PathVariable(value = "id") long id) {
		return goalServiceClient.getGoalById(id);
	}

	@PostMapping("/goals")
	public Map createGoal(@Valid @RequestBody Goal goal) {
		return goalServiceClient.createGoal(goal);
	}

	@PutMapping("/goals/{id}")
	public Map updateGoal(@PathVariable(value = "id") Long id, @Valid @RequestBody Goal goalDetails) {
		return goalServiceClient.updateGoal(id, goalDetails);
	}

	@DeleteMapping("/goals/{id}")
	public Map deleteGoal(@PathVariable(value = "id") Long id) {
		return goalServiceClient.deleteGoal(id);
	}

}
