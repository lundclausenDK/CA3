import React from "react"
import {Route, Switch} from "react-router-dom"
import Login from "./Login";
import Logout from "./Logout";
import About from "./About";
import AdminPage from "./AdminPage";
import TopMenu from "./TopMenu";
import Places from "./Places";
import Signup from "./Signup";
import AddPlace from "./AddPlace";
import AddSummerhouse from "./AddSummerhouse";
import Map from "./Map";
import SummerhouseList from "./SummerhouseList";
import SummerhouseDetail from "./SummerhouseDetail";

function App() {
    return (
        <div>
            <TopMenu/>
            <Switch>
                <Route path="/login" component={Login}/>
                <Route path="/logout" component={Logout}/>

                <Route path="/about" component={About}/>
                <Route path="/admin" component={AdminPage}/>

                <Route path="/add-new-place" component={AddPlace}/>
                <Route path="/places" component={Places}/>

                <Route path="/add-new-summerhouse" component={AddSummerhouse}/>
                <Route path="/summerhouses" component={SummerhouseList}/>

                <Route path="/house/:summerHouseId" component={SummerhouseDetail}/>

                <Route path="/signup" component={Signup}/>
            </Switch>
        </div>

    )
}

export default App;