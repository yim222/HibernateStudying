import React from 'react';

//Components and etc imports
import MainMenu from './MainMenu'
import MainRouter from '../routing/AppRouter'

export class MainView extends React.Component{

  constructor(props){
    super();

  }

  render(){


    return (
      <div >



<div >
  <div>
    <div>

      <MainRouter/>
      <footer >
        <MainMenu/>
      </footer>
    </div>



    <div className = "col-sm-4 sideArea">
    <h4>Side Area</h4>
  </div>
  </div>

</div>




      </div>
  );
  }

}
