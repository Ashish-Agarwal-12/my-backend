package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    @Length(min = 5)
    @NotEmpty(message = "Please Enter Username of minimum of 5 characters")
    private String userName;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$", message = "password must be min 8 and max 16 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    @Length(min = 8, max = 16)
    private String password;

    @NotEmpty(message = "UserType must be either Admin/User")
    private String userType;

    @Length(min = 10 , max = 12)
    @NotEmpty(message = "Mobile Number should be of minimum 10 digits")
    @Column(unique = true)
    private String mobileNumber;

    @NotEmpty
    @Email(message = "Please Enter a valid Email Address")
    @Column(unique = true)
    private String emailId;
}
