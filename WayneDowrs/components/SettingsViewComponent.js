

import React, {Component} from 'react';
import {View, StyleSheet, FlatList, TouchableHighlight, ToastAndroid} from 'react-native';
import {List, ListItem} from 'react-native-elements';
import Icon from 'react-native-vector-icons/Ionicons';



class SettingsViewComponent extends Component{

    constructor(props){
        super(props);

        this.state = {
            dataSource: [{title: 'Price Settings',
            subtitle: 'Manage item price details and add item details',
            itemIcon: 'md-pricetags'}, 
            {title: 'File Sharing',
            subtitle: 'share the backup files.',
            itemIcon: 'md-swap'}]
        };
    }


    render(){
        return(
            <View style = {styleSheet.container}>
                <List>
                    <FlatList 
                    data = {this.state.dataSource}
                    renderItem = {({item}) => (
                        <TouchableHighlight onPress = {() => this.listOnClick(item)}>
                            <ListItem roundAvatar
                             title = {item.title}
                             subtitle = {item.subtitle}
                             avatar = {<Icon name= {item.itemIcon} size = {30} />} 
                             containerStyle = {{borderBottomWidth: 0}}
                            />
                        </TouchableHighlight>
                    )}
                    keyExtractor = {item => item.title}    
                    ItemSeparatorComponent = {this.renderSeparator}
                    />
                </List>
            </View>
        );
    }

    renderSeparator = () => {
		return (
		  <View
			style={{
			  height: 1,
			  width: "100%",
			  backgroundColor: "#CED0CE"
			}}
		  />
		);
    };

    listOnClick(data) {
        ToastAndroid.show(`You Pressed: ${data.title}`, ToastAndroid.SHORT);
    }
}

export default SettingsViewComponent;

const styleSheet = StyleSheet.create({
    container: {
        flex: 1,
        borderTopWidth: 0,
		borderBottomWidth: 0
	}
});