package org.gojo.learn.service.trino.dao.impl;

import org.gojo.learn.service.trino.enums.DataSourceType;
import org.gojo.learn.service.trino.datasource.DataSourceFactory;
import org.gojo.learn.service.trino.dao.BaseDataSourceSourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlatformLakeHouseDao extends BaseDataSourceSourceDao {

  @Autowired
  public PlatformLakeHouseDao(
      DataSourceFactory dataSourceFactory) {
    super(dataSourceFactory);
  }

  @Override
  public DataSourceType getType() {
    return DataSourceType.PLATFORM_LAKEHOUSE;
  }

}
