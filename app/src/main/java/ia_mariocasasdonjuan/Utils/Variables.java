/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Variables {
    public static class TimeVariables {
        public static final LocalDate today = LocalDate.now();
        public static final LocalTime now = LocalTime.now();
        public static final LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        public static final String sToday = today.toString();
        public static final String sNow = now.toString();
        public static final String sDateTime = dateTime.toString();
    }
}
