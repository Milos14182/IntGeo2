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

import com.milos.neo4j.data.CityData;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.services.CityService;
import com.milos.neo4j.services.UserService;
import com.milos.neo4j.validators.RegistrationValidator;
import java.io.UnsupportedEncodingException;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    CityService cityService;

    @Autowired
    RegistrationValidator registrationValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationValidator);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(final Model m) {
        Iterable<CityData> citys = cityService.findAllCitys();
        m.addAttribute("citys", citys);
        UserData user = new UserData();
        user.setCity(new CityData());
        m.addAttribute("userData", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userData") UserData user, final HttpServletRequest request,
            final HttpServletResponse response, final BindingResult errors, final Model model) throws UnsupportedEncodingException {
        registrationValidator.validate(user, errors);
        if (errors.hasErrors()) {
            Iterable<CityData> citys = cityService.findAllCitys();
            model.addAttribute("citys", citys);
            return "register";
        } else {
            user.setAdmin(Boolean.FALSE);
            user = userService.save(user);
            request.getSession().setAttribute("userDetails", user);
            return "redirect:/";
        }
    }
}
