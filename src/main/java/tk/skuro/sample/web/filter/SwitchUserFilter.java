package tk.skuro.sample.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author Carlo Sciolla
 */
public class SwitchUserFilter extends GenericFilterBean {

    public static final String SWITCH_USERNAME = "j_switch_username";

    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        if (requireSwitchUser(request)) {
            final Authentication originalUser = SecurityContextHolder.getContext().getAuthentication();
            try {
                final Authentication targetUser = attemptSwitchUser(request);

                // update the current context to the new target user
                SecurityContextHolder.getContext().setAuthentication(targetUser);
                chain.doFilter(req, res);
            }
            finally {
                SecurityContextHolder.getContext().setAuthentication(originalUser);
            }
        }
        else {
            chain.doFilter(req, res);
        }
    }

    private Authentication attemptSwitchUser(HttpServletRequest request) {
        final String targetUsername = request.getParameter(SWITCH_USERNAME);
        UserDetails targetUser = userDetailsService.loadUserByUsername(targetUsername);
        return createSwitchUserToken(targetUser);
    }

    private Authentication createSwitchUserToken(UserDetails targetUser) {
        return new UsernamePasswordAuthenticationToken(targetUser, targetUser.getPassword(), targetUser.getAuthorities());
    }

    private boolean requireSwitchUser(HttpServletRequest request) {
        return request.getParameterMap().containsKey(SWITCH_USERNAME);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
