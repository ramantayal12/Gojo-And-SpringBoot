package org.gojo.learn.service.trino.service.trino.impl;

import java.util.List;

public abstract class TrinoService {

  public abstract List<String> getTransactionsForUser(String growwUserId);

}
