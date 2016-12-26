package com.alaurentino.RedeNeural.Treinamento;

import com.alaurentino.RedeNeural.Camada;
import com.alaurentino.RedeNeural.Neuronio;
import com.alaurentino.RedeNeural.RedeNeural;
import com.alaurentino.RedeNeural.Sinapse;

import java.util.List;

/**
 * Created by Anderson Laurentino on 22/12/2016.
 */
public class Backpropagation {

    private RedeNeural redeNeural;
    private double taxaDeAprendizagem;
    private long tempoInicial;
    private long tempoFinal;
    int epoca;

    public Backpropagation(RedeNeural redeNeural, double taxaDeAprendizagem) {
        this.redeNeural = redeNeural;
        this.taxaDeAprendizagem = taxaDeAprendizagem;
    }

    public void treino(double[][] entradas, double[][] saidas) {

        double erro;
        epoca = 1;
        tempoInicial = System.currentTimeMillis();

        do {
            erro = retropopagacaoDoErro(entradas, saidas);
            ++epoca;
        }while(erro != 0);

        tempoFinal = System.currentTimeMillis();
    }

    private double retropopagacaoDoErro(double[][] entradas, double[][] saidasEsperadas) {

        double erro = 0;

        for(int i = 0; i < entradas.length; i++) {

            double[] entrada = entradas[i];
            double[] saidaEsperada = saidasEsperadas[i];

            List<Camada> camadas = redeNeural.getCamadas();

            redeNeural.setEntradas(entrada);
            double[] saida = redeNeural.getSaidas();

            // Primeira etapa: encontrar os erros ate a camada intermediaria
            for(int j = 0; j < camadas.size(); j++){
                Camada c = camadas.get(j);

                for(int k = 0; k < c.getNeuronios().size(); k++) {
                    Neuronio n = c.getNeuronios().get(k);
                    double erroDoNeuronio = 0;

                    if(c.ehCamadaDeSaida())
                        erroDoNeuronio = saida[k] - saidaEsperada[k];
                    else {
                        for(Neuronio neuronioDaProximaCamada : c.getCamadaPosterior().getNeuronios()) {
                            for(Sinapse s : neuronioDaProximaCamada.getEntradas()) {
                                if(s.getNeuronioDeOrigem() == n)
                                    erroDoNeuronio += s.getPeso() * neuronioDaProximaCamada.getErro();
                            }
                        }
                    }
                    n.setErro(erroDoNeuronio);
                }
            }

            // Segunda etapa: redefinir os pesos
            for(Camada c : camadas)
                for(Neuronio n : c.getNeuronios())
                    for(Sinapse s : n.getEntradas())
                        s.setPeso(s.getPeso() - taxaDeAprendizagem * n.getErro() * n.getDerivada());

            saida = redeNeural.getSaidas();
            erro = erro(saida, saidaEsperada);
        }
        return erro;
    }

    private double erro(double[] saida, double[] saidaEsperada) {

        if(saida.length == saidaEsperada.length) {
            double soma = 0;

            for(int i = 0; i < saidaEsperada.length; i++)
                soma += Math.pow(saidaEsperada[i] - saida[i], 2);

            return soma / 2;
        }

        throw new IllegalArgumentException("O número de saidas esperadas não é igual ao número de saidas!");
    }

    public void comentario() {
        String sufixo;
        System.out.println("Treinamento da rede neural " + redeNeural.getNome());
        System.out.println("\tEpocas necessárias: " + epoca);
        System.out.println("\tTempo necessário: " + (tempoFinal - tempoInicial) + " milissegundos");
        for(int g = 0; g < redeNeural.getCamadas().size(); g++) {
            Camada c = redeNeural.getCamadas().get(g);
            if(g == 0) System.out.println("\tCamada de entrada:");
            else if(g == redeNeural.getCamadas().size()-1) System.out.println("\tCamada de saída:");
            else System.out.println("\tCamada intermediaria " + g + ":");
            for (int h = 0; h < c.getNeuronios().size(); h++) {
                Neuronio n = c.getNeuronios().get(h);
                if(g == 0) sufixo = "Entrada " + (h+1);
                else if(g == redeNeural.getCamadas().size()-1) sufixo = "Saída " + (h+1);
                else sufixo = "Neurônio " + String.valueOf(h+1);
                System.out.println("\t\t" + sufixo + ":");
                System.out.println("\t\t\tSaída: " + n.getSaida());
                System.out.println("\t\t\tEntrada: " + n.getEntradas().toString());
                for(int i = 0; i < n.getEntradas().size(); i++) {
                    System.out.println("\t\t\tPeso " + i + ": " + n.getEntradas().get(i).getPeso());
                }
            }
        }

    }
}
