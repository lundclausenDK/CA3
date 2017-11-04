import React from "react";
import fetchHelper from "../facades/fetchHelpers";

const URL = require("../../package.json").serverURL;

export default class AddPlace extends React.Component {

    constructor() {
        super();

        this.state = {
            name: null,
            street: null,
            city: null,
            zip: null,
            desc: null,
        }

        this.addData = this.addData.bind(this);
    }

    returnValue = (e) => {
        let id = e.target.id;
        let value = e.target.value;

        this.setState({ [id]: value });
        e.preventDefault();
    };

    addData = () => {

        const options = fetchHelper.makeOptions("POST", true, {
            name: this.state.name,
            street: this.state.street,
            city: this.state.city,
            zip: this.state.zip,
            desc: this.state.description
        });

        fetch(URL + "api/upload/place", options);
    };

    pushPlaceToServer = () => {

        /*
        const place = { name: this.state.name, street: this.state.street, city: this.state.city, zip: this.state.zip, desc: this.state.desc, file: this.state.file };
        const options = fetchHelper.makeOptions("POST", true, place);

        fetch(URL + "api/upload/placeUpload", options);
        */

        var input = document.querySelector('input[type="file"]');
        var data = new FormData();
        
        data.append("name", this.state.name);
        data.append("street", this.state.street);
        data.append("city", this.state.city);
        data.append("zip", this.state.zip);
        data.append("desc", this.state.desc);

        data.append('file', input.files[0]);
        fetch(URL + 'api/upload/placeUpload', {
            method: 'POST',
            body: data, headers: {'Authorization': sessionStorage.token}
        });
    }

    render() {
        return (
            <div>
                <h2>Add new place</h2>
                <form className="ca3">
                    <input placeholder="Type name here" value={this.state.name} onChange={this.returnValue} id="name" />
                    <input placeholder="Type street here" value={this.state.street} onChange={this.returnValue} id="street" />
                    <input placeholder="Type city here" value={this.state.city} onChange={this.returnValue} id="city" />
                    <input type="number" placeholder="Type zip here" value={this.state.zip} onChange={this.returnValue} id="zip" />
                    <textarea placeholder="Please type description" value={this.state.description} onChange={this.returnValue} id="desc" />
                    <label>Select File</label><input type="file" id="file" /> <br /><br />
                    <button onClick={this.pushPlaceToServer}>Submit</button>
                </form>
            </div>
        );

    }


}