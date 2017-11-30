import React, {Component} from 'react'
import {Link} from "react-router-dom";
import auth from '../authorization/auth'

class TopMenu extends Component {

    constructor(props) {
        super(props);
        this.state = {loggedIn: auth.isloggedIn, userName: auth.userName, isUser: auth.isUser, isAdmin: auth.isAdmin}
    }

    loginStatus = (status, userName, isUser, isAdmin) => {
        this.setState({loggedIn: status, userName, isUser, isAdmin});
    }

    componentDidMount() {
        auth.setLoginObserver(this.loginStatus);
    }

    render() {

        const logInStatus = this.state.loggedIn ? "Logged in as: " + this.state.userName : "";

        return (
            <div>
                <img src="/redfox.png" className="headerIcon"/><h1 className="headerFont"><span>RedFox</span> - Invest n' rent</h1>

                <nav className="navbar navbar-default">
                    <div className="container-fluid">
                        <div className="navbar-header">
                            <a className="navbar-brand" href="/" style={{pointerEvents: "none"}}></a>
                        </div>
                        <ul className="nav navbar-nav">
                            <li><Link to="">Home</Link></li>
                            <li><Link to="/about">About</Link></li>
                            {this.state.isUser && <li><Link to="/add-new-place">Add new place</Link></li>}

                            <li><Link to="/places">Places</Link></li>
                            <li><Link to="/summerhouses">Summerhouses</Link></li>

                            {this.state.isAdmin && <li><Link to="/admin">Page for Admins</Link></li>}
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            <li className="navbar-text" style={{color: "steelBlue"}}>{logInStatus}</li>
                            <li>
                                {this.state.loggedIn ?
                                    (
                                        <Link to="/logout"><span className="glyphicon glyphicon-log-out"></span> Logout</Link>
                                    ) :
                                    (
                                        <li>
                                            <Link to="/login">
                                                <span className="glyphicon glyphicon-log-in"></span> Login</Link> |
                                            <Link to="/signup"><span className="glyphicon glyphicon-signin"></span>Signup</Link>&nbsp;&nbsp;
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