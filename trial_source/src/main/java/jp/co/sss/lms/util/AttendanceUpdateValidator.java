package jp.co.sss.lms.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AttendanceUpdateValidator implements ConstraintValidator<AttendanceUpdate, Object> {

	private String trainingStartTimeHour;
	private String trainingStartTimeMinute;
	private String trainingEndTimeHour;
	private String trainingEndTimeMinute;
	private String note;

	@Override
	public void initialize(AttendanceUpdate annotation) {
		this.trainingStartTimeHour = annotation.fieldTrainingStartTimeHour();
		this.trainingStartTimeMinute = annotation.fieldTrainingStartTimeMinute();
		this.trainingEndTimeHour = annotation.fieldTrainingEndTimeHour();
		this.trainingEndTimeMinute = annotation.fieldTrainingEndTimeMinute();
		this.note = annotation.fieldNote();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);

		String trainingStartTimeHour = (String) beanWrapper.getPropertyValue(this.trainingStartTimeHour);
		String trainingStartTimeMinute = (String) beanWrapper.getPropertyValue(this.trainingStartTimeMinute);
		String trainingEndTimeHour = (String) beanWrapper.getPropertyValue(this.trainingEndTimeHour);
		String trainingEndTimeMinute = (String) beanWrapper.getPropertyValue(this.trainingEndTimeMinute);
		String note = (String) beanWrapper.getPropertyValue(this.note);

		boolean valid = true;
		
		ResourceBundle bundleA = ResourceBundle.getBundle("ValidationMessages", Locale.getDefault());
		String templateA = bundleA.getString("maxlength");
		
		// 備考の文字数が100文字を超える場合
		if (note.length() > 100) {
			String message = MessageFormat.format(templateA, "備考", 100);
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode("note")
					.addConstraintViolation();
			valid = false;
		}

		ResourceBundle bundleB = ResourceBundle.getBundle("ValidationMessages", Locale.getDefault());
		String templateB = bundleB.getString("input.invalid");

		// 出勤時間の時間が未入力
		if (trainingStartTimeHour == "" && trainingStartTimeMinute != "") {
			String message = MessageFormat.format(templateB, "出勤時間");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode("trainingStartTimeHour")
					.addConstraintViolation();
			valid = false;
		}

		// 出勤時間の分が未入力
		if (trainingStartTimeHour != "" && trainingStartTimeMinute == "") {
			String message = MessageFormat.format(templateB, "出勤時間");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode("trainingStartTimeMinute")
					.addConstraintViolation();
			valid = false;
		}

		// 退勤時間の時間が未入力
		if (trainingEndTimeHour == "" && trainingEndTimeMinute != "") {
			String message = MessageFormat.format(templateB, "退勤時間");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode("trainingEndTimeHour")
					.addConstraintViolation();
			valid = false;
		}

		// 退勤時間の分が未入力
		if (trainingEndTimeHour != "" && trainingEndTimeMinute == "") {
			String message = MessageFormat.format(templateB, "退勤時間");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode("trainingEndTimeMinute")
					.addConstraintViolation();
			valid = false;
		}

		ResourceBundle bundleC = ResourceBundle.getBundle("ValidationMessages", Locale.getDefault());
		String templateC = bundleC.getString("attendance.punchInEmpty");

		// 出勤時間に入力がなし＆退勤時間に入力がある
		if ((trainingStartTimeHour == "" && trainingStartTimeMinute == "")
				&& trainingEndTimeHour != "" && trainingEndTimeMinute != "") {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(templateC)
					.addPropertyNode("trainingStartTimeHour")
					.addConstraintViolation();

			context.buildConstraintViolationWithTemplate("")
					.addPropertyNode("trainingStartTimeMinute")
					.addConstraintViolation();
			valid = false;
		}

		//		if (inputCheck(trainingStartTimeHour, trainingStartTimeMinute)) {
		//			String message = MessageFormat.format(template, "出勤時刻");
		//			context.disableDefaultConstraintViolation();
		//			context.buildConstraintViolationWithTemplate(message)
		//					.addPropertyNode("trainingStartTimeHour")
		//					.addPropertyNode("trainingStartTimeMinute")
		//					.addConstraintViolation();
		//			valid = false;
		//		}

		//		if (inputCheck(trainingEndTimeHour, trainingEndTimeMinute)) {
		//			String message = MessageFormat.format(template, "退勤時刻");
		//			context.disableDefaultConstraintViolation();
		//			context.buildConstraintViolationWithTemplate(message)
		//					.addPropertyNode("trainingEndTimeHour")
		//					.addPropertyNode("trainingEndTimeMinute")
		//					.addConstraintViolation();
		//			valid = false;
		//		}

		return valid;
	}

	//	private boolean inputCheck(String hour, String minute) {
	//		boolean isHourEmpty = (hour == null || hour.trim().isEmpty());
	//		boolean isMinuteEmpty = (minute == null || minute.trim().isEmpty());
	//		return isHourEmpty ^ isMinuteEmpty;
	//	}
}
