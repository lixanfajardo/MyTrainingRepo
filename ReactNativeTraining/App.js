/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {StyleSheet, 
        Text, 
        View, 
        ToastAndroid, 
        TextInput} from 'react-native';
import FetchLocation from './components/FetchLocation'



export default class App extends Component<Props> {


  constructor() {

    super()
    this.state = {value: ""}
    this.state.customStyle = {
      color: 'blue'
    },

    this.state.customStyle2 = {
      color: 'white'
    }

    setInterval(() => {

      if(this.state.customStyle.color == 'blue') {
        this.setState({
          customStyle: {
            color: 'red'
          }
      }) 
      } else {
        this.setState({
          customStyle: {
            color: 'blue'
          }
      })
    }
      if(this.state.customStyle2.color == 'blue') {
        this.setState({
          customStyle2: {
            color: 'red'
          }
        })
      } else {
        this.setState({
          customStyle2: {
            color: 'blue'
          }
        }) 
      }
    }, 1000);
  }
  launchCalculator = () => {
    ToastAndroid.show("Launching Calculator Project", ToastAndroid.LONG);
  }

  displayMessage = () => {
    ToastAndroid.show("GWAPO KAAYO SI LIXAN :D", ToastAndroid.LONG);
  }

  render() {
    return (

      <View style={styles.container}>

      <View style={{flex: 1, flexDirection: 'row'}}>
        <View style={styles.half1}>
            <View style={{flex: 1, flexDirection: 'column', alignItems: 'center', justifyContent: 'center'}}>
              <Text style={[styles.welcome, this.state.customStyle]}>Welcome to my React Native Calculator Sample Project!</Text>
              <Text style={[styles.instructions, this.state.customStyle2]}>To launch calculator sample project, Press the button below </Text>
              <View style={styles.bottomView}>
                <FetchLocation launchApp = {this.launchCalculator}/>
              </View>
              
            </View>
          </View>

          <View style={styles.half2}>
            <View style={{flex: 1, flexDirection: 'column', alignItems: 'center', justifyContent: 'center'}}>
              <Text style={[styles.welcome, this.state.customStyle]}>Welcome to React Native!</Text>
              <Text style={[styles.instructions, this.state.customStyle2]}>To get started, Press the button below</Text>
              <View style={styles.bottomView}>
            	<FetchLocation launchApp = {this.displayMessage} />
              </View>
              
              
            </View>
          </View>
      </View>
      
        {/* <View style={{flex: 1, flexDirection: 'column', alignItems: 'center', 
        justifyContent: 'center', backgroundColor: 'steelblue'}}>
        <Text style={{color: 'white', fontSize: 30}}>LOGIN</Text>

        <View style={styles.loginArea}>
          <Text style={{color: 'white', fontSize: 15}}>Username: </Text>
          <TextInput onChangeText={(value) => this.setState({value})} placeholder= "Type Here!" 
            style={styles.textInputStyle}/>
        </View>
         
          <Text style={{color: 'white', fontSize: 30}}>You Typed: {this.state.value}</Text>
        </View> */}
      </View>
    );
  }
}

const styles = StyleSheet.create({
  
  container: {
    flex: 1,
    flexDirection: 'column',
    // justifyContent: 'center',
    // alignItems: 'center',
    backgroundColor: '#1E1E1E'
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5
  },
  half1: {
    flex: 1,
    backgroundColor: 'black',
    alignItems: 'center',
  },
  half2: {
    flex: 1,
    backgroundColor: 'yellow',
    alignItems: 'center',
  },
  textInputStyle: {
    // fontSize: 20,
    color: 'white'
  },
  loginArea: {
    flex: 1,
    flexDirection: 'row'
  },
  bottomView: {
    width: '100%',
    height: 50,
    alignItems: 'center',
    position: 'absolute',
    bottom: 0
  }
});
