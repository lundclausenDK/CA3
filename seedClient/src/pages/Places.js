import React from 'react';

//const URL = require("http://localhost:8080/seedMaven/").serverURL;

export default class Place extends React.Component {
    constructor() {
        super();
        this.state = {
            places: [],
            view: []
        };
        this.getData = this.getData.bind(this);
        this.getData();
    }

    getData = () => {
        fetch("http://localhost:8080/seedMaven/api/places/")
            .then(function (response) {
                return response.json()
            }).then(function (data) {
            console.log(data);
            this.setState({places: data, view: data});
        }.bind(this));

    };

    getSearch = (e) => {
        const name = document.getElementById("searchText").value;
        let viewList = this.state.places.filter((place)=>{
            if(place.name.toLocaleLowerCase().includes(name.toLocaleLowerCase())){
                return place;
            }
        });
        this.setState({view: viewList});
        e.preventDefault();
    };

    sortOnName = (e) => {
        let sorted = this.state.view.sort((a, b) => {
            if (a.name < b.name) {
                return -1;
            } else if (b.name.toLocaleLowerCase() < a.name.toLocaleLowerCase()) {
                return 1;
            }
        });
        this.setState({view: sorted});
        e.preventDefault();
    };

    sortOnRating = (e) => {
        let sorted = this.state.view.sort((a, b) => {
            if (a.rating < b.rating) return -1;
            if (a.rating > b.rating) return 1;
            return 0;
        });
        this.setState({view: sorted});
        e.preventDefault();
    };

    render() {
        return (
            <div>

                <h2>Places</h2>
                <div className="tools-container">
                    <form>
                        <input id="searchText" type="text" placeholder="Type the name here"/>
                        <button onClick={this.getSearch}>submit</button> - <button onClick={this.sortOnName}>Sort on Name</button> - <button onClick={this.sortOnRating}>Sort on Rating</button>
                    </form>
                </div>

                {this.state.view.map((item) => (
                    <div className="places-container clearfix">

                        <div className="left image"><img src={item.url}/></div>
                        <div className="bold">{item.name}</div>
                        <div>{item.description}</div>
                        <div>{item.street}</div>
                        <div>{item.zip} {item.city}</div>
                        <div>Rating: {item.rating}</div>


                    </div>

                ))}

            </div>


        )
    }
}