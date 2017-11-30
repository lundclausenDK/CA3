import React from "react";
import fetchHelper from "../facades/fetchHelpers";
import auth from '../authorization/auth';

const URL = require("../../package.json").serverURL;

export default class AddPlace extends React.Component {

    constructor() {
        super();

        this.state = {
            title: null,
            address: null,
            city: null,
            zip: null,
            desc: null
        }

        auth.initDataFromToken();
        if (auth.isloggedIn) {
            this.state.userName = auth.userName;
        }
    }

    returnValue = (e) => {
        let id = e.target.id;
        let value = e.target.value;

        this.setState({ [id]: value });
        e.preventDefault();
    };

    pushSummerhouseToServer = () => {

        /*
        const place = { name: this.state.name, street: this.state.street, city: this.state.city, zip: this.state.zip, desc: this.state.desc, file: this.state.file };
        const options = fetchHelper.makeOptions("POST", true, place);

        fetch(URL + "api/upload/placeUpload", options);
        */

        var input = document.querySelector('input[type="file"]');
        var data = new FormData();

        data.append("name", this.state.title);
        data.append("street", this.state.address);
        data.append("city", this.state.city);
        data.append("zip", this.state.zip);
        data.append("desc", this.state.description);
        data.append("price", this.state.price);
        //data.append("userName", this.state.userName);

        data.append('file', input.files[0]);
        fetch(URL + 'api/summerhouses/add_home', {
            method: 'POST',
            body: data, headers: {'Authorization': `Bearer ${sessionStorage.token}`}
        });
    }

    render() {
        return (
            <div>
                <h2>Add new summerhouse</h2>
                <form className="ca3">
                    <input placeholder="Type name here" value={this.state.title} onChange={this.returnValue} id="title" />
                    <input placeholder="Type street here" value={this.state.address} onChange={this.returnValue} id="address" />
                    <input placeholder="Type city here" value={this.state.city} onChange={this.returnValue} id="city" />
                    <input type="number" placeholder="Type zip here" value={this.state.zip} onChange={this.returnValue} id="zip" />
                    <input type="number" placeholder="Type price here" value={this.state.zip} onChange={this.returnValue} id="price" />
                    <textarea placeholder="Please type description" value={this.state.description} onChange={this.returnValue} id="description" />
                    <label>Select File</label><input type="file" id="file" /> <br /><br />
                    <button onClick={this.pushSummerhouseToServer}>Submit</button>
                </form>
            </div>
        );

    }


}