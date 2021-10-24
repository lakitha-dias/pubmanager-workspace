package com.pubmanager.scheduler.repositories;

import  com.pubmanager.scheduler.model.UserTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionsRepository extends CrudRepository<UserTransaction, Long> {

}
