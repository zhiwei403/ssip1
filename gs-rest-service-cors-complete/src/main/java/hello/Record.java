package hello;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Record {

    
    
	private String cameraid;
	
	@JsonSerialize(using=DateTimeSerial.class)
   	private DateTime timestamp;
	
	
    private String filename;
 private String location;
    
    public Record(String cameraid, DateTime timestamp, String filename, String location) {
    
    	this.cameraid = cameraid;
    	this.timestamp = timestamp;
        this.filename = filename;
        this.location = location;
  
    }
    
    public String getLocation() {
		return location;
    }

	public String getCameraid() {
		return cameraid;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}

	public String getFilename() {
		return filename;
	}
	

    public void setLocation(String location) {
		this.location = location;
	}
    public void setCameraid(String cameraid) {
		this.cameraid = cameraid;
	}
	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}

  

