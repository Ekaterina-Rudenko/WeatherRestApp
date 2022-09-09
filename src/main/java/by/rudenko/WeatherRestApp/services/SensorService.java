package by.rudenko.WeatherRestApp.services;

import by.rudenko.WeatherRestApp.models.Sensor;
import by.rudenko.WeatherRestApp.repositories.SensorsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SensorService {

  private final SensorsRepository sensorsRepository;

  @Autowired
  public SensorService(SensorsRepository sensorsRepository) {
    this.sensorsRepository = sensorsRepository;
  }

  @Transactional
  public void save(Sensor sensor){
    sensorsRepository.save(sensor);
  }

  public Optional<Sensor> findByName(String name){
    return sensorsRepository.findByName(name);
  }
}
