package org.launchcode.models.data;

import org.launchcode.models.Category;
import org.launchcode.models.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ScheduleDao extends CrudRepository<Schedule, Integer> {
}