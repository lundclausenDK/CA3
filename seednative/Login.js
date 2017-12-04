import React, {Component} from 'react'
import auth from "./authorization/auth";
import {View, Text, TextInput, Button} from 'react-native'

class Login extends Component {
    constructor() {
        super();
        this.state = {err: "", user: {username: "", password: ""}}
    }

    handleSubmit = () => {
        const user = this.state.user.username;
        const pass = this.state.user.password;
        console.log(user + " " + pass);
        auth.login(user, pass, (err, loggedIn) => {
            if (err) {
                return this.setState({err: err.errorMessage});
            }
            this.setState({err: ""});
        });
        this.props.update();
    }

    render() {
        return (
            <View>
                <Text>Please sign in</Text>
                <Text>Email address</Text>
                <TextInput value={this.state.user.username} onChangeText={(value)=>{
                    let user = this.state.user;
                    user['username'] = value;
                    this.setState({user})}}/>
                <Text>Password</Text>
                <TextInput value={this.state.user.password} onChangeText={(value)=>{
                    let user = this.state.user;
                    user['password'] = value;
                    this.setState({user})
                }}/>
                <Button title='sign in' onPress={this.handleSubmit}/>
            </View>
        )
    }
}

export default Login;
