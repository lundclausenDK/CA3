import React, {Component} from 'react'
import adminData from "../facades/adminFacade";
import auth from '../authorization/auth'

class AdminPage extends Component {

    constructor() {
        super();
        this.state = {data: [], err: ""}
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



    render() {
        return (
            <div>
                <h2>Admin Panel</h2>
                <div>
                    {this.state.data.map((item) => {
                        return(<div>{item} <button>Edit</button> <button>Delete</button></div>)
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