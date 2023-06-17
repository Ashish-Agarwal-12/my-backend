package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "slotId")
    private Slot slot;

    @ManyToOne
    @JoinColumn(name = "booking_user_id", referencedColumnName = "userId")
    private User user;

    @NotNull(message = "Please Enter the booking Date")
    @FutureOrPresent
    private LocalDate bookingDate;

    private String status;

    @NotNull(message = "Please Select the Number of seats")
    @Min(1)
    private Integer noOfSeats;
}

