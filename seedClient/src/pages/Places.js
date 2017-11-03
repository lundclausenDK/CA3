import React from 'react'
import auth from '../authorization/auth'

const URL = require("../../package.json").serverURL;

export default class Place extends React.Component {
    constructor() {
        super();
        this.state = {
            places: [],
            view: [],
            ratingSort: true,
            rated: false,
            userName: auth.userName
        };

        this.getData = this.getData.bind(this);
        this.getData();
    }

    getData = () => {
        fetch(URL + "api/places/")
            .then(function (response) {
                return response.json()
            }).then(function (data) {
            //console.log(data);
            for(let i = 0; i < data.length; i++){
                for(let y = 0; y < data[i].raters.length; y++){
                    if(data[i].raters[y].user.includes(this.state.userName.toLocaleLowerCase())){
                        data[i].rated = true;
                    }
                }
            }
            this.setState({places: data, view: data});
        }.bind(this));

    };

    getSearch = (e) => {
        const name = document.getElementById("searchText").value;
        let viewList = this.state.places.filter((place) => {
            if (place.name.toLocaleLowerCase().includes(name.toLocaleLowerCase())) {
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
        let sorted;
        if (this.state.ratingSort) {
            sorted = this.state.view.sort((a, b) => {
                if (a.rating > b.rating) return -1;
                if (a.rating < b.rating) return 1;
                return 0;
            });
            this.setState({ratingSort: false});
        } else {
            sorted = this.state.view.sort((a, b) => {
                if (a.rating < b.rating) return -1;
                if (a.rating > b.rating) return 1;
                return 0;
            });
            this.setState({ratingSort: true});
        }

        this.setState({view: sorted});
        e.preventDefault();
    };

    submitRating = (e) => {
        console.log(e.target.value);

        fetch('https://mywebsite.com/endpoint/', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                ratingValue: e.target.value
            })
        });

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
                        <div>GEO: {item.geo}</div>

                        {item.rated?
                            (<div>{item.rating}</div>) :
                            (<form>
                                <select onChange={this.submitRating}>
                                    <option>Rate this place...</option>
                                    <option>5 (Most positive)</option>
                                    <option>4</option>
                                    <option>3</option>
                                    <option>2</option>
                                    <option>1 (Low of the lowest)</option>
                                </select>
                            </form>)}


                    </div>


                ))}

            </div>


        )
    }
}