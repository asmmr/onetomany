package edu.cseju.onetomany.controller;

import edu.cseju.onetomany.model.Faculty;
import edu.cseju.onetomany.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @RequestMapping("/user")
    public ModelAndView userView()
    {
        int accessTypeFlag=0; //accessTypeFlag=0 means user access
        ModelAndView modelAndView=new ModelAndView();
        List<Faculty> list=facultyService.getAllFaculty();

        modelAndView.addObject("listOfModel",list);
        modelAndView.addObject("accessTypeFlag",accessTypeFlag);

        modelAndView.setViewName("faculty");
        return modelAndView;
    }

    @RequestMapping("/admin")
    public ModelAndView adminView()
    {
        int MODE=1; // 1 means view mode
                    // 0 means no data
        Faculty faculty=new Faculty();
        int accessTypeFlag=1 ; //accessTypeFlag=1 means admin
        ModelAndView modelAndView=new ModelAndView();
        List<Faculty> list=facultyService.getAllFaculty();
        if(list.isEmpty()) MODE=0;
        modelAndView.addObject("listOfModel",list);
        modelAndView.addObject("model",faculty);
        modelAndView.addObject("MODE",MODE);
        modelAndView.addObject("accessTypeFlag",accessTypeFlag);

        modelAndView.setViewName("faculty");
        return modelAndView;
    }


    @RequestMapping(value = "/admin/save",method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("model") Faculty faculty)
    {
        facultyService.saveOrUpdate(faculty);

        return adminView();
    }

    @RequestMapping(value = "/admin/remove/{modelId}",method = RequestMethod.GET)
    public ModelAndView save(@RequestParam("modelId") String modelId)
    {
        facultyService.removeFaculty(modelId);

        return new ModelAndView("redirect:/faculty/admin");
    }

    @RequestMapping(value = "/admin/edit/{modelId}",method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam("modelId") String modelId)
    {
        int MODE=2; //MODE=2 means edit/update mode
        Faculty faculty=facultyService.getFacultyById(modelId);

        int accessTypeFlag=1 ; //accessTypeFlag=1 means admin
        ModelAndView modelAndView=new ModelAndView();

        //modelAndView.addObject("listOfModel",faculty);
        modelAndView.addObject("model",faculty);
        modelAndView.addObject("accessTypeFlag",accessTypeFlag);
        modelAndView.addObject("MODE",MODE);
        modelAndView.setViewName("faculty");
        return modelAndView;

    }


}
