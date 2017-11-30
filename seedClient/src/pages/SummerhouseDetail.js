import React from 'react';

const URL = require("../../package.json").serverURL;

export default class SummerhouseDetail extends React.Component {

    constructor(props) {
        super();
        this.state = {
            title: "",
            description: "",
            city: "",
            address: "",
            price: "",
            geo: "",
            picture: "",
        };
        this.getDetails(props.match.params.summerHouseId);
    }

    getDetails = (id) => {
        fetch(URL + "/api/summerhouses/" + id, {
            method: "GET"
        }).then((response) => {
            return response.json();
        }).then((data) => {
            this.setState({
                title: data.title,
                description: data.description,
                city: data.city,
                address: data.address,
                price: data.price,
                geo: data.geo,
                picture: data.picture
            });
        });
    };

    render() {
        const {title, description, city, address, price, geo, picture} = this.state;
        return (
            <SummerhouseDetails title={title} discription={description} city={city} address={address} price={price}
                               picture={picture} geo={geo}/>
        );
    }
}

const SummerhouseDetails = (props) => {
    return (
        <div className="house-detail">
            <div className="house-detail-photo">
                <img src={props.picture} alt=""/>
            </div>
            <div className="house-detail-info">
                <h1 className="house-title">
                    {props.title}
                </h1>
                <p className="house-description">
                    {props.description}
                </p>
                <p className="house-city">
                    City : {props.city}
                </p>
                <p className="house-address">
                    Address : {props.address}
                </p>
                {props.geo && <p>Geo: {props.geo}</p>}
                <p className="house-price">
                    Price : ${props.price}
                </p>
                <button className="house-booking">
                    Book Now
                </button>
            </div>
        </div>
    );
};