package org.gojo.learn.stateMachineFlow.springStateMachine;

public enum OrderStates {

  START_STATE("START_STATE"),
  S1("S1"),
  S2("S2"),
  FINAL_STATE("FINAL_STATE");

  private final String stateName;

  OrderStates(String stateName) {
    this.stateName = stateName;
  }

  public String getName() {
    return stateName;
  }
}
