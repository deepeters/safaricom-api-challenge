package com.external.files.readdata.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.external.files.readdata.Model.Customer;
import com.external.files.readdata.Service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired 
	private CustomerService customerService;
	
	@GetMapping(value="/")
	public String home (Model model) {
		model.addAttribute("film", new Customer());
		List <Customer> customers = customerService.findAll();
		model.addAttribute("films", customers);
		
		return "View/films";
	}
	
	@PostMapping(value="/fileupload")
	public String uploadFile(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
		boolean isFlag = customerService.saveDataFromUploadfile(customer.getFile());
		
		if(isFlag) {
			redirectAttributes.addFlashAttribute("successmessage", "File Upload Successful!");
		}
		else {
			redirectAttributes.addFlashAttribute("errormessage", "File Upload NOT Successful!");
		}
		
		return "redirect:/";
	}

}
