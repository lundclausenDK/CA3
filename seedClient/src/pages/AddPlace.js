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
            description: null
        }

        this.addData = this.addData.bind(this);
    }

    returnValue = (e) => {
        let id = e.target.id;
        let value = e.target.value;

        this.setState({[id]: value});
        e.preventDefault();
    };

    addData = () => {
        /*
        fetch(URL + "api/upload/place", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + sessionStorage.token
            },
            body: JSON.stringify({
                name: this.state.name,
                street: this.state.street,
                city: this.state.city,
                zip: this.state.zip,
                description: this.state.description
            })
        })
        */

        const options = fetchHelper.makeOptions("POST", true, {
            name: this.state.name,
            street: this.state.street,
            city: this.state.city,
            zip: this.state.zip,
            description: this.state.description
        });

        fetch(URL + "api/upload/place", options);
    };

    render() {
        return (
            <div>
                <h2>Add new place</h2>
                <form className="ca3">
                    <input placeholder="Type name here" value={this.state.name} onChange={this.returnValue} id="name"/>
                    <input placeholder="Type street here" value={this.state.street} onChange={this.returnValue} id="street"/>
                    <input placeholder="Type city here" value={this.state.city} onChange={this.returnValue} id="city"/>
                    <input placeholder="Type zip here" value={this.state.zip} onChange={this.returnValue} id="zip"/>
                    <textarea placeholder="Please type description" value={this.state.description} onChange={this.returnValue} id="description"/>
                    <button onClick={this.addData}>Submit</button>
                </form>
            </div>
        );

    }


}