import React from 'react';
//import { Link, Router } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import {Container,Row, Col, Form, Fade, Collapse} from 'react-bootstrap';
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
    this.defaultsValues = SocialEventServices.prepareDefaultValues(defaultsValues);
    this.state = emptyValues;
    console.log ("initialData", initialData);
    console.log ("defaultsValues", defaultsValues);
    console.log ("emptyValues", emptyValues);
    //Add more values to the state
    this.state["open"] = false;
    //defaultsValues
    console.log(this.state , "state")


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

  onChange = (ev)=>{

    let name = ev.target.name;
    let value = ev.target.value;
    //alert(value);

    this.setState({
      [name]:value
    });

  }

  onNumbersChange(ev, index){
    let name = ev.target.name;
    let value = ev.target.value;
    let nums = this.state[name];

    nums[index] = value;
    this.setState({
      [name]:nums
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

    let switchContactLabel = (index) =>{


      let label = "";
      switch (index) {
      case 0:
        label =  'שם'
        break;
      case 1:
        label =  'טלפון'
        break;
      case 2:
        label = 'אימייל'
        break;
      }
      return label;

    }

    let showArray = (array) => {
      array.map(el =>{
        console.log(el.value + ", ")
      })
    }

    //alert(choosedValues.length );
    console.log("this.state.name = " , this.state.name);
    console.log("this.state.isOrganizer = " , this.state.isOrganizer);
    console.log("this.state.publisherName = " , this.state.publisherName);
    console.log("this.state.date = " , this.state.date);
    console.log("this.state.time = " , this.state.time + "");
    console.log("this.state.contactDetails = " , this.state.contactDetails + "");
    console.log("this.state.jewLvlKeep = " , showArray(this.state.jewLvlKeep) );
    console.log("this.state.agesRange = " , this.state.agesRange + "");
    console.log("this.state.eventType = " , showArray(this.state.eventType) );
    console.log("this.state.lowSelection = " , this.state.lowSelection + "");
    console.log("this.state.shortDescription = " , this.state.shortDescription + "");
    console.log("this.state.longDescription = " , this.state.longDescription + "");
    console.log("this.state.matchingIdea = " , showArray(this.state.matchingIdea) );
    console.log("this.state.eventLink = " , this.state.eventLink );
    console.log("this.state.imgUrl = " , this.state.imgUrl );
    console.log("this.state.area = " , showArray(this.state.area) );
    console.log("this.state.address = " , this.state.address );
    console.log("this.state.price = " , this.state.price );
    console.log("this.state.x = " , this.state.x );



    //console.log("this.state.jewLvlKeep = " , showArray(this.state.jewLvlKeep) );



    let stateData = this.state;

    //Important - don't delete :
    const { open } = this.state;//like const  open  = this.state.open;
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
              <button className="btn btn-warning  m-2" type="button"
                onClick={() => this.setState({ open: !open })}
                aria-controls="crud-board"
                aria-expanded={open}
                >
            פתח    סינון אירועים
              </button>

            </Col>

          </Row>
          <Collapse in={this.state.open}>
            <div id = "crud-board" className = "collapse d-md-block">
              <Form className="needs-validation" novalidate>{/*start of the form block */}
                <Form.Row className="">{/*start of the form row of 3 columns*/}

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group controlId = "eventName" className ="">
                      <Form.Label for="validationTooltip02">שם האירוע</Form.Label><br/>
                      <Form.Control
                         type="text" placeholder="שם האירוע"
                         value = {stateData.name}
                        onChange = {this.onChange} name = "name"
                        required
                      />


                    </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className =""  >
                      <Form.Label for="validationTooltip02">האם ממארגני האירוע?</Form.Label><br/>

                      <Form.Check
                        type={'radio'}
                        id= ""
                        label= ""
                        name = "isOrganizer"
                        onChange = {this.onChange}
                        value = {true}
                        required
                      />כן

                      <Form.Check
                        type={'radio'}
                        id= ""
                        label= ""
                        name = "isOrganizer"
                        onChange = {this.onChange}
                        value = {false}
                        required
                      />לא



                      </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="" controlId = "publisherName">
                      <Form.Label for="validationTooltip02">שם המפרסם (לא חובה)</Form.Label><br/>

                      <Form.Control type="text" placeholder= "שם המפרסם" value = {stateData.publisherName}
                        onChange = {this.onChange} name = "publisherName"
                      />


                      </Form.Group>

                  </Col>

                </Form.Row>{/* End of the form rows of three cols*/}

                <Form.Row className="">{/*start of the form row of 3 columns*/}

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="" controlId = "date">


                        <Col  className ="">
                          <Form.Label >תאריך האירוע</Form.Label>
                          <Form.Control
                             type="date" placeholder="תאריך"
                             value = {stateData.date}
                            onChange = {this.onChange} name = "date"
                            required
                          />

                        </Col>




                    </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="">
                      <Form.Label for="validationTooltip02">שעת האירוע</Form.Label><br/>
                      <Row>
                        <Col xs = {3}  className ="">
                          <Form.Label >דקות</Form.Label>
                          <Form.Control
                           type="number"
                           value = {stateData.time[1]}
                           onChange = {(ev) => {
                             this.onNumbersChange(ev,1);
                           }}
                           name = "time"
                           min = "0" max = "45"
                           step = {15}
                           required
                          />

                        </Col>


                        <Col xs = {3}  className ="">
                          <Form.Label >שעה</Form.Label>
                          <Form.Control
                           type="number"
                           value = {stateData.time[0]}
                           onChange = {(ev) => {
                             this.onNumbersChange(ev,0);
                           }}
                           name = "time"
                           min = "0" max = "23"
                           required
                          />

                        </Col>
                      </Row>
                    </Form.Group>
                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="">
                      <Form.Label for="validationTooltip02">פרטי קשר:</Form.Label><br/>

                        {stateData.contactDetails.map((e, index) =>
                          <div>
                            <Form.Label>
                              {switchContactLabel(index)}
                            </Form.Label><br/>
                            <Form.Control
                              type="text" placeholder={switchContactLabel(index)}
                              value = {e}
                              onChange = {(ev) => {
                                this.onNumbersChange(ev,index);
                              }}

                              name = "contactDetails"
                              key = {index}

                            />

                         </div>



                         )}

                      </Form.Group>

                  </Col>

                </Form.Row>{/* End of the form rows of three cols*/}

                <Form.Row className="">{/*start of the form row of 3 columns*/}

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="">
                      <Form.Label controlId="validationTooltip02">רמת שמירת יהדות</Form.Label><br/>

                      <MutipleInputLimited
                        name = "jewLvlKeep"
                        limitation = {102}
                        choosedValues = {stateData.jewLvlKeep}
                        onChange = {this.onMultipleChange}
                        optionsList = {this.defaultsValues.jewLvlKeep}

                      />

                      </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="">
                      <Form.Label for="validationTooltip02">טווח גילאים</Form.Label><br/>

                      <Row>
                        <Col xs = {3}  className ="">
                          <Form.Label >מ-</Form.Label>
                          <Form.Control
                           type="number"
                           value = {stateData.agesRange[0]}
                           onChange = {(ev) => {
                             this.onNumbersChange(ev,0);
                           }}
                           name = "agesRange"
                           min = "0" max = "120"

                           required
                          />

                        </Col>


                        <Col xs = {3}  className ="">
                          <Form.Label >עד-</Form.Label>
                          <Form.Control
                           type="number"
                           value = {stateData.agesRange[1]}
                           onChange = {(ev) => {
                             this.onNumbersChange(ev,1);
                           }}
                           name = "agesRange"
                           min = "0" max = "120"

                           required
                          />

                        </Col>
                      </Row>


                      </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="">
                      <Form.Label for="validationTooltip02">סוג האירוע ( 3 + 1)</Form.Label><br/>

                        <MultipleLimitedWithAddMore
                            name = "eventType"
                            limitation = {3}
                            choosedValues = {stateData.eventType}
                            onChange = {this.onMultipleChange}
                            optionsList = {this.defaultsValues.eventType}
                            onAddMoreValuesSave = {this.onAddMoreValuesSave}
                            values = {stateData.eventType}
                            addMoreLimitation = {1}



                          />


                      </Form.Group>

                  </Col>

                </Form.Row>{/* End of the form rows of three cols*/}

                <Form.Row className="">{/*start of the form row of 3 columns*/}

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="">
                      <Form.Label for="validationTooltip02">סלקציות נמוכות (עד 3)</Form.Label><br/>
                      <AddMoreValues
                        values = {stateData.lowSelection}
                        onChange = {this.onAddMoreValuesChange}
                        name = "lowSelection"
                        limitation = {3}
                        addMore = {this.addMoreInputs}

                      />
                      </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="" controlId = "shortDescription">
                      <Form.Label for="validationTooltip02">תיאור קצר -180 תווים*</Form.Label><br/>
                      <Form.Control
                         as = "textarea" placeholder="תיאור קצר (180 תווים)"
                         value = {stateData.shortDescription}
                        onChange = {this.onChange} name = "shortDescription"
                         rows= {3}
                         minlength = {20}
                         maxlength = {180}
                        required
                      />

                      </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="" controlId = "longDescription">
                      <Form.Label for="validationTooltip02">תיאור ארוך - לא חובה</Form.Label><br/>

                      <Form.Control
                         as = "textarea" placeholder="תיאור ארוך - לא חובה"
                         value = {stateData.longDescription}
                        onChange = {this.onChange} name = "longDescription"
                         rows= {3}


                      />

                      </Form.Group>

                  </Col>

                </Form.Row>{/* End of the form rows of three cols*/}
                <Form.Row className="">{/*start of the form row of 3 columns*/}

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="" controlId = "matchingIdea">
                      <Form.Label for="validationTooltip02">רעיון ההיכרות  (  * + 2)</Form.Label><br/>
                      <MultipleLimitedWithAddMore
                          name = "matchingIdea"
                          limitation = {100}
                          choosedValues = {stateData.matchingIdea}
                          onChange = {this.onMultipleChange}
                          optionsList = {this.defaultsValues.matchingIdea}
                          onAddMoreValuesSave = {this.onAddMoreValuesSave}
                          values = {stateData.matchingIdea}
                          addMoreLimitation = {2}



                        />

                    </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="" controlId = "eventLink">
                      <Form.Label for="validationTooltip02">לינק לפרטים/הרשמה</Form.Label><br/>

                      <Form.Control
                         type="text" placeholder="לינק לפרטים/הרשמה"
                         value = {stateData.eventLink}
                        onChange = {this.onChange} name = "eventLink"
                        required
                      />
                      </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="" controlId = "imgUrl">
                      <Form.Label for="validationTooltip02">סוג התמונה (1-5)</Form.Label><br/>

                      <Form.Control
                         type="number" placeholder="מס' תמונה" min ={1} max = {5}
                         value = {stateData.imgUrl}
                        onChange = {this.onChange} name = "imgUrl"

                        required
                      />


                      </Form.Group>

                  </Col>

                </Form.Row>{/* End of the form rows of three cols*/}
                <Form.Row className="">{/*start of the form row of 3 columns*/}

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="" controlId = "area">
                      <Form.Label for="validationTooltip02">איזור בארץ</Form.Label><br/>

                      <MutipleInputLimited
                        name = "area"
                        limitation = {1}
                        choosedValues = {stateData.area}
                        onChange = {this.onMultipleChange}
                        optionsList = {this.defaultsValues.area}

                      />

                      </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="" controlId = "address">
                      <Form.Label for="validationTooltip02">כתובת (כולל פירוט דרכי הגעה)</Form.Label><br/>

                      <Form.Control
                         type="text" placeholder="כתובת"
                         value = {stateData.address}
                        onChange = {this.onChange} name = "address"
                        required
                      />

                      </Form.Group>

                  </Col>

                  <Col xs ={12} md = {4} className = "">
                    <Form.Group className ="" controlId = "price">
                      <Form.Label for="validationTooltip02">מחיר ( ערך שלילי = לא נמסר )</Form.Label><br/>

                        <Form.Control
                           type="text" placeholder="מחיר"
                           value = {stateData.price}
                          onChange = {this.onChange} name = "price"
                          required
                        />

                      </Form.Group>

                  </Col>

                </Form.Row>{/* End of the form rows of three cols*/}
                <Button type="submit">Submit form</Button>

                <button className="btn btn-primary" type="submit">סנן אירועים</button>
              </Form>{/*end of the form block */}
            </div>{/* <!--End of filter -->*/}
          </Collapse>
        </div>


      </div>
    )
  }


}
