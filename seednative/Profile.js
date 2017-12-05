import React from 'react';
import Login from './Login';
import auth from './authorization/auth';
import {View, Text, StyleSheet, Button} from 'react-native';
import {StackNavigator} from 'react-navigation';
import UploadPlace from './UploadPlace';

class Profile extends React.Component {

    constructor() {
        super();
        this.state = ({});
    }

    componentDidMount() {
        //auth.logout();
        auth.initDataFromToken((username) => {
            this.setState({username: username});
        });
    }

    componentWillMount() {

    }

    update = () => {
        auth.initDataFromToken((username) => {
                console.log(username);
                this.setState({username: username});
        });
    };

    static navigationOptions = {tabBarLabel: 'Profile', header: null};

    render() {
        const {navigate} = this.props.navigation;
        return (
            <View style={styles.container}>
                <Text>Hej</Text>
                <Text>{auth.userName}</Text>
                {!this.state.username && <Login update={this.update}/> ||
                <Button title='Upload a Place' onPress={() => {
                    navigate('Upload')
                }}/>}
                {this.state.username && <Button title='logout' onPress={() => {
                    auth.logout(this.update)
                }}/>}
            </View>
        );
    }
}

const App = StackNavigator({
    Home: {screen: Profile},
    Upload: {screen: UploadPlace},

}, {
    navigationOptions: {
        headerStyle: {
            paddingTop: '5%'
        }
    }
});

export default App;

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