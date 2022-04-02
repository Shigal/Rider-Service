package com.fooddeliverysystem.riderservice.service;

import com.fooddeliverysystem.riderservice.model.Rider;
import com.fooddeliverysystem.riderservice.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RiderServiceImpl  implements UserDetailsService {

    @Autowired
    RiderRepository riderRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        if("jack".equals(username)){
//            return new User("jack", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayDeque<>());
//        }
//        else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
        Rider rider = riderRepository.findByUsername(username);
        if(rider == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return  rider;
    }


    //    @Override
    public Rider saveRider(Rider rider) {
        return riderRepository.save(rider);
    }

//    @Override
    public Rider updateRider(Rider rider, long id) {
        Optional<Rider> optionalRider = riderRepository.findById(id);

        if(optionalRider.isPresent()) {
            Rider currentRider = optionalRider.get();

            currentRider.setFirstName(rider.getFirstName());
            currentRider.setLastName(rider.getLastName());
            currentRider.setContact(rider.getContact());
            currentRider.setLicense(rider.getLicense());
            currentRider.setStatus(rider.isStatus());
            currentRider.setLatitude(rider.getLatitude());
            currentRider.setLongitude(rider.getLongitude());

            return riderRepository.save(currentRider);
        }
        else {
            return null;
        }
    }

}

