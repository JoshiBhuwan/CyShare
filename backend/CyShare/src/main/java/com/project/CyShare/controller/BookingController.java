package com.project.CyShare.controller;


import com.project.CyShare.app.BookingDetails;
import com.project.CyShare.app.Maps;
import com.project.CyShare.app.Status;
import com.project.CyShare.service.BookingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller used to perform tasks on current booking orders.
 * @author Bhuwan Joshi
 */

@Api(tags = "BookingController", value = "BookingController", description = "This controller " +
        " is used to create or modify booking details.")
@RestController
public class BookingController {
    /**
     * initialize a bookingService.
     */
    @Autowired
    BookingService service;

    /**
     * This mapping returns a list of all bookings saved.
     * @return List of all bookings.
     */
    @ApiOperation(value = "Get list of all bookings saved.", response = Iterable.class)
    @ApiImplicitParam("No input parameters, just call using path booking/all")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Success!!"),
                    @ApiResponse(code = 401, message = "Not authorized!"),
                    @ApiResponse(code = 403, message = "Forbidden!"),
                    @ApiResponse(code = 404, message = "Oops... Not found")
            })
    @GetMapping("/booking/all")
    ArrayList<BookingDetails> getAllBooking()
    {
        return (ArrayList<BookingDetails>) service.findAll();
    }

    /**
     * This mapping gets the booking details by name.
     * @param name Booking name.
     * @return BookingDetails object.
     */
    @ApiOperation(value = "Get booking details by name.")
    @ApiImplicitParam(name = "name", value = "Booking name", paramType = "string")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 401, message = "Not authorized!"),
                    @ApiResponse(code = 403, message = "Forbidden!"),
                    @ApiResponse(code = 404, message = "Oops... not found")
            })
    @GetMapping("/booking/{name}")
    BookingDetails getDetailsByName(@PathVariable String name)
    {
        return service.findByname(name);
    }

    /**
     * This mapping is used to add a new booking into the database.
     * @param book Booking details to add.
     * @return Success/failure message.
     */
    @ApiOperation(value = "Add a new booking entity to the database.")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 401, message = "Not authorized!"),
                    @ApiResponse(code = 403, message = "Forbidden!"),
                    @ApiResponse(code = 404, message = "Oops... not found")
            })
    @PostMapping("/booking/add")
    String addBooking(@RequestBody BookingDetails book)
    {
        if(book==null) return "Want able to add";
        service.save(book);
        return "Booking Created Successfully";
    }

    /**
     * This mapping is used to assign a location to a given user.
     * @param map Location to be assigned.
     * @param userName Username of the user that will have location updated.
     * @return Success/failure message.
     */
    @ApiOperation(value = "Assign location to a given user.")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 401, message = "Not authorized!"),
                    @ApiResponse(code = 403, message = "Forbidden!"),
                    @ApiResponse(code = 404, message = "Oops... not found")
            })
    @ApiImplicitParam(name = "userName", value = "userName of the user that needs location updated.")
    @PutMapping("/booking/add/maps/{userName}")
    String addMaps(@RequestBody List<Maps> map,@PathVariable String userName)
    {  if(userName==null) return "Unable to add";
        BookingDetails booking = service.findByname(userName);
        for(Maps m:map)
        {m.setBook(booking);}
        booking.setLocation(map);
        service.save(booking);
        return "Maps added to the Booking";
    }

    /**
     * This mapping is used to set the status of a booking.
     * @param user userName.
     * @param status new booking status.
     * @return Success/failure message.
     */
    @ApiOperation(value = "Set status for a given booking entity.")
    @ApiImplicitParams(value =
            {
                    @ApiImplicitParam(name = "user", value = "userName", dataType = "string"),
                    @ApiImplicitParam(name = "status", value = "status to assign", dataType = "Status")
            })
    @ApiResponses(value =
            {
                    @ApiResponse(code = 401, message = "Not authorized!"),
                    @ApiResponse(code = 403, message = "Forbidden!"),
                    @ApiResponse(code = 404, message = "Oops... not found")
            })
    @PutMapping("/booking/status/{user}/{status}")
    String addStatus(@PathVariable String user,  @PathVariable Status status )
    {
        BookingDetails booking = service.findByname(user);
        booking.setIsBooked(status);
        service.save(booking);
        return "Added Succesfully";
    }

}
