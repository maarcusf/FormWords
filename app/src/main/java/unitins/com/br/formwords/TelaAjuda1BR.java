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

public class TelaAjuda1BR extends AGScene
{
    AGSprite fundoSobre = null;
    AGSprite botaoVoltar = null;

    AGSprite botaoExemplo = null;


    public TelaAjuda1BR(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init()
    {
        // configura a cor da cena de menu para amarelo
        this.setSceneBackgroundColor(1,1,1);

        // CRIA UM ELEMENTO VISUAL POSICIONAL nA TELA
        fundoSobre = createSprite(R.drawable.telaajuda,1,1);
        fundoSobre.setScreenPercent(100,100);
        fundoSobre.vrPosition.setXY((AGScreenManager.iScreenWidth/2),(AGScreenManager.iScreenHeight/2));

        botaoVoltar = createSprite(R.drawable.voltar,1,1);
        botaoVoltar.setScreenPercent(25,10);
        botaoVoltar.vrPosition.setXY(AGScreenManager.iScreenWidth/4,AGScreenManager.iScreenHeight/8);

        botaoExemplo = createSprite(R.drawable.exemplo,1,1);
        botaoExemplo.setScreenPercent(25,10);
        botaoExemplo.vrPosition.setXY((AGScreenManager.iScreenWidth)*(0.7f), AGScreenManager.iScreenHeight/8);

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

        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            vrGameManager.setCurrentScene(1);
            return;
        }

        // verifica se foi dado um toque na tela
        if (AGInputManager.vrTouchEvents.screenClicked())
        {    // ao clicar na tela dispara o som carregado anteriormente

            if (botaoVoltar.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                vrGameManager.setCurrentScene(1);
                return;
            }
            if(botaoExemplo.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                vrGameManager.setCurrentScene(8); //arrumar aqui
                return;
            }

        }


    }
}
