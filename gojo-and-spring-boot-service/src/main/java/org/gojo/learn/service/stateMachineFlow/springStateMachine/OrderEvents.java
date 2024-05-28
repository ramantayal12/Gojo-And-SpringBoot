package org.gojo.learn.service.stateMachineFlow.springStateMachine;


public enum OrderEvents {

  E1("E1"),
  E2("E2"),
  FINAL_EVENT("END_EVENT");

  private final String eventName;

  OrderEvents(String eventName) {
    this.eventName = eventName;
  }

  public String getName() {
    return eventName;
  }
}
