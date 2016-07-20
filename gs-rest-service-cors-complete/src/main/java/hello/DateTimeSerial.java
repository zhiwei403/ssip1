package hello;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateTimeSerial extends JsonSerializer<DateTime> {
    // Customize format as per your need 
    private static DateTimeFormatter formatter = DateTimeFormat
            .forPattern("yyyy-MM-dd HH:mm");

    @Override
    public void serialize(DateTime value, JsonGenerator generator,
                          SerializerProvider serializerProvider)
            throws IOException {
        generator.writeString(formatter.print(value));
    }

}
