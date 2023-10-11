package features.samples;

import com.example.model.PolicyInfo;
import com.example.service.PolicyService;
import features.CucumberBootstrap;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;

@Slf4j
@ContextConfiguration
public class SampleSteps extends CucumberBootstrap {

  @Autowired
  private PolicyService policyService;

  private List<PolicyInfo> searchResult;

  // This method executes after every scenario
  @After
  public void cleanUp() {
    log.info(">>> cleaning up after scenario!");
  }

  // This method executes after every step
  @AfterStep
  public void afterStep() {
    log.info(">>> AfterStep!");
  }

  // This method executes before every scenario
  @Before
  public void before() {
    log.info(">>> Before scenario!");
  }

  // This method executes before every step
  @BeforeStep
  public void beforeStep() {
    log.info(">>> BeforeStep!");
  }

  @Given("^the following policies exist:$")
  public void collectionOfPolicies(DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    rows.forEach(row -> {
      policyService.addPolicy(
          PolicyInfo.builder()
              .policyNumber(row.get("Policy"))
              .agentCode(row.get("Agent"))
              .build()
      );
    });
    log.info(">>> policies: {}", policyService.getAllPolicies());
  }

  @When("^an agent (.+) query his or her servicing policies$")
  public void getPoliciesByAgent(String agentCode) {
    searchResult = policyService.getPoliciesByAgentCode(agentCode);
    log.info(">>> searchResult: {}", searchResult);
  }

  @Then("^the total number of policies is (.+)$")
  public void policyCount(int policyCount) {
    log.info(">>> policyCount: {}", policyCount);
    assert searchResult.size() == policyCount;
  }
}
