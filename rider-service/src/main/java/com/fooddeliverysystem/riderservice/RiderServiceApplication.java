package com.fooddeliverysystem.riderservice;

import com.fooddeliverysystem.riderservice.model.Authority;
//import com.fooddeliverysystem.riderservice.model.User;
//import com.fooddeliverysystem.riderservice.repository.UserDetailsRepository;
import com.fooddeliverysystem.riderservice.model.Rider;
import com.fooddeliverysystem.riderservice.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RiderServiceApplication {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RiderRepository riderRepository;

	public static void main(String[] args) {
		SpringApplication.run(RiderServiceApplication.class, args);
	}

//	@PostConstruct
//	protected void init() {
//
//		List<Authority> authorityList=new ArrayList<>();
//
//		authorityList.add(createAuthority("USER","User role"));
//		authorityList.add(createAuthority("ADMIN","Admin role"));
//
//		Rider rider = new Rider();
//
//		rider.setUsername("jackson");
//		rider.setPassword(passwordEncoder.encode("test@123"));
//		rider.setEnabled(true);
//		rider.setAuthorities(authorityList);
//
//		riderRepository.save(rider);
//
//	}

//	private Authority createAuthority(String roleCode,String roleDescription) {
//		Authority authority=new Authority();
//		authority.setRoleCode(roleCode);
//		authority.setRoleDescription(roleDescription);
//		return authority;
//	}

//	@KafkaListener(topics = "rider-topic", groupId = "group-id")
//	public void listen(String message){
//		System.out.println("received method:" + message);
//	}
}
