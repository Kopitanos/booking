package com.example.demo.repository;

import com.example.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findBySeatNumber(Long seatNumber);
}
