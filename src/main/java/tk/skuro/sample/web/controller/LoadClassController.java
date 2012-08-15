package tk.skuro.sample.web.controller;

import tk.skuro.sample.web.loadclass.DummyInterface;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlo Sciolla
 */
@Controller
public class LoadClassController {
    @RequestMapping(method = RequestMethod.GET, value = {"/loadclass"})
    public ModelAndView getSample() {
        final ModelAndView modelAndView = new ModelAndView("loadclass");
        String result = DummyInterface.INSTANCE.toString();
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
