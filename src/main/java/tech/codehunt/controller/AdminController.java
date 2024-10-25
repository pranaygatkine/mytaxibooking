package tech.codehunt.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tech.codehunt.model.ServiceForm;
import tech.codehunt.service.AdminCredentialsService;
import tech.codehunt.service.BookingFormService;
import tech.codehunt.service.ContactFormService;
import tech.codehunt.service.ServiceFormService;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("admin")
public class AdminController {
	
	private ContactFormService contactFormService;
	private AdminCredentialsService adminCredentialsService;
	private BookingFormService bookingFormService;
	private ServiceFormService serviceFormService;
	
	
	
	
	@Autowired
	public void setServiceFormService(ServiceFormService serviceFormService) {
		this.serviceFormService = serviceFormService;
	}

	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}

	@Autowired
public void setAdminCredentialsService(AdminCredentialsService adminCredentialsService) {
		this.adminCredentialsService = adminCredentialsService;
	}

@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}
	
	@GetMapping("dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}
		
		@GetMapping("readAllContacts")
		public String readAllContacts(Model model) {
			
			model.addAttribute("allcontacts",contactFormService.readAllContactService());
			return "admin/readallcontacts";
		}
		
		@GetMapping("deleteContact/{id}")
		public String deleteContacts(@PathVariable int id, RedirectAttributes redirectAttributes ) {
			
			contactFormService.deleteContactService(id);
			redirectAttributes.addFlashAttribute("message", "CONTACT DELETED SUCESSFULLY");
			return "redirect:/admin/readAllContacts";
		}
		
		@GetMapping("changeCredentials")
		public String changeCredentialsView() {
		return "admin/changeCredentials";
		}
		

		@GetMapping("readAllServices")
		public String readAllServices(Model model) {
			
			model.addAttribute("allservice",serviceFormService.readAllServices());
		
			return "admin/readallservice";
		}
		
		
		@GetMapping("deleteService/{id}")
		public String deleteService(@PathVariable int id, Model m, RedirectAttributes r) {
			String s = serviceFormService.deleteById(id);
	
			if (s.equals("success")) {
				r.addFlashAttribute("message", "Deleted Successfully.");
			} else {
				r.addFlashAttribute("message", "Something went wrong.");
			}
			return "redirect:/admin/readAllServices";
		}
		
		@PostMapping("changeCredentials")
		public String changeCredentials(
				@RequestParam("oldusername") String oldusername,
				@RequestParam("oldpassword") String oldpassword,
				@RequestParam("newusername") String newusername,
				@RequestParam("newpassword") String newpassword,
				RedirectAttributes redirectAttributes

				) {
			
			String result = adminCredentialsService.checkAdminCredentials(oldusername, oldpassword);
		if(result.equals("success")) {
			//passsword update
			
			result=adminCredentialsService.updateAdminCredentials(newusername, newpassword, oldusername);
			redirectAttributes.addFlashAttribute("message",result);
			
			
		}else {
			redirectAttributes.addFlashAttribute("message",result);

		}
			return "redirect:/admin/dashboard";
		}
		

		@GetMapping("readAllBookings")
		public String readAllBookings(Model model) {
			
			model.addAttribute("allbookings",bookingFormService.readAllBookingService());
		
			return "admin/readallbookings";
		}
		

		@GetMapping("deleteBooking/{id}")
		public String deleteBokking(@PathVariable int id, RedirectAttributes redirectAttributes ) {
			
			bookingFormService.deleteBookingService(id);
			redirectAttributes.addFlashAttribute("message", "BOOKING DELETED SUCESSFULLY");
			return "redirect:/admin/readAllBookings";
		}
		
		
		@GetMapping("addService")
		public String addServiceView( ) {
			
			return "admin/addservice";
		}
		
		@InitBinder
		public void stopBinding(WebDataBinder webDataBinder) {
			webDataBinder.setDisallowedFields("image");
			
		}
		
		@PostMapping("addService")
		public String addService(@ModelAttribute ServiceForm serviceForm,
				@RequestParam("image") MultipartFile multipartFile , RedirectAttributes  redirectAttributes)  {
			String originalFilename = multipartFile.getOriginalFilename();
			serviceForm.setImage(originalFilename);
			
		try {  
				ServiceForm service = serviceFormService.addService(serviceForm, multipartFile);
          
				if(service!=null) {
					redirectAttributes.addFlashAttribute("msg","Service Added Successfully");
	                }else {
						redirectAttributes.addFlashAttribute("msg","Something Went Wrong");
                            }
				
		} catch (Exception e) {
				redirectAttributes.addFlashAttribute("msg","Something Went Wrong");
			    }
			
			
			return "redirect:/admin/addService";
		}
		
}
