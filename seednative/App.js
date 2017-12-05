import React from 'react';
import Places from './Places';
import Houses from './Houses';
import Profile from './Profile';
import {TabNavigator} from 'react-navigation';

export default App = () => <MyApp/>;

const hey = true;

const MyApp = TabNavigator({
    Places: {
        screen: Places
    },
    Houses: {
        screen: Houses
    },
    Profile: {
        screen: Profile
    }
}, {
    tabBarPosition: 'bottom',
    animationEnabled: true,
    tabBarOptions: {
        activeTintColor: '#e91e63',
        indicatorStyle: {
            backgroundColor: '#ffffff'
        },
        tabStyle:{
            borderRightWidth: 1,
            borderLeftWidth: 1,
            borderTopWidth: 1,
            paddingTop: '2%'
        },
        style: {
            height: 40
        }
    }
});