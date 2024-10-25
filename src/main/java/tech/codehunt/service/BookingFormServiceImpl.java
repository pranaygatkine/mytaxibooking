package tech.codehunt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.codehunt.dao.BookingFormCrud;
import tech.codehunt.model.BookingForm;

@Service 
public class BookingFormServiceImpl implements BookingFormService {
	
	private BookingFormCrud bookingFormCrud;

	@Autowired
	public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
		this.bookingFormCrud = bookingFormCrud;
	}

	@Override
	public BookingForm saveBookingFormService(BookingForm bookingForm) {
		// TODO Auto-generated method stub
		bookingFormCrud.save(bookingForm);
		return null;
	}

	@Override
	public List<BookingForm> readAllBookingService() {
		// TODO Auto-generated method stub
		return bookingFormCrud.findAll();
	}

	@Override
	public void deleteBookingService(int id) {
		// TODO Auto-generated method stub
		bookingFormCrud.deleteById(id);
	}

}
