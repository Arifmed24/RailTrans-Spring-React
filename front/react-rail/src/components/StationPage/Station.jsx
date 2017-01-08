import React, { Component } from 'react';

class Station extends Component {
	render() {
		return <li> {this.props.stationName} </li>;
	}	
}

export default Station;