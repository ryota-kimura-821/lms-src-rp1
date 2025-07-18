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
	
	String message() default "*勤怠情報が正しく入力されていません。"; 
	Class<?>[] groups() default {}; 
	Class<? extends Payload>[] payload() default {}; 
	String fieldTrainingStartTimeHour() default "trainingStartTimeHour";
    String fieldTrainingStartTimeMinute() default "trainingStartTimeMinute";
    String fieldTrainingEndTimeHour() default "trainingEndTimeHour";
    String fieldTrainingEndTimeMinute() default "trainingEndTimeMinute";
}
