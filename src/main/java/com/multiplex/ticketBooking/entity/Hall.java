package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hallId;

    @Column(unique = true)
    @NotEmpty(message = "Please Enter the hall Name")
    @Length(min = 3)
    private String hallName;

    @NotEmpty(message = "Please Enter address of the Hall")
    private String address;

    @NotNull
    private Integer totalCapacity;
}
