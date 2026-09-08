package org.manchirevula.locationtrackerserver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "location_records")
public class LocationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;
    private double speed;
    private double speedKmh;

    // epoch millis
    private long timestamp;

    private String deviceId;

    public LocationRecord() {}

    public LocationRecord(double latitude, double longitude, double speed, double speedKmh, long timestamp, String deviceId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.speedKmh = speedKmh;
        this.timestamp = timestamp;
        this.deviceId = deviceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeedKmh() {
        return speedKmh;
    }

    public void setSpeedKmh(double speedKmh) {
        this.speedKmh = speedKmh;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "LocationRecord{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", speed=" + speed +
                ", speedKmh=" + speedKmh +
                ", timestamp=" + timestamp +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}