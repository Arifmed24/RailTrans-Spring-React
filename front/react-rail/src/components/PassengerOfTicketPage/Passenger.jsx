import React, { Component } from 'react';

class Passenger extends Component {

	constructor(props){
		super(props);

		this.handleFirstChange = this.handleFirstChange.bind(this);
		this.handleLastChange = this.handleLastChange.bind(this);
		this.handleBirthChange = this.handleBirthChange.bind(this);
		this.handleClickTicket = this.handleClickTicket.bind(this);

		this.state = {
			first: '',
			last: '',
			birth: ''
		}
	}

	handleFirstChange(val) {
		const first = val.target.value;
		this.setState({ first: first });
	}

	handleLastChange(val){
		const last = val.target.value;
		this.setState({ last: last });
	}

	handleBirthChange(val){
		const birth = val.target.value;
		this.setState({ birth: birth });
	}

	handleClickTicket()
	{
		const first = this.state.first;
		const last = this.state.last;
		const birth = this.state.birth;
		const requestTicket = this.props.requestTicket;
		const way = this.props.way;
		this.props.buyTicket(first,last,birth,requestTicket,way);
	}

	render() {
		return (
			<div>
				<h3>Passenger</h3>
				<div className="newStation">
					<input  className="inputNewStation" onChange={this.handleFirstChange} type="text" placeholder="First name"/>
					<input  className="inputNewStation" onChange={this.handleLastChange} type="text" placeholder="Last name"/>
					<input  className="inputNewStation" onChange={this.handleBirthChange} type="text" placeholder="Birth date"/>
					<button className='btn' onClick={this.handleClickTicket}>Buy ticket</button>
				</div>
			</div>
		)
	}
}

export default Passenger;