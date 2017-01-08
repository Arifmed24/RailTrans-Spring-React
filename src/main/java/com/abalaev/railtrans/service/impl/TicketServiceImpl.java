package com.abalaev.railtrans.service.impl;

import com.abalaev.railtrans.dao.api.TicketDao;
import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Ticket;
import com.abalaev.railtrans.service.api.TicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

@Service("ticketService")
@Transactional
public class TicketServiceImpl implements TicketService {

    private static final Logger LOG = Logger.getLogger(TicketServiceImpl.class);
    private final TicketDao ticketDao;

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public List<Ticket> getTicketsFromRtLists(List<List<RouteTimetables>> ways) {
        LOG.info("finding tickets of ways");
        List<Ticket> tickets = new ArrayList<>();
        LOG.info("going through ways");
        for (List<RouteTimetables> variant: ways) {
            Ticket newTicket = new Ticket();
            newTicket.setTicketTrain(variant.get(0).getRouteId().getTrain());
            newTicket.setDepartureDate(variant.get(0).getDateDeparture());
            newTicket.setDepartureStation(variant.get(0).getLine().getStationDeparture());
            newTicket.setArrivalDate(variant.get(variant.size()-1).getDateArrival());
            newTicket.setArrivalStation(variant.get(variant.size()-1).getLine().getStationArrival());
            double price = 0;
            for (RouteTimetables aVariant : variant) {
                price += aVariant.getLine().getDistance() * 4;
            }
            BigDecimal b = new BigDecimal(price, MathContext.DECIMAL64);
            newTicket.setPrice(b);
            tickets.add(newTicket);
            LOG.info("ticket was added");
        }
        return tickets;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        Ticket result;
        result = ticketDao.create(ticket);
        LOG.info("ticket created");
        return result;
    }
}
