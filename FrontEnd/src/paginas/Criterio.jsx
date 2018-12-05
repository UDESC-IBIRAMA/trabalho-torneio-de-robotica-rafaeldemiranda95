import React, { Component } from 'react';

//import { Link, Switch, Route } from 'react-router-dom';

import Header from '../componentes/Header'

import Footer from '../componentes/Footer'
//import Competicao from "./Competicao";


class Conteudo_Criterio extends React.Component {
    constructor() {
        super();
        this.state = {
            nome: "",
            categoriaId:"",
            descricao:""
        }
        this.onChange = (evento) => {
            const state = Object.assign({}, this.state);
            const campo = evento.target.name;
            state[campo] = evento.target.value;
            this.setState(state);
        }
        this.onSubmit = (evento) => {
            evento.preventDefault();
            fetch('http://localhost:8080/criterios/adiciona', {
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
            <div  class="conteudo">
                <label>Descrição</label> <input name="descricao" type = "text" value = {this.state.descricao} onChange={this.onChange}/>
                <label>Nome</label> <input name="nome" type = "text" value = {this.state.nome} onChange={this.onChange}/>
                <label>Categoria Id</label> <input name="categoriaId" type = "text" value = {this.state.categoriaId} onChange={this.onChange}/>
                <button onClick={this.onSubmit}>Enviar</button>
            </div>
        )
    }
}



class Criterio extends Component {

    render() {
        return (
            <React.Fragment>
                <Header/>


                <Conteudo_Criterio/>
                <Footer/>

            </React.Fragment>
        );
    }
}

export default Criterio;