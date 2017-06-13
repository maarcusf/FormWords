package unitins.com.br.formwords;

import unitins.com.br.formwords.AndGraph.AGGameManager;
import unitins.com.br.formwords.AndGraph.AGInputManager;
import unitins.com.br.formwords.AndGraph.AGScene;
import unitins.com.br.formwords.AndGraph.AGScreenManager;
import unitins.com.br.formwords.AndGraph.AGSoundManager;
import unitins.com.br.formwords.AndGraph.AGSprite;

/**
 * Created by mvini on 04/03/2017.
 */

public class TelaMenuBR extends AGScene {

    // atributos da classe
    AGSprite telaMenu = null;
    AGSprite botaoSair = null;
    AGSprite botaoJogar = null;
    AGSprite botaoSobre = null;
    AGSprite botaoAjuda = null;
    AGSprite slogan = null;
    AGSprite botaoIdioma = null;




    int codigoSom1 = 0;

    TelaMenuBR(AGGameManager gerenciador){

        super(gerenciador);
    }
    // chamado sempre que a cena for chamada
    public void init()
    { // colocar o que serÃ¡ necessario para a cena


        // configura a cor da cena de menu para amarelo
        this.setSceneBackgroundColor(1,1,1);

        // CRIA UM ELEMENTO VISUAL POSICIONAL nA TELA
        telaMenu = createSprite(R.drawable.fundo,1,1);
        telaMenu.setScreenPercent(100,100);
        telaMenu.vrPosition.setXY((AGScreenManager.iScreenWidth/2),(AGScreenManager.iScreenHeight/2));

        slogan = createSprite(R.drawable.slogan,1,1);
        slogan.setScreenPercent(40,20);
        slogan.vrPosition.setXY((AGScreenManager.iScreenWidth/2),(AGScreenManager.iScreenHeight/2)*1.8f );

        botaoJogar = createSprite(R.drawable.jogar,1,1);
        botaoJogar.setScreenPercent(25,10);
        botaoJogar.vrPosition.setXY(AGScreenManager.iScreenWidth/2,(AGScreenManager.iScreenHeight/2)*1.3f );

        botaoAjuda = createSprite(R.drawable.ajuda,1,1);
        botaoAjuda.setScreenPercent(25,10);
        botaoAjuda.vrPosition.setXY(AGScreenManager.iScreenWidth/2,((AGScreenManager.iScreenHeight/2)) * 1.05f);

        botaoSobre = createSprite(R.drawable.sobre,1,1);
        botaoSobre.setScreenPercent(25,10);
        botaoSobre.vrPosition.setXY(AGScreenManager.iScreenWidth/2,((AGScreenManager.iScreenHeight/2) *0.8f));

        botaoSair = createSprite(R.drawable.sair,1,1);
        botaoSair.setScreenPercent(25,10);
        botaoSair.vrPosition.setXY(AGScreenManager.iScreenWidth/2,(AGScreenManager.iScreenHeight/2)* 0.6f);

        botaoIdioma = createSprite(R.drawable.idioma,1,1);
        botaoIdioma.setScreenPercent(25,10);
        botaoIdioma.vrPosition.setXY(AGScreenManager.iScreenWidth/2, (AGScreenManager.iScreenHeight/6));

        // carrega um efeito de som
        codigoSom1 = AGSoundManager.vrSoundEffects.loadSoundEffect("tiro.mp3");

    }

    @Override
    // chamado quando a aplicaÃ§Ã£o voltar de uma  interrupÃ§Ã£o
    public void restart()
    {

    }

    @Override
    // chamado quando a aplicaÃ§Ã£o sofrer uma interrupÃ§Ã£o
    public void stop()
    {

    }

    @Override
    // chamado x vezes por segundo
    // implementar a logica da cena
    public void loop()
    {
        // verifica se foi dado um toque na tela
        if (AGInputManager.vrTouchEvents.screenClicked())
            {    // ao clicar na tela dispara o som carregado anteriormente
            AGSoundManager.vrSoundEffects.play(codigoSom1);
            // verifica se esse toque foi dentro da area do botÃ£o Jogar
            if(botaoJogar.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                vrGameManager.setCurrentScene(2);
                return;
            }
            if(botaoSobre.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                vrGameManager.setCurrentScene(3);
                return;
            }
             if(botaoIdioma.collide(AGInputManager.vrTouchEvents.getLastPosition()))
             {
                    vrGameManager.setCurrentScene(4);
                    return;
             }
             if(botaoAjuda.collide(AGInputManager.vrTouchEvents.getLastPosition()))
             {
                 vrGameManager.setCurrentScene(7);
                 return;
             }
             if(botaoSair.collide(AGInputManager.vrTouchEvents.getLastPosition()))
                {
//                AGSoundManager.vrSoundEffects.play(codSom2);
                    AGSoundManager.vrMusic.stop();
                    vrGameManager.vrActivity.finish();
                }
        }



    }
}
