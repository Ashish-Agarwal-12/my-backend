package com.multiplex.ticketBooking.payLoads;

import com.multiplex.ticketBooking.entity.Hall;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HallPostResponse {

    private List<Hall> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPage;
    private boolean lastPage;



}
