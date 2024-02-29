package org.gojo.repository;

import org.gojo.entity.EventEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;


public interface EventsCassandraRepository extends CassandraRepository<EventEntity, String> {

}
