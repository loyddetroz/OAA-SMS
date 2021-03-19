package com.ateneo.server.controller;

import com.ateneo.server.domain.Donation;
import com.ateneo.server.domain.Donor;
import com.ateneo.server.service.DonationService;
import com.ateneo.server.util.DonorDonationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping("/add")
    public List<Donation> addDonation(@RequestBody Donation donation) {
        return donationService.saveDonation(donation);
    }

    @PostMapping("/add/many")
    public List<Donation> addDonations(List<Donation> donations) {
        return donationService.saveDonations(donations);
    }

    @GetMapping("/asc")
    public List<Donation> getAllDonationsAsc() {
        return donationService.getAllDonationsAsc();
    }

    @GetMapping("/desc")
    public List<Donation> getAllDonationsDesc() {return donationService.getAllDonationsDesc();}

    @GetMapping("/{id}")
    public Donation getDonation(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }


    @GetMapping("/donors/{id}")
    public List<Donor> getDonorsWithDonation(@PathVariable Long id) {
        return donationService.getDonorsWithDonation(id);
    }

    @PatchMapping("/update")
    public Donation updateDonation(@RequestBody Donation donation) {
        return donationService.updateDonation(donation);
    }

    @DeleteMapping(value = "/{id}")
    public List<Donation> deleteDonorById(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return donationService.getAllDonationsAsc();
    }

}
