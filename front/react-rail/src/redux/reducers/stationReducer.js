import { GET_STATIONS_REQUEST, CREATE_STATION } from '../constants/stationConstants';

const initialState = {
  stations: [],
  station: {
  	idStation: '',
  	stationName: ''
  }
};

export default function stationReducer (state = initialState, action) {
  switch (action.type) {
    case GET_STATIONS_REQUEST:
	return {...state, stations: action.payload};

	case CREATE_STATION:
	return {...state, station: action.payload};

    default:
	return state;
  }
}