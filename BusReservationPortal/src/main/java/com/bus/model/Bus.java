package com.bus.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Bus {
	private static final LocalTime LocalTime = null;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	private String busName;
	private String busType;
	private String routeFrom;
	private String routeTo;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private Integer seats;
	private Integer availableSeats;
	@ManyToOne
	private Route route;

    public Bus() {	
		this.arrivalTime=LocalTime.now();
		this.departureTime=LocalTime.minusMinutes(15);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
