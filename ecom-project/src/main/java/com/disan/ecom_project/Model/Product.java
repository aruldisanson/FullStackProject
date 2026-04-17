package com.disan.ecom_project.Model;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.boot.jackson.autoconfigure.JacksonProperties.Json;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private String brand;
	private BigDecimal price;
	private String category;
	private Date releaseDate;
	private Boolean available;
	private Integer quantity;
	private String imageName;
	private String imageType;
	@Lob
	private byte[] imageDate;
}
