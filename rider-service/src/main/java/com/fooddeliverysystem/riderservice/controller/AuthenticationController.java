package com.fooddeliverysystem.riderservice.controller;

import com.fooddeliverysystem.riderservice.config.JwtTokenHelper;
import com.fooddeliverysystem.riderservice.model.*;
//import com.fooddeliverysystem.riderservice.model.User;
import com.fooddeliverysystem.riderservice.repository.RiderRepository;
//import com.fooddeliverysystem.riderservice.service.RiderService;
import com.fooddeliverysystem.riderservice.service.RiderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private RiderServiceImpl riderService;
//    private RiderService riderService;

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Rider rider = (Rider)authentication.getPrincipal();
        String jwtToken = jwtTokenHelper.generateToken(rider.getUsername());
        JwtResponse loginResponse = new JwtResponse(jwtToken);

        return ResponseEntity.ok(loginResponse);

    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Rider createRider(@RequestBody SignupRequest signupRequest){
        List<Authority> authorityList=new ArrayList<>();
        authorityList.add(createAuthority("ADMIN","Admin role"));
//        Rider rider = new Rider(signupRequest.getEmail(), signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()));
        Rider rider = new Rider();
        rider.setEmail(signupRequest.getEmail());
        rider.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        rider.setUsername(signupRequest.getUsername());
        rider.setAuthorities(authorityList);
        return riderService.saveRider(rider);
    }

//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public Rider createRider(@RequestBody Rider rider){
//        List<Authority> authorityList=new ArrayList<>();
//        authorityList.add(createAuthority("ADMIN","Admin role"));
////        authorityList.add(createAuthority("USER","Admin role"));
//        Rider rider1 = new Rider();
//        rider1.setUsername(rider.getUsername());
//        rider1.setEmail(rider.getEmail());
//        rider1.setPassword(passwordEncoder.encode(rider.getPassword()));
//        rider1.setAuthorities(authorityList);
//
//        return riderService.saveRider(rider1);
//    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.PUT)
    public Rider updateRider(@RequestBody Rider rider, @PathVariable("id") int id) {
        return riderService.updateRider(rider, id);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<?> fetchProfile(Principal user){
//        Rider rider = (Rider) riderService.loadUserByUsername(user.getName());
        MessageResponse message = new MessageResponse();
        RiderProfile riderProfile = new RiderProfile();
        try {
            Rider rider = (Rider) riderService.loadUserByUsername(user.getName());
            riderProfile.setFirstName(rider.getFirstName());
            riderProfile.setLastName(rider.getLastName());
            riderProfile.setUsername(rider.getUsername());
            message.setMessage("Rider profile is available");
            return ResponseEntity.ok(riderProfile);
        }
        catch (Exception e) {
            message.setMessage(e.getMessage());
            return ResponseEntity.ok(message);
        }
    }

    private Authority createAuthority(String roleCode,String roleDescription) {
        Authority authority=new Authority();
        authority.setRoleCode(roleCode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }

}

