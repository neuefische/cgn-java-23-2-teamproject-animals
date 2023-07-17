package de.neuefische.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("animals")
public class Animal {
    @Id
    private String id;
    private String name;
    private List<String> foods;
    private Type type;
    private String dateOfBirth;
    private String age;
    private String imageUrl;

    public Date parseDateString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public String calcAge(String dob) {
        long newDate = new Date().getTime() - parseDateString(dob).getTime();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(newDate);
        int mMonth = c.get(Calendar.MONTH);
        int mYear = c.get(Calendar.YEAR) - 1970;
        return mYear + " year(s)" + " and " + mMonth + " month(s)";
    }


}
