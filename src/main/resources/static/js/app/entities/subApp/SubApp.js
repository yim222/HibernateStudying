import React, { Component } from 'react';
//import logo from './logo.svg';
//import './SubApp.css';
import {MainView} from './app/views/MainView.js';
import {BrowserRouter as Router} from 'react-router-dom';


class SubApp extends Component {
	render() {
	    return (

	      <div>
	      	<h1>I am Sub-APP</h1>
	        <Router>
	          <div>
	            <MainView/>

	          </div>
	        </Router>

	      </div>
	    );
	  }
}

export default SubApp;
