import React from 'react';
//import { Link, Router } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import {Container,Row, Col, Form} from 'react-bootstrap';
import a from  'bootstrap-select';
import Select from 'react-select';

export class MutipleInputLimited extends React.Component{
  /*
  Make input that have dropdown, with limited choicing options. If the user want to choose more he cannot .


  //array for using the data  :

   const options = [
     { value: 'chocolate', label: 'Chocolate' },
     { value: 'strawberry', label: 'Strawberry' },
     { value: 'vanilla', label: 'Vanilla' }
   ];


  */
  constructor(props){
    super()
  }


  onChange = (ev, meta) => {
    console.log(ev, "ev\n", meta , "meta" );
    if (this.props.choosedValues.length == this.props.limitation && (meta.action === "select-option")) {
      alert("אתה לא יכול לבחור יותר מ-" + this.props.limitation + " אפשרויות"  );
      return;
    }

    this.props.onChange(ev, meta);
  }
  render(){


    return (
      <div>

          <Select   isMulti
            MultiValueRemove
            options={this.props.optionsList} defaultMenuIsOpen = {false} closeMenuOnSelect = {false}
            placeholder  = "בחר"
            value = {this.props.choosedValues}
            onChange = {
                this.onChange
               }
              name = {this.props.name}
          />
        <div>
          {this.props.children}
        </div>



      </div>
    )
  }


}

export class AddMoreValues extends React.Component{

  constructor(props){
    super()
    this.state = {


    }
  }

  onChange = (ev) =>{
    this.props.onChange(ev, this.props.name);

  }

  addMore = () =>{

    if(this.props.values.length == this.props.limitation){
      alert("אתה לא יכול להוסיף יותר מ-" +this.props.limitation + " ערכים" );
      return;
    }
    this.props.addMore(this.props.name);

  }

  render(){
    console.log(this.props, "this.props");
    let inputsView = this.props.values.map((input , index) => {

      return (


        <Form.Control

              type="text"
              value = {this.props.values[index]}
              onChange = {this.onChange}
              name = {index}
            />
              )
    });
    return(

      <div>

        {inputsView}
        <Button onClick = {this.addMore} type="button" className="mt-2">
          <span className="glyphicon glyphicon-plus">&#x2b; הוספת ערך</span>
        </Button>






      </div>

    )
  }
}


export class MultipleLimitedWithAddMore extends React.Component{
  constructor(props){
    super()
    this.state = {
      values: []
    }

  }

  //onAddMoreValuesSave() - don't need saving.
  onAddMoreValuesSave = () =>{

    let arr1 = this.state.values;
    this.props.onAddMoreValuesSave (arr1, this.props.name);

  }

  addMoreInputs = ( propName) =>{

    let arr1 = this.state.values;
    arr1.push("new value");
    this.setState({
      values: arr1
    });
  }
  onAddMoreValuesChange = (ev, propName) =>{

    let arr1 = this.state[propName];
    arr1[ev.target.name] = ev.target.value;
    this.setState({
      [propName]: arr1
    });

  }



   render(){

     let addMoreLimitation = this.props.addMoreLimitation;
     return(
       <div>

         <MutipleInputLimited  {...this.props}>{/*Passing this.props to the child  */}
           {console.log(this.props)}

           <AddMoreValues
             values = {this.state.values}
             onChange = {(ev) => this.onAddMoreValuesChange(ev, "values")}
             name = {this.props.name}
             limitation = {this.props.addMoreLimitation}
             addMore = {this.addMoreInputs}

           />

           <Button  className = "mt-2"
             onClick = {
               this.onAddMoreValuesSave
             }

             >שמירת הערכים </Button>
         </MutipleInputLimited>







       </div>

     )
   }

}
