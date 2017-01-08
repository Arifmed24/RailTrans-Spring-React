import axios from 'axios';
import {
	GET_VARIANTS,
	URL_GET_VARIANTS,
	URL_BUY_TICKET
} from '../constants/ticketConstants';

export function getVariants(stationDep, stationArr, dDep, dArr, search){
	return function(dispatch) {
		axios.post(URL_GET_VARIANTS, {
			stationDep: stationDep,
			stationArr: stationArr,
			dDep: dDep,
			dArr: dArr,
			search: search
		})
    	.then(response => {
      		dispatch({
        		type: GET_VARIANTS,
        		payload: response.data
      		});
    	})
		.catch(function (error) {
    		console.log(error);
      	})
    }
}

export function buyTicket(first, last, birth, requestTicket, way){
	return function(dispatch) {
		var contentType = 'application/json; charset=utf-8';
		var user = {
					idUser: '1',
					login: 'admin',
					password: 'qwerty',
					role: 'ADMIN',
					fio: 'Arif Balaev'
				};
		var test = {};
		test['first'] = first;
		test['last'] = last;
		test['birth'] = birth;
		test['requestTicket'] = requestTicket;
		test['way'] = way;
		test['user'] = user;
		var json = JSON.stringify(test);
		axios({
			method: 'post',
			url: URL_BUY_TICKET,
			data: json,
			headers: {
			'content-type': contentType
			},
			processData:false
		})
		.then(function (response) {
    		console.log(response);
  		})
  		.catch(function (error) {
    		console.log(error);
      	});
	}
}