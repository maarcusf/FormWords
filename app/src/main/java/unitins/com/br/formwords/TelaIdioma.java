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

public class TelaIdioma extends AGScene {
    //AGSprite labelBr = null;
    AGSprite botaoPortugues = null;
    // AGSprite labelEUA = null;
    AGSprite botaoInglês = null;

    AGSprite fundoIdioma = null;


    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     * ****************************************
     *
     * @param pManager
     */
    public TelaIdioma(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        fundoIdioma = createSprite(R.drawable.fundoescolhaidioma, 1, 1);
        fundoIdioma.setScreenPercent(100, 100);
        fundoIdioma.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));

        botaoPortugues = createSprite(R.drawable.brasil, 1, 1);
        botaoPortugues.setScreenPercent(30, 15);
        botaoPortugues.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2 * 1.5f));

        botaoInglês = createSprite(R.drawable.eua, 1, 1);
        botaoInglês.setScreenPercent(40, 20);
        botaoInglês.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2) * 0.6f);

    }

    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        if (AGInputManager.vrTouchEvents.screenClicked()) {

            //SE FOR ESCOLHIDO PORTUGUÊS É ESCOLHIDO A CENA DO PORTUGUÊS
            if (botaoPortugues.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                vrGameManager.setCurrentScene(1);
                return;
            }

            if (botaoInglês.collide(AGInputManager.vrTouchEvents.getLastPosition()))
            {
                vrGameManager.setCurrentScene(5);
                return;
            }

        }
    }
}

