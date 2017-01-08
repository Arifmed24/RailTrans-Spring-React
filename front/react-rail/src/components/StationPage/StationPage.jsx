import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import StationList from './StationList';
import CurrentStation from './CurrentStation';
import * as stationAction from 'redux/actions/stationAction';

class StationPage extends Component {
  
    render() {
  	const { stations } = this.props
  	const { getStations, createStation } = this.props.stationAction
    return (
		<div>
        <CurrentStation getStations={getStations}  createStation={createStation} station={stations.station}/>
        <StationList getStations={getStations} stations={stations.stations}/>
    	</div>
    )
  }
}

function mapStateToProps (state) {
  return {
    stations: state.stations
  };
}

function mapDispatchToProps(dispatch) {
  return {
    stationAction: bindActionCreators(stationAction,dispatch)
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(StationPage);