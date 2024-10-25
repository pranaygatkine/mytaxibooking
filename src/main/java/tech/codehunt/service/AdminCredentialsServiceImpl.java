package tech.codehunt.service;

import java.util.Optional;

import org.hibernate.validator.internal.util.privilegedactions.IsClassPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tech.codehunt.dao.AdminDao;
import tech.codehunt.model.Admin;

@Service
public class AdminCredentialsServiceImpl implements AdminCredentialsService {

	
	private AdminDao adminDao;
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	
	
	@Override
	public String checkAdminCredentials(String oldusername, String oldpassword) {
		Optional<Admin> byUsername = adminDao.findByUsername(oldusername);
	
	if( byUsername.isPresent()){
		Admin admin = byUsername.get();	
		boolean matches = passwordEncoder.matches(oldpassword, admin.getPassword());
		if(matches) {
			return "success";
		}else
		{
			return"Wrong Old Credentials";

			
		}
		
		
		}else {
			return"Wrong Old Credentials";
		}
	
	}

	@Override
	public String updateAdminCredentials(String newusername, String newpassword, String oldusername) {
int updateCredentials = adminDao.updateCredentials
(newusername,passwordEncoder.encode(newpassword) , oldusername);
		
if(updateCredentials==1) {
	return "CREDENTIALS UPDATED SUCCESSFULY";
}else {
	return " FAILED TO UPDATE";
}

	}

}
