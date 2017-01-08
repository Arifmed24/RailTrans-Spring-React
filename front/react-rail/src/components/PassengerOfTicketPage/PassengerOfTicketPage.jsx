import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import Passenger from './Passenger';
import * as ticketAction from 'redux/actions/ticketAction';

class PassengerOfTicketPage extends Component {
	render () {
		const { index } = this.props.location.query;
		const requestTicket = this.props.variants.variants.tickets[index];
		const way = this.props.variants.variants.ways[index];
		const { buyTicket } = this.props.ticketAction;
		return(
			<div>
				<h3>Passenger of ticket page</h3>
				You choosed variant : {1 + +index}
				<p>Please fill name and you birth for ticket</p>
				<ul>
					<li><b>Station departure:</b> {requestTicket.departureStation.stationName}</li>
					<li><b>Date departure:</b> {new Date(requestTicket.departureDate).toString()}</li>
					<li><b>Station arrival:</b> {requestTicket.arrivalStation.stationName}</li>
					<li><b>Date arrival:</b> {new Date(requestTicket.arrivalDate).toString()}</li>
					<li><b>Price:</b> {requestTicket.price}</li>
				</ul>
				<Passenger requestTicket={requestTicket} way={way} buyTicket={buyTicket}/>
			</div>
		)
	}
}

function mapStateToProps (state) {
  return {
    variants: state.variants
  };
}

function mapDispatchToProps(dispatch) {
  return {
    ticketAction: bindActionCreators(ticketAction,dispatch)
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(PassengerOfTicketPage);