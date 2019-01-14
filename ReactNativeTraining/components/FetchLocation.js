import React from 'react';
import { Button,
        StyleSheet } from "react-native";

const fetchloc = props => {

    return (
        <Button title = "Press to launch application" onPress = {props.launchApp} style = {styles.button}/>
    );
};

const styles = StyleSheet.create({
    button: {
        backgroundColor: '#1E1E1E',
        color: '#1E1E1E',
        fontSize: 11
      }
});

export default fetchloc;