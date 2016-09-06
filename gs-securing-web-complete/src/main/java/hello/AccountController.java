package hello;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController


public class AccountController {
    @Autowired
    
    AccountRepository rep3;

	@RequestMapping(value="store3") //controller for store
	   
	   public String camera2 (@RequestParam(value="user",required=true) String username,
	           @RequestParam(value="pass",required=true) String password)
	   {
	   	try {
	   		Account cam = new Account(username, password);
	   		rep3.save(cam);
	           return "OK";
	       }catch(Exception ex){
	           String errorMessage;
	           errorMessage = ex + " <== error";
	           return ("error: " + ex.getMessage());}
	   }
	
	
	   
	   @RequestMapping(value="list3") //controller for list
	   public List<Account> getList2() {
	    	    List<Account> recordList3 = rep3.findAll(); 
	    	   
	    	    return recordList3;
	   }
	   
	   @RequestMapping(value = "/list3/{user}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<Account> deleteUser(@PathVariable("user") String username) {
	       System.out.println("Fetching & Deleting data " + username);

	       Account user1 = rep3.findByUsername(username);
	       if (user1 == null) {
	           System.out.println("Unable to delete." + username + " not found");
	           return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
	       }

	       rep3.deleteByUsername(username);
	       return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
	   }
	   
	   
	   
	}
