
import React, {Component} from 'react';
import {
    View, 
    Text,
    StyleSheet,
    FlatList,
    ToastAndroid,
    TouchableOpacity,
    Alert,
    TouchableHighlight,
    ActivityIndicator } from 'react-native';
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
           success: false,
           query: "",
           loadedData: []
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
                dataSource: res.results,
                error: res.error || null,
                loaded: false,
				refreshing: false,
                success: true,
                loadedData: res.results
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
                            <TouchableHighlight onPress = {() => this.flatListOnClick(item)}>
                                <ListItem roundAvatar
                                title = {`${item.name.first} ${item.name.last}`}
                                subtitle = {item.email}
                                avatar = {{uri: item.picture.thumbnail}} 
                                containerStyle = {{borderBottomWidth: 0}}   
                                />
                            </TouchableHighlight>
                            
                        )}
                        keyExtractor = {item => item.login.md5}
                        ItemSeparatorComponent = {this.renderSeparator}
                        ListHeaderComponent = {this.renderSearchBar}
                        />
                </List> 

                <TouchableOpacity activeOpacity = {0.5} 
                onPress = {this.addButtonOnClick}
                style = {styleSheet.addButtonStyle}>
                    <Icon name = 'md-add-circle-outline'
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
        <SearchBar placeholder="Search" lightTheme round 
            onChangeText = {query => this.handleSearchQuery(query)} />
        
        );
    };
    
    handleSearchQuery = (searchQuery) => {
      const filteredData = this.state.loadedData.filter(dataItem => {
          const item = `${dataItem.name.first.toLowerCase()} ${dataItem.name.last.toLowerCase()}`;

          const dataText = searchQuery.toLowerCase();
          return item.indexOf(dataText) > -1;
      });

      this.setState({dataSource: filteredData});
    };

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

    renderFooter = () => {

        if(!this.state.loaded){
            return null;
        }else{
            return(
                <View style = {{paddingVertical: 20, borderTopWidth: 1, borderTopColor: "#CED0CE"}}>
                    <ActivityIndicator animating size='large' />
                </View>
            )
        }
        
    }
    
    addButtonOnClick = () => {
        Alert.alert("TEST", 'Button Clicked');
    }

    flatListOnClick = (data) => {
        ToastAndroid.show(`INDEX: ${data.name.first} ${data.name.last}`, ToastAndroid.SHORT);
    }

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