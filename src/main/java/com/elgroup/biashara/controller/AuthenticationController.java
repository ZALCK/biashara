package com.elgroup.biashara.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elgroup.biashara.exception.UserNotFoundException;
import com.elgroup.biashara.user.IUserService;
import com.elgroup.biashara.user.User;

import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping(value = "/authentication")
public class AuthenticationController {
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	IUserService ius;
	
	private String CLIENT_ROLE = "CLIENT";
	private String PARTNER_ROLE = "PARTENAIRE";
	private String MODERATOR_ROLE = "MODERATEUR";

	 
	@GetMapping(value = "/login")
	 public String loginForm() {
		 return "/authentication/signin";
	 }

	@GetMapping(value = "/")
	@ResponseBody
	public String getHomePage() {
		if (getCurrentUserConnected() != null) {
			User userConnected = getCurrentUserConnected();
			/*if (userConnected.getRole().getPrivileges().contains(ius.getRoleByName(CLIENT_ROLE))) {
				return "redirect:/customer/list";
			} else if (userConnected.getRole().getPrivileges().contains(ius.getRoleByName(PARTNER_ROLE))) {
				return "redirect:/partner/list";
			} else if (userConnected.getRole().getPrivileges().contains(ius.getRoleByName(MODERATOR_ROLE))) {
				return "redirect:/moderator/list";
			} else {*/
				return "Fully authenticated : " + userConnected.toString();
			//}
		}
		return "redirect:/login";
	}
	
	@GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
		return "/authentication/forgot_password_form";
    }
 
    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
    	String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            ius.updateResetPasswordToken(token, email);
            String resetPasswordLink = getSiteURL(request) + "/authentication/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
             
        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
             
        return "/authentication/forgot_password_form";
    }
     
    public void sendEmail(String recipientEmail, String link) 
    		throws MessagingException, UnsupportedEncodingException {
    	
    	MimeMessage message = mailSender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom("noreply@biashara.com", "Biashara Support");
        helper.setTo(recipientEmail);
         
        String subject = "Here's the link to reset your password";
         
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
         
        helper.setSubject(subject);
         
        helper.setText(content, true);
         
        mailSender.send(message);
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = ius.getByResetPasswordToken(token);
        model.addAttribute("token", token);
         
        if (user == null) {
            model.addAttribute("message", "Invalid Token, Please retry");
            return "/authentication/forgot_password_form";
        }
         
        return "/authentication/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
         
        User user = ius.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");
         
        if (user == null) {
            model.addAttribute("message", "Invalid Token, Please retry");
            return "/authentication/forgot_password_form";
        } else {           
            ius.updatePassword(user, password);
             
            model.addAttribute("message", "You have successfully changed your password.");
        }
         
        return "/authentication/signin";
    }

    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    
	private User getCurrentUserConnected() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userConnected;
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			userConnected = ius.findByEmail(userDetails.getUsername());
			return userConnected;
		}
		return null;
	}
}
