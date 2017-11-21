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
<<<<<<< HEAD
import Map from "./Map";
=======
import SummerhouseList from "./SummerhouseList";
>>>>>>> 48b64e66dc5c37d8d15b827bfe3c2b45fd45af5e

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
<<<<<<< HEAD
                <Route path="/map" component={Map} />
=======
                <Route path="/summerhouses" component={SummerhouseList}/>
>>>>>>> 48b64e66dc5c37d8d15b827bfe3c2b45fd45af5e
                <Route path="/signup" component={Signup}/>
            </Switch>
        </div>

    )
}

export default App;