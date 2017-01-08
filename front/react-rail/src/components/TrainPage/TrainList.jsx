import React, { PropTypes, Component } from 'react';
import Train from './Train';

class TrainList extends Component {
	
  	componentWillMount() {
  		this.props.getTrains();
	}

  render() {
  	const { trains } = this.props;
  	return (
		<div>
			<ul>
				{
				trains.map(function(train) {
				return <li><Train key={train.idTrain} idTrain={train.idTrain} seats={train.seats}/></li> ;
				})
				}
			</ul>
		</div>
	)
  }
}

TrainList.propTypes = {
  getTrains: PropTypes.func.isRequired,
  trains: PropTypes.arrayOf(PropTypes.object)
}

export default TrainList;