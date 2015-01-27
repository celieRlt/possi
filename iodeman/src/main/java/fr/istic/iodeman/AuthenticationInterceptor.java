package fr.istic.iodeman;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.yale.its.tp.cas.client.ServiceTicketValidator;
import fr.istic.iodeman.cas.TicketValidatorFactory;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private TicketValidatorFactory ticketValidatorFactory;

    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	
    	boolean isValidated = false;
    	
    	 HttpSession session = request.getSession();
	     String sessionTicket = (String) session.getAttribute("casticket");
	             
	     if(sessionTicket != null){	 
	    	 ServiceTicketValidator ticketValidator = ticketValidatorFactory.getServiceTicketValidator(sessionTicket);
	    	 isValidated = ticketValidator.isAuthenticationSuccesful();
	     }
	     
	     if(!isValidated){
    		 response.sendRedirect("/login");
    		 return false;
    	 }
	     
        return super.preHandle(request, response, handler);
    }
	
}