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
        auth.initDataFromToken();
        this.setState({username: auth.userName});
    }

    update = () => {
        auth.initDataFromToken();
        if (auth.userName) {
            this.setState({username: auth.userName});
        }
    }

    static navigationOptions = {tabBarLabel: 'Profile', title: 'Profile'};

    render() {
        const {navigate} = this.props.navigation;
        return (
            <View style={styles.container}>
                <Text>Hej</Text>
                <Text>{auth.userName}</Text>
                {!this.state.username && <Login update={this.update}/> ||
                <Text>Logged in</Text> && <Button title='Upload a Place' onPress={()=>{
                    navigate('Upload')
                }}/>}
            </View>
        );
    }
}

const App = StackNavigator({
    Home: { screen: Profile},
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