package tk.skuro.sample.web.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

    List<String> allowedAuthorities = Collections.emptyList();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        if (requireSwitchUser(request)) {
            final Authentication originalUser = SecurityContextHolder.getContext().getAuthentication();
            try {
                final Authentication targetUser = attemptSwitchUser(request, originalUser);

                // update the current context to the new target user
                SecurityContextHolder.getContext().setAuthentication(targetUser);
                chain.doFilter(req, res);
            }
            catch (AccessDeniedException e) {
                // NOP
            }
            finally {
                SecurityContextHolder.getContext().setAuthentication(originalUser);
                chain.doFilter(req, res);
            }
        }
        else {
            chain.doFilter(req, res);
        }
    }

    private Authentication attemptSwitchUser(HttpServletRequest request, Authentication originalUser) {
        checkGrantedAuthorities(originalUser);
        final String targetUsername = request.getParameter(SWITCH_USERNAME);
        UserDetails targetUser = userDetailsService.loadUserByUsername(targetUsername);
        return createSwitchUserToken(targetUser);
    }

    private void checkGrantedAuthorities(Authentication originalUser) {
        final Collection<? extends GrantedAuthority> authorities = originalUser.getAuthorities();
        HashSet<String> currentAuths = new HashSet<String>(authorities.size());
        for (GrantedAuthority auth : authorities) {
            currentAuths.add(auth.getAuthority());
        }
        currentAuths.retainAll(this.allowedAuthorities);
        if (currentAuths.isEmpty()) {
            throw new AccessDeniedException("You shall not pass!");
        }
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

    public void setAllowedAuthorities(List<String> allowedAuthorities) {
        this.allowedAuthorities = allowedAuthorities;
    }
}
