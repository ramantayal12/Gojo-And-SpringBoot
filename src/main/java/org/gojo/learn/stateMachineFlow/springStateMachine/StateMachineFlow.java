package org.gojo.learn.stateMachineFlow.springStateMachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
public class StateMachineFlow {

  private final StateMachine<String, String> stateMachine;;

  @Autowired
  public StateMachineFlow(StateMachine<String, String> stateMachine) {
    this.stateMachine = stateMachine;
  }

  private void process(){
    stateMachine.startReactively();
    stateMachine.getState();

    sendMachineEvent("E1");
    stateMachine.getState();

    sendMachineEvent("E2");
    stateMachine.getState();

    sendMachineEvent("end");
    stateMachine.getState();
  }

  private void sendMachineEvent(String eventName){
    stateMachine.sendEvent("E!");
  }
}
