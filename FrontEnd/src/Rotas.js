import React, { Component } from 'react';
import { Link, Switch, Route } from 'react-router-dom';

import Home from './paginas/Home';
import Equipes from './paginas/Equipes';
import Criterio from './paginas/Criterio';
import Integrante_Equipe from './paginas/Integrante_Equipe';
import Juizes from './paginas/Juizes';
import Temporada from './paginas/Temporada';
import Alternativas from './paginas/Alternativas';
import Arenas from './paginas/Arenas';
import Competicao from './paginas/Competicao';
import Reavaliacoes from './paginas/Reavaliacoes';
import Categorias from './paginas/Categorias';




export default class Rotas extends Component {
    render() {
        return (
            <div>
                <Switch>
                    <Route exact path='/' component={Home} />
                    <Route exact path='/cadastro-equipes' component={Equipes} />
                    <Route exact path='/cadastro-criterios' component={Criterio} />
                    <Route exact path='/cadastro-integrante-equipe' component={Integrante_Equipe} />
                    <Route exact path='/cadastro-juizes' component={Juizes} />
                    <Route exact path='/cadastro-temporada' component={Temporada} />
                    <Route exact path='/cadastro-alternativas' component={Alternativas} />
                    <Route exact path='/cadastro-competicao' component={Competicao} />
                    <Route exact path='/cadastro-arenas' component={Arenas} />

                    <Route exact path='/cadastro-reavaliacoes' component={Reavaliacoes} />
                    <Route exact path='/cadastro-categorias' component={Categorias} />
                </Switch>
            </div>
        );
    }
}