import React, { Component } from 'react';

class Train extends Component {
	render() {
		return <li> id:{this.props.idTrain} -- seats:{this.props.seats} </li>;
	}	
}

export default Train;