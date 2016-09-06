package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class RegisterController {
	 @Autowired
	    BCryptPasswordEncoder passwordEncoder;
	 @Autowired
	    AccountRepository rep3;
	 
	@RequestMapping(value="/register", method=RequestMethod.GET)
    public String showRegisterForm(Account user, Model model) {
    	model.addAttribute("user", user);
    	
        return "register";
    }

    //when the POST request with action = Submit(When the submit button is received)
    //is received at /register add the user to database
    @RequestMapping(value="/register", method=RequestMethod.POST, params="action=Submit")
    //ModelAttribute actually take the http request and map it to the user object for me
    public String registerUser(Account user){
    	 user.setPassword(passwordEncoder.encode(user.getPassword()));
        //Save the user to database
        rep3.save(user);
    
      //these line are just for debugging so that I know the data is entered into database
        for (Account temp : rep3.findAll()) {
            System.out.println(temp);
        }
        //redirect to the success tempalte
        return "redirect:/success";
    }
}
