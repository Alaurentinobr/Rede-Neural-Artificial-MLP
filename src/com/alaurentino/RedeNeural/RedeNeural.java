package com.alaurentino.RedeNeural;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Anderson Laurentino on 20/12/2016.
 */
public class RedeNeural implements Serializable {

    private String nome;
    private List<Camada> camadas;
    private Camada camadaDeEntrada;
    private Camada camadaDeSaida;

    public RedeNeural(String name) {
        this.nome = name;
        camadas = new ArrayList<>();
    }

    public void addCamada(Camada camada) {
        camadas.add(camada);

        if(camadas.size() == 1)
            camadaDeEntrada = camada;

        if(camadas.size() > 1) {
            Camada camadaAnteriorAEssa = camadas.get(camadas.size()-2);
            camadaAnteriorAEssa.setCamadaPosterior(camada);
        }

        camadaDeSaida = camada;
    }

    public void setEntradas(double[] entradas) {
        if(camadaDeEntrada != null) {
            int bias = camadaDeEntrada.hasBias() ? 1 : 0;

            if(camadaDeEntrada.getNeuronios().size() - bias != entradas.length)
                throw new IllegalArgumentException("O número de entradas não corresponde ao número de entradas necessárias! Conte com o bias");
            else {
                List<Neuronio> neuroniosDaCamadaDeEntrada = camadaDeEntrada.getNeuronios();
                for(int i = bias; i < neuroniosDaCamadaDeEntrada.size(); i++) {
                    neuroniosDaCamadaDeEntrada.get(i).setSaida(entradas[i-bias]);
                }
            }
        }
    }

    public double[] getSaidas() {
        double[] saidas = new double[camadaDeSaida.getNeuronios().size()];

        for(int i = 1; i < camadas.size(); i++)
            camadas.get(i).feedForward();

        for(int i = 0; i < camadaDeSaida.getNeuronios().size(); i++)
            saidas[i] = camadaDeSaida.getNeuronios().get(i).getSaida();

        return saidas;
    }

    public void reset() {
        for(Camada camada : camadas)
            for(Neuronio neuronio : camada.getNeuronios())
                for (Sinapse sinapse : neuronio.getEntradas())
                    sinapse.setPeso((Math.random() * 1) - 0.5);
    }

    public void persistencia() {
        String nomeDoArquivo = nome.replaceAll(" ", "_") + "-" + new Date().getTime() + ".net";

        System.out.println("Escrevendo sua rede neural no aquivo " + nomeDoArquivo);

        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(nomeDoArquivo));
            out.writeObject(this);
        } catch (IOException e) {
            System.out.println("Não foi possível salvar sua rede no arquivo " + nomeDoArquivo);
        }

        finally {
            try {
                if(out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                System.out.println("Não foi possível fechar o arquivo " + nomeDoArquivo);
            }
        }
    }

    public List<Camada> getCamadas() {
        return camadas;
    }

    public String getNome() {
        return nome;
    }
}
