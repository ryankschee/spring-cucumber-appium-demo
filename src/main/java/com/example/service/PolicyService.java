package com.example.service;

import com.example.model.PolicyInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PolicyService {
  private Map<String, PolicyInfo> policies = new HashMap<>();

  public void addPolicy(final PolicyInfo policy) {
    policies.put(policy.getPolicyNumber(), policy);
  }

  public List<PolicyInfo> getAllPolicies() {
    return new ArrayList<>(policies.values());
  }

  public List<PolicyInfo> getPoliciesByAgentCode(final String agentCode) {
    List<PolicyInfo> result = new ArrayList<>();
    policies.forEach((key, policy) -> {
      if (policy.getAgentCode().equals(agentCode)) {
        result.add(policy);
      }
    });

    return result;
  }
}
