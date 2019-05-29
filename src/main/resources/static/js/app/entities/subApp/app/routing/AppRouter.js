import React from 'react'
import { Switch, Route } from 'react-router-dom'
import {SocialEvents} from '../entities/socialEvent/components/SocialEvents'
import {CrudBoard} from '../entities/socialEvent/components/CrudBoard'
import {Filter} from '../entities/socialEvent/components/Filter'






const MainRouter = () => (
  <main>
    <Switch>
     
        <Route path='/socialEvents' component={SocialEvents}/>
        <Route path='/crudBoard' component={CrudBoard}/>
        <Route path='/filter' component={Filter}/>

    </Switch>
  </main>
)

export default MainRouter
