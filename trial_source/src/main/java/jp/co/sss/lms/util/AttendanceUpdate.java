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
	// デフォルトのエラーメッセージ（未使用）
	String message() default "*勤怠情報が正しく入力されていません。"; 
	Class<?>[] groups() default {}; 
	Class<? extends Payload>[] payload() default {}; 
	// 出勤時間：時
	String fieldTrainingStartTimeHour() default "trainingStartTimeHour";
	// 出勤時間：分
    String fieldTrainingStartTimeMinute() default "trainingStartTimeMinute";
    // 退勤時間：時
    String fieldTrainingEndTimeHour() default "trainingEndTimeHour";
    // 退勤時間：分
    String fieldTrainingEndTimeMinute() default "trainingEndTimeMinute";
    // 備考
    String fieldNote() default "note";
    // インデックス
    String fieldIndex() default "index";
    // 中抜け時間
    String fieldBlankTime() default "blankTime";
}
