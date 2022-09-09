package by.rudenko.WeatherRestApp.repositories;

import by.rudenko.WeatherRestApp.models.Sensor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
 public Optional<Sensor> findByName(String name);
}
