package tk.skuro.sample.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlo Sciolla
 */
@Controller
public class SampleController extends BaseSampleController {

    @RequestMapping(method = RequestMethod.GET, value = {"/sample"})
    public ModelAndView getSample() {
        final ModelAndView modelAndView = new ModelAndView("hello");
        addUsername(modelAndView);
        return modelAndView;
    }
}
