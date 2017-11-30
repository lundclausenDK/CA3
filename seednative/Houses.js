import React from 'react';
import {View, Text, StyleSheet} from 'react-native';

export default class Houses extends React.Component{
    static navigationOptions = {tabBarLabel: 'Houses'};
    render(){
        return (<View style={styles.container}>
            <Text>Hej fra Houses</Text>
        </View>);
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

