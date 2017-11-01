


import React from 'react';



const URL = require("../../package.json").serverURL;


export default class SummerHouse extends React.Component{
    constructor(){
        super();
        this.state = {houses: []}

    }
    getdata(){

        let resFromFirstPromise=null;

        let method = "GET";
        let headers = {
            "Content-type": "Application/json"};
        let options = {
            method,
            headers
          }


        fetch(URL + "api/demouser", options)
        .then((res) => {
          resFromFirstPromise = res;
          return res.json();
        }).then((data) => {
          //errorChecker(resFromFirstPromise,data);
          
        }).catch(err => {
          console.log(JSON.stringify(err))
          
        })
    }

    render(){
        return(
            <div>heloooo</div>


        )
    }
}