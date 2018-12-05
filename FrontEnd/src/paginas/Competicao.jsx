import React, { Component } from 'react';

//import { Link, Switch, Route } from 'react-router-dom';

import Header from '../componentes/Header'

import Footer from '../componentes/Footer'
//import Competicao from "./Competicao";


class Conteudo_Competicao extends React.Component {
    constructor() {
        super();
        this.state = {
            cidade:"",
            dataEvento:"",
            responsavel:"",
            temporadaId:"",
            tipo:"",
            uf:""
        }
        this.onChange = (evento) => {
            const state = Object.assign({}, this.state);
            const campo = evento.target.name;
            state[campo] = evento.target.value;
            this.setState(state);
        }
        this.onSubmit = (evento) => {
            evento.preventDefault();
            fetch('http://localhost:8080/competicoes/adiciona', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(this.state)
            })
        }
    }

    render(){
        return(
            <div class="conteudo">
              <label>Cidade</label> <input name="cidade" type = "text" value = {this.state.cidade} onChange={this.onChange}/>
              <label>Data do Evento</label> <input name="dataEvento" type = "text" value = {this.state.dataEvento} onChange={this.onChange}/>
              <label>Responsável</label> <input name="responsavel" type = "text" value = {this.state.responsavel} onChange={this.onChange}/>
              <label>Temporada Id</label> <input name="temporadaId" type = "text" value = {this.state.temporadaId} onChange={this.onChange}/>
              <label>Tipo</label> <input name="tipo" type = "text" value = {this.state.tipo} onChange={this.onChange}/>
              <label>UF</label> <input name="uf" type = "text" value = {this.state.uf} onChange={this.onChange}/>
                <button onClick={this.onSubmit}>Enviar</button>
            </div>
        )
    }
}



class Competicao extends Component {

    render() {
        return (
            <React.Fragment>
                <Header/>


                <Conteudo_Competicao/>
                <Footer/>

            </React.Fragment>
        );
    }
}

export default Competicao;