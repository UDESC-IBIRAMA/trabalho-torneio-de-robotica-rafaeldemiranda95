import React, { Component } from 'react';

import { Link, Switch, Route } from 'react-router-dom';

import Header from '../componentes/Header'

import Footer from '../componentes/Footer'


class Conteudo_Home extends Component {
  render() {
    return (
      <div>

        {/* <div class="titulo"><h2>Veja os próximos eventos</h2></div>

        <div class="banner-principal">
       

       
       
        <div>
        <img src={Torneio}/>
        
        </div>
        <div>
        <img src={Torneio}/>
        
        </div>
        <div>
        <img src={Torneio}/>
        
        </div>
        </div> */}




        

 <div class="titulo"><h2>Realize o cadastro para o evento</h2></div>

        <div class="menu-cadastros">

        <ul class="lista-links">
          
          <li><Link to="/cadastro-equipes" style={{ textDecoration: 'none' }}><p>Clique aqui para cadastro de equipes</p></Link></li>

          <li><Link to="/cadastro-integrante-equipe"><p>Clique aqui para cadastrar integrantes das equipes</p></Link></li>
          <li><Link to="/cadastro-juizes"><p>Clique aqui para cadastrar juízes </p></Link></li>
          <li><Link to="/cadastro-categorias"><p>Clique aqui para cadastrar categorias </p></Link></li>
          <li><Link to="/cadastro-criterios"><p>Clique aqui para cadastrar critério/missões </p></Link></li>
          <li><Link to="/cadastro-alternativas"><p>Clique aqui para cadastrar alternativas</p></Link></li>
          <li><Link to="/cadastro-temporada"><p>Clique aqui para cadastrar temporada </p></Link></li>
          <li><Link to="/cadastro-competicao"><p>Clique aqui para cadastrar competição </p></Link></li>
          <li><Link to="/cadastro-arenas"><p>Clique aqui para cadastrar arenas </p></Link></li>
          <li><Link to="/cadastro-sala-avaliacao"><p>Clique aqui para cadastrar sala de avaliação </p></Link></li>
          
          
          </ul>
          </div>

        <div class="titulo"><h2>Realize o cadastro para o evento</h2></div>



      </div>
    );
  }
}


class Home extends Component {

  render() {
    return (
      <React.Fragment>
     <Header/>
  <Conteudo_Home/>
      <Footer/>
  
      </React.Fragment>
    );
  }
}

export default Home;
