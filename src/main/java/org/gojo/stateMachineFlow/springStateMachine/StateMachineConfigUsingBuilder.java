package org.gojo.stateMachineFlow.springStateMachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

@Configuration
public class StateMachineConfigUsingBuilder {

  private StateMachine<String, String> stateMachine;

  @Bean
  public void buildStateMachine() throws Exception {
    StateMachineBuilder.Builder<String, String> builder
        = StateMachineBuilder.builder();
    builder.configureStates().withStates()
        .initial("SI")
        .state("S1")
        .end("SF");

    builder.configureTransitions()
        .withExternal()
        .source("SI").target("S1").event("E1")
        .and().withExternal()
        .source("S1").target("SF").event("E2");

    this.stateMachine = builder.build();
  }
}
