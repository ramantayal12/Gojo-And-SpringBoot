package org.example.SateMachineFlow;

/**
 * This file defines an enum for the different states of a work progress.
 * The states are:
 *   - Submitted: The work has been submitted by the employee.
 *   - Escalated: The work has been escalated to the team leader.
 *   - Approved: The work has been approved by the department manager.
 */

/**
 * The responsiblePerson() method returns the name of the person responsible for the work in the current state.
 * The nextState() method returns the next state of the work.
 */
public enum WorkProgressState {
  Submitted {
    @Override
    public String responsiblePerson() {
      return "Employee";
    }

    @Override
    public WorkProgressState nextState() {
      return WorkProgressState.Escalated;
    }
  },
  Escalated {
    @Override
    public String responsiblePerson() {
      return "Team Leader";
    }

    @Override
    public WorkProgressState nextState() {
      return WorkProgressState.Approved;
    }
  },
  Approved {
    @Override
    public String responsiblePerson() {
      return "Department Manager";
    }

    @Override
    public WorkProgressState nextState() {
      return null;
    }
  };

  public abstract String responsiblePerson();
  public abstract WorkProgressState nextState();
}
