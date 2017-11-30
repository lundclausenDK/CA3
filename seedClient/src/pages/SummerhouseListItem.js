import React from 'react';
import {Link} from 'react-router-dom';

const SummerhouseListItem = (props) => (
  <div className="summerhouse-list-item" onClick={props.onClick}>
    <div className="photo">
      <img src={"https://designcookies.dk/img/"+props.house.picture} alt=""/>
    </div>
    <div className="content">
      <h2 className="title">{props.house.title}</h2>
      <p>City : {props.house.city}</p>
      <p>Price : {props.house.price}</p>
      <p>{props.house.description}</p>
      <Link to={"/house/" + props.house.id}>see details</Link>
    </div>
  </div>
);

export default SummerhouseListItem;