package tk.skuro.sample.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlo Sciolla
 */
@Controller
public class SampleController {

    @RequestMapping(method = RequestMethod.GET, value = {"/sample"})
    public ModelAndView getSample(ModelMap pModelMap) {
        return new ModelAndView("hello");
    }

}
