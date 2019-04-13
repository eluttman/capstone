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

    public void setAppointments(Iterable<Appointment> appointments) {this.appointment = appointment;}

    public void setAppointmentId (int appointmentId) {this.appointmentId = appointmentId;}

    @NotNull
    private int appointmentId;
    public AddScheduleItemForm() {}

    public AddScheduleItemForm(Schedule schedule, Iterable<Appointment> appointments) {
        this.schedule = schedule;
        this.appointments = appointments;
    }

    public Schedule getSchedule() {return schedule};
    public void setScheduleId(int scheduleId) {this.scheduleId = scheduleId;}
    public int getAppointments() { return appointments; ]
    public int getScheduleIf() [ return scheduleId;]
    public int getAppointmentId() {return appointmentId;]

}
