package org.launchcode.controllers;

import org.launchcode.models.Appointment;
import org.launchcode.models.Category;
import org.launchcode.models.data.AppointmentDao;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value= "")
    public String index(Model model) {

        model.addAttribute("appointments", appointmentDao.findAll());
        model.addAttribute("name", "My Schedule");

        return "home/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddAppointmentForm(Model model) {

        model.addAttribute("title", "Add Appointment");
        model.addAttribute(new Appointment());
        model.addAttribute("categories", categoryDao.findAll());
        return "home/add";
    }
/*
     public String processAddAppointmentForm(@ModelAttribute @Valid Appointment newAppointment,
        Errors errors, @RequestParam int categoryId, Model model){
*/

     @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddAppointmentForm(Model model, @ModelAttribute @Valid Appointment newAppointment,
                Errors errors, @RequestParam int categoryId) {

            if (errors.hasErrors()) {
                model.addAttribute("title", "Add Appointment");
                model.addAttribute(new Appointment());
                model.addAttribute("categories", categoryDao.findAll());
                return "home/add";
            }
            Category cat = categoryDao.findOne(categoryId);
            newAppointment.setCategory(cat);

            appointmentDao.save(newAppointment);
            return "redirect:";
        }
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveAppointmentForm(Model model) {

            model.addAttribute("appointments", appointmentDao.findAll());
            model.addAttribute("title", "Remove Appointment");
            return "home/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveAppointmentForm(@RequestParam int[] appointmentIds) {

            for (int appointmentId : appointmentIds) {
                appointmentDao.delete(appointmentId);
            }

            return "redirect:";

    }

}

