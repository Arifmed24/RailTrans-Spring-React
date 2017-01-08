import { GET_ROUTE_LIST } from '../constants/routeConstants';

const initialState = {
	routes: []
};

export default function routeReducer (state = initialState, action) {
	switch (action.type) {
		case GET_ROUTE_LIST:
			return {...state, routes: action.payload};

		default:
			return state;
	}
}