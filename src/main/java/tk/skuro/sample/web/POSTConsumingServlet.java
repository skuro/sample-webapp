package tk.skuro.sample.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Carlo Sciolla
 */
public class POSTConsumingServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        POSTUtils.execute(req, result);

        resp.getWriter().write(POSTUtils.serialize(result));
    }
}
