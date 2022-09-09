package by.rudenko.WeatherRestApp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

  @NotEmpty(message = "Sensor should have name")
  @Size(min = 3, max = 30, message = "Sensor name should contain from 3 to 30 characters")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
