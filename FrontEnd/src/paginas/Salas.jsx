import React, { Component } from 'react';

//import { Link, Switch, Route } from 'react-router-dom';

import Header from '../componentes/Header'

import Footer from '../componentes/Footer'
//import Competicao from "./Competicao";


class Conteudo_Salas extends React.Component {
    constructor() {
        super();
        this.state = {
            nome: "",
            categoriaId:0,
            competicaoId:0
        }
        this.onChange = (evento) => {
            const state = Object.assign({}, this.state);
            const campo = evento.target.name;
            state[campo] = evento.target.value;
            this.setState(state);
        }
        this.onSubmit = (evento) => {
            evento.preventDefault();
            fetch('http://localhost:8080/salas/adiciona', {
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
           <label>Nome</label> <input name="nome" type = "text" value = {this.state.nome} onChange={this.onChange}/>
           <label>Competição ID</label> <input name="competicaoId" type = "text" value = {this.state.competicaoId} onChange={this.onChange}/>
           <label>Categoria ID</label> <input name="categoriaId" type = "text" value = {this.state.categoriaId} onChange={this.onChange}/>
                <button onClick={this.onSubmit}>Enviar</button>
            </div>
        )
    }
}



class Salas extends Component {

    render() {
        return (
            <React.Fragment>
                <Header/>


                <Conteudo_Salas/>
                <Footer/>

            </React.Fragment>
        );
    }
}

export default Salas;