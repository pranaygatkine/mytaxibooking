package tech.codehunt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="service")
@Data
public class ServiceForm {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private int id;
	private String image;
	private String title;
	private String description;

}
