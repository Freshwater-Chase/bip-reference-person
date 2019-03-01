Feature: PID based Person Info from Person Partner Service.

  @personinfo @happypath
  Scenario Outline: PID based Person Info from Person Partner Service.
    Given the claimant is a "<Veteran>"
    And invoke token API by passing header from "<tokenrequestfile>" and sets the authorization in the header
    When client request person info "<ServiceURL>" with PID data "<RequestFile>"
    Then the service returns status code = 200
    And the service returns ParticipantID PID based on participantId <participantID>

    @DEV
    Examples: 
      | Veteran           | tokenrequestfile               | ServiceURL          | RequestFile               | participantID |
      | dev-janedoe       | dev/janedoetoken.request       | /api/v1/persons/pid | dev/janedoe.request       |       6666345 |
      | dev-russellwatson | dev/russellwatsontoken.request | /api/v1/persons/pid | dev/russellwatson.request |      13364995 |

    @VA
    Examples: 
      | Veteran          | tokenrequestfile              | ServiceURL          | RequestFile              | participantID |
      | va-janedoe       | va/janedoetoken.request       | /api/v1/persons/pid | va/janedoe.request       |       6666345 |
      | va-russellwatson | va/russellwatsontoken.request | /api/v1/persons/pid | va/russellwatson.request |      13364995 |

  @personinfo
  Scenario Outline: PID based Person Info from Person Partner Service for incorrect PID.
    Given the claimant is a "<Veteran>"
    And invoke token API by passing header from "<tokenrequestfile>" and sets the authorization in the header
    When client request person info "<ServiceURL>" with PID data "<RequestFile>"
    Then the service returns status code = 400
    And the service returns message "<Text>"

    @DEV
    Examples: 
      | Veteran           | tokenrequestfile               | ServiceURL          | RequestFile         | Text                                           |
      | dev-janedoe       | dev/janedoetoken.request       | /api/v1/persons/pid | dev/invalid.request | No person found for participantID 12           |
      | dev-russellwatson | dev/russellwatsontoken.request | /api/v1/persons/pid | dev/null.request    | PersonByPidDomainRequest.participantID cannot be null |

    @VA
    Examples: 
      | Veteran          | tokenrequestfile              | ServiceURL          | RequestFile        | Text                                           |
      | va-janedoe       | va/janedoetoken.request       | /api/v1/persons/pid | va/invalid.request | No person found for participantID 12           |
      | va-russellwatson | va/russellwatsontoken.request | /api/v1/persons/pid | va/null.request    | PersonByPidDomainRequest.participantID cannot be null |
