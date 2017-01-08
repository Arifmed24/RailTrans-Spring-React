import { GET_TRAINS_REQUEST } from '../constants/trainConstants';

const initialState = {
	trains: []
}

export default function trainReducer (state = initialState, action) {
	switch (action.type) {
		case GET_TRAINS_REQUEST:
		return {...state, trains: action.payload}

		default:
		return state;
	}
}