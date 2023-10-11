Feature: Get policies serviced by agent
  # User story

  Background: Available policies
    Given the following policies exist:
      | Policy  | Agent |
      | P0001   | A0001 |
      | P0002   | A0002 |
      | P0003   | A0002 |
      | P0004   | A0003 |
      | P0005   | A0003 |
      | P0006   | A0003 |
      | P0007   | A0001 |
      | P0008   | A0002 |
      | P0009   | A0002 |
      | P0010   | A0001 |

  Scenario: An agent with servicing policies
    When an agent A0001 query his or her servicing policies
    Then the total number of policies is 3
#    Then the policies should be listed as following
#      | Policy  | Agent |
#      | P0001   | A0001 |
#      | P0007   | A0001 |
#      | P0010   | A0001 |

  Scenario: An agent without servicing policy
    When an agent A0004 query his or her servicing policies
    Then the total number of policies is 0

