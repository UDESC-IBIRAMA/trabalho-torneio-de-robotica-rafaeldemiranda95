import React, { Component } from 'react';

//import { Link, Switch, Route } from 'react-router-dom';

import Header from '../componentes/Header'

import Footer from '../componentes/Footer'
//import Competicao from "./Competicao";


class Conteudo_Juizes extends React.Component {
    constructor() {
        super();
        this.state = {
           categoriaId:0,
           cpf:"",
           dataNasc:"",
           nome:"",
           rg:""
        }
        this.onChange = (evento) => {
            const state = Object.assign({}, this.state);
            const campo = evento.target.name;
            state[campo] = evento.target.value;
            this.setState(state);
        }
        this.onSubmit = (evento) => {
            evento.preventDefault();
            fetch('http://localhost:8080/juizes/adiciona', {
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
               <label>Categoria Id</label> <input name="categoriaId" type = "text" value = {this.state.categoriaId} onChange={this.onChange}/>
               <label>CPF</label> <input name="cpf" type = "text" value = {this.state.cpf} onChange={this.onChange}/>
               <label>Data de Nascimento</label> <input name="dataNasc" type = "text" value = {this.state.dataNasc} onChange={this.onChange}/>
               <label>Nome</label> <input name="nome" type = "text" value = {this.state.nome} onChange={this.onChange}/>
               <label>RG</label> <input name="rg" type = "text" value = {this.state.rg} onChange={this.onChange}/>
                <button onClick={this.onSubmit}>Enviar</button>
            </div>
        )
    }
}



class Juizes extends Component {

    render() {
        return (
            <React.Fragment>
                <Header/>


                <Conteudo_Juizes/>
                <Footer/>

            </React.Fragment>
        );
    }
}

export default Juizes;