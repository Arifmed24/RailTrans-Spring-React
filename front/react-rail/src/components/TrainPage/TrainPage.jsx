import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import TrainList from './TrainList';
import * as trainAction from 'redux/actions/trainAction';

class TrainPage extends Component {
  render() {
  	const { trains } = this.props
  	const { getTrains } = this.props.trainAction
    return (
		<div>
    		<h3>Trains</h3>
    		<TrainList getTrains={getTrains} trains={trains.trains}/>
    	</div>
    )
  }
}

function mapStateToProps (state) {
  return {
    trains: state.trains
  };
}

function mapDispatchToProps(dispatch) {
  return {
    trainAction: bindActionCreators(trainAction,dispatch)
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(TrainPage);