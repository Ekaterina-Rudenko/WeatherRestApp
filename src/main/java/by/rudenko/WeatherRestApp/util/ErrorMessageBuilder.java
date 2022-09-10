package by.rudenko.WeatherRestApp.util;

import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorMessageBuilder {
  public static void buildErrorResponse(BindingResult bindingResult){
    StringBuilder errorMessage = new StringBuilder();
    List<FieldError> errorList =bindingResult.getFieldErrors();
    for(FieldError error : errorList){
      errorMessage.append(error.getField())
          .append(" - ")
          .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
          .append("; ");
    }
    throw new MeasurementException(errorMessage.toString());
  }

}
