package by.rudenko.WeatherRestApp.util;

import by.rudenko.WeatherRestApp.models.Measurement;
import by.rudenko.WeatherRestApp.services.MeasurementService;
import by.rudenko.WeatherRestApp.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

  private final MeasurementService measurementService;
  private final SensorService sensorService;

  @Autowired
  public MeasurementValidator(
      MeasurementService measurementService,
      SensorService sensorService) {
    this.measurementService = measurementService;
    this.sensorService = sensorService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Measurement.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Measurement measurement = (Measurement) target;
    if (((Measurement) target).getSensor() == null) {
      errors.rejectValue("sensor", "", "Sensor should be indicated");
    }
    if(!sensorService.findByName(((Measurement) target).getSensor().getName()).isPresent()){
      errors.rejectValue("sensor", "","Sensor with such a name doesn't exist.");
    }
  }
}
