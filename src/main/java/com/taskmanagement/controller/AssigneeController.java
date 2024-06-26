package com.taskmanagement.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.taskmanagement.dao.AssigneeDao;
import com.taskmanagement.dao.TaskDao;
import com.taskmanagement.model.Assignee;
import com.taskmanagement.model.Assigner;
import com.taskmanagement.model.Task;

@Controller
public class AssigneeController {
	
	@Autowired
	private AssigneeDao assigneeDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@GetMapping("/assigneelogin")
	public String goToAssigneeLoginPage() {
		return "assigneelogin";
	}
	
	@GetMapping("/assigneeregister")
	public String goToAssigneeRegisterPage() {
		
		return "assigneeregister";
	}
	
	@PostMapping("/assigneeregister")
	public ModelAndView registerAssignee(@ModelAttribute Assignee assignee) {
		ModelAndView mv = new ModelAndView();
		if(this.assigneeDao.save(assignee)!= null) {
			mv.addObject("status", assignee.getFirstname()+" Successfully Registered as Assignee");
			mv.setViewName("assigneelogin");
		}
		
		else {
			mv.addObject("status", assignee.getFirstname()+" Failed to Registered as Assignee");
			mv.setViewName("assigneeregister");
	
		}
		
		return mv;
	}
	
	@PostMapping("/assigneelogin")
	public ModelAndView loginAssignee(HttpServletRequest request, @RequestParam("emailid") String emailId, @RequestParam("password") String password ) {
		ModelAndView mv = new ModelAndView();
		
		Assignee assignee = assigneeDao.findByEmailidAndPassword(emailId, password);
		
		if(assignee != null) {
			HttpSession session = request.getSession();
			session.setAttribute("active-user", assignee);
			session.setAttribute("user-login","assignee");
			mv.addObject("status", assignee.getFirstname()+" Successfully Logged in as Assignee!");
			mv.setViewName("index");
		}
		
		else {
			mv.addObject("status","Failed to login as Assignee!");
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	@GetMapping("/updateAssigneeTaskStatus")
	public ModelAndView goToTaskupdatebyassignerPage(@RequestParam("taskId") String taskId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("taskId",taskId);
		mv.setViewName("taskupdatebyassignee");
		
		return mv;
	}
	
	@PostMapping("/updateAssigneeTaskStatus")
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
	
	@GetMapping("/searchMyTaskByStartDate")
	public ModelAndView searchMyAssignedTaskByStartDate(HttpSession session , @RequestParam("startDate") String startDate) {
		
		System.out.println(startDate);
		ModelAndView mv = new ModelAndView();
		Assignee assignee = (Assignee)session.getAttribute("active-user");
		List<Task> tasks = taskDao.findByStartDateAndAssigneeId(startDate, assignee.getId());
		
		mv.addObject("tasks",tasks);
		mv.setViewName("myassigned");
		
		return mv;
	}
	
	@GetMapping("/mytask")
	public ModelAndView goToMyassignedPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Assignee assignee = (Assignee)session.getAttribute("active-user");
		
		List<Task> tasks = taskDao.findByAssigneeId(assignee.getId());
		mv.addObject("tasks", tasks);
		mv.setViewName("mytask");
		
		return mv;
	}

}
