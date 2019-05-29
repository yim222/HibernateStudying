import React from 'react'
import { Switch, Route } from 'react-router-dom'
import {Screen1} from '../screens/Screen1'
import SubApp from '../entities/subApp/SubApp'






const MainRouter = () => (
  <main>
    <Switch>
      {/*<Route exact path='/' component={Locations}/>*/}
      <Route path='/screen1' component={Screen1}/>   
      <Route path='/subApp' component={SubApp}/>
    </Switch>
  </main>
)

export default MainRouter
