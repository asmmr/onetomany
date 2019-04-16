package edu.cseju.onetomany.controller;

import edu.cseju.onetomany.model.Department;
import edu.cseju.onetomany.model.Faculty;
import edu.cseju.onetomany.service.DeptService;
import edu.cseju.onetomany.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {


    @Autowired
    private FacultyService facultyService;

    @Autowired
    private DeptService deptService;


    @RequestMapping("/user")
    public ModelAndView userView()
    {
        int accessTypeFlag=0; //accessTypeFlag=0 means user access
        ModelAndView modelAndView=new ModelAndView();
        List<Department> list=deptService.getAllDepartment();

        modelAndView.addObject("listOfModel",list);
        modelAndView.addObject("accessTypeFlag",accessTypeFlag);

        modelAndView.setViewName("department");
        return modelAndView;
    }

    @RequestMapping("/admin")
    public ModelAndView adminView()
    {
        int MODE=1; // 1 means view mode
        // 0 means no data
        Department department=new Department();
        int accessTypeFlag=1 ; //accessTypeFlag=1 means admin
        ModelAndView modelAndView=new ModelAndView();

        List<Department> list=deptService.getAllDepartment();
        List<Faculty> fList=facultyService.getAllFaculty();

        if(list.isEmpty()) MODE=0;

        modelAndView.addObject("listOfModel",list);
        modelAndView.addObject("fList",fList);
        modelAndView.addObject("model",department);
        modelAndView.addObject("MODE",MODE);
        modelAndView.addObject("accessTypeFlag",accessTypeFlag);

        modelAndView.setViewName("department");
        return modelAndView;
    }


    @RequestMapping(value = "/admin/save",method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("model") Department department)
    {
        System.out.println(department.toString());
        Faculty faculty=new Faculty();

        faculty=facultyService.getFacultyById(department.getFaculty().getFacultyId());

        department.setFaculty(faculty);
        deptService.saveOrUpdate(department);

        return adminView();
    }

    @RequestMapping(value = "/admin/remove/{modelId}",method = RequestMethod.GET)
    public ModelAndView save(@RequestParam("modelId") String modelId)
    {
        deptService.removeDepartment(modelId);

        return new ModelAndView("redirect:/department/admin");
    }

    @RequestMapping(value = "/admin/edit/{modelId}",method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam("modelId") String modelId)
    {
        int MODE=2; //MODE=2 means edit/update mode
        Department model=deptService.getDepartmentById(modelId);

        int accessTypeFlag=1 ; //accessTypeFlag=1 means admin
        ModelAndView modelAndView=new ModelAndView();
        List<Faculty> fList=facultyService.getAllFaculty();

        modelAndView.addObject("fList",fList);
        modelAndView.addObject("model",model);
        modelAndView.addObject("accessTypeFlag",accessTypeFlag);
        modelAndView.addObject("MODE",MODE);
        modelAndView.setViewName("department");
        return modelAndView;

    }


}
