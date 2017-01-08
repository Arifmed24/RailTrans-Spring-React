import React from 'react';
import { Route, IndexRoute }  from 'react-router';
import App from 'components/App';
import HelloWorldPage from 'components/HelloWorldPage';
import StationPage from 'components/StationPage';
import TrainPage from 'components/TrainPage';
import RoutePage from 'components/RoutePage';
import TicketPage from 'components/TicketPage';
import PassengerOfTicketPage from 'components/PassengerOfTicketPage';

export default (
  <Route component={App} path='/'>
    <IndexRoute component={HelloWorldPage} />
    <Route component={StationPage} path='station' />
    <Route component={TrainPage} path='train' />
    <Route component={RoutePage} path="route" />
    <Route component={TicketPage} path="ticket"/>
	<Route component={PassengerOfTicketPage}  path="ticket/passenger" handler={PassengerOfTicketPage}/>
  </Route>
);