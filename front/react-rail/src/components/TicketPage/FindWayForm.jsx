import React, { PropTypes, Component } from 'react';

class FindWayForm extends Component {

	constructor(props) {
		super(props);
		this.state = { 	stationDep: {idStation: "", stationName:""},
						stationArr: {idStation: "", stationName:""},
						dDep: "",
						dArr: "",
						search: "ways"
					};

		this.handleChangeStationDep = this.handleChangeStationDep.bind(this);
		this.handleChangeStationArr = this.handleChangeStationArr.bind(this);
		this.handleDateDepChange = this.handleDateDepChange.bind(this);
		this.handleDateArrChange = this.handleDateArrChange.bind(this);
		this.handleClickVariants = this.handleClickVariants.bind(this);
	}

	componentWillMount() {
  		this.props.getStations();
	}

	handleChangeStationDep(event) {
		var index = event.nativeEvent.target.selectedIndex;
		this.setState({stationDep: {idStation: event.nativeEvent.target[index].id, stationName: event.nativeEvent.target[index].value}});
	}

	handleChangeStationArr(event) {
		var index = event.nativeEvent.target.selectedIndex;
		this.setState({stationArr: {idStation: event.nativeEvent.target[index].id, stationName: event.nativeEvent.target[index].value}});
	}

	handleDateDepChange(val){
		const dateDep = val.target.value;
		this.setState({dDep: dateDep});
	}

	handleDateArrChange(val){
		const dateArr = val.target.value;
		this.setState({dArr: dateArr});
	}

	handleClickVariants() {
		const stationDep = this.state.stationDep;
		const stationArr = this.state.stationArr;
		const dDep = this.state.dDep; 
		const dArr = this.state.dArr;
		const search = this.state.search;

		this.props.getVariants(stationDep,stationArr,dDep,dArr,search);
	}

	render() {
		const { stations } = this.props;
		return (
			<div>
				<h3>Find way form</h3>
				<h4>Start station</h4>
				<select value={this.state.stationDep.stationName} id={this.state.stationDep.idStation} onChange={this.handleChangeStationDep}>
					{
						stations.map(function(station) {
        				return  <option key={station.idStation} id={station.idStation}>{station.stationName}</option> ;
      					})
					}
				</select>
				<h4>Finish station</h4>
				<select value={this.state.stationArr.stationName} id={this.state.stationArr.idStation} onChange={this.handleChangeStationArr}>
					{
						stations.map(function(station) {
        				return  <option key={station.idStation} id={station.idStation}>{station.stationName}</option> ;
      					})
					}
				</select>
				<div className="newStation">
					<h4>Start Date</h4>
					<input onChange={this.handleDateDepChange} className="inputNewStation" type="text" placeholder="Start date"/>
					<h4>Finish Date</h4>
					<input className="inputNewStation" onChange={this.handleDateArrChange}  type="text" placeholder="Finish date"/>
					<button className='btn' onClick={this.handleClickVariants}>Get Variants</button>
				</div>
			</div>
		)
	}
}

FindWayForm.propTypes = {
	getStations: PropTypes.func.isRequired,
	stations: PropTypes.arrayOf(PropTypes.object)
}

export default FindWayForm;