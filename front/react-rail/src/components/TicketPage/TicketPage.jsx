import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import FindWayForm from './FindWayForm';
import VariantList from './VariantList';
import * as stationAction from 'redux/actions/stationAction';
import * as ticketAction from 'redux/actions/ticketAction';

class TicketPage extends Component {

	render() {
		const { stations, variants } = this.props;
  		const { getStations, createStation } = this.props.stationAction;
  		const { getVariants } = this.props.ticketAction;
		return (
			<div>
				<FindWayForm getStations={getStations} stations={stations.stations} getVariants={getVariants} variants={variants.variants}/>
				<VariantList variants={variants.variants}/>
			</div>
		)
	}
}

function mapStateToProps (state) {
  return {
    stations: state.stations,
    variants: state.variants
  };
}

function mapDispatchToProps(dispatch) {
	return {
    	stationAction: bindActionCreators(stationAction,dispatch),
    	ticketAction: bindActionCreators(ticketAction,dispatch)
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(TicketPage);