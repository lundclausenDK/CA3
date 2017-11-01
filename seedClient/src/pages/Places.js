import React from 'react';

//const URL = require("http://localhost:8080/seedMaven/").serverURL;

export default class Place extends React.Component {
    constructor() {
        super();
        this.state = {places: []};
        this.getData = this.getData.bind(this);
        this.getData();
    }

    getData = () => {
        fetch("http://localhost:8080/seedMaven/api/places/")
            .then(function (response) {
                return response.json()
            }).then(function (data) {
            console.log(data);
            this.setState({places: data});
        }.bind(this));

    };

    render() {
        return (
            <div>

                <h2>Places</h2>

                {this.state.places.map((item) => (
                    <div>ID:{item.id}</div>
                ))}

            </div>


        )
    }
}