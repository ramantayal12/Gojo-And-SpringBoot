package org.gojo.learn.service.trino.clients;

import java.util.Map;
import org.gojo.learn.service.trino.enums.ClientType;

public abstract class BaseHttpClient<T> {

  public abstract T postCall(String url,
      Map<String, String> data);

  public abstract ClientType getType();

}
