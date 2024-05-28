package org.gojo.learn.service.stateMachineFlow.springStateMachine;

import java.util.EnumSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderStates, OrderEvents> {

  @Override
  public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states)
      throws Exception {

    states
        .withStates()
        .initial(OrderStates.START_STATE)
        .end(OrderStates.FINAL_STATE)
        .states(EnumSet.allOf(OrderStates.class));

  }

  @Override
  public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions)
      throws Exception {

    transitions.withExternal()
        .source(OrderStates.START_STATE)
        .target(OrderStates.S1)
        .event(OrderEvents.E1)
        .and()

        .withExternal()
        .source(OrderStates.S1)
        .target(OrderStates.S2)
        .event(OrderEvents.E2)
        .and()

        .withExternal()
        .source(OrderStates.S2)
        .target(OrderStates.FINAL_STATE)
        .event(OrderEvents.FINAL_EVENT);
  }

  @Override
  public void configure(StateMachineConfigurationConfigurer<OrderStates, OrderEvents> config)
      throws Exception {

    StateMachineListenerAdapter<OrderStates, OrderEvents> listenerAdapter =
        new StateMachineListenerAdapter<>() {
          @Override
          public void stateChanged(State<OrderStates, OrderEvents> from,
              State<OrderStates, OrderEvents> to) {
            log.info("State changed from {} to {}", from, to);
          }
        };
    config.withConfiguration().listener(listenerAdapter);
  }

}
