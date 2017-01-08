import axios from 'axios';
import { GET_ROUTE_LIST, 
 URL_GET_ROUTE_LIST } from '../constants/routeConstants';

 export function getRoutes() {
 	return function(dispatch) {
 		axios.get(URL_GET_ROUTE_LIST)
    	.then(response => {
      	dispatch({
        	type: GET_ROUTE_LIST,
        	payload: response.data
	    });
	    })
	    .catch((error) => {
	    	console.log(error);
	    })
 	}
 }