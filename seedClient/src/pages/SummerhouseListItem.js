import React from 'react';

const SummerhouseListItem = (props) => (
  <div className="summerhouse-list-item" onClick={props.onClick}>
    <div className="photo">
      <img src={props.house.picture} alt=""/>
    </div>
    <div className="content">
      <h2 className="title">{props.house.title}</h2>
      <p>City : {props.house.city}</p>
      <p>Price : {props.house.price}</p>
      <p>{props.house.description}</p>
    </div>
  </div>
);

export default SummerhouseListItem;