import React, { useState, useEffect } from 'react';
import Facade from './login/ApiFacade';
import { BrowserRouter as Router, Switch, Route, NavLink } from "react-router-dom";
import './style/App.css';
import LoginForm from './login/LoginForm';
import URLSettings from './settings'

function App() {


  return (
    <div className="App">
      <Router>
        <Header />
        <Switch>
          <Route exact path={URLSettings.getURL("Home")}> <Welcome /> </Route>
          <Route path={URLSettings.getURL("Login")}> <LoginForm /> </Route>
          <Route path={URLSettings.getURL("Teacher")}> <LoginForm /> </Route>
          <Route path={URLSettings.getURL("NoMatch")}> <NoMatch /> </Route>
        </Switch>
        <Footer />
      </Router>
    </div>
  )
}

const Header = () => {
  return (
    <ul className="header">
      <li><NavLink activeClassName="active" exact to={URLSettings.getURL("Home")}>Home</NavLink></li>
      <li><NavLink activeClassName="active" to={URLSettings.getURL("Login")}>Student Login</NavLink></li>
      <li><NavLink activeClassName="active" to={URLSettings.getURL("Teacher")}>Teacher Login</NavLink></li>
    </ul>
  )
}

const NoMatch = () => <div>No match!</div>

const Footer = () => {
  return (
    <footer>
      <div className="d-flex justify-content-center align-items-center">
        <span> © Copyright 2019 - Martin Frederiksen, Andreas Vikke, Emil Svensmark, Asger Sørensen, & William Huusfeldt. </span>
      </div>
    </footer>
  )
}


//If Welcome function reaches about 10 lines of code place the function in separate file.
function Welcome() {
  const [data, setData] = useState([]);

  useEffect(() => {
    Facade.fetchData1().then(res => setData(res));
  }, [])

  return (
    <div className="container">
      <h3>All Courses</h3>
      <table className="table">
        <thead className="thead-dark">
          <tr>
            <th scope="col">Course Name</th>
            <th scope="col">Description</th>
            <th scope="col">Amount of classes</th>
          </tr>
        </thead>
        <tbody>
          {data.map((course, index) => <tr key={index}><td>{course.courseName}</td><td>{course.description}</td><td>{course.amountOfClasses}</td></tr>)}
        </tbody>
      </table>
    </div>
  )
}

export default App;