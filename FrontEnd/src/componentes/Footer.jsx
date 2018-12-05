import React, { Component } from 'react';
import Robos from '../img/Robos.png'; 
import Legoman from '../img/Legoman.png'
class Footer extends Component {
    render() {
        return (
            <footer>

            <div class="rodape">
            
            <div class="imagem-rodape">
            
            <img src={Robos}/>
            
            </div>


            <div>Competição de Robótica</div>


            <div class="imagem-rodape">
            
            <img src={Robos}/>
            
            </div>
          
            
            
            </div>

     
            </footer>

        );
    }
}

export default Footer;
