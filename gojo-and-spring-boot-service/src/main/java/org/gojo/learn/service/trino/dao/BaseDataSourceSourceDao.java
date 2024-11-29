package org.gojo.learn.service.trino.dao;

import org.gojo.learn.service.trino.enums.DataSourceType;
import org.gojo.learn.service.trino.datasource.DataSourceFactory;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDataSourceSourceDao {

  private final DataSourceFactory dataSourceFactory;

  @Autowired
  public BaseDataSourceSourceDao(DataSourceFactory dataSourceFactory) {
    this.dataSourceFactory = dataSourceFactory;
  }

  public abstract DataSourceType getType();

  public List<String> executeQuery(DataSourceType dataSourceType, String query, Integer offset,
      Integer limit) {
    return dataSourceFactory.getDataSource(dataSourceType).getJdbcTemplate()
        .queryForStream(query, (rs, RowNum) -> rs.getString((2)), new Object[]{offset, limit})
        .collect(Collectors.toList());
  }

}
