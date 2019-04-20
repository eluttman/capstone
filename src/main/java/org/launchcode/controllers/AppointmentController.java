package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Appointment;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private CategoryDao categoryDao;

    // Request path: /appointment
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("appointments", appointmentDao.findAll());
        model.addAttribute("title", "My Appointments");

        return "appointment/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddAppointmentForm(Model model) {
        model.addAttribute("title", "Add Appointment");
        model.addAttribute(new Appointment());
        model.addAttribute("categories", categoryDao.findAll());
        return "appointment/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddAppointmentForm(@ModelAttribute  @Valid Appointment newAppointment,
                                            Errors errors, @RequestParam int categoryId,
                                            Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Appointment");
            return "appointment/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newAppointment.setCategory(cat);
        appointmentDao.save(newAppointment);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveAppointmentForm(Model model) {
        model.addAttribute("title", "Remove Appointment");
        model.addAttribute("appointments", appointmentDao.findAll());
        return "appointment/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveAppointmentForm(@RequestParam int[] appointmentIds) {

        for (int appointmentId : appointmentIds) {
            appointmentDao.delete(appointmentId);
        }

        return "redirect:";
    }
    @RequestMapping(value = "edit/{appointmentId}", method = RequestMethod.GET)
    public String displayEditAppointmentForm(Model model, @PathVariable int appointmentId) {

        model.addAttribute("title", "Edit Appointment");
        model.addAttribute("appointment", appointmentDao.findOne(appointmentId));
        model.addAttribute("categories", categoryDao.findAll());
        return "appointment/edit";
    }

    @RequestMapping(value = "edit/{appointmentId}", method = RequestMethod.POST)
    public String processEditForm(Model model, @PathVariable int appointmentId,
                                  @ModelAttribute  @Valid Appointment newAppointment,
                                  @RequestParam int categoryId,
                                  Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Appointment");
            return "appointment/edit";
        }

        Appointment editedAppointment = appointmentDao.findOne(appointmentId);
        editedAppointment.setName(newAppointment.getName());
        editedAppointment.setDate(newAppointment.getDate());
        editedAppointment.setCategory(categoryDao.findOne(categoryId));
        appointmentDao.save(editedAppointment);

        return "redirect:/appointment";
    }
}
