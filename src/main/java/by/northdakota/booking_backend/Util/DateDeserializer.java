package by.northdakota.booking_backend.Util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String dateStr = jsonParser.getText();
        if (dateStr.length() == 10) { // yyyy-MM-dd
            return LocalDate.parse(dateStr).atStartOfDay(); // 00:00:00
        }
        return LocalDateTime.parse(dateStr); // expects yyyy-MM-ddTHH:mm[:ss]
    }
}
