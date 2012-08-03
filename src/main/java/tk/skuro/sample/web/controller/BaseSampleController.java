package tk.skuro.sample.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlo Sciolla
 */
public class BaseSampleController {

    protected void addUsername(ModelAndView modelAndView) {
        modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
