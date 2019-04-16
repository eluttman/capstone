package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    //HOW DO WE GET THIS TO BE A DATE SELECTOR, ETC OR WHAT FORMAT WOULD BE EASIEST TO USE IN THIS OPTION?
    @NotNull
    @Size(min=1, message = "Please Select Date")
    private Integer date;

    @OneToMany
    private Category category;

    public Appointment(String name, Integer date) {
        this.name = name;
        this.date = date;
    }

    public Appointment() { }

    @ManyToMany(mappedBy = "appointments")
    private List<Schedule> schedules;

    public int getId() {return id;}

    public String getName() {return name;]

    public void setName(String name) {this.name = name;}

    public int getDate(Integer date) {this.date = date;}

    public Category getCategory() {return category}

    public void setCategory(Category category) {this.category = category;}

    }
