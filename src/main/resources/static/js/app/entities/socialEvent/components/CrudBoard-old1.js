import React from 'react';
//import { Link, Router } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import {Container,Row, Col, Form} from 'react-bootstrap';
import a from  'bootstrap-select';
import Select from 'react-select';
import {MutipleInputLimited, MultipleLimitedWithAddMore, AddMoreValues} from './InputsTypes'
import {GetDataServices} from '../../globalServices/GetDataServices'
import {SocialEventServices} from '../services/SocialEventServices'
import clone from 'lodash'
//import clonedeep from 'lodash/clonedeep'
import cloneDeep from 'lodash/cloneDeep';


//const clone = require('lodash.clone')
//const clonedeep = require('lodash.clonedeep')



export class CrudBoard extends React.Component{
  constructor(props){
    super()

    //*****TEST AREA
    //eventType
    console.log('DEFAULT_EVENT_PROPERTIES:');
    console.log(GetDataServices.getFakeData('DEFAULT_EVENT_PROPERTIES'));
    //this.state = GetDataServices.getFakeData('DEFAULT_EVENT_PROPERTIES');
    console.log(this.state , "\nthis.state")
    let obj = {
      a: "dddd",
      b: ["1","2","3"],
      c: true,
      d: 0
    }/*
    let initialData = GetDataServices.getFakeData('DEFAULT_EVENT_PROPERTIES');


    let obj2 = cloneDeep(initialData);
    let obj3 = cloneDeep(initialData);
    obj2.name = "izhar";
    console.log(obj2, obj3);
    SocialEventServices.cleanValues(obj2);
    console.log(obj2, obj3);
    */
    //*****TEST AREA END
    this.state = {
      xxx : ["A", "B"],
      //eventType:[],
      //eventType : ['1','2']
    }
    let initialData = GetDataServices.getFakeData('DEFAULT_EVENT_PROPERTIES');


    let defaultsValues = cloneDeep(initialData);
    let emptyValues = cloneDeep(initialData);
    emptyValues = SocialEventServices.cleanValues(emptyValues);
    defaultsValues = SocialEventServices.prepareDefaultValues(defaultsValues);
    this.state = emptyValues;
    console.log ("initialData", initialData);
    console.log ("defaultsValues", defaultsValues);
      console.log ("emptyValues", emptyValues);


    //defaultsValues


    this.defaultEventType = [{value:'1-One', label : "HI"},{value:'1-Two',  label : "BYE"},{value:'1-Three',label : "Don't Lie"},{value:'1-Four' , label : "High"}];
  }



  checkMe = (ev) =>{
    let dataArr = this.state.eventType;
    //ev.length > 0 ? dataArr.push(ev[ev.length-1].value) : dataArr =[];

    this.setState({
      eventType: ev
    })
  }

  onMultipleChange = (ev, meta) =>{


    let propName = meta.name;
    let dataArr = this.state[propName];


    this.setState({
      [propName]: ev
    })
  }

  onAddMoreValuesChange = (ev, propName) =>{

    let arr1 = this.state[propName];
    arr1[ev.target.name] = ev.target.value;
    this.setState({
      [propName]: arr1
    });

  }

  //onAddMoreValuesSave() - for the mixed inputs
  onAddMoreValuesSave = (dataArr, propName) =>{
    //convert the array to good data array
    let newArr = [];
    dataArr.forEach(function(element) {
      console.log(element);
      newArr.push({
        value: element,
        label: element

      });
    });

    //get the state array
    let arr1 = this.state[propName];

    //concatenate them together
    newArr = arr1.concat(newArr);
    this.setState({
      [propName]: newArr
    });

  }
  addMoreInputs = ( propName) =>{
    let arr1 = this.state[propName];
    arr1.push("new value");
    this.setState({
      [propName]: arr1
    });
  }

  render(){


    let dataArr ={label:'Group One',children:[{value:'1-One'},{value:'1-Two'},{value:'1-Three'},{value:'1-Four',label:'Four Label'}, "sss"]};
    const options = [
      { value: 'chocolate', label: 'Chocolate' },
      { value: 'strawberry', label: 'Strawberry' },
      { value: 'vanilla', label: 'Vanilla' }
    ];
    const obj1 = {
      arrA : [1,2,3]

    }
    let view1 = "";

    let array1 =this.state.eventType;



    array1.forEach(function(element) {
      console.log(element);
      view1 += element.label + ", ";
    });

    let choosedValues = this.state.eventType;

    //alert(choosedValues.length );


    return (

      <div>

        <h2>I am Crud board</h2>
        <p>State area : {this.state.area+""}</p>
        <AddMoreValues
          values = {this.state.eventType}
          onChange = {this.onAddMoreValuesChange}
          name = "eventType"
          limitation = {3}
          addMore = {this.addMoreInputs}

        />
        <MutipleInputLimited
          name = "eventType"
          limitation = {2}
          choosedValues = {choosedValues}
          onChange = {this.onMultipleChange}
          optionsList = {this.defaultEventType}

        />
        <MultipleLimitedWithAddMore
          name = "eventType"
          limitation = {1}
          choosedValues = {choosedValues}
          onChange = {this.onMultipleChange}
          optionsList = {this.defaultEventType}
          onAddMoreValuesSave = {this.onAddMoreValuesSave}
          values = {this.state.eventType}


        />

        <h3>With null values </h3>
        <MutipleInputLimited
          name = "eventType"
          limitation = {2}
          choosedValues = {this.state.eventType}
          onChange = {this.onMultipleChange}
          optionsList = {this.defaultEventType}

        />
        <p> State eventType(eventType) = {view1}</p>
        {/*<!--Start of all the Area -->*/}
        {/*<!-- Button of the mobile view collapse -->*/}
        <div className = "container filter pb-2" >
          <Row className = "d-md-none">
            <Col className = "col">
              <button className="btn btn-warning  m-2" type="button" data-toggle="collapse" data-target="#filter" aria-expanded="false"
              aria-controls="collapseExample">
            פתח    סינון אירועים
              </button>

            </Col>

          </Row>

          <div id = "crud-board" className = "collapse d-md-block">
            <Form className="needs-validation" novalidate>{/*start of the form block */}
              <Form.Row className="">{/*start of the form row of 3 columns*/}

                <Col xs ={12} md = {4} className = "">
                  <Form.Group className ="">
                    <Form.Label for="validationTooltip02">סוג האינפוט</Form.Label><br/>

                      <select  onChange = {
                        (ev )=>{console.log("ev = " + ev.target.value)} } >
                        <option>מסיבה</option>
                        <option>שיעור</option>
                        <option>מפגש</option>
                        <option>מאטצ'-אפ</option>
                        <option>טיול</option>
                      </select>

                    </Form.Group>

                </Col>

                <Col xs ={12} md = {4} className = "">
                  <Form.Group className ="">
                    <Form.Label for="validationTooltip02">סוג האינפוט</Form.Label><br/>

                    <MutipleInputLimited
                      name = "eventType"
                      limitation = {2}
                      choosedValues = {choosedValues}
                      onChange = {this.onMultipleChange}
                      optionsList = {this.defaultEventType}

                    />
                    </Form.Group>

                </Col>

                <Col xs ={12} md = {4} className = "">
                  <Form.Group className ="">
                    <Form.Label for="validationTooltip02">סוג האינפוט</Form.Label><br/>

                      <select  onChange = {
                        (ev )=>{console.log("ev = " + ev.target.value)} } >
                        <option>מסיבה</option>
                        <option>שיעור</option>
                        <option>מפגש</option>
                        <option>מאטצ'-אפ</option>
                        <option>טיול</option>
                      </select>

                    </Form.Group>

                </Col>

              </Form.Row>{/* End of the form rows of three cols*/}

                <Col xs = {12} md = {4} className="">

                  <MutipleInputLimited
                    name = "eventType"
                    limitation = {2}
                    choosedValues = {choosedValues}
                    onChange = {this.onMultipleChange}
                    optionsList = {this.defaultEventType}

                  />
                  <div class="valid-tooltip">
                    Looks good!
                  </div>
                </Col>
                <Col xs = {12} md = {4} className="">

                  <AddMoreValues
                    values = {this.state.eventType}
                    onChange = {this.onAddMoreValuesChange}
                    name = "eventType"
                    limitation = {3}
                    addMore = {this.addMoreInputs}

                  />
                  <div class="valid-tooltip">
                    Looks good!
                  </div>
                </Col>
                <div className="col-12 col-md-4">
                  <label for="validationTooltip02">מיועד ל-</label><br/>
                  <div class="form-group">

                    <select class="selectpicker" multiple data-actions-box="true">
                      <option>שומרי שבת</option>
                      <option>לא שומרי שבת</option>
                    </select>



                  </div>
                  <div class="valid-tooltip">
                    Looks good!
                  </div>
                </div>



                  <div className="col-12 col-md-4">
                    <label for="validationTooltip02">איזור בארץ</label><br/>
                    <div class="form-group">

                        <select class="selectpicker" multiple data-actions-box="true">
                          <option>ירושלים</option>
                          <option>המרכז</option>
                          <option>צפון רחוק</option>
                          <option>צפון קרוב</option>
                          <option>דרום</option>
                          <option>אילת והערבה</option>
                          <option>שומרון</option>

                        </select>
                      </div>



                  </div>



                  <div className="col-12 col-md-4">
                    <div className="form-group">
                      <p >לגילאים</p>
                      <div className = "form-row">
                        <div className = "col-6">
                          <label >מ-</label>
                          <input type="number" min = "0" max = "120" className="form-control" value="0" required/>

                        </div>
                        <div className = "col-6">


                          <label >עד -</label>
                          <input type="number" min = "0" max = "120" class="form-control" value="120" required/>
                        </div>

                      </div>
                    </div>

                  </div>
                  */}

                <div className="d-none d-md-inline col-md-4"></div>
                <div className="col-12 col-md-4">
                  <div className="form-group">
                    <p >בין תאריכים:</p>
                      <div classNameName = "form-row">
                        <div classNameName = "col-6">
                          <label >מ-</label>
                          <input type="date" className="form-control" value="2013-01-08" required/>

                        </div>
                        <div classNameName = "col-6">
                          <label >עד -</label>
                          <input type="date" className="form-control" value="2014-01-08" required/>
                        </div>
                      </div>
                    </div>
                </div>



              <button className="btn btn-primary" type="submit">סנן אירועים</button>
            </Form>{/*end of the form block */}
          </div>{/* <!--End of filter -->*/}
        </div>


      </div>
    )
  }


}
