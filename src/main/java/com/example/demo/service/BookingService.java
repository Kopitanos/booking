package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.Movie;
import com.example.demo.model.User;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private EmailingService emailingService;

    public Booking createBooking(Booking booking, Long movieId, Long userId) {
        Movie movie = movieRepository.findById(movieId).get();
        User user = userRepository.findById(userId).get();

        if (bookingRepository.findBySeatNumber(booking.getSeatNumber()).isPresent()) {
            throw new RuntimeException("Seat number " + booking.getSeatNumber() + " has been reserved");
        }

        booking.setUser(user);
        booking.setMovie(movie);
        booking.setTicketNumber(UUID.randomUUID().toString());
        booking.setBookingDate(LocalDateTime.now().plusDays(2));

        double totalPrice = movie.getPrice() * booking.getNumberOfSeat();

        booking.setTotalPrice(totalPrice);

        emailingService.sendEmail(user.getEmail(), "Booking confirmation", "Hi, " +
                user.getName() + " " + user.getSurname() + " this is to confirm that you have successfully book \n Date: " + booking.getBookingDate());

        return  bookingRepository.save(booking);
    }
}
