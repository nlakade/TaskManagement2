package com.taskmanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.taskmanagement.dao.AssignerDao;
import com.taskmanagement.dao.TaskDao;
import com.taskmanagement.model.Assigner;
import com.taskmanagement.model.Task;
import com.taskmanagement.utility.Constants.TaskStatus;

@Controller
public class AssignerController {
	
	@Autowired
	private AssignerDao assignerDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@GetMapping("/assignerlogin")
	public String goToAssignerLoginPage() {
		return "assignerlogin";
	}
	
	@GetMapping("/assignerregister")
	public String goToAssigneeRegisterPage() {
		
		return "assignerregister";
	}
	
	@GetMapping("/myassigned")
	public ModelAndView goToMyassignedPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Assigner assigner = (Assigner)session.getAttribute("active-user");
		
		List<Task> tasks = taskDao.findByAssignerId(assigner.getId());
		mv.addObject("tasks", tasks);
		mv.setViewName("myassigned");
		
		return mv;
	}

	@GetMapping("/assigntasktoassigner")
	public String goToAssignTaskToAssigner() {
		return "assigntasktoassigner";
	}
	
	@PostMapping("/assignerregister")
	public ModelAndView registerAssigner(@ModelAttribute Assigner assigner) {
		ModelAndView mv = new ModelAndView();
		if(this.assignerDao.save(assigner)!= null) {
			mv.addObject("status", assigner.getFirstname()+" Successfully Registered as Assigner");
			mv.setViewName("assignerlogin");
		}
		
		else {
			mv.addObject("status", assigner.getFirstname()+" Failed to Registered as Assigner");
			mv.setViewName("assignerregister");
	
		}
		
		return mv;
	}
	
	@PostMapping("/assignerlogin")
	public ModelAndView loginAssignee(HttpServletRequest request, @RequestParam("emailid") String emailId, @RequestParam("password") String password ) {
		ModelAndView mv = new ModelAndView();
		
		Assigner assigner = assignerDao.findByEmailidAndPassword(emailId, password);
		
		if(assigner != null) {
			HttpSession session = request.getSession();
			session.setAttribute("active-user", assigner);
			session.setAttribute("user-login","assigner");
			mv.addObject("status", assigner.getFirstname()+" Successfully Logged in as Assigner!");
			mv.setViewName("index");
		}
		
		else {
			mv.addObject("status","Failed to login as Assigner!");
			mv.setViewName("index");
		}
		
		return mv;
	}

	@PostMapping("/getDepartmentWiseAssigners")
	public ModelAndView getDepartmentWiseAssigners(@RequestParam("department") String department) {
		ModelAndView mv = new ModelAndView();
		
		if(department != null) {
			List<Assigner> assigners = assignerDao.findByDepartment(department);
			mv.addObject("assigners",assigners);
			mv.setViewName("assigntasktoassigner");
		}
		
		else {
			mv.addObject("assigners",null);
			mv.setViewName("assigntasktoassigner");
			mv.addObject("status", "No Assigners present in "+department+" Department.");
		}
		
		return mv;
	}
	
	@PostMapping("/assignTaskToAssigner")
	public ModelAndView assignTaskToAssigner(@RequestParam("assignerId") int assignerId) {
		ModelAndView mv = new ModelAndView();
		
		if(assignerId > 0) {
			Assigner assigner = assignerDao.findById(assignerId).get();
			
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
            String strDate= formatter.format(date);  
          
			Task task = new Task();
			task.setAssignerId(assignerId);
			task.setDepartment(assigner.getDepartment());
			task.setStartDate(strDate);
			
			taskDao.save(task);
			
			mv.addObject("status","Task Assigned to Assigner");
			mv.setViewName("adminpage");
		}
		
		else {
			mv.addObject("status","No Assigner found to assign task");
			mv.setViewName("assigntasktoassigner");
		}
		
		return mv;
	}
	
	@GetMapping("/assignTaskToAssignee")
	public ModelAndView goToAssignTaskToAssignee(@RequestParam("taskId") String taskId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("taskId",taskId);
		mv.setViewName("assigntoassignee");
		
		return mv;
	}
	
	@PostMapping("/assignTaskToAssignee")
	public ModelAndView assignTaskToAssignee(@ModelAttribute Task task) {
		ModelAndView mv = new ModelAndView();
		
		if(task != null) {
			
			task.setAssigneeStatus(TaskStatus.PENDING.value());
			task.setAssignerStatus(TaskStatus.PENDING.value());
			
			taskDao.save(task);
			
			mv.addObject("status","Task successfully Assigned");
			mv.setViewName("index");
		}
		
		else {
			mv.addObject("status","Failed to assign Task");
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	@GetMapping("/updateTaskAssigned")
	public ModelAndView goToTaskupdatebyassignerPage(@RequestParam("taskId") String taskId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("taskId",taskId);
		mv.setViewName("taskupdatebyassigner");
		
		return mv;
	}
	
	@PostMapping("/updateTaskAssigned")
	public ModelAndView updateTaskAssigned(@ModelAttribute Task task) {
		ModelAndView mv = new ModelAndView();
		
		if(task != null) {
			
			taskDao.save(task);
			
			mv.addObject("status","Task Status updated sucessfully.");
			mv.setViewName("index");
		}
		
		else {
			mv.addObject("status","Failed to update the task");
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	@GetMapping("/searchMyAssignedTaskByStartDate")
	public ModelAndView searchMyAssignedTaskByStartDate(HttpSession session , @RequestParam("startDate") String startDate) {
		
		System.out.println(startDate);
		ModelAndView mv = new ModelAndView();
		Assigner assigner = (Assigner)session.getAttribute("active-user");
		List<Task> tasks = taskDao.findByStartDateAndAssignerId(startDate, assigner.getId());
		
		mv.addObject("tasks",tasks);
		mv.setViewName("myassigned");
		
		return mv;
	}
	
}
