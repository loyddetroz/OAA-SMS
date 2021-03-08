package com.ateneo.server.controller;

import com.ateneo.server.domain.Donor;
import com.ateneo.server.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DonorController {

    @Autowired
    private DonorService donorService;

    @PostMapping("/addDonor")
    public Donor addDonor(@RequestBody Donor donor) {
        return  donorService.saveDonor(donor);
    }

    @PostMapping("/addDonors")
    public List<Donor> addDonors(@RequestBody List<Donor> donors) {
        return  donorService.saveDonors(donors);
    }

    @GetMapping("/donors")
    public List<Donor> findAllDonorsAsc() {
        return donorService.getDonorsAsc();
    }

    @GetMapping("/donors/desc")
    public List<Donor> findAllDonorsDesc() {
        return donorService.getDonorsDesc();
    }

    @GetMapping("/donors/byname/asc")
    public List<Donor> findAllDonorsByNameAsc() {
        return donorService.sortByNameAsc();
    }

    @GetMapping("/donors/byname/desc")
    public List<Donor> findAllDonorsByNameDesc() {
        return donorService.sortByNameDesc();
    }

    @GetMapping("/donor/{id}")
    public Donor findDonorById(@PathVariable  Long id) {
        return donorService.getDonorById(id);
    }

    @GetMapping("/donor/{accountName}")
    public Donor findDonorByAccountName(@PathVariable String accountName) {
        return donorService.getDonorByAccountName(accountName);
    }

    @DeleteMapping("/donors")
    public String deleteAllDonors() {
        return donorService.deleteAllDonors();
    }


}
