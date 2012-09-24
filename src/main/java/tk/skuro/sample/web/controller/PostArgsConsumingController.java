package tk.skuro.sample.web.controller;

import tk.skuro.sample.web.POSTUtils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Sample MVC controller to test POST body consumption
 * @author Carlo Sciolla
 */
@Controller
public class PostArgsConsumingController {
    @RequestMapping(method = RequestMethod.POST, value = {"/postcontroller"})
    @ResponseBody
    public String consumePOST(HttpServletRequest request) {

        Map<String, Object> result = new HashMap<String, Object>();
        POSTUtils.execute(request, result);

        return POSTUtils.serialize(result);
    }

}
