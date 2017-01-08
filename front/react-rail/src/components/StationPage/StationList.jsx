import React, { PropTypes, Component } from 'react';
import Station from './Station';


class StationList extends Component {
 
 componentWillMount() {
  this.props.getStations();
}

  render() {
	const { stations } = this.props;
	return (
  <div>
  <h3>Stations</h3>
    <ul>
    {
      stations.map(function(station) {
        return  <li><Station key={station.idStation} stationName={station.stationName}/></li> ;
      })
    }
    </ul>
  </div>
  )
}
}

StationList.propTypes = {
  getStations: PropTypes.func.isRequired,
  stations: PropTypes.arrayOf(PropTypes.object)
}

export default StationList;