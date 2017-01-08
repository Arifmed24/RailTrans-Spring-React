import React, { Component } from 'react';

class Route extends Component {
	render() {
		return <li> {this.props.routeName} -- {this.props.startStation} -- {this.props.finishStation} </li>;
	}	
}

export default Route;