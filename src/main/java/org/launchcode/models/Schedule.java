package org.launchcode.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Schedule {
    @ManyToMany
    private List<Appointment> appointments;
    @NotNull
    @Size(min=2, max=15)
    private String name;
    @Id
    @GeneratedValue
    private int id;

    public Schedule(){}

    public Schedule(String name){
        this.name = name;
    }

    public void addItem(Appointment item){
        Appointments.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<Cheese> getAppointments() {
        return appointments;
    }

}
