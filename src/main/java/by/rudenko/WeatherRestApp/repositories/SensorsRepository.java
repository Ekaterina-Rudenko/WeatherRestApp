package by.rudenko.WeatherRestApp.repositories;

import by.rudenko.WeatherRestApp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {

}
