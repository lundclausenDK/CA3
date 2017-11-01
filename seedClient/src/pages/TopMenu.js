import React, { Component } from 'react'
import { Link } from "react-router-dom";
import auth from '../authorization/auth'

class TopMenu extends Component {

  constructor(props) {
    super(props);
    this.state = { loggedIn: auth.loggedIn, userName: auth.userName, isUser: false, isAdmin: false }
  }

  loginStatus = (status, userName, isUser, isAdmin) => {
    this.setState({ loggedIn: status, userName, isUser, isAdmin });
  }

  componentDidMount() {
    auth.setLoginObserver(this.loginStatus);
  }

  render() {

    const logInStatus = this.state.loggedIn ? "Logged in as: " + this.state.userName : "";

    return (
      <div>
        <nav className="navbar navbar-default" >
          <div className="container-fluid">
            <div className="navbar-header">
              <a className="navbar-brand" href="/" style={{ pointerEvents: "none" }}></a>
            </div>
            <ul className="nav navbar-nav">
              <li><Link to="">Home</Link></li>
              <li><Link to="/about">About</Link></li>
              <li><Link to="/user">Page for Users </Link></li>
              <li><Link to="/admin">Page for Admins</Link></li>
              <li><Link to="/places">Places</Link></li>
              {this.state.isAdmin && <li><Link to="/admin">Page for Admins</Link></li>}
            </ul>
            <ul className="nav navbar-nav navbar-right">
              <li className="navbar-text" style={{ color: "steelBlue" }}>{logInStatus}</li>
              <li>
                {this.state.loggedIn ?
                  (
                    <Link to="/logout"><span className="glyphicon glyphicon-log-out"></span> Logout</Link>
                  ) :
                  (
                    <li>
                      <Link to="/login">
                        <span className="glyphicon glyphicon-log-in"></span> Login </Link><br></br>
                      <Link to="/signup">
                        <span className="glyphicon glyphicon-signin"></span> Signup </Link>
                    </li>
                  )}
              </li>
            </ul>
          </div>
        </nav>

      </div>
    )
  }
}


export default TopMenu;