package unitins.com.br.formwords;

import android.os.Bundle;
import unitins.com.br.formwords.AndGraph.*;


public class TelaPrincipal extends AGActivityGame
{



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // INSTANCIAR OOBJETO RESPONSAVEL PELO GERENCIAMNETO DE CENAS
        //O PARAMETRO BOOLEANO(TRUE) SERVE PARA PROJETO  QUE USAM ACELEROMETRO
        this.vrManager = new AGGameManager(this,false);

        TelaAbertura abertura = new TelaAbertura(vrManager);
        TelaMenuBR menuBR = new TelaMenuBR(vrManager);
        TelaAnimacaoBR animacao = new TelaAnimacaoBR(vrManager);
        TelaSobreBR sobreBR = new TelaSobreBR(vrManager);
        TelaIdioma idioma = new TelaIdioma(vrManager);
        TelaMenuEUA menuEUA = new TelaMenuEUA(vrManager);
        TelaSobreEUA sobreEUA = new TelaSobreEUA(vrManager);
        TelaAjuda1BR telaAjuda1BR = new TelaAjuda1BR(vrManager);
        TelaAjuda2BR telaAjuda2BR = new TelaAjuda2BR(vrManager);
        TelaAjuda1EUA telaAjuda1EUA = new TelaAjuda1EUA(vrManager);
        TelaAjuda2EUA telaAjuda2EUA = new TelaAjuda2EUA(vrManager);

        //REGISTRA AS CENAS NO REGENTE DE CENAS
        vrManager.addScene(abertura); // RESPONDE PELO INDICE 0
        vrManager.addScene(menuBR);    // RESPONDE PELO INDICE 1
        vrManager.addScene(animacao);// RESPONDE PELO INDICE 2
        vrManager.addScene(sobreBR); //   RESPONDE PELO INDICE 3
        vrManager.addScene(idioma); //Responde pelo INDICE 4
        vrManager.addScene(menuEUA); //Responde pelo INDICE 5
        vrManager.addScene(sobreEUA); //RESPONDE PELO INDICE 6
        vrManager.addScene(telaAjuda1BR); //RESPONDE PELO ÍNDICE 7
        vrManager.addScene(telaAjuda2BR); //RESPONDE PELO ÍNDICE 8
        vrManager.addScene(telaAjuda1EUA); //RESPONDE PELO ÍNDICE 9
        vrManager.addScene(telaAjuda2EUA); //RESPONDE PELO ÍNDICE 10

    }
}
