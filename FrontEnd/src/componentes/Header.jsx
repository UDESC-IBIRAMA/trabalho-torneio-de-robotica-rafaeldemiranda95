import React, { Component } from 'react';
import Logo from '../img/Logo.jpg';
import { Link, Switch, Route } from 'react-router-dom';


class Header extends Component {
  render() {
    return (
        <header>

    <div class="barra-navegacao">

      <div class="logo-barra">
     <Link to="/"> <img src={Logo}/></Link>
       <div><h2 id="titulo-logo">Competição de Robótica</h2></div>
      </div>


    <div class="menu-barra">
    
   
    
    
    </div>
    
    
    </div> 
    
        </header>
     
    );
  }
}

export default Header;
