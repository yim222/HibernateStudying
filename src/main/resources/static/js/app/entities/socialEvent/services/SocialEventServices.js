import React from 'react'
import {SocialEvent} from '../components/SocialEvent'
import {GetDataServices} from '../../globalServices/GetDataServices'


export class SocialEventServices {

  //convert defaults to empty object with properies names
  static cleanValues(obj){
    for (let prop in obj){
      //console.log(prop);
      if ( (Array.isArray(obj[prop])  && isNaN(obj[prop][0]) )&& !(prop === 'contactDetails')
      //checking if it's array not of numbers (numbers don't clean ), and if it's not contactDetails.

    ){
        obj[prop].length = 0;
      }


      //obj[prop] = null;
    }
    return obj;
  }

  //convert the regular arrays to {value , label}
  static prepareDefaultValues(obj){

    for (let prop in obj){
      //console.log(prop);
      if ( (Array.isArray(obj[prop])  && isNaN(obj[prop][0]) )&& !(prop === 'contactDetails'))
      //checking if it's array not of numbers (numbers don't clean ), and if it's not contactDetails.
      {
        let newNestedArray =[]; //array for work on
        let currentEl =  obj[prop];//more readability
        //let el = {value: obj[prop], label : obj[prop]};
        //iterating on the nested array
        for (let prop2 in currentEl ){
          //each element becomes to object
          let el = {value: currentEl[prop2], label : currentEl[prop2]};
          newNestedArray.push(el)
        }
        console.log (newNestedArray, "newNestedArray")

        //after working on that - assigning the new nested array to the object
        obj[prop] = newNestedArray;
      }



    }
    return obj;
  }




  /**** OLD things ****/
  constructor(){

    //global locations configurations
    this.defaultId = "000A";

    // const dao2 = new GetDataServices(); // u can't do it right now because this service is using this class. That's the problem of doing non-static service memebers.

    //let categoriesData = dao.getCategories();
    //sort the data alphabetically
    /*
    categoriesData.arr.sort(function(a, b){

      let elA = a[1].toUpperCase();
      let elB = b[1].toUpperCase();

      if (elA > elB){return 1;}
      else if (elA < elB ){return -1};
      return 0 ;
    });
    */
    //Clean null categories - for case that the assigned categories have deleted, and if does, insert
    //default category ID once.
    this.cleanNullCategories = (updatedCategoriesList, locationsList ) => {
      //get the updatedCategoriesList as Map object

      //console.log("locationsList before filtering", locationsList );

      for(let location of locationsList){


        for (let id of location[1].categoriesIDs){
          //if default value already exist - not need to add another one
          if(location[1].categoriesIDs.includes(this.defaultId) ){
            break;
          }
          //check if one of the id's doesn't exist in the array
          if (updatedCategoriesList.get(id) == null  ){
            //console.log(id + "is missing");//
            //Insert once the default id for assigning instead
            location[1].categoriesIDs.push(  this.defaultId);
            break;


          }

        }

        //clean the don't exist categories id's
        location[1].categoriesIDs = location[1].categoriesIDs.filter(id =>
          (updatedCategoriesList.get(id)  != null || id === this.defaultId)
        );

      }
      //console.log("locationsList after filtering ", locationsList);

      return locationsList;
    }

    //Get categories data for use in the class

    //Function that sort the array of locations by name.
    this.sortListAlphabetically = (locationList) =>{
      //Sorting the array by alphabetically order. I keep the original array as is, for future possible using.
      let sortedList  = locationList.slice(0);
      //console.log(sortedList);

      //sort the data alphabetically
      sortedList.sort(function(a, b){

        let elA = a[1].name.toUpperCase();
        let elB = b[1].name.toUpperCase();

        if (elA > elB){return 1;}
        else if (elA < elB ){return -1};
        return 0 ;
      });
      var obj1 = [{a : "1"}, {b : "2"}];
      //let view = <div>;
      obj1.map((item) =>{


        console.log(Object.keys(item)[0]);//find the key of item = {a : "1"}
      })

      console.log("sortedList",sortedList)

      return sortedList;
    }

    //filter the list by group or ungroup
    //Maybe should sorting the data but right now prefere not to touch in the original data that arrived from the DB.
    this.generateGroupedList = (byGroup, locationList) =>{

        //get Categories by abc
        const dao = new GetDataServices();
        let categoriesData = dao.getCategories();
        let organizedData =[];

        //sort the data alphabetically

        categoriesData.arr.sort(function(a, b){

          let elA = a[1].toUpperCase();
          let elB = b[1].toUpperCase();

          if (elA > elB){return 1;}
          else if (elA < elB ){return -1};
          return 0 ;
        });

        //console.log("categoriesData.arr", categoriesData.arr);
        //make array object of each category's id  with its assigned locations
        for(let category of categoriesData.arr){

          organizedData.push({[category[0]]: this.getSocialEventsByCategory(category[0],locationList)});
        }
        //console.log("organizedData", organizedData);
        return organizedData;

      }

    this.getSocialEventsByCategory = (categoryId, locationsList)=>{
      let dataArr = [];
      for(let location of locationsList ){
        if (location[1].categoriesIDs.includes(categoryId)){
          dataArr.push(location);
        }
      }

      return dataArr;

    }

    this.filterAndGenerateList = (settings, list, byGroup ) =>{//filterAndGenerateList should be called
      //sort the list
      list  = this.sortListAlphabetically(list);
      console.log("settings",settings.state.currentId)

      //If not filtered by group return the sorted list .
      if(!byGroup){
        return(<SocialEventsList list = {list} settings = {settings}/>);
      }


      let groupedList = this.generateGroupedList(true, list);

      let view = groupedList.map((item, index) =>
        <div key = {index}>

          <CategorySection group = {item} settings = {settings} />
          <hr/>
        </div>
      ) ;
      return view;

    }


  }//Constructor end.

}


//SocialEvents List Component:
function SocialEventsList(props){
  let view = props.list.map((item)=>

    <div key = {item[0]} className = "itemStyle1"
      style  = {props.settings.state.currentId === item[0] ?  props.settings.styles.choosed1: null}>

      <div  >
        <SocialEvent data = {item[1]} id = {item[0]}
        onClick1 = {props.settings.onSocialEventClick}

        />


      </div>
    </div>);

  return (view);
}

//Category with its locations component
function CategorySection(props){//Going to get item of {categoryName: [[locationId , {locationData}]...]} - nmae = group
  //get the categories list


  const dao = new GetDataServices(); // u can't do it right now because this service is using this class. That's the problem of doing non-static service memebers.

  let categoriesDataMap = dao.getCategories().map;
   //sort the data alphabetically




  // to do all the below vars assigned from the props here in the method.
  //then get the organzied (as above ) and deliver it to this function at the main render.

  let categoryId = (Object.keys(props.group)[0]);
  let categoryName = (categoriesDataMap.get(categoryId));
  let locationsList = props.group[categoryId] , settings = props.settings;
  //console.log(Object.keys(item)[0]);//find the key of item = {a : "1"}

  return(


    <div>
      <h3>{categoryName}</h3>
      <SocialEventsList list = {locationsList} settings = {settings} />

    </div>

  );
}
