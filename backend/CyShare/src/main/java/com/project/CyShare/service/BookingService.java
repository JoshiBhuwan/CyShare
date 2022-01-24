package com.project.CyShare.service;

import com.project.CyShare.app.BookingDetails;
import com.project.CyShare.app.Car;
import com.project.CyShare.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Bhuwan Joshi
 */

@Service
public class BookingService
{
    /**
     * initialize a booking repository.
     */
    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Method used to find all current bookings.
     * @return List of all bookings.
     */
    public List<BookingDetails> findAll()
    {

        List<BookingDetails> list = new ArrayList<BookingDetails>((Collection<? extends BookingDetails>) bookingRepository.findAll());
        return list;
    }

    /**
     * Method used to find booking by name.
     * @param name Name.
     * @return BookingDetails object.
     */
    public BookingDetails findByname(String name)
    {
        List<BookingDetails>  list = findAll();
        for(BookingDetails b: list)
        {
            if(b.getName().equals(name)) return b;
        }
        return null;
    }

    /**
     * Method used to save booking details into the database.
     * @param booking BookingDetails object.
     */
    public void save(BookingDetails booking)
    {
        bookingRepository.save(booking);
    }

}
