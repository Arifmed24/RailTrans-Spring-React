package com.abalaev.railtrans.dao.impl;

import com.abalaev.railtrans.dao.api.TicketDao;
import com.abalaev.railtrans.model.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("ticketDao")
@Transactional
public class TicketDaoImpl extends GenericDaoImpl<Ticket, Integer> implements TicketDao {
}
