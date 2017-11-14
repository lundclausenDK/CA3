import React from 'react';
import Places from './Places';
import Houses from './Houses';
import {TabNavigator} from 'react-navigation';

export default App = () => <MyApp/>;

const MyApp = TabNavigator({
    Places: {
        screen: Places
    },
    Houses: {
        screen: Houses
    }
}, {
    tabBarPosition: 'bottom',
    animationEnabled: true,
    tabBarOptions: {
        activeTintColor: '#e91e63',
        indicatorStyle: {
            backgroundColor: '#ffffff'
        }
    }
});