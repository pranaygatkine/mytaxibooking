package tech.codehunt.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table
public class BookingForm {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@NotEmpty(message="Name Cannot be Empty")
	@Size(min=2 , max=30 , message= "Invalid Name length")
	@Column(length = 30)
	@NotBlank(message="Name cannot be blank")
	@Pattern(regexp = "^[A-Za-z ]+$" ,message= "name must be contain only alphabet")
    private String name;
	
	@Column(length = 100)
   @Size(min=2 , max=100 , message= "Invalid Source length")
    @NotBlank(message="Source cannot be blank")
    @NotEmpty(message="Source Cannot be Empty")
   private String source;
	
	@Size(min=8 , max=50 , message= "Invalid Email length")
   @NotBlank(message="Email cannot be blank")
    @NotEmpty(message="Email Cannot be Empty")
	@Column(length = 30)
    private String email;
	
	@Size(min=2 , max=100 , message= "Invalid destination length")
   @NotBlank(message="destination cannot be blank")
    @NotEmpty(message="destination Cannot be Empty")
	@Column(length = 100)
    private String destination;
	
	@NotNull(message="Time Cannot be Empty")
    private LocalTime time;
	
	@NotNull(message="Date Cannot be Empty")
    private LocalDate date;
	
	@Size(min=2 , max=20 , message= "Invalid Comfort length")
    @NotEmpty(message="Comfort Cannot be Empty")
	@Column(length = 30)
   private String comfort;
	
	@Min(value=1,message= "Adult can be at least 1")
    @Max(value=4, message=  "Adult can be at most 3")
	private int adult;
	
    @Max(value=3, message= "Children can be at most 3")
    private int children;
	
	
	@Size(min=2 , max=2000 , message= "Invalid Message length")
  @NotBlank(message="Message cannot be blank")
    @NotEmpty(message="Message Cannot be Empty")
	@Column(length = 2000)
private String message;
	
	
	
	





	
}
