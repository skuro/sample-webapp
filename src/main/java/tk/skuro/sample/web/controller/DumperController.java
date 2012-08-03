package tk.skuro.sample.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Dumps the user name on the screen
 * @author Carlo Sciolla
 */
@Controller
public class DumperController extends BaseSampleController {

    @RequestMapping(method = RequestMethod.GET, value = {"/dumper"})
    public ModelAndView getSample() {
        final ModelAndView modelAndView = new ModelAndView("dumper");
        addUsername(modelAndView);
        return modelAndView;
    }
}
