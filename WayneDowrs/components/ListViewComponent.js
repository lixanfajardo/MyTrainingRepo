
import React, {Component} from 'react';
import {View, Text, StyleSheet, FlatList, ToastAndroid} from 'react-native';
import {List, ListItem, SearchBar} from 'react-native-elements';



class ListViewComponent extends Component{

    constructor(props){
        super(props);

        // const ds = new FlatList.DataSource({rowHasChanged: (row1, row2) => row1 !== row2});
        
        this.state = {
           dataSource: [],
           loaded: false,
           page: 1,
           seed: 1,
           error: null,
		   refreshing: false,
		   success: false
        };
    }

    componentDidMount(){
        this.getDataFromAPI();
    }

    getDataFromAPI = () => {
        const {page, seed} = this.state;
        const api = 'https://randomuser.me/api/?seed=${seed}&page=${page}&results=30';
        ToastAndroid.show("Loading Content...", ToastAndroid.LONG);
        this.setState({loaded: true});
        fetch(api).then(res => res.json())
        .then(res => {
            this.setState({
                dataSource: page === 1 ? res.results : [...this.state.dataSource, ...res.results],
                error: res.error || null,
                loaded: false,
				refreshing: false,
				success: true
			});
        }).catch(error => {
			this.setState({error, 
				loaded: false,
				success: false});
            ToastAndroid.show(`Unable to connect to server. ${this.state.error}`, ToastAndroid.LONG);
           
        });
    };

    render() {
		if(this.state.success){
			return(
				<List containerStyle={{ borderTopWidth: 0, borderBottomWidth: 0 }}>
                <FlatList
                data = {this.state.dataSource}
                renderItem = {({item}) => (
                    <ListItem roundAvatar
                    title = {`${item.name.first} ${item.name.last}`}
                    subtitle = {item.email}
                    avatar = {{uri: item.picture.thumbnail}} 
					containerStyle = {{borderBottomWidth: 0}}
                    />
                )}
                keyExtractor = {item => item.email}
				ItemSeparatorComponent = {this.renderSeparator}
				ListHeaderComponent = {this.renderSearchBar}
                />
			</List> 
			);
		}else if(this.state.loaded){
			return(
				<View style = {styleSheet.loadingView}>
					<Text>Loading Content... </Text>	
				</View>
			);
		}else{
			return(
				<View style = {styleSheet.loadingView}>
					<Text>Couldn't load data from server. Please check connection settings. </Text>	
				</View>
			);
		}
        
	}
	renderSearchBar = () => {
		return (<SearchBar placeholder="Search" lightTheme round />);
	};

	renderSeparator = () => {
		return (
		  <View
			style={{
			  height: 1,
			  width: "86%",
			  backgroundColor: "#CED0CE",
			  marginLeft: "14%"
			}}
		  />
		);
	};

}

export default ListViewComponent;

const styleSheet = StyleSheet.create({
    container: {
        flex: 1,
		borderTopWidth: 0,
		borderBottomWidth: 0
	},
	
    loadingView: {
       flex: 1,
       justifyContent: 'center',
       alignItems: 'center',
    }
});