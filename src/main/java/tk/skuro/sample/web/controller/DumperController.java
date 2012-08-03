package tk.skuro.sample.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public ModelAndView getSample(ModelMap pModelMap) {
        final ModelAndView modelAndView = new ModelAndView("dumper");
        addUsername(modelAndView);
        return modelAndView;
    }
}
