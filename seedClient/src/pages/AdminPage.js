import React, {Component} from 'react'
import adminData from "../facades/adminFacade";
import auth from '../authorization/auth';
import fetchHelper, {errorChecker} from "../facades/fetchHelpers";

const URL = require("../../package.json").serverURL;

class AdminPage extends Component {

    constructor() {
        super();
        this.state = {data: [], err: "", roles: []}
    }

    componentWillMount() {
        /*
        This will fetch data each time you navigate to this route
        If only required once, add "logic" to determine when data should be "refetched"
        */
        adminData.getData((e, data) => {
            console.log(data);
            if (e) {
                return this.setState({err: e.err})
            }
            this.setState({err: "", data});

        });
    }

    deleteUser = (username) => {
        const options = fetchHelper.makeOptions("POST", true, {username: username});
        const index = this.state.data.indexOf(username);
        
        index > -1 && this.state.data.splice(index, 1);

        fetch(URL + "api/user_control/delete", options)
            .then((response) => { this.setState({err: response.status}) });
    }

    addUser = () => {

    }

    render() {
        return (
            <div>
                <h2>Admin Panel</h2>
                <div>
                    <span> Username: </span><input type="text"/><span> Password: </span><input type="text"/><span> Roles: </span><select> {} </select>
                </div>
                    <br/>
                <div>
                    {this.state.data.map((item) => {
                        return(<div>{item} <button>Edit</button> <button onClick={ () => {this.deleteUser(item)} }>Delete</button></div>)
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