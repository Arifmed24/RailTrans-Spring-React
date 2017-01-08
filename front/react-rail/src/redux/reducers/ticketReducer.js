import {
	GET_VARIANTS
} from '../constants/ticketConstants';

const initialState = {
	variants: []
};

export default function ticketReducer (state = initialState, action) {
	switch (action.type) {
		case GET_VARIANTS:
			return {...state, variants: action.payload};

		default:
			return state;
	}
}