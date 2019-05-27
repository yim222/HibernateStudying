import React from 'react'
import { Link } from 'react-router-dom'

// The Header creates links that can be used to navigate
// between routes.
const MainMenu = () => (


      <ul  className="nav navbar-nav footer1">
        <li ><Link to='/'>Home</Link></li>
        <li><Link to='/locations'>Locations</Link></li>
        <li><Link to='/categories'>Categories</Link></li>
        <li><Link to='/socialEvents'>Social Events</Link></li>
        <li><Link to='/draft1'>Draft 1</Link></li>
        <li><Link to='/crudBoard'>CRUD Board</Link></li>
        <li><Link to='/filter'>Filter</Link></li>
      </ul>


)

export default MainMenu
