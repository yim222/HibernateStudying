import React from 'react';
//import { Link, Router } from 'react-router-dom'

import {SocialEvent} from './SocialEvent';
import {Category} from '../../category/components/Category'
import {InputsView} from './InputsView';
import {SOCIAL_EVENTS_MOCK, testData} from '../Mocks';
import {DBServices} from '../../globalServices/DBServices';
import {SocialEventHeader} from '../SocialEventHeader';
import {GetDataServices} from '../../globalServices/GetDataServices';
import {SocialEventServices} from '../services/SocialEventServices'


export class SocialEvents extends React.Component{

  constructor(props){
    super();
    console.log("SocialEvents Component Has Created");



    this.state = {
      currentId : -1,
      socialEventName: "",
      socialEventPublisherName: "",
      socialEventAddress:"",
      socialEventLng: null,
      socialEventLat: null,
      actionView: '',
      socialEventCategoriesIDs: [],
      filterByGroups: false


    }
    this.socialEventServices = new SocialEventServices();
    const dao = new GetDataServices();

    //Get the categories data for using in the cateories choice
    const categoriesData = dao.getCategories();

    //for use it as parameter. TODO - change all the local categoriesData to this.
    this.categoriesData = categoriesData;

    //data name - for using with the get/save db services
    this.dataName = "socialEvents";

    //Getting SocialEvents Data:
    const socialEventsData = dao.getSocialEvents();
    //console.log("check", categoriesData);

    //Different settings style:
    this.styles = {

      choosed1: {backgroundColor: "black"}

    };

    //Id's generator for assign the general using
    this.idGenerator = require("uuid/v1");

    //Inner CRUD Views
    //Update view
    this.updateView = (()=>{
      return(
      <div>

      <label>
      <h4>Update SocialEvent:</h4>
        <InputsView categoriesData = {this.categoriesData.arr} handleChange = {this.handleChange} initialValue = {this.state}
        handleCategoriesSelect = {this.handleCategoriesSelect}
        onMapClick = {this.onMapClick} />

      </label>
      <br/>
      <button type="button" onClick = {() => this.updateSocialEvent()}>Save</button>
      </div>)
    });

    //Just for testing
    this.deleteView = (()=>{
      return(
      <div>
      <h1>deleteView to remove at the end</h1>

      <button onClick = {() => this.deleteSocialEvent()}>Delete</button>
      </div>)
    });

    //Create view
    this.createView = (()=>{

      return(
      <div>

        <InputsView handleChange = {this.handleChange} initialValue = {this.state} categoriesData = {this.categoriesData.arr}
        handleCategoriesSelect = {this.handleCategoriesSelect}
        onMapClick = {this.onMapClick}
        />
        <button onClick = {() => this.createSocialEvent()}>Create New</button>
      </div>)
    });

    //for testing
    this.test1 = "testMe;"

    //Array that contain all data of socialEvents
    this.socialEventsData = {};

    //DB Service Object
    this.dbService = new DBServices();

    //if storgae null - getting initial data
    if (this.dbService.getData(this.dataName) == null){
      console.log("Storgae Empty - set an initial Data ");
      this.socialEventsData = SOCIAL_EVENTS_MOCK;
      const socialEventsData = Array.from(SOCIAL_EVENTS_MOCK);
      this.dbService.saveData(this.dataName,socialEventsData );

    }

    //get data from the storgae
    else{
      console.log("Storgae Not null");

      //Getting the updated data
      const socialEventsData = dao.getSocialEvents();

      //We'll use Map object on data changing and etc.
      this.socialEventsData = socialEventsData.map;
    }

    //Bind functions:
    this.displaySocialEvents = this.displaySocialEvents.bind(this);
    this.onSocialEventClick = this.onSocialEventClick.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.updateSocialEvent = this.updateSocialEvent.bind(this);
    this.deleteSocialEvent = this.deleteSocialEvent.bind(this);
    this.createSocialEvent = this.createSocialEvent.bind(this);
    this.chooseOption = this.chooseOption.bind(this);
    this.displayActionView = this.displayActionView.bind(this);
    this.inputNotEmpty = this.inputNotEmpty.bind(this);
    this.validLngLat = this.validLngLat.bind(this);
    this.validInputs = this.validInputs.bind(this);
    this.handleCategoriesSelect = this.handleCategoriesSelect.bind(this);
    this.onMapClick = this.onMapClick.bind(this);


  }//End Of constructor






  componentDidMount(){


  }

  //Display all socialEvents
  displaySocialEvents(){
    //Convert the Map data object into array for running it Array.map iterate functions
    let arr = Array.from(this.socialEventsData); // change from


    //Sorting the array by alphabetically order. I keep the original array as is, for future possible using.
    let sortedList  = this.socialEventServices.sortListAlphabetically(arr);
    //Trying -
    //this.socialEventServices.filterAndGenerateList(true,sortedList );
    //sortedList
    /*trying carefully
    let view = sortedList.map((item)=>

      <div key = {item[0]} className = "itemStyle1"
        style  = {this.state.currentId === item[0] ?  this.styles.choosed1: null}>

        <div  >
          <SocialEvent data = {item[1]} id = {item[0]}
          onClick1 = {this.onSocialEventClick}

          />


        </div>
      </div>
    );
    */
    let x = {

    }
    console.log("this", this);
    let view = this.socialEventServices.filterAndGenerateList(this, sortedList, this.state.filterByGroups);

    return view;
  }

  //What's happen when SocialEvent clicked
  onSocialEventClick(id){

    //Assign to state the SocialEvent data from the local data Map
    var socialEventData = this.socialEventsData.get(id);

    this.setState({

      currentId: id,
      socialEventName: socialEventData.name,
      socialEventAddress: socialEventData.address,
      socialEventLat: socialEventData.coordinates.lat,
      socialEventLng: socialEventData.coordinates.lng,
      socialEventCategory: socialEventData.categories,
      socialEventCategoriesIDs: socialEventData.categoriesIDs,

      toRender: true,
    });
  }

  //CRUD ACTIONS
  //update
  updateSocialEvent(){

    //Do inputs validation
    if(!this.validInputs()){
      return;
    }


    let newSocialEventData = Object.assign(
      {
        currentId: "",
        socialEventName:"",
        socialEventAddress:"",
        socialEventLat:"",
        socialEventLng:"",
        socialEventCategoriesIDs:[]
      }, this.state);


    let passData = {
      id: newSocialEventData.currentId,
      name: newSocialEventData.socialEventName,
      address: newSocialEventData.socialEventAddress,
      coordinates:{
        lat: newSocialEventData.socialEventLat,
        lng: newSocialEventData.socialEventLng
      },
      categoriesIDs: newSocialEventData.socialEventCategoriesIDs



    }
    //console.log("newSocialEventData",newSocialEventData);
    this.socialEventsData.set(newSocialEventData.currentId, passData);
      this.setState({
        toRender: true

    });


    //Save data to storage
    const socialEventsData = Array.from(this.socialEventsData);
    this.dbService.saveData(this.dataName,socialEventsData );


    }

    //Delete
    deleteSocialEvent(){
      this.socialEventsData.delete(this.state.currentId);
      this.setState({
        toRender: true
      });
      //Save data to storage
      const socialEventsData = Array.from(this.socialEventsData);
      this.dbService.saveData(this.dataName,socialEventsData );

    }

  //Create New
  createSocialEvent(){

    //Do inputs validation
    if(!this.validInputs()){
      return;
    }

    //Generates new Id for new component
    const id = this.idGenerator();
    this.setState({
      currentId : id
    });
    let newSocialEventData = Object.assign(
      {socialEventName:"",
      socialEventAddress:"",
      socialEventLat:null,
      socialEventLng:null,
      socialEventCategoriesIDs:[]
    }, this.state);

    let passData = {
      name: newSocialEventData.socialEventName,
      address: newSocialEventData.socialEventAddress,
      coordinates:{
        lat: newSocialEventData.socialEventLat,
        lng: newSocialEventData.socialEventLng
      },
      categoriesIDs: newSocialEventData.socialEventCategoriesIDs
    }


    //console.log("newSocialEventData",newSocialEventData);

    this.socialEventsData.set(id, passData);
      this.setState({//reset the data
        socialEventName:"",
        socialEventAddress:"",
        socialEventLat:"",
        socialEventLng:"",
        socialEventCategoriesIDs:[],
        toRender: true

    });

    //Save data to storage
    const socialEventsData = Array.from(this.socialEventsData);
    this.dbService.saveData(this.dataName,socialEventsData );

  }

  //What's happen when one of the CRUD options clicked
  chooseOption = (thisEl)=>{
    this.setState({
      actionView: thisEl
    });
    if (thisEl === "create"){

      this.setState({
        currentId: "",
        socialEventName: "",
        socialEventAddress: "",
        socialEventLat: "",
        socialEventLng: "",
        socialEventCategory: "",
        socialEventCategoriesIDs:[]
      });
    }
    else if(thisEl === "delete"){
       this.deleteSocialEvent();
    }
    else if (thisEl === "display"){
      //Nothing to do, because the view sets in "displayActionView()"
    }
  }

  //Handle input changes - in each input u just need to make its "name" attribute corrsespond to the
  //state property u want to change.
  handleChange(ev){

    let name = ev.target.name;
    this.setState({
      [name]:ev.target.value
    });
  }

  //Displays the CRUD action view, when needed.
  displayActionView =()=>{
    let view ={};
    let choosed = this.state.actionView;
    if(choosed === 'create'){
      view = this.createView();
    }
    else if(choosed === 'update'){
      view = this.updateView();
    }
    else if(choosed === 'display'){
      //alert(this.state.currentId);
      if(!this.state.currentId > 0 ){
        alert("First choose socialEvent");
        return;
      }
      const data =  this.socialEventsData.get(this.state.currentId);
      view = <h4><SocialEvent data = {data}/></h4>
      //this.setState();

    }

    else if (choosed === 'delete'){
      view = (
      <div>
      <h4>SocialEvent has deleted</h4>

    </div>)
      ;
    }

    else{
      view = (
      <div>
      <h4>Get Start -</h4>
      <p> You can choose SocialEvent from the list, and manage it, or create new.
      </p>
    </div>)
      ;
    }

    return view;
  };

  //inputs validation()
  inputNotEmpty(){
    const values = this.state;
    const testedInputs = [values.socialEventName, values.socialEventAddress,
    values.socialEventLat.toString(), values.socialEventLng.toString()]

    //console.log(testedInputs);

    for(let str of testedInputs){

      if(!(str && str.trim().length > 0)
       ||  !(values.socialEventCategoriesIDs.length > 0 )//valid categories choice
      ){
        alert("All inputs must be filled");
        return false;
      }
    }
    return true ;//All inputs are filled
  }

  //Lng and Lat validation
  validLngLat(){

    const lng = this.state.socialEventLng;
    const lat = this.state.socialEventLat;
    let valid = true;
    if ((lng > 180 || lng < -180)|| (lat > 90 || lat < -90)){
      alert("Lng/Lat values not valid - \n"
    +"Lng range = -180 to +180\n"
    +"Lat range = -90 to +90");
    return false;

    }
    return true;


  }

  validInputs(){

    return (this.validLngLat() && this.inputNotEmpty());
  }



  handleCategoriesSelect(ev ){
    ev.preventDefault();
    var newElement = ev.target.value;
    let dataArr = this.state.socialEventCategoriesIDs.slice();//assign the state

    //if include remove it
    //console.log("dataArr", dataArr);
    if(dataArr.includes(newElement)){
      dataArr = dataArr.filter(e => e !== newElement);
      console.log("const dataArr",  dataArr);

    }
    else{
      dataArr.push(newElement);
    }

    //set state with function because it's on array
    this.setState(prevState => (
      {socialEventCategoriesIDs: dataArr}
    ));

  }

  onMapClick(ev){
    console.log(ev.latLng.lat(), "Lat");
    const latLng = ev.latLng;
    this.setState({
      socialEventLat: latLng.lat(),
      socialEventLng: latLng.lng()
    });
  }


  render(){


    return(
      <div >

        <div className = "topBarArea">
        {/*Navigation Top Bar */}
            <nav className ="navbar navbar-default header1">
              <div className ="container-fluid">
                <div className ="navbar-header">
                  <a className ="navbar-brand" href="#">SocialEvents List</a>
                </div>

                <SocialEventHeader onChoose = {this.chooseOption}/>
                <button onClick ={()=>{

                  this.setState({
                    filterByGroups: !this.state.filterByGroups
                  });



                }}>Switch Filter by groups</button>

              </div>
            </nav>
          </div>

          <div style = {{paddingBottom: "100px"}}></div>

          <div className = "mainEntityArea">
              {/*The CRUD action view */}
              <div className = "crudView1" >


                {this.displayActionView()}

              </div>


              {/*<h1>SocialEvents List</h1>
                Here all started :)
                */}
              <br/>

              <div >{this.displaySocialEvents()}</div>
          </div>
</div>
    )
  }

}
