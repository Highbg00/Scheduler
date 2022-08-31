package org.zerock.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.scheduler.entity.Schduler;

public interface SchedulerRepository extends JpaRepository<Schduler, String>, QuerydslPredicateExecutor<Schduler> {
}
