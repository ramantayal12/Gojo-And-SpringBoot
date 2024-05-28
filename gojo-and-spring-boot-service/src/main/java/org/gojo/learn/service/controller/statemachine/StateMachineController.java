package org.gojo.learn.service.controller.statemachine;


import org.gojo.learn.service.stateMachineFlow.springStateMachine.OrderEvents;
import org.gojo.learn.service.stateMachineFlow.springStateMachine.OrderStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/state-machine")
public class StateMachineController {

  private final StateMachineFactory<OrderStates, OrderEvents> stateMachine;

  @Autowired
  public StateMachineController(StateMachineFactory<OrderStates, OrderEvents> stateMachine) {
    this.stateMachine = stateMachine;
  }

  @GetMapping("/start-machine")
  public String startMachine() {

    stateMachine.getStateMachine().startReactively();
    return "State Machine Started Successfully! ";

  }

  @PostMapping("/send-event")
  public String sendEvent(@RequestParam("event") OrderEvents event) {
    stateMachine.getStateMachine().sendEvent(event);
    var state = stateMachine.getStateMachine().getState();
    return String.valueOf(state);
  }


}
