package org.launchcode.models.data;

import org.launchcode.models.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AppointmentDao extends CrudRepository<Appointment, Integer> {
}
