package hello;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camera")
public class RecordController {

    @Autowired
  
    RecordRepository rep;
    
   
    
    
    @RequestMapping(value="store") //controller for store
   
    public  String  camera (@RequestParam(value="cam",required=true) String cameraid,
    		@RequestParam(required=true, value="ts") String timestamp,
            @RequestParam(value="fn",required=true) String filename,
            @RequestParam(value="loc",required=true, defaultValue="Bedok Mall") String location)
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
   
   /*@RequestMapping(method = RequestMethod.GET, value="/list/{fn}", produces = MediaType.APPLICATION_JSON_VALUE)
   public Record getBookDetails(@PathVariable("fn") String filename){
	   System.out.println("Fetching data " + filename);
     return rep.findOne(filename);
   }*/
   
   
   
   @RequestMapping(value = "/list/{fn}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Record> deleteUser(@PathVariable("fn") String filename) {
       System.out.println("Fetching & Deleting data " + filename);

       Record user1 = rep.findByfilename(filename);
       if (user1 == null) {
           System.out.println("Unable to delete." + filename + " not found");
           return new ResponseEntity<Record>(HttpStatus.NOT_FOUND);
       }

       rep.deleteByFilename(filename);
       return new ResponseEntity<Record>(HttpStatus.NO_CONTENT);
   }
   
   /*@RequestMapping(method = RequestMethod.DELETE, value="/list/{fn}")
   public Map<String, String> deleteBook(@PathVariable("fn") String filename){
     rep.delete(filename);
     Map<String, String> response = new HashMap<String, String>();
     response.put("message", "Book deleted successfully");

     return response;
   }*/
   
   /*@RequestMapping(method = RequestMethod.DELETE, value = "/list/{fn}")
   public void delete(@PathVariable String filename) {
       rep.delete(filename);
   }*/
   
	}
 

   /* @RequestMapping("/camera-javaconfig")
    public @ResponseBody Record greetingWithJavaconfig(@RequestParam(required=false, defaultValue="World") String cameraid,
    		@RequestParam(required=false, defaultValue=Date) String datetime,
    	    @RequestParam(required=false, defaultValue="dog") String filename) {
      //  System.out.println("==== in greeting ====");
        return new Record(counter.incrementAndGet(), cameraid, datetime,filename);
    }*/
