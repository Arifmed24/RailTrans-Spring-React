import React, { PropTypes, Component } from 'react';
import Route from './Route';

class RouteList extends Component {
	
	componentWillMount() {
  		this.props.getRoutes();
	}

	render() {
		const { routes } = this.props;
		return (
			<div>
			<h3>Routes</h3>
			<ul>
			{
				routes.map(function(route) {
				return  <li><Route key={route.idRoute} routeName={route.routeName} startStation={route.startStation.stationName}  finishStation={route.finishStation.stationName} /></li> ;
			})
			}
			</ul>
			</div>
		)
	}
}

RouteList.propTypes = {
	getRoutes: PropTypes.func.isRequired,
	routes: PropTypes.arrayOf(PropTypes.object)
}

export default RouteList;