package tech.codehunt.service;

import java.util.List;

import tech.codehunt.model.ContactForm;

public interface ContactFormService {
public ContactForm saveContactFormService(ContactForm contactForm);
public List<ContactForm> readAllContactService( );
public void deleteContactService( int id);

}
