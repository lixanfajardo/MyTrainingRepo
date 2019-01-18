
import React, {Component} from 'react';
import {
    View, 
    Text,
    StyleSheet,
    FlatList,
    ToastAndroid,
    TouchableOpacity,
    Alert,
    TouchableHighlight } from 'react-native';
import {List, ListItem, SearchBar} from 'react-native-elements';
import Icon from 'react-native-vector-icons/Ionicons';


class ProjectViewComponent extends Component{

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
                <View style = {styleSheet.container}>
                    <List>
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

                <TouchableOpacity activeOpacity = {0.5} 
                onPress = {this.addButtonOnClick}
                style = {styleSheet.addButtonStyle}>
                    <Icon name = 'md-add-circle'
                    size = {60} color = '#007ACC' />
                </TouchableOpacity>

                </View>
				

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
		return (
        <SearchBar placeholder="Search" lightTheme round />
        
        );
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
    
    addButtonOnClick = () => {
        Alert.alert("TEST", 'Button Clicked');
    }

    // flatListOnClick = (data) => {
    //     ToastAndroid.show(`INDEX: ${data.name.first}`)
    // }

}

export default ProjectViewComponent;

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
    },
    addButtonStyle: {
        position: 'absolute',
        alignItems: 'center',
        justifyContent: 'center',
        right: 20,
        bottom: 10,
        backgroundColor: 'transparent',
        borderRadius: 60
    }
});