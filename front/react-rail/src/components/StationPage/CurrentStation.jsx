import React, { PropTypes, Component } from 'react';

class CurrentStation extends Component {
	constructor(props){
		super(props);

		this.handleNameChange = this.handleNameChange.bind(this);
		this.handleClickName = this.handleClickName.bind(this);

		this.state = {
			stationName: this.props.station.stationName
		}
	}

	handleNameChange(val) {
    const name = val.target.value;
    this.setState({ stationName: name });
   }

	handleClickName() {
		this.props.createStation(this.state.stationName);
		this.props.getStations();
		alert("Done!");
	}

	render() {
		const { station } = this.props;
		return (
			<div className="newStation">
			<h3>New Station</h3>
			<input className="inputNewStation" onChange={this.handleNameChange} type="text" placeholder="Station name"/>
			<button className='btn' onClick={this.handleClickName}>Create Station</button>
			</div>
		)
	}
}

CurrentStation.propTypes = {
  createStation: PropTypes.func.isRequired,
  station: PropTypes.object
}

export default CurrentStation;


