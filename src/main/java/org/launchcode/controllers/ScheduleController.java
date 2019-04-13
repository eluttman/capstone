package org.launchcode.controllers;


import org.launchcode.models.Appointment;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.forms.AddScheduleItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("schedules", scheduleDao.findAll());
        model.addAttribute("title", "Schedules");

        return "schedule/index";
    }
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute("title", "Add Schedule");
        model.addAttribute(new Schedule());
        return "menu/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Schedule schedule, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Schedule");
            return "schedule/add";
        }

        scheduleDao.save(schedule);
        return "redirect:view/" + schedule.getId();
    }
    @RequestMapping(value = "add-item/(id)", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int id) {
        Schedule schedule = scheduleDao.findOne(id);

        Iterable<Appointment> appointments = appointmentDao.findAll();
        AddScheduleItemForm form = new AddScheduleItemForm(Schedule, Appointments);

        model.addAttribute("title", "Add appointment to schedule:" + schedule.getName());
        model.addAttribute("schedule", schedule);
        model.addAttribute("form", form);

        return "schedule/add-item";
    }
    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddScheduleItemForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add appointment to schedule:" + schedule.getName());
            model.addAttribute("schedule", schedule);
            model.addAttribute("form", form);
            return "schedule/add-item";
        }

        Appointment appointment = appointmentDao.findOne(form.getAppointmentId());
        schedule.addItem(appointment);
        scheduleDao.save(schedule);

        model.addAttribute ("title", schedule.getName());
        model.addAttribute("schedule", schedule);
        return "schedule/view";
    }
}
