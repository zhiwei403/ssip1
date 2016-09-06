package hello;


import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
@RequestMapping("/camera")
public class RecordController extends WebMvcConfigurerAdapter{

    @Autowired
    RecordRepository rep;
    
   // defaultValue="Bedok Mall"
    @RequestMapping(value="store") //controller for store
   
    public  String  camera (@RequestParam(value="cam",required=true) String cameraid,
    		@RequestParam(required=true, value="ts") String timestamp,
            @RequestParam(value="fn",required=true) String filename,
            @RequestParam(value="lo",required=true, defaultValue="Bedok Mall") String location)
    {
    	try {
    		Record cam = new Record(cameraid, ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC).parseDateTime(timestamp), filename, location);
    		rep.save(cam);
            return "OK";
        }catch(Exception ex){
            String errorMessage;
            errorMessage = ex + " <== error";
            return ("error: " + ex.getMessage());}
    }
   @RequestMapping(value="list") //controller for list
   public List<Record> getList() {
    	    List<Record> recordList = rep.findAll(); 
    	   
    	    return recordList;
   }
   
   
   
   @RequestMapping(value = "/list/{fn}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Record> getUser(@PathVariable("fn") String filename) {
       System.out.println("Fetching data " + filename);
       Record user = rep.findByfilename(filename);
       if (user == null) {
           System.out.println("Data with " + filename + " not found");
           return new ResponseEntity<Record>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<Record>(user, HttpStatus.OK);
   }
   
   @RequestMapping(value = "/list/{fn}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Record> deleteUser(@PathVariable("fn") String filename) {
       System.out.println("Fetching & Deleting data " + filename);

       Record user1 = rep.findByfilename(filename);
       if (user1 == null) {
           System.out.println("Unable to delete." + filename + " not found");
           return new ResponseEntity<Record>(HttpStatus.NOT_FOUND);
       }

       rep.deleteByfilename(filename);
       return new ResponseEntity<Record>(HttpStatus.NO_CONTENT);
   }
}
