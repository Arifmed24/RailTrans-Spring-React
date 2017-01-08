import axios from 'axios'; 
import { GET_STATIONS_REQUEST, 
  URL_GET_STATIONS,
  CREATE_STATION,
  URL_CREATE_STATION } from '../constants/stationConstants';

export function getStations() {
  return function(dispatch) {
    axios.get(URL_GET_STATIONS)
    .then(response => {
      dispatch({
        type: GET_STATIONS_REQUEST,
        payload: response.data
      });
    })
    .catch((error) => {
      console.log(error);
    })
  }
}

export function createStation(stationName) {
    return function(dispatch) {
      axios.post(URL_CREATE_STATION, { 
        idStation: '',
        stationName: stationName })
      .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
     console.log(stationName);
      });
    }
}


  