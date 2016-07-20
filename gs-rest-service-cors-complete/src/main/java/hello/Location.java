package hello;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Location {

    
 private String location;
    
    public Location(String location) {
    
        this.location = location;
  
    }
    
    public String getLocation() {
		return location;
    }
    
    public void setLocation(String location) {
		this.location = location;
	}
}
