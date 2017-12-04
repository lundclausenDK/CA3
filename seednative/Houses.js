import React from 'react';
import {View, Text, StyleSheet, ScrollView, Image} from 'react-native';

export default class Houses extends React.Component {
    static navigationOptions = {tabBarLabel: 'Houses'};

    constructor() {
        super();
        this.state = {
            houseList: []
        };
        this.loadData = this.loadData.bind(this);
    }

    componentDidMount() {
        this.loadData();
    }

    loadData() {
        let scope = this;

        fetch("https://designcookies.dk/backend/seedMaven/api/summerhouses", {
            method: "GET"
        }).then(function (response) {
            return response.json()
        }).then(function (data) {
            scope.setState({
                houseList: data
            })
        })
    }

    render() {
        let {houseList} = this.state;
        return (<View style={styles.container}>
            <ScrollView style={{width: '98%'}}>
                {houseList.map((item) => (
                    <View key={item.id} style={{borderColor: "black", borderWidth: 2, marginBottom: 5}}>
                        {item.picture &&
                        <Image source={{uri: "https://designcookies.dk/img/" + item.picture}} style={styles.Image}/>}
                        <Text style={styles.textPadding}>{item.title}</Text>
                        <Text style={styles.textPadding}>{item.description}</Text>
                        <Text style={styles.textPadding}>{item.address}</Text>
                        <Text style={styles.textPadding}>{item.city}</Text>
                        <Text style={styles.textPadding}>GEO: {item.geo}</Text>
                    </View>
                ))}
            </ScrollView>
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

