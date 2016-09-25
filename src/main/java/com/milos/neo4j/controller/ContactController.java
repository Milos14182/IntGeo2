package com.milos.neo4j.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.milos.neo4j.data.ContactData;
import com.milos.neo4j.services.impl.MailService;
import com.milos.neo4j.validators.ContactValidator;

@Controller
@SessionAttributes
public class ContactController {
	@Autowired
	MailService mailService;
	
	@Autowired
	ContactValidator contactValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(contactValidator);
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactPage(final Model m) {
		m.addAttribute("contact", new ContactData());
		return "contact";
	}

	@RequestMapping("/contacts")
	public ModelAndView showContacts() {
		return new ModelAndView("contact", "command", new ContactData());
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact") final ContactData contact, final HttpServletRequest request,
			final HttpServletResponse response, BindingResult errors, Model model) {
		contactValidator.validate(contact, errors);
		if (errors.hasErrors()) {
			model.addAttribute("contact", contact);
			return "contact";
		}
		mailService.contactUs(contact);
		return "redirect:/";
	}
}
