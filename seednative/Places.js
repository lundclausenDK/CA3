import React from 'react';
import {StyleSheet, Text, View, TextInput, Button, Form, AppRegistry, Header, Image, ScrollView} from 'react-native';

const URL = require("./package.json").serverURL;

export default class Places extends React.Component {

    constructor() {
        super();
        this.state = {
            places: [],
            view: [],
            ratingSort: true,
            nameSort: true,
            rated: false,
            userName: "none",
            optionPressed: false,
            initialized: false
        };

        this.getData = this.getData.bind(this);
        this.getData();
        setInterval(this.getData, 10000);
    }

    static navigationOptions = {
        tabBarLabel: 'Places',
    };

    getData = () => {
        fetch(URL + "api/places", {
            method: "GET", headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then(function (response) {
                return response.json()
            }).then(function (data) {
            for (let i = 0; i < data.length; i++) {
                for (let y = 0; y < data[i].raters.length; y++) {
                    if (data[i].raters[y].user.includes(this.state.userName.toLocaleLowerCase())) {
                        data[i].rated = true;
                    }
                }
            }
            this.setState({places: data});
            if(this.state.initialized === false){
                this.initData();
            }
        }.bind(this));
    };

    initData = () =>{
      this.setState({view: this.state.places, initialized: true});
    };

    getSearch = () => {
        const name = this.state.searchText;
        let viewList = this.state.places.filter((place) => {
            if (place.name.toLocaleLowerCase().includes(name.toLocaleLowerCase())) {
                return place;
            }
        });
        this.setState({view: viewList, optionPressed: false, searchText: ""});
    };

    sortOnName = (e) => {
        let sorted;
        if(this.state.nameSort){
            sorted = this.state.view.sort((a, b) => {
                if (a.name < b) {
                    return -1;
                } else if (a.name > b.name) {
                    return 1;
                }
            });
            this.setState({nameSort: false});
        }else{
            sorted = this.state.view.sort((a, b) => {
                if (a.name > b.name) {
                    return -1;
                } else if (a.name < b.name) {
                    return 1;
                }
            });
            this.setState({nameSort: true});
        }
        this.setState({view: sorted, ratingSort: true});
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
            this.setState({ratingSort: true, nameSort: true});
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

    changeOptionPressed= ()=>{
        let {optionPressed} = this.state;
        if(optionPressed === false){
            this.setState({optionPressed: true});
        }else{
            this.setState({optionPressed: false});
        }
    };

    render() {
        let {view, optionPressed} = this.state;
        return (
            <View style={styles.container}>
                <View style={{flexDirection: 'row', paddingBottom: 5, justifyContent: 'space-between'}}>
                    <TextInput onChangeText={(text)=>{this.setState({searchText: text})}} style={{/*borderWidth: 2, borderColor: "black",*/ width: "45%", textAlign: 'center'}} placeholder={"Search Text"}/>
                    <Button onPress={this.getSearch} title="Search"/>
                    <Button onPress={this.changeOptionPressed} title="Options"/>
                </View>

                {optionPressed && <View style={{flexDirection: 'row', paddingBottom: 5, justifyContent: 'space-between'}}>
                    <Button onPress={this.sortOnName} title="Sort on Name"/>
                    <Button onPress={this.sortOnRating} title="Sort on Rating"/>
                </View>}


                <ScrollView style={{width: '98%'}}>
                    {view.map((item) => (
                        <View key={item.id} style={{borderColor: "black", borderWidth: 2, marginBottom: 5}}>
                            {item.url && <Image source={{uri: "https://designcookies.dk/img/"+item.url}} style={styles.Image}/>}
                            <Text style={styles.textPadding}>{item.name}</Text>
                            <Text style={styles.textPadding}>{item.description}</Text>
                            <Text style={styles.textPadding}>{item.street}</Text>
                            <Text style={styles.textPadding}>{item.zip} {item.city}</Text>
                            <Text style={styles.textPadding}>GEO: {item.geo}</Text>
                            <Text style={styles.textPadding}>Rating: {item.rating}</Text>
                        </View>
                    ))}
                </ScrollView>

            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        paddingTop: 25,
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
    Image: {
        width: '100%',
        height: 300
    },
    textPadding: {
        paddingLeft: 5,
        paddingBottom: 5
    }
});