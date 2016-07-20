package hello;

import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
  
    LocationRepository rep2;
    
   
    
    
    @RequestMapping(value="store2") //controller for store
   
    public  String  camera (@RequestParam(value="loc",required=true) String location)
    {
    	try {
    		Location cam = new Location(location);
    		rep2.save(cam);
            return "OK";
        }catch(Exception ex){
            String errorMessage;
            errorMessage = ex + " <== error";
            return ("error: " + ex.getMessage());}
    }
   @RequestMapping(value="list2") //controller for list
   public List<Location> getList() {
    	    List<Location> recordList2 = rep2.findAll(); 
    	   
    	    return recordList2;
   }
}