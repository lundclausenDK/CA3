import React, { Component } from 'react'
import adminData from "../facades/adminFacade";
import auth from '../authorization/auth';
import fetchHelper, { errorChecker } from "../facades/fetchHelpers";

const URL = require("../../package.json").serverURL;

class AdminPage extends Component {

    constructor() {
        super();
        this.state = { data: [], err: "", roles: [], edit_mode: "add", user_being_edited: null };
    }

    componentWillMount() {
        /*
        This will fetch data each time you navigate to this route
        If only required once, add "logic" to determine when data should be "refetched"
        */
        
        adminData.getData((e, data) => {
            console.log(data);
            if (e) {
                return this.setState({ err: e.err })
            }
            this.setState({ err: "", data });

        });

        this.fetchRoles();
    }

    fetchRoles = () => {
        const options = fetchHelper.makeOptions("GET", true);

        fetch(URL + "api/user_control/list_roles", options)
            .then((response) => response.json())
            .then((json) => { this.setState({ roles: json }) });
    }

    deleteUser = (username) => {
        const options = fetchHelper.makeOptions("POST", true, { username: username });
        const index = this.state.data.indexOf(username);

        index > -1 && this.state.data.splice(index, 1);

        fetch(URL + "api/user_control/delete", options)
            .then((response) => { this.setState({ err: response.status }) });
    }

    addUser = () => {
        const username = document.getElementById("username_input").value;
        const password = document.getElementById("password_input").value;
        const startRole = document.getElementById("role_select").value;
        const roles = [];
        roles.push(startRole);

        const newUser = {username: username, password: password, roles: roles};
        const options = fetchHelper.makeOptions("POST", true, newUser);

        fetch(URL + "api/user_control/add", options)
            .then((response) => { this.setState({ err: response.status }); 
                response.status == 200 && this.state.data.push(username); this.setState({data: this.state.data})});
    }

    editUser = () => {
        this.changeToAddMode();

        const roles = [];
        const select = (document.getElementById("role_select"));
        roles.push(select.options[select.selectedIndex].text);
        this.state.user_being_edited.roles = roles;

        const options = fetchHelper.makeOptions("PUT", true, this.state.user_being_edited);
        this.setState({user_being_edited: null});

        fetch(URL + "api/user_control/edit", options)
            .then((response) => { this.setState({ err: response.status }) });
    }

    changeToEditMode = (user) => {
        this.setState({edit_mode: "edit", user_being_edited: user});
        document.getElementById("username_input").value = user.username;
        document.getElementById("roles_p").innerText = this.listRoles(user);
    }

    changeToAddMode = () => {
        this.setState({edit_mode: "add"});
        document.getElementById("username_input").value = "";
        document.getElementById("roles_p").innerText = "";
    }

    listRoles = (user) => {
        let roles = "Roles: ";
        for (let i = 0; i < user.roles.length; i++) {
            roles += user.roles[i];

            if (i + 1 < user.roles.length) {
                roles += ", ";
            }
        }

        return roles;
    } 

    render() {
        return (
            <div>
                <h2>Admin Panel</h2>
                <div>
                    <span> Username: </span><input type="text" id="username_input" /><span> Password: </span><input type="password" id="password_input" />
                    <span> Roles: </span><select id="role_select"> {this.state.roles.map((ele) => { return (<option value={ele}>{ele}</option>) })} </select>
                    {this.state.edit_mode === "add" && <button onClick={this.addUser}>Add User</button>}
                    {this.state.edit_mode === "edit" && <button onClick={this.editUser}>Edit User</button>}
                    <p id="roles_p"></p>
                </div>
                <br />
                <div>
                    {this.state.data.map((user) => {
                        return (<div><div>{user.username} | {this.listRoles(user)}</div>
                            <span>
                                <button onClick={() => this.changeToEditMode(user)}>Edit</button> 
                                <button onClick={() => this.deleteUser(user.username)}>Delete</button>
                            </span></div>)
                    })}
                </div>
                {this.state.err && (
                    <div className="alert alert-danger errmsg-left" role="alert">
                        {this.state.err}
                    </div>
                )}
            </div>
        )
    }
}

export default AdminPage;