package unitins.com.br.formwords;
/**
 * Created by mvini on 15/02/17.
 */
import unitins.com.br.formwords.AndGraph.AGGameManager;
import unitins.com.br.formwords.AndGraph.AGInputManager;
import unitins.com.br.formwords.AndGraph.AGScene;
import unitins.com.br.formwords.AndGraph.AGScreenManager;
import unitins.com.br.formwords.AndGraph.AGSoundManager;
import unitins.com.br.formwords.AndGraph.AGSprite;
import unitins.com.br.formwords.AndGraph.AGTimer;


public class TelaAbertura extends AGScene {

    AGTimer temporizador = null;
    AGSprite logoSi = null;
    AGSprite logoUnitins = null;
    AGSprite logoPrefeitura = null;
    int tempo;

    TelaAbertura(AGGameManager gerenciador){
        super(gerenciador);
    }

    //usado para ser chamada cena
    @Override
    public void init() {
        //cor de fundo da cena vermelha
        this.setSceneBackgroundColor(1,1,1);

        //maximo X e Y da tela
        int x = AGScreenManager.iScreenWidth;
        int y = AGScreenManager.iScreenHeight;

        //cria temporizador de 8 segundos
        tempo = 5000;
        temporizador = new AGTimer(tempo);

        //Logo Prefeitura
        logoPrefeitura = createSprite(R.drawable.prefeitura,1,1);
        logoPrefeitura.setScreenPercent(80,15);
        logoPrefeitura.vrPosition.setXY(x/2, y*0.65f);
        logoPrefeitura.fadeIn(tempo/2);

        //logo Unitins
        logoUnitins = createSprite(R.drawable.logounitins,1,1);
        logoUnitins.setScreenPercent(60,20);
        logoUnitins.vrPosition.setXY(x/2,y*0.9f);
        logoUnitins.fadeIn(tempo/2);
//x/2,y*0.9f
//(x/2,y - (y*0.75f))
        //logo SI
        logoSi = createSprite(R.drawable.logosi,1,1);
        logoSi.setScreenPercent(50,50);
        logoSi.vrPosition.setXY(x/2,y - (y*0.75f));
        logoSi.fadeIn(tempo/2);

        AGSoundManager.vrMusic.loadMusic("abertura.wav",true);
        AGSoundManager.vrMusic.play();
    }

    //usada para reestart da cena
    @Override
    public void restart() {

    }

    //usada para parar a cena
    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        temporizador.update();

        if( logoSi.fadeEnded()){
            logoSi.fadeOut((tempo/2)-500);
            logoUnitins.fadeOut((tempo/2)-500);
            logoPrefeitura.fadeOut((tempo/2)-500);
            AGSoundManager.vrMusic.stop();

        }
        if (temporizador.isTimeEnded()){
            vrGameManager.setCurrentScene(4);
            AGSoundManager.vrMusic.stop();
            return;
        }

        if (AGInputManager.vrTouchEvents.screenClicked()){
            vrGameManager.setCurrentScene(4);
            AGSoundManager.vrMusic.stop();
            return;
        }
    }
}
