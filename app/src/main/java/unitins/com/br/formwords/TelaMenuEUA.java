package unitins.com.br.formwords;

import unitins.com.br.formwords.AndGraph.AGGameManager;
import unitins.com.br.formwords.AndGraph.AGInputManager;
import unitins.com.br.formwords.AndGraph.AGScene;
import unitins.com.br.formwords.AndGraph.AGScreenManager;
import unitins.com.br.formwords.AndGraph.AGSoundManager;
import unitins.com.br.formwords.AndGraph.AGSprite;

/**
 * Created by mvini on 28/03/2017.
 */

public class TelaMenuEUA extends AGScene
{

    // atributos da classe
    AGSprite telaMenu = null;
    AGSprite botaoSair = null;
    AGSprite botaoJogar = null;
    AGSprite botaoSobre = null;
    AGSprite botaoAjuda = null;
    AGSprite botaoIdioma = null;
    AGSprite slogan = null;

    public TelaMenuEUA(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init()
    {
        // CRIA UM ELEMENTO VISUAL POSICIONAL nA TELA
        telaMenu = createSprite(R.drawable.fundo,1,1);
        telaMenu.setScreenPercent(100,100);
        telaMenu.vrPosition.setXY((AGScreenManager.iScreenWidth/2),(AGScreenManager.iScreenHeight/2));

        slogan = createSprite(R.drawable.slogan,1,1);
        slogan.setScreenPercent(40,20);
        slogan.vrPosition.setXY((AGScreenManager.iScreenWidth/2),(AGScreenManager.iScreenHeight/2)*1.8f );

        botaoJogar = createSprite(R.drawable.play,1,1);
        botaoJogar.setScreenPercent(25,10);
        botaoJogar.vrPosition.setXY(AGScreenManager.iScreenWidth/2,(AGScreenManager.iScreenHeight/2)*1.3f );

        botaoAjuda = createSprite(R.drawable.help,1,1);
        botaoAjuda.setScreenPercent(25,10);
        botaoAjuda.vrPosition.setXY(AGScreenManager.iScreenWidth/2,(AGScreenManager.iScreenHeight/2 * 1.05f));

        botaoSobre = createSprite(R.drawable.about,1,1);
        botaoSobre.setScreenPercent(28,10);
        botaoSobre.vrPosition.setXY(AGScreenManager.iScreenWidth/2,((AGScreenManager.iScreenHeight/2) *0.8f));

        botaoSair = createSprite(R.drawable.exit,1,1);
        botaoSair.setScreenPercent(25,10);
        botaoSair.vrPosition.setXY(AGScreenManager.iScreenWidth/2,(AGScreenManager.iScreenHeight/2) *0.6f);

        botaoIdioma = createSprite(R.drawable.language,1,1);
        botaoIdioma.setScreenPercent(28,12);
        botaoIdioma.vrPosition.setXY(AGScreenManager.iScreenWidth/2, (AGScreenManager.iScreenHeight/6));

    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop()
    {
        // verifica se foi dado um toque na tela
        if (AGInputManager.vrTouchEvents.screenClicked())
        {
            // verifica se esse toque foi dentro da area do botÃ£o Jogar
            if(botaoJogar.collide(AGInputManager.vrTouchEvents.getLastPosition())){
                vrGameManager.setCurrentScene(2);
                return;
            }
            if(botaoSobre.collide(AGInputManager.vrTouchEvents.getLastPosition())){
                vrGameManager.setCurrentScene(6);
                return;
            }
            if(botaoIdioma.collide(AGInputManager.vrTouchEvents.getLastPosition())){
                vrGameManager.setCurrentScene(4);
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
