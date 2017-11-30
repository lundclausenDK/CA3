import React from 'react';

const SummerhouseDetail = (props) => {
  return (
    <div className="house-detail">
      <div className="house-detail-photo">
        <img src={props.info.picture} alt=""/>
      </div>
      <div className="house-detail-info">
        <h1 className="house-title">
          {props.info.title}
        </h1>
        <p className="house-description">
          {props.info.description}
        </p>
        <p className="house-city">
          City : {props.info.city}
        </p>
        <p className="house-address">
          Address : {props.info.address}
        </p>
        <p className="house-price">
          Price : ${props.info.price}
        </p>
        <button className="house-booking">
          Book Now
        </button>
      </div>
    </div>
  );
}

export default SummerhouseDetail;