package jp.co.sss.lms.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload; 

@Target({ java.lang.annotation.ElementType.TYPE }) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented 
@Constraint(validatedBy = { AttendanceUpdateValidator.class }) 
public @interface AttendanceUpdate {
	
	String message() default "{attendance.time.invalid}"; 
	Class<?>[] groups() default {}; 
	Class<? extends Payload>[] payload() default {}; 
	String startHourField() default "trainingStartTimeHour";
    String startMinuteField() default "trainingStartTimeMinute";
    String endHourField() default "trainingEndTimeHour";
    String endMinuteField() default "trainingEndTimeMinute";
}
