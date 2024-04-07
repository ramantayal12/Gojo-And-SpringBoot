package org.gojo.learn.stateMachineFlow.springStateMachine;

import static org.gojo.learn.stateMachineFlow.springStateMachine.OrderEvents.E1;
import static org.gojo.learn.stateMachineFlow.springStateMachine.OrderEvents.E2;
import static org.gojo.learn.stateMachineFlow.springStateMachine.OrderEvents.FINAL_EVENT;
import static org.gojo.learn.stateMachineFlow.springStateMachine.OrderStates.FINAL_STATE;
import static org.gojo.learn.stateMachineFlow.springStateMachine.OrderStates.S1;
import static org.gojo.learn.stateMachineFlow.springStateMachine.OrderStates.S2;
import static org.gojo.learn.stateMachineFlow.springStateMachine.OrderStates.START_STATE;

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
        .initial(START_STATE)
        .end(FINAL_STATE)
        .states(EnumSet.allOf(OrderStates.class));

  }

  @Override
  public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions)
      throws Exception {

    transitions.withExternal()
        .source(START_STATE)
        .target(S1)
        .event(E1)
        .and()

        .withExternal()
        .source(S1)
        .target(S2)
        .event(E2)
        .and()

        .withExternal()
        .source(S2)
        .target(FINAL_STATE)
        .event(FINAL_EVENT);
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
