/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';
import {createMaterialBottomTabNavigator} from 'react-navigation-material-bottom-tabs';
import Icon from 'react-native-vector-icons/Ionicons';
import SplashScreen from 'react-native-splash-screen';
import ProjectViewComponent from './components/ProjectViewComponent';
import SettingsViewComponent from './components/SettingsViewComponent';


const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});



type Props = {};
class ProjectsScreen extends Component{
  
  componentDidMount(){
    SplashScreen.hide();
  }

  render() {
    return (
      <ProjectViewComponent />
    );
  }
}

class SettingsScreen extends Component{
  render() {
    return (
        <SettingsViewComponent/>
    );
  }
}

export default createMaterialBottomTabNavigator({

  Projects: { 
    screen: ProjectsScreen,
    navigationOptions: {
      tabBarLabel: 'Projects',
      title: 'Projects',
      tabBarColor: '#007ACC',
      tabBarIcon: ({tintColor}) => (
        <Icon name="md-folder" color={tintColor} size={24} />
      )
  } 
},
  Settings: { 
    screen: SettingsScreen,
    navigationOptions: {
      tabBarLabel: 'Settings',
      title: 'Settings',
      tabBarColor: '#694fad',
      tabBarIcon: ({tintColor}) => (
        <Icon name="md-settings" color={tintColor} size={24} />
      )
    }
  }
},
{
  initialRouteName: 'Projects',
  shifting: true,
  activeColor: 'white',
  barStyle: {
    alignItems: "center",
    borderTopWidth: 1,
    borderColor: 'grey'
  }
});

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'flex-start',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
