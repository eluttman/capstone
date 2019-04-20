package org.launchcode.models.forms;

import org.launchcode.models.Appointment;
import org.launchcode.models.Schedule;

import javax.validation.constraints.NotNull;

public class AddScheduleItemForm {

    private Schedule schedule;
    private Iterable<Appointment> appointments;

    @NotNull
    private int scheduleId;

    public  void setSchedule(Schedule schedule) {this.schedule = schedule;}

    public void setAppointment(Iterable<Appointment> appointments) {this.appointments = appointments;}

    public void setAppointmentId (int appointmentId) {this.appointmentId = appointmentId;}

    @NotNull
    private int appointmentId;
    public AddScheduleItemForm() {}

    public AddScheduleItemForm(Iterable<Appointment> appointments, Schedule schedule) {
        this.appointments = appointments;
        this.schedule = schedule;
    }

    public Schedule getSchedule() { return schedule; }

    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }

    public Iterable getAppointments() { return appointments; }

    public int getScheduleId() { return scheduleId; }

    public int getAppointmentId() { return appointmentId; }

}
