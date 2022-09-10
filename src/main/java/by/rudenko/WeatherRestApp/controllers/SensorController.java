package by.rudenko.WeatherRestApp.controllers;

import by.rudenko.WeatherRestApp.dto.SensorDTO;
import by.rudenko.WeatherRestApp.models.Sensor;
import by.rudenko.WeatherRestApp.services.SensorService;
import by.rudenko.WeatherRestApp.util.ErrorMessageBuilder;
import by.rudenko.WeatherRestApp.util.AppErrorResponse;
import by.rudenko.WeatherRestApp.util.SensorNotCreatedException;
import by.rudenko.WeatherRestApp.util.SensorValidator;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorController {

  private final SensorService sensorService;
  private final ModelMapper modelMapper;
  private final SensorValidator sensorValidator;

  @Autowired
  public SensorController(SensorService sensorService, ModelMapper modelMapper,
      SensorValidator sensorValidator) {
    this.sensorService = sensorService;
    this.modelMapper = modelMapper;
    this.sensorValidator = sensorValidator;
  }

  @PostMapping("/registration")
  public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO,
      BindingResult bindingResult) {
    Sensor sensor = convertToSensor(sensorDTO);
    sensorValidator.validate(sensor, bindingResult);
    if (bindingResult.hasErrors()) {
      String errorMsg = ErrorMessageBuilder.buildErrorResponse(bindingResult);
      throw new SensorNotCreatedException(errorMsg);
    }
    sensorService.save(sensor);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  private Sensor convertToSensor(SensorDTO sensorDTO) {
    return modelMapper.map(sensorDTO, Sensor.class);
  }

  @ExceptionHandler
  private ResponseEntity<AppErrorResponse> handleException(SensorNotCreatedException e) {
    AppErrorResponse errorResponse = new AppErrorResponse(
        e.getMessage(), System.currentTimeMillis());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
