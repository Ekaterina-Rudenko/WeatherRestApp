package by.rudenko.WeatherRestApp.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Measurement")
public class Measurement {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "value")
  @NotNull(message = "Temperature value can't be empty")
  @Min(value = -100, message = "Temperature value can't be below -100")
  @Max(value = 100, message = "Temperature value can't be above 100")
  private double value;

  @Column(name = "raining")
  @NotNull
  private boolean raining;

  @Column(name = "created_at")
  @NotNull
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "sensor", referencedColumnName = "name")
  private Sensor sensor;

  public Measurement(double value, boolean raining, LocalDateTime createdAt,
      Sensor sensor) {
    this.value = value;
    this.raining = raining;
    this.createdAt = createdAt;
    this.sensor = sensor;
  }

  public Measurement() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public boolean isRaining() {
    return raining;
  }

  public void setRaining(boolean raining) {
    this.raining = raining;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Sensor getSensor() {
    return sensor;
  }

  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }
}
