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

public class TelaAjuda2BR extends AGScene
{
    AGSprite fundoAjuda = null;
    AGSprite botaoVoltar = null;



    public TelaAjuda2BR(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init()
    {
        // configura a cor da cena de menu para amarelo
        this.setSceneBackgroundColor(1,1,1);

        // CRIA UM ELEMENTO VISUAL POSICIONAL nA TELA
        fundoAjuda = createSprite(R.drawable.telaajuda2,1,1);
        fundoAjuda.setScreenPercent(100,100);
        fundoAjuda.vrPosition.setXY((AGScreenManager.iScreenWidth/2),(AGScreenManager.iScreenHeight/2));

        botaoVoltar = createSprite(R.drawable.voltar,1,1);
        botaoVoltar.setScreenPercent(25,10);
        botaoVoltar.vrPosition.setXY(AGScreenManager.iScreenWidth/4,AGScreenManager.iScreenHeight/10);

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
                vrGameManager.setCurrentScene(7);
                return;
            }
        }


    }
}
