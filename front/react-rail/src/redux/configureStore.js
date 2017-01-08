import { applyMiddleware, combineReducers, createStore } from 'redux';
import thunk from 'redux-thunk';
import stationReducer from './reducers/stationReducer';
import trainReducer from './reducers/trainReducer';
import routeReducer from './reducers/routeReducer';
import ticketReducer from './reducers/ticketReducer';

export default function (initialState = {}) {
  const rootReducer = combineReducers({
    stations: stationReducer,
    trains: trainReducer,
    routes: routeReducer,
    variants: ticketReducer
  });

  return createStore(rootReducer, initialState, applyMiddleware(thunk));
}
