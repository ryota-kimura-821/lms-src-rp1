package jp.co.sss.lms.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AttendanceUpdateValidator implements ConstraintValidator<AttendanceUpdate, Object> {

	private String trainingStartTimeHour;
	private String trainingStartTimeMinute;
	private String trainingEndTimeHour;
	private String trainingEndTimeMinute;

	@Override
	public void initialize(AttendanceUpdate annotation) {
		this.trainingStartTimeHour = annotation.fieldTrainingStartTimeHour();
		this.trainingStartTimeMinute = annotation.fieldTrainingStartTimeMinute();
		this.trainingEndTimeHour = annotation.fieldTrainingEndTimeHour();
		this.trainingEndTimeMinute = annotation.fieldTrainingEndTimeMinute();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);

		String trainingStartTimeHour = (String) beanWrapper.getPropertyValue(this.trainingStartTimeHour);
		String trainingStartTimeMinute = (String) beanWrapper.getPropertyValue(this.trainingStartTimeMinute);
		String trainingEndTimeHour = (String) beanWrapper.getPropertyValue(this.trainingEndTimeHour);
		String trainingEndTimeMinute = (String) beanWrapper.getPropertyValue(this.trainingEndTimeMinute);

		boolean valid = true;

		if (inputCheck(trainingStartTimeHour, trainingStartTimeMinute)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{input.valid}")
					.addPropertyNode(this.trainingStartTimeHour)
					.addConstraintViolation();
			valid = false;
		}

		if (inputCheck(trainingEndTimeHour, trainingEndTimeMinute)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{input.valid}")
					.addPropertyNode(this.trainingEndTimeHour)
					.addConstraintViolation();
			valid = false;
		}

		return valid;
	}

	private boolean inputCheck(String hour, String minute) {
		boolean isHourEmpty = (hour == null || hour.trim().isEmpty());
		boolean isMinuteEmpty = (minute == null || minute.trim().isEmpty());
		return isHourEmpty ^ isMinuteEmpty;
	}
}
