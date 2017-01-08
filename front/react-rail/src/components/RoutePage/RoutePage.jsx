import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import RouteList from './RouteList';
import * as routeAction from 'redux/actions/routeAction';

class RoutePage extends Component {
	render() {
		const { routes } = this.props;
		const { getRoutes } = this.props.routeAction;
		return(
			<div>
				<RouteList getRoutes={ getRoutes } routes={ routes.routes }/>
			</div>
		)
	}
}

function mapStateToProps (state) {
  return {
    routes: state.routes
  };
}

function mapDispatchToProps(dispatch) {
  return {
    routeAction: bindActionCreators(routeAction,dispatch)
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(RoutePage);