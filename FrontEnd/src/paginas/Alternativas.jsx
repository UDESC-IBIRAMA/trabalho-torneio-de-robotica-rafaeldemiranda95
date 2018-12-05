import React, { Component } from 'react';

//import { Link, Switch, Route } from 'react-router-dom';

import Header from '../componentes/Header'

import Footer from '../componentes/Footer'
//import Competicao from "./Competicao";


class Conteudo_Alternativas extends React.Component {
    constructor() {
        super();
        this.state = {
            valor:"",
            criterioId:0
        }
        this.onChange = (evento) => {
            const state = Object.assign({}, this.state);
            const campo = evento.target.name;
            state[campo] = evento.target.value;
            this.setState(state);
        }
        this.onSubmit = (evento) => {
            evento.preventDefault();
            fetch('http://localhost:8080/alternativas/adiciona', {
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
            <div>
                <label>Valor</label> <input name="valor" type = "text" value = {this.state.valor} onChange={this.onChange}/>
                <label>Criterio ID</label> <input name="criterioId" type = "text" value = {this.state.criterioId} onChange={this.onChange}/>
                
                <button onClick={this.onSubmit}>Enviar</button>
            </div>
        )
    }
}



class Alternativas extends Component {

    render() {
        return (
            <React.Fragment>
                <Header/>


                <Conteudo_Alternativas/>
                <Footer/>

            </React.Fragment>
        );
    }
}

export default Alternativas;