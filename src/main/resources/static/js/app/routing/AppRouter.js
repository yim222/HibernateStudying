import React from 'react'
import { Switch, Route } from 'react-router-dom'
import {Locations} from '../entities/location/components/Locations'
import {Categories} from '../entities/category/components/Categories'
import {Location} from '../entities/location/components/Location'
import {SocialEvents} from '../entities/socialEvent/components/SocialEvents'
import {Draft1} from '../entities/draftEntity/Draft'
import {CrudBoard} from '../entities/socialEvent/components/CrudBoard'
import {Filter} from '../entities/socialEvent/components/Filter'






const MainRouter = () => (
  <main>
    <Switch>
      <Route exact path='/' component={Locations}/>
      <Route path='/locations' component={Locations}/>
      <Route path='/categories' component={Categories}/>
        <Route path='/socialEvents' component={SocialEvents}/>
        <Route path='/draft1' component={Draft1}/>
        <Route path='/crudBoard' component={CrudBoard}/>
        <Route path='/filter' component={Filter}/>

    </Switch>
  </main>
)

export default MainRouter
