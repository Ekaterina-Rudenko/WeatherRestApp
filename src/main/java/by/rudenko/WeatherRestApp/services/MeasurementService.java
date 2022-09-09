package by.rudenko.WeatherRestApp.services;

import by.rudenko.WeatherRestApp.models.Measurement;
import by.rudenko.WeatherRestApp.repositories.MeasurementRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

  private final MeasurementRepository measurementRepository;
  private final SensorService sensorService;

  @Autowired
  public MeasurementService(
      MeasurementRepository measurementRepository,
      SensorService sensorService) {
    this.measurementRepository = measurementRepository;
    this.sensorService = sensorService;
  }

  public List<Measurement> findAll() {
    return measurementRepository.findAll();
  }

  @Transactional
  public void save(Measurement measurement) {
    enrichMeasurement(measurement);
    measurementRepository.save(measurement);
  }

  private void enrichMeasurement(Measurement measurement) {
    measurement.setCreatedAt(LocalDateTime.now());
    measurement.setSensor(
        sensorService.findByName(measurement.getSensor().getName()).get());
  }


}
