package by.rudenko.WeatherRestApp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {
  @NotNull(message = "Temperature value can't be empty")
  @Min(value = -100, message = "Temperature value can't be below -100")
  @Max(value = 100, message = "Temperature value can't be above 100")
  private Double value;

  @NotNull
  private Boolean raining;

  private SensorDTO sensor;

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Boolean getRaining() {
    return raining;
  }

  public void setRaining(Boolean raining) {
    this.raining = raining;
  }

  public SensorDTO getSensor() {
    return sensor;
  }

  public void setSensor(SensorDTO sensor) {
    this.sensor = sensor;
  }
}
