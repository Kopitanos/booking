package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/create/{movieId}/{userId}")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, @PathVariable Long movieId, @PathVariable Long userId) {
       return ResponseEntity.ok(bookingService.createBooking(booking, movieId, userId));
    }
}
