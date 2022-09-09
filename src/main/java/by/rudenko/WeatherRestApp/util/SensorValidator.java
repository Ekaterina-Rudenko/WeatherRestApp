package by.rudenko.WeatherRestApp.util;

import by.rudenko.WeatherRestApp.models.Sensor;
import by.rudenko.WeatherRestApp.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

  private final SensorService sensorService;

  @Autowired
  public SensorValidator(SensorService sensorService) {
    this.sensorService = sensorService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Sensor.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Sensor sensor = (Sensor) target;
    if(sensorService.findByName(sensor.getName()).isPresent()){
      errors.rejectValue("name", "Sensor with such name already exists.");
    }
  }
}
