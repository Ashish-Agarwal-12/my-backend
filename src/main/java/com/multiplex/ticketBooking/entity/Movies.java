package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @NotEmpty(message = "Please Enter the movie title")
    @Column(unique = true)
    private String title;

    @NotEmpty(message = "Please Enter the description of the movie")
    private String description;

    @NotEmpty(message = "Please Provide the genre")
    private String genre;

    @NotNull(message = "Please Enter the Duration")
    private Integer duration;

    @FutureOrPresent
    @NotNull(message = "Please Enter the release date for the movie")
    private LocalDate releaseDate;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Slot.class)
    @JoinColumn(referencedColumnName = "movieId")
    private List<Slot> slots;
}
