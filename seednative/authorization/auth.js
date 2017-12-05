import jwtDecode from "jwt-decode";
import {AsyncStorage} from 'react-native';
import fetchHelper, {errorChecker} from "../facades/fetchHelpers"

const URL = require("../package.json").serverURL;

class AuthenticationHandler {

    constructor() {
        this._token = null;  //Keps users logged in, even after a refresh of the page
        //this.failedLogin = false;
        this._userName = "";
        this._isAdmin = false;
        this._isUser = false;
        this._errorMessage = "";
    }

    get isloggedIn() {
        return this._token !== null;
    }

    get isUser() {
        return this._isUser;
    }

    get isAdmin() {
        return this._isAdmin;
    }

    get userName() {
        return this._userName;
    }

    setLoginObserver = (observer) => {
        this._loginObserver = observer;
    }

    setToken = async (value, cb2) => {
        await AsyncStorage.setItem('MyAppToken', value);
        cb2();
    }

    initDataFromToken = (cb) => {
        AsyncStorage.getItem('MyAppToken').then((value) => {
            console.log(value);
            if (value === null) {
                cb(null);
                return;
            }
            console.log("Initializing Data From Token");
            this._token = value;
            let decoded = jwtDecode(this._token);
            this._userName = decoded.username;
            this._isAdmin = false;
            this._isUser = false;
            decoded.roles.forEach(function (role) {
                if (role === "Admin") {
                    this._isAdmin = true;
                }
                if (role === "User") {
                    this._isUser = true;
                }
            }, this);
            if (cb !== null) {
                cb(this.userName);
            }
        });
    }

    logout = (cb) => {
        try {
            AsyncStorage.removeItem('MyAppToken').then(() => {
                this._token = null;
                this._userName = "";
                this._isAdmin = false;
                this._isUser = false;
                this._errorMessage = "";
                if (this._loginObserver) {
                    this._loginObserver(false, this._userName, this._isUser, this._isAdmin);
                }
                cb();
            });
        } catch (e) {

        }
    }

    _userWasLoggenIn = (cb) => {
        if (cb) {
            cb(null, true);
            if (this._loginObserver) {
                this._loginObserver(true, this._userName, this._isUser, this._isAdmin);
            }
            return;
        }
    }

    register = (username, password, cb) => {
        this._errorMessage = "";
        if (this._token != null) {
            this._userWasLoggenIn(cb);
        }

        let roles = ["User"];
        let user = {username, password, roles};

        let options = {
            method: "POST",
            body: JSON.stringify(user),
            headers: new Headers({
                'Content-Type': 'application/json'
            })
        }
        let resFromFirstPromise = null;
        fetch(URL + "api/register", options)
            .then(res => {
                resFromFirstPromise = res;
                return res.json();
            })
            .then(data => {
                errorChecker(resFromFirstPromise, data);
                this.setToken(data.token);
                if (this._token != null) {
                    this._userWasLoggenIn(cb);
                }
            })
            .catch(err => {
                console.log(err);
                if (cb) {
                    cb({errorMessage: fetchHelper.addJustErrorMessage(err)});
                }
            })
        return;
    }

    login = (username, password, cb, cb2) => {
        this._errorMessage = "";
        if (this._token != null) {
            this._userWasLoggenIn(cb);
        }

        let user = {username, password};

        let options = {
            method: "POST",
            body: JSON.stringify(user),
            headers: new Headers({
                'Content-Type': 'application/json'
            })
        }

        let resFromFirstPromise = null;  //Pass on response the "second" promise so we can read errors from server
        fetch(URL + "api/login", options)
            .then(res => {
                resFromFirstPromise = res;
                return res.json();
            })
            .then(data => {
                errorChecker(resFromFirstPromise, data);
                this.setToken(data.token, cb2);
                if (this._token != null) {
                    this._userWasLoggenIn(cb);
                }
            })
            .catch(err => {
                console.log(err);
                if (cb) {
                    cb({errorMessage: fetchHelper.addJustErrorMessage(err)});
                }
            })
        return;
    }
}

let auth = new AuthenticationHandler();

//Call init, if a new Instance was created due to a refresh (F5 or similar)
//auth.initDataFromToken();

//Comment out for debugging
//window.auth = auth;

export default auth;


