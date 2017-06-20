package unitins.com.br.formwords;

/**
 * Created by mvini on 04/03/2017.
 */

import android.content.Context;
import android.widget.Toast;

import unitins.com.br.formwords.AndGraph.AGGameManager;
import unitins.com.br.formwords.AndGraph.AGInputManager;
import unitins.com.br.formwords.AndGraph.AGScene;
import unitins.com.br.formwords.AndGraph.AGScreenManager;
import unitins.com.br.formwords.AndGraph.AGSoundManager;
import unitins.com.br.formwords.AndGraph.AGSprite;
import unitins.com.br.formwords.AndGraph.AGTimer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class TelaAnimacaoBR extends AGScene {

    // atributos da classe
    AGSprite alfabeto = null;
    AGSprite palavra = null;
    AGSprite btCancel = null;
    AGSprite btConfirm = null;


    ArrayList<AGSprite>vetorBotao = null;
    ArrayList<AGSprite>vetorObjetos = null;
    ArrayList<AGSprite>vetorPalavra = null;
    ArrayList<String>palavrasFormadas = null;

    AGTimer apresentacao;
    //contadores de ponto
    AGSprite[] placar = null;
    AGSprite[] acert = null;
    AGSprite[] err = null;
    AGSprite[] rtk = null;
    AGSprite[] cronometro = null;
    int estado;

    Boolean ativo = true;
    Boolean primeiraVez = true;

    int inicia = 0;

    //pegar o valor maximo de x e Y
    int maxW;
    int maxH;

    //incremente de posição x e y
    int desWX;
    int desHY;
    int posWX;
    int posHY;

    //indices do jogo
    int pontos;
    int acertos;
    int erros;
    int retake;
    int apresent;
    int tempo;
    int cont;

    //Sons de efeito do jogo
    int errou = 0;
    int acertou = 0;
    int palavrarepetida = 0;
    int acabou = 0;

    //Indicar quantas vezes já chamou a função para formar palavras
    int formacaoPalavras;

    String[] possibilidades = {"a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z"};
    private Context resources;

    TelaAnimacaoBR(AGGameManager gerenciador){
        super(gerenciador);
        TelaAnimacaoBR vrContexto = this;
    }
    // chamado sempre que a cena for chamada
    public void init()
    {
        apresentacao = new AGTimer(1000);
        // colocar o que serÃ¡ necessario para a cena
//CRIA OS PLACARES
        placar = new AGSprite[4];
        acert = new AGSprite[4];
        err = new AGSprite[4];
        rtk = new AGSprite[4];
        cronometro = new AGSprite[4];

        //Sons do jogo
        errou = AGSoundManager.vrSoundEffects.loadSoundEffect("errou.mp3");
        acertou = AGSoundManager.vrSoundEffects.loadSoundEffect("acertou.mp3");
        palavrarepetida = AGSoundManager.vrSoundEffects.loadSoundEffect("palavrarepetida.mp3");
        acabou = AGSoundManager.vrSoundEffects.loadSoundEffect("acabou.mp3");


        //PEGA O TAMANHO DA TELA
        maxW = AGScreenManager.iScreenWidth;
        maxH = AGScreenManager.iScreenHeight;

        //SCORES DO JOGO
        pontos = 0000;
        acertos = 0;
        erros = 0;
        cont = 0;
        retake = 2;
        tempo = 60;
        estado = 0;
        /**
         * valores de identificação do estado do jogo
         * 0 :jogo normal
         * -1: perdedo o jogo
         * 1: ganhando o jogo
         * 2: ganhou
         * -2: perdeu
         */

        // configura a cor da cena de menu para azul
        this.setSceneBackgroundColor(0,1,1);

        palavrasFormadas = new ArrayList<String>();

        //  INSTANCIA UM ARRAYDE OBJETOS DO TIPO AGSPRITE
        vetorObjetos = new ArrayList<AGSprite>();

        vetorPalavra = new ArrayList<AGSprite>();
        formacaoPalavras = 0;

        vetorBotao = new ArrayList<AGSprite>();


        //CRIA OS PLACARES
        criarMarcadores();

        desHY = maxH - (int) (rtk[0].getSpriteHeight() / 2);
        desWX = (int) (rtk[0].getSpriteWidth() / 2);
        //placar principal posicao x
        posWX = (maxW / 2);
        posHY = maxW - (maxW / 4);
        for (int i = 0; i < placar.length; i++) {
            if (i == 0) {
                rtk[i].vrPosition.setXY(maxW - desWX, desHY);
                acert[i].vrPosition.setXY(desWX, desHY);
                err[i].vrPosition.setXY(maxW / 4, desHY);
                cronometro[i].vrPosition.setXY(posHY, desHY);
            } else if (i == 1) {
                acert[i].vrPosition.setXY(desWX + (float) (desWX * 1.5), desHY);
                err[i].vrPosition.setXY(((maxW / 4) + (float) (desWX * 1.5)), desHY);
                cronometro[i].vrPosition.setXY(posHY, desHY);
            } else if (i == 2) {
                cronometro[i].vrPosition.setXY(posHY, desHY);
            }
            placar[i].vrPosition.setXY(posWX, desHY);
            posWX += (desWX * 1.5);
            posHY += (desWX * 1.5);

        }



        // instancia um array de objeto com 2 posiÃ§Ãµes
       /* placar = new AGSprite[2];
        for(int posicao = 0; posicao < 2; posicao++)
        {
            placar[posicao]= createSprite(R.drawable.fonte,4,4);
            placar[posicao].vrPosition.setXY(100*posicao+ 100,1100);

            // nÃ£o sera desenhado pela cena automaticamente
            placar[posicao].bAutoRender = false;

            for (int quadro = 0 ; quadro< 10; quadro++)
            {
                placar[posicao].addAnimation(1,true,quadro);
            }
        }*/

    }

    // rescreve o metodo de desenho da cena
    // desenha primeiro todos os objetos autorender true
    // desenha  o restante dos elementos atraves da chamada ao metodo render
    // do objeto a ser desenhado por ultimo


    //renderiza os placares
    public void render() {
        super.render();
        rtk[0].render();
        acert[0].render();
        acert[1].render();
        err[0].render();
        err[1].render();
        placar[0].render();
        placar[1].render();
        placar[2].render();
        placar[3].render();
        cronometro[0].render();
        cronometro[1].render();
        cronometro[2].render();
    }


   /* public void render(){

        super.render();
        placar[0].render();
        placar[1].render();

    }*/

    //metodo que vai criar os placares
    private void criarMarcadores() {
        for (int i = 0; i < placar.length; i++) {
            placar[i] = createSprite(R.drawable.fonte, 4, 4);
            placar[i].setScreenPercent(4, 4);
            placar[i].bAutoRender = false;

            rtk[i] = createSprite(R.drawable.fonte, 4, 4);
            rtk[i].setScreenPercent(4, 4);
            rtk[i].bAutoRender = false;

            acert[i] = createSprite(R.drawable.fonte, 4, 4);
            acert[i].setScreenPercent(4, 4);
            acert[i].bAutoRender = false;

            err[i] = createSprite(R.drawable.fonte, 4, 4);
            err[i].setScreenPercent(4, 4);
            err[i].bAutoRender = false;

            cronometro[i] = createSprite(R.drawable.fonte, 4, 4);
            cronometro[i].setScreenPercent(4, 4);
            cronometro[i].bAutoRender = false;

            for (int quadro = 0; quadro < 10; quadro++) {
                rtk[i].addAnimation(1, true, quadro);
                placar[i].addAnimation(1, true, quadro);
                acert[i].addAnimation(1, true, quadro);
                err[i].addAnimation(1, true, quadro);
                cronometro[i].addAnimation(1, true, quadro);

            }


        }

    }

    //metodo ingrato que atualiza os marcadores
    private void atualizaMarcadores() {
        //retakes
        rtk[0].setCurrentAnimation(retake);
        //erros
        err[0].setCurrentAnimation(erros / 10);
        err[1].setCurrentAnimation(erros % 10);
        //acertos
        acert[0].setCurrentAnimation(acertos / 10);
        acert[1].setCurrentAnimation(acertos % 10);
        //placar
        if (pontos > 0) {
            placar[0].setCurrentAnimation(pontos / 1000);
            placar[1].setCurrentAnimation((pontos % 1000) / 100);
            placar[2].setCurrentAnimation((pontos % 100) / 10);
            placar[3].setCurrentAnimation(pontos % 10);
        } else {
            for (AGSprite dd : placar)
                dd.setCurrentAnimation(0);

        }
        //cronometro
        if (tempo > 0) {
            cronometro[0].setCurrentAnimation(tempo / 100);
            cronometro[1].setCurrentAnimation((tempo % 100) / 10);
            cronometro[2].setCurrentAnimation((tempo % 10));
        } else {
            for (AGSprite dd : cronometro)
                dd.setCurrentAnimation(0);
        }


    }

    // metodo que criar um objeto visual com base em uma posicao

    public void criaLetra() {

        /**
         * ************* FAZER O SORTEIO DAS LETRAS DE ACORDO COM OS RESULTADOS DAS MAIS EXISTENTES ****************
         */

        Random r = new Random();

        double[] probabilidades = {0.131386808, 0.018196808, 0.053796808, 0.041216808, 0.091656808,
                0.016376808, 0.020126808, 0.005279808, 0.086376808, 0.007176808, 0.005856808,
                0.040326808, 0.033366808, 0.054046808, 0.095636808, 0.027706808, 0.008216808,
                0.085286808, 0.048416808, 0.054496808, 0.031386808, 0.017586808, 0.004546808,
                0.007796808, 0.004596808, 0.009136808};

        float[] positionHorizontal = {0.2f, 0.7f, 1.2f, 1.7f,
                0.2f, 0.7f, 1.2f, 1.7f,
                0.2f, 0.7f, 1.2f, 1.7f,
                0.2f, 0.7f, 1.2f, 1.7f};

        float[] positionVertical = {1, 1, 1, 1,
                0.75f, 0.75f, 0.75f, 0.75f,
                0.5f, 0.5f, 0.5f, 0.5f,
                0.25f, 0.25f, 0.25f, 0.25f};

        int sorteios = 16;

        for (int i = 0; i < sorteios; i++) {
            double total = 0;
            double chanceSorteada = r.nextDouble(); // numero entre 0 e 1
            for (int j = 0; j < possibilidades.length; j++) {
                total += probabilidades[j];
                if (chanceSorteada <= total) {
                    System.out.println(possibilidades[j]);
                    alfabeto = createSprite(R.drawable.alphabet2, 7,8);
                    alfabeto.setScreenPercent(20, 10);
                    alfabeto.vrPosition.setXY(AGScreenManager.iScreenWidth/2*(positionHorizontal[i]),AGScreenManager.iScreenHeight/2*(positionVertical[i]));
                    alfabeto.addAnimation(1, false, j);
                    vetorObjetos.add(alfabeto);
                    break;
                }
            }
        }
    }

    //FORMAR PALAVRAS - ESSA AQUI VAI SER MAIS COMPLICADINHA.
    public void formarPalavra(int numPalavra)
    {
        //É preciso setar as posições horizontais e verticais.
        float[] positionHorizontal =
                {0.2f, 0.4f, 0.6f, 0.8f,
                        1.0f, 1.2f, 1.4f, 1.6f,
                        1.8f, 2.0f};

        palavra = createSprite(R.drawable.alphabet2, 7,8);
        palavra.setScreenPercent(14, 7);
        palavra.vrPosition.setXY(AGScreenManager.iScreenWidth/2*(positionHorizontal[formacaoPalavras]),
                AGScreenManager.iScreenHeight/2*(1.7f));
        palavra.addAnimation(1, false, numPalavra);
        vetorPalavra.add(palavra);

        formacaoPalavras++;
        if(ativo == true)
        {
            criaBtCancelConfirm();
        }

    }

    public void criaBtCancelConfirm()
    {
        btCancel = createSprite(R.drawable.cancel, 1,1);
        btCancel.setScreenPercent(18, 9);
        btCancel.vrPosition.setXY(AGScreenManager.iScreenWidth/2*(0.4f),AGScreenManager.iScreenHeight/2*(1.4f));
        btCancel.addAnimation(1, false, 1);
        vetorBotao.add(btCancel);

        btConfirm = createSprite(R.drawable.confirm, 1,1);
        btConfirm.setScreenPercent(20, 10);
        btConfirm.vrPosition.setXY(AGScreenManager.iScreenWidth/2*(1.6f),AGScreenManager.iScreenHeight/2*(1.4f));
        btConfirm.addAnimation(1, false, 1);
        vetorBotao.add(btConfirm);

        ativo = false;
    }

    public void cancelarPalavra()
    {
        vetorPalavra.remove(vetorPalavra.size()-1);

        //palavra.bRecycled = true;
        palavra.bVisible = false;

        //Quando não tiver mais palavra pra tirar sai da função, se não remove uma palavra.
        if(vetorPalavra.size() == 0)
        {
            formacaoPalavras = 0;
            return;

        }
        else {

            palavra = vetorPalavra.get(vetorPalavra.size() - 1);

            formacaoPalavras--;

            if (formacaoPalavras < 0) {
                formacaoPalavras = 0;
            }
        }
    }

    //Vai procurar no dicionário a existência da palavra
    public void confirmarPalavra() {
        String palavraFormada ="";
        int letra = 0;
        Boolean existePalavra = null;

        System.out.println("Botão Confirmar Clicado com sucesso!!!!!!!!!\n");

        System.out.println("Vetor palavras formadas contém: "+ vetorPalavra.size()+"\n");

        for(int i = 0; i < vetorPalavra.size(); i++)
        {
            letra = vetorPalavra.get(i).getCurrentAnimationFrame();

            palavraFormada += possibilidades[letra];
        }

        palavraFormada = palavraFormada.toUpperCase();
        System.out.println("\nPalavra formada: " +palavraFormada);
        int tamanhoPalavra = palavraFormada.length();

        try {
            existePalavra = procurarPalavraDicionario(palavraFormada);
            if (tamanhoPalavra == 1 || tamanhoPalavra == 2)
            {
                existePalavra = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Existe a palavra?
        if(existePalavra == true)
        {
            System.out.println("\nExiste a palavra... ");
            //É A PRIMEIRA PALAVRA A SER FORMADA??
            if(!primeiraVez)
            {
                //percorrer o array para ver se já foi formada a palavra
                for (int i =0; i < palavrasFormadas.size(); i++) {
                    if (palavraFormada.equals(palavrasFormadas.get(i).toString())) {
                        System.out.println("Essa palavra já foi selecionada...");
                        AGSoundManager.vrSoundEffects.play(palavrarepetida);
                        limpaPalavra(tamanhoPalavra);
                        return;
                    }
                }
            }

            //Adiciona em um arrayList as palavras que já foram formadas.
            palavrasFormadas.add(palavraFormada);

            primeiraVez = false;

            verfificaPontuacao(tamanhoPalavra);
            limpaPalavra(tamanhoPalavra);
            System.out.println("Ainda entrou aqui, não pode entrar........");
            acertos++;
            AGSoundManager.vrSoundEffects.play(acertou);
        }
        else
        {
            System.out.println("\nNão existe a palavra...");
            erros++;
            limpaPalavra(tamanhoPalavra);

            AGSoundManager.vrSoundEffects.play(errou);
        }

    }

    public void limpaPalavra(int tamanhoPalavra)
    {
        for (int posicao = 0; posicao < tamanhoPalavra; posicao++)
        {
            vetorPalavra.remove(vetorPalavra.size()-1);
            //palavra.bRecycled = true;
            palavra.bVisible = false;

            //Quando não tiver mais palavra pra tirar sai da função, se não remove uma palavra.
            if(vetorPalavra.size() == 0)
            {
                formacaoPalavras = 0;
                return;

            }
            else {

                palavra = vetorPalavra.get(vetorPalavra.size() - 1);

                formacaoPalavras--;

                if (formacaoPalavras < 0) {
                    formacaoPalavras = 0;
                }
            }
        }
    }

    public void verfificaPontuacao(int tamanhoPalavra)
    {
        if (tamanhoPalavra == 3)
        {
            pontos+=50;
        }
        if(tamanhoPalavra == 4)
        {
            pontos+=100;
        }
        if (tamanhoPalavra==5)
        {
            pontos+=200;
        }
        if (tamanhoPalavra>5)
        {
            pontos+=300;
        }
    }

    //Procurar Palavra no dicionário
    public boolean procurarPalavraDicionario(String palavra) throws IOException {
       String linha="";
       InputStream caminho = vrGameManager.vrActivity.getAssets().open("dicionario.txt");
       InputStreamReader input = new InputStreamReader(caminho);
       BufferedReader reader = new BufferedReader(input);

        while ((linha = reader.readLine()) != null) {
            if(linha.contains(palavra)){
                return true;
            }
        }

        return false;
    }
    
    public int lerRecorde() throws IOException {
        String recordelista = "";

        String linha="";
        InputStream caminho = vrGameManager.vrActivity.getAssets().open("recorde.txt");
        InputStreamReader input = new InputStreamReader(caminho);
        BufferedReader reader = new BufferedReader(input);

        linha = reader.readLine();
        recordelista = (linha.toString());
        
        if (recordelista!=null)
        {
            return Integer.parseInt(recordelista);    
        }
        else {
            return 0;
        }
    }

    public void salvaRecorde(int pontuacao) throws IOException {
        int recordeLista =0;
        recordeLista = lerRecorde();
        
//Se tiver algum recorde já salvo...        
        if (recordeLista != 0)
        {
            //Se a pontuação realizada no jogo for maior que a do recorde
            if(pontos>recordeLista)
            {
                //SALVA NO ARQUIVO OS PONTOS
            }
        }
        //Senão, será a primeira vez que estará salvando o recorde.
        
        //SALVA NO ARQUIVO OS PONTOS. 
                
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
    public void loop(){
        apresentacao.update();

        if(inicia == 0){
            criaLetra();
            inicia++;
        }

        if (apresentacao.isTimeEnded()) {
            tempo -= 1;
            apresentacao.restart();
        }

        //Se o tempo acabar, volta para o início.
        //Salva em um arquivo o recorde
        if (tempo == 0)
        {
            AGSoundManager.vrSoundEffects.play(acabou);
            try {
                salvaRecorde(pontos);
            } catch (IOException e) {
                e.printStackTrace();
            }


            vrGameManager.setCurrentScene(1);
            return;

        }


        if(AGInputManager.vrTouchEvents.backButtonClicked()){
            inicia = 0;
            formacaoPalavras = 0;
            vetorObjetos = new ArrayList<AGSprite>();
            vetorPalavra = new ArrayList<AGSprite>();
            ativo = true;

            vrGameManager.setCurrentScene(1);
            return;
        }

        atualizaMarcadores();

        if (AGInputManager.vrTouchEvents.screenClicked()){


            //Para saber qual letra foi clicada, pega pelo frame dela.
            int letra = 0;
            for (int aux = 0; aux < 16; aux ++){
                if(formacaoPalavras >=9){
                    System.out.println("Foi atingido o número máximo de palavras...");
                    break;
                }

                letra = vetorObjetos.get(aux).getCurrentAnimationFrame();

                if(vetorObjetos.get(aux).collide(AGInputManager.vrTouchEvents.getLastPosition()))
                {
                    formarPalavra(letra);
                }
            }

            if(formacaoPalavras >= 0)
            { //Se for clicado nos botões de cancelar ou confirmar.
                if(vetorBotao.get(0).collide(AGInputManager.vrTouchEvents.getLastPosition()))
                {
                    if(vetorPalavra.size() == 0)
                    {
                        return;
                    }
                    cancelarPalavra();
                }
                else if(vetorBotao.get(1).collide(AGInputManager.vrTouchEvents.getLastPosition()))
                {
                    if(vetorPalavra.size()>0)
                    {
                        confirmarPalavra();
                    }


                }
            }

        }

    }
}
