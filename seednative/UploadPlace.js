import React, {Component} from 'react';
import {ScrollView, Picker, Text, AsyncStorage, TextInput, Button, Image} from 'react-native';
import {ImagePicker, Location} from 'expo';
import fetchHelper from "./facades/fetchHelpers";
import auth from './authorization/auth';

const URL = require("./package.json").serverURL;

export default class UploadPlace extends Component{
    static navigationOptions = {tabBarLabel: 'Profile', title: 'Upload a Place'};

    constructor() {
        super();

        this.state = {
            name: null,
            street: null,
            city: null,
            zip: null,
            desc: null,
            rating: null,
            geo: null,
            image: null,
        }

        auth.initDataFromToken();
        if (auth.isloggedIn) {
            this.state.userName = auth.userName;
        }
        this.getGeoLocation();
    }

    returnValue = (e) => {
        let id = e.target.id;
        let value = e.target.value;

        this.setState({ [id]: value });
        e.preventDefault();
    };

    getGeoLocation=async()=>{
        let location = await Location.getCurrentPositionAsync({});
        console.log(location.coords.latitude + ',' + location.coords.longitude);
        this.setState({
            latitude: location.coords.latitude,
            longitude: location.coords.longitude
        });
    }

    pushPlaceToServer = async () => {
        const {navigate} = this.props.navigation;
        /*
        const place = { name: this.state.name, street: this.state.street, city: this.state.city, zip: this.state.zip, desc: this.state.desc, file: this.state.file };
        const options = fetchHelper.makeOptions("POST", true, place);

        fetch(URL + "api/upload/placeUpload", options);
        */

        let data = new FormData();
        const token = await AsyncStorage.getItem('MyAppToken');

        data.append("name", this.state.name);
        data.append("street", this.state.street);
        data.append("city", this.state.city);
        data.append("zip", this.state.zip);
        data.append("desc", this.state.desc);
        data.append("userName", this.state.userName);
        data.append("rating", this.state.rating);
        data.append("geo", this.state.latitude + ',' + this.state.longitude);

        let uriParts = this.state.image.uri.split('.');
        let fileType = uriParts[uriParts.length - 1];
        let fileName = this.state.image.uri.split('/').pop();
        data.append('file', {
            uri: this.state.image.uri,
            name: `photo.${fileName}`,
            type: `image/${fileType}`,});
        fetch(URL + 'api/upload/placeUpload', {
            method: 'POST',
            body: data, headers: {'Authorization': `Bearer ${token}`}
        });
        navigate('Home');
    }

    _pickImage = async () => {
        let pickerResult = await ImagePicker.launchImageLibraryAsync({
            allowsEditing: true,
            aspect: [4, 3],
        });

        this.setState({image: pickerResult});
        //this._handleImagePicked(pickerResult);
    };

    render(){
        return(
            <ScrollView>
                <Text>Title</Text>
                <TextInput value={this.state.name} onChangeText={(text)=>{this.setState({name: text})}}/>
                <Text>Street</Text>
                <TextInput value={this.state.street} onChangeText={(text)=>{this.setState({street: text})}}/>
                <Text>City</Text>
                <TextInput value={this.state.city} onChangeText={(text)=>{this.setState({city: text})}}/>
                <Text>Zip</Text>
                <TextInput value={this.state.zip} onChangeText={(text)=>{this.setState({zip: text})}}/>
                <Text>Description</Text>
                <TextInput value={this.state.desc} onChangeText={(text)=>{this.setState({desc: text})}}/>
                <Text>Rating</Text>
                <Picker
                    selectedValue={this.state.rating}
                    onValueChange={(itemValue, itemIndex)=>{this.setState({rating: itemValue})}}>
                    <Picker.Item label='1' value='1'/>
                    <Picker.Item label='2' value='2'/>
                    <Picker.Item label='3' value='3'/>
                    <Picker.Item label='4' value='4'/>
                    <Picker.Item label='5' value='5'/>
                </Picker>
                <Text>Image</Text>
                <Button title='Select Image' onPress={this._pickImage}/>
                {this.state.image && <Image source={this.state.image}/>}
                {this.state.image && <Button title='upload' onPress={this.pushPlaceToServer}/>}
            </ScrollView>
        );
    };
}