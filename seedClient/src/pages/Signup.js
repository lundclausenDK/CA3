import React from 'react'
import auth from "../authorization/auth";

export default class Signup extends React.Component {

    constructor() {
        super();
        this.state = { err: "", user: { username: "", password: "" } };
        this.handleSignIn = this.handleSignIn.bind(this)
    }

    onChange = (e) => {
        const propertyName = e.target.id;
        const value = e.target.value;
        let user = this.state.user;
        user[propertyName] = value;
        this.setState({ user });
        console.log(this.state)
    }
    handleSignIn(e) {
        e.preventDefault()

        const user = this.state.user.username;
        const pass = this.state.user.password;
        auth.register(user, pass, (err, loggedIn) => {
            if (err) {
                return this.setState({ err: err.errorMessage });
            }
            this.setState({ err: "" });
            this.props.history.push("/");
        });



    }

    render() {

        return (
            <div className="container">
                <form className="form-signin" onSubmit={this.handleSignIn}>
                    <h2 className="form-signin-heading">Signup</h2>
                    <label htmlFor="inputEmail" className="sr-only">Email address</label>
                    <input type="text" value={this.state.user.username} onChange={this.onChange} className="form-control" id="username" placeholder="User Name" required autoFocus />
                    <label htmlFor="inputPassword" className="sr-only">Password</label>
                    <input type="password" value={this.state.user.password} onChange={this.onChange} id="password" className="form-control" placeholder="Password" required />
                    <button className="btn btn-lg btn-primary btn-block" type="submit">Register</button>
                    <br />
                </form>
                {this.state.err && (
                    <div className="alert alert-danger errmsg" role="alert">
                        {this.state.err}
                    </div>
                )}
            </div>
        )
    }

}