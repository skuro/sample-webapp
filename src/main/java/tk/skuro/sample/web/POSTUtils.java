package tk.skuro.sample.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

/**
 * @author Carlo Sciolla
 */
public class POSTUtils {

    public static void execute(HttpServletRequest request, Map<String, Object> result) {
        // should empty the body
        consumePOSTArgs(request);

        // should not get access to parameter names anymore
        Enumeration<String> parametersNames = request.getParameterNames();

        while (parametersNames.hasMoreElements()) {
            String parameterName = parametersNames.nextElement();
            String[] parameterValues = request.getParameterValues(parameterName);
            result.put(parameterName, parameterValues);
        }
    }

    public static void consumePOSTArgs(HttpServletRequest request) {
        byte[] postBody;
        try {
            postBody = IOUtils.toByteArray(
                    request.getInputStream()
            );

            if (postBody == null || 0 >= postBody.length) {
                throw new RuntimeException("No POST body?");
            }
        } catch (IOException e) {
            // NOP
        }
    }

    public static String serialize(Map<String, Object> result) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        for (String key : result.keySet()) {
            sb.append("\t").append("key : ").append(result.get(key)).append(";\n");
        }
        sb.append("}\n");

        return  sb.toString();
    }
}
