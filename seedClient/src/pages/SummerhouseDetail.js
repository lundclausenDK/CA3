import React from 'react';
import { DateRangePicker, SingleDatePicker, DayPickerRangeController } from 'react-dates';
import 'react-dates/initialize';
import 'react-dates/lib/css/_datepicker.css';
import auth from '../authorization/auth';
import moment from 'moment';

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
            startDate: null,
            endDate: null,
            moments: []
        };

        auth.initDataFromToken();
        this.getDetails(props.match.params.summerHouseId);
    }

    prepareMoments = () => {
        this.state.bookings.forEach((booking)=>{
            let startDate = moment(booking.startDate);
            let endDate = moment(booking.endDate);

            const moments = [];
            while(startDate.format('DD') <= endDate.format('DD')){
                moments.push(moment(startDate));
                startDate.add(1, 'day');
            }
            this.setState({moments: moments});
        })
    };

    getDetails = (id) => {
        fetch(URL + "api/summerhouses/" + id, {
            method: "GET"
        }).then((response) => {
            return response.json();
        }).then((data) => {
            this.setState({
                id: data.id,
                title: data.title,
                description: data.description,
                city: data.city,
                address: data.address,
                price: data.price,
                geo: data.geo,
                picture: data.picture,
                bookings: data.bookings
            });
            this.prepareMoments();
        });
    };

    addRent = () =>{
        console.log(moment(moment(this.state.startDate, "milli").format("MM/DD/YYYY")));
        let start = moment(this.state.startDate, "milli").format("MM/DD/YYYY");
        let end = moment(this.state.endDate, "milli").format("MM/DD/YYYY");
        console.log("ping");
        fetch(URL + "api/summerhouses/rent/" + this.state.id, {
            method: "POST",
            body: JSON.stringify({userName: auth.userName, start: start, end: end}), headers: {'Authorization': `Bearer ${sessionStorage.token}`, "Content-type": "Application/json"}
        });
    };



    render() {
        const {title, description, city, address, price, geo, picture} = this.state;
        const isDayBlocked = day => this.state.moments.filter(d => d.isSame(day, 'day')).length > 0;
        return (
            <div className="house-detail">
                <div className="house-detail-photo">
                    <img src={"https://designcookies.dk/img/"+picture} alt=""/>
                </div>
                <div className="house-detail-info">
                    <h1 className="house-title">
                        {title}
                    </h1>
                    <p className="house-description">
                        {description}
                    </p>
                    <p className="house-city">
                        City : {city}
                    </p>
                    <p className="house-address">
                        Address : {address}
                    </p>
                    {geo && <p>Geo: {geo}</p>}
                    <p className="house-price">
                        Price : ${price}
                    </p>
                    {auth.isUser && (<div><DateRangePicker
                        isDayBlocked={isDayBlocked}
                        startDate={this.state.startDate}
                        endDate={this.state.endDate}
                        onDatesChange={({ startDate, endDate }) => this.setState({ startDate, endDate })}
                        focusedInput={this.state.focusedInput}
                        onFocusChange={focusedInput => this.setState({ focusedInput })}
                    />
                    <button className="house-booking" onClick={this.addRent}>
                        Book Now
                    </button></div>)}
                </div>
            </div>
        );
    }
}