package org.example.SateMachineFlow;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.SateMachineFlow.WorkProgressState.Approved;
import static org.example.SateMachineFlow.WorkProgressState.Escalated;
import static org.example.SateMachineFlow.WorkProgressState.Submitted;

/**
 * <p>
 * This file defines a controller that exposes an API endpoint for hitting a state machine. The
 * endpoint takes a state as a path parameter and returns the name of the person responsible for the
 * work in the next state.
 * <p>
 * The hitState() method uses a switch statement to determine the current state of the work. The
 * method then calls the nextState() method to get the next state of the work. The method finally
 * calls the responsiblePerson() method to get the name of the person responsible for the work in
 * the next state.
 */
@RestController
public class StateMachinesThroughApiHit {

  @RequestMapping("/hitState/{state}")
  public String hitState(@PathVariable String state) {
    WorkProgressState workProgressState = switch (state) {
      case "approved" -> Approved;
      case "escalated" -> Escalated;
      case "submitted" -> Submitted;
      default -> throw new IllegalStateException("Unexpected value: " + state);
    };

    return workProgressState.nextState().responsiblePerson();
  }
}
