import React, { Component } from 'react';

//import { Link, Switch, Route } from 'react-router-dom';

import Header from '../componentes/Header'

import Footer from '../componentes/Footer'
//import Competicao from "./Competicao";


class Conteudo_Equipes extends React.Component {
    constructor() {
        super();
        this.state = {
            nome: "",
            competicaoId: 0
        }
        this.onChange = (evento) => {
            const state = Object.assign({}, this.state);
            const campo = evento.target.name;
            state[campo] = evento.target.value;
            this.setState(state);
        }
        this.onSubmit = (evento) => {
            evento.preventDefault();
            fetch('http://localhost:8080/equipes/adiciona', {
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
                Nome da Equipe: <input name="nome" type = "text" value = {this.state.nome} onChange={this.onChange}/>

                <br /><br />
                Competição:
                <select name="competicaoId" onChange={this.onChange} value={this.state.competicaoId}>
                    <option value="">Selecione</option>
                    <option value="1">De Inverno</option>
                    <option value="2">De Verão</option>
                    <option value="3">De Primavera</option>
                    <option value="4">De Outono</option>
                </select>
                <br /><br />
                <button onClick={this.onSubmit}>Enviar</button>
            </div>
        )
    }
}



class Equipes extends Component {

    render() {
        return (
            <React.Fragment>
                <Header/>


                <Conteudo_Equipes/>
                <Footer/>

            </React.Fragment>
        );
    }
}

export default Equipes;