package org.gojo.learn.service.trino.service.trino.impl;

import java.util.List;
import org.gojo.learn.service.trino.dao.DaoDataSourceFactory;
import org.gojo.learn.service.trino.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrinoServiceImpl extends TrinoService {

  private final DaoDataSourceFactory dataSourceFactory;

  @Autowired
  public TrinoServiceImpl(DaoDataSourceFactory dataSourceFactory) {
    this.dataSourceFactory = dataSourceFactory;
  }

  @Override
  public List<String> getTransactionsForUser(String growwUserId) {
    String query =
        "select * from cp_shared_consumer_payments.upi_payment_transactions where groww_user_id = '"
            + growwUserId + "'";
    DataSourceType dataSourceType = DataSourceType.PLATFORM_LAKEHOUSE;

    return dataSourceFactory.getDao(dataSourceType)
        .executeQuery(dataSourceType, query, 0, 100);
  }
}
