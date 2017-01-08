package com.abalaev.railtrans.service.api;


import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getTicketsFromRtLists(List<List<RouteTimetables>> ways);

    Ticket createTicket(Ticket ticket);
}
