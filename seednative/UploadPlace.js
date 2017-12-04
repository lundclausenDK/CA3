import React, {Component} from 'react';
import {View, Text} from 'react-native';

export default class UploadPlace extends Component{
    static navigationOptions = {title: 'Upload a Place'};
    render(){
        return(
            <View>
                <Text>Hej fra Places</Text>
            </View>
        );
    };
}