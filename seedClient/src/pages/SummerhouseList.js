import React, { Component } from 'react';
import SummerhouseListItem from './SummerhouseListItem';

class SummerhouseList extends Component {

  constructor() {
    super();
    this.state = {
      houseList : []
    }
    this.loadData = this.loadData.bind(this);
    this.handleClick = this.handleClick.bind(this);
  }

  componentDidMount() {
    this.loadData();
  }

  loadData() {
    let scope = this;

    fetch("https://designcookies.dk/backend/seedMaven/api/summerhouses", {
      method: "GET"
    }).then(function(response) {
      return response.json()
    }).then(function(data){
      console.log(data);
      scope.setState({
        houseList : data
      })
    })
  }

  handleClick = () => {
    alert('action dispatch');
  }

  render() {
    return (
      <div>
        <h1>Test</h1>
        {this.state.houseList.map((h, i) =>
          <SummerhouseListItem house={h} key={i}/>
        )}
      </div>
    );
  }
}

export default SummerhouseList;