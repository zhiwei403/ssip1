package hello;


import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@Document
public class Record {
    
	private String cameraid;
	
	@JsonSerialize(using=CustomizeDateTime.class)
   	private DateTime timestamp;
	
	@Indexed(unique = true)
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

  
