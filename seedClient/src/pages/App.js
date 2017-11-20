import React from "react"
import {Route, Switch} from "react-router-dom"
import Login from "./Login";
import Logout from "./Logout";
import About from "./About";
import UserPage from "./UserPage";
import AdminPage from "./AdminPage";
import TopMenu from "./TopMenu";
import Places from "./Places";
import Signup from "./Signup";
import AddPlace from "./AddPlace";
import SummerhouseList from "./SummerhouseList";

function App() {
    return (
        <div>
            <TopMenu/>
            <Switch>
                <Route path="/login" component={Login}/>
                <Route path="/logout" component={Logout}/>
                <Route path="/about" component={About}/>
                <Route path="/user" component={UserPage}/>
                <Route path="/admin" component={AdminPage}/>
                <Route path="/add-new-place" component={AddPlace}/>
                <Route path="/places" component={Places}/>
                <Route path="/summerhouses" component={SummerhouseList}/>
                <Route path="/signup" component={Signup}/>
            </Switch>
        </div>

    )
}

export default App;