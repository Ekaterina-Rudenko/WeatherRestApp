package by.rudenko.WeatherRestApp.controllers;

import by.rudenko.WeatherRestApp.dto.MeasurementDTO;
import by.rudenko.WeatherRestApp.models.Measurement;
import by.rudenko.WeatherRestApp.services.MeasurementService;
import by.rudenko.WeatherRestApp.util.ErrorMessageBuilder;
import by.rudenko.WeatherRestApp.util.MeasurementException;
import by.rudenko.WeatherRestApp.util.MeasurementValidator;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
  private final MeasurementService measurementService;
  private final MeasurementValidator measurementValidator;
  private final ModelMapper modelMapper;

  @Autowired
  public MeasurementController(
      MeasurementService measurementService,
      MeasurementValidator measurementValidator, ModelMapper modelMapper) {
    this.measurementService = measurementService;
    this.measurementValidator = measurementValidator;
    this.modelMapper = modelMapper;
  }
  @GetMapping()
  public List<MeasurementDTO> findAll(){
    return measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
    Measurement measurement = convertToMeasurement(measurementDTO);
    measurementValidator.validate(measurement, bindingResult);
    if(bindingResult.hasErrors()){
      String errorMsg = ErrorMessageBuilder.buildErrorResponse(bindingResult);
      throw new MeasurementException(errorMsg);
    }
    measurementService.save(measurement);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping("/rainyDaysCount")
  public Long countRainyDays(){
    return measurementService.findAll().stream().filter(Measurement::isRaining).count();
  }

  private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
    return modelMapper.map(measurement, MeasurementDTO.class);
  }

  private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
    return modelMapper.map(measurementDTO, Measurement.class);
  }

}
