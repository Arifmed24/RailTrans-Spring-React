import React, { Component } from 'react';
import { Link } from 'react-router';

class Variant extends Component {
	render() {
		const {ticket, index} = this.props;
		return (
			<div>
			---------------------------------------------------------------------------
				<h4>Variant</h4>
				<ul>
					<li><b>Station departure:</b> {ticket.departureStation.stationName}</li>
					<li><b>Date departure:</b> {new Date(ticket.departureDate).toString()}</li>
					<li><b>Station arrival:</b> {ticket.arrivalStation.stationName}</li>
					<li><b>Date arrival:</b> {new Date(ticket.arrivalDate).toString()}</li>
					<li><b>Price:</b> {ticket.price}</li>
				</ul>
				<button><Link to={{ pathname: "/ticket/passenger", query: { index: index }}}>Buy</Link></button>
			</div>
		)
	}
}

export default Variant;