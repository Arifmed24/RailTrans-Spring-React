import axios from 'axios'; 
import { GET_TRAINS_REQUEST, URL_GET_TRAINS } from '../constants/trainConstants';

export function getTrains() {
	return function(dispatch) {
		axios.get(URL_GET_TRAINS)
		.then(response => {
			dispatch({
				type: GET_TRAINS_REQUEST,
				payload: response.data
			});
		})
		.catch((error) => {
			console.log(error);
		})
	}
}