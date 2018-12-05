import React, { Component } from 'react';

//import { Link, Switch, Route } from 'react-router-dom';

import Header from '../componentes/Header'

import Footer from '../componentes/Footer'
//import Competicao from "./Competicao";
import Equipes from './Juizes';


class Conteudo_Integrante_Equipes extends React.Component {
    constructor() {
        super();
        this.state = {
            nome: "",
            autorizado: true,
            cpf: "",
            equipeId:0,
            datNasc: "",
            nomeMae: "",
            rg: ""
        }
        this.onChange = (evento) => {
            const state = Object.assign({}, this.state);
            const campo = evento.target.name;
            state[campo] = evento.target.value;
            this.setState(state);
        }
        this.onSubmit = (evento) => {
            evento.preventDefault();
            fetch('http://localhost:8080/integrantes/adiciona', {
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
             
                <label>RG:</label> <input name="rg" type = "text" value = {this.state.rg} onChange={this.onChange}/>
                <label>CPF:</label> <input name="cpf" type = "text" value = {this.state.cpf} onChange={this.onChange}/>
                <label>Data de Nascimento</label> <input name="datNasc" type = "text" value = {this.state.datNasc} onChange={this.onChange}/>
                {/* <label>Nome do Pai</label> <input name="nome" type = "text" value = {this.state.nome} onChange={this.onChange}/> */}
                <label>Equipe ID</label> <input required name="equipeId" type = "text" value = {this.state.equipeID} onChange={this.onChange}/>
                <label>Nome da Mãe</label> <input required name="nomeMae" type = "text" value = {this.state.nomeMae} onChange={this.onChange}/>
                <label>Autorização</label>  <input type="radio" name="autorizado" value = {this.state.autorizado} onChange={this.onChange}/>

                <button onClick={this.onSubmit}>Enviar</button>
            </div>
        )
    }
}



class Integrante_Equipes extends Component {

    render() {
        return (
            <React.Fragment>
                <Header/>


                <Conteudo_Integrante_Equipes/>
                <Footer/>

            </React.Fragment>
        );
    }
}

export default Integrante_Equipes;