package controller;

import java.lang.InterruptedException;
import java.util.concurrent.Semaphore;

public class Competidor extends Thread
{
    private int id;
    private Semaphore semaforo;
    private Competicao competicao;
    private int distCorrida;
    private int distCiclismo;

    public Competidor(int id, Semaphore semaforo, Competicao competicao)
    {
        this.id = id;
        this.semaforo = semaforo;
        this.competicao = competicao;
        this.distCorrida = 0;
        this.distCiclismo = 0;
    }

    @Override
    public void run()
    {
        corrida();
        try {
            semaforo.acquire();
            tiro();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            semaforo.release();
        }
        ciclismo();
    }

    public void corrida()
    {
        int distancia;
        while (distCorrida < 3000) {
            distancia = (int)((Math.random() * 5) + 20);
            distCorrida += distancia;
            try {
                sleep(30);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("Competidor " + this.id + " terminou a corrida");
    }

    public void ciclismo()
    {
        int distancia;
        while (true) {
            if (distCiclismo >= 5000) {
                this.competicao.chegar(this.id);
                System.out.println("Competidor " + this.id + " terminou a prova");
                break;
            }
            distancia = (int)((Math.random() * 10) + 30);
            distCiclismo += distancia;
            try {
                sleep(40);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void tiro()
    {
        int tempo, pontuacao;
        for (int i = 0; i < 3; i++) {
            tempo = (int)((Math.random() * 2500) + 500);
            pontuacao = (int)((Math.random() * 10));
            this.competicao.addPtosTiro(this.id, pontuacao);
            System.out.println("Competidor " + this.id + " fez " + pontuacao + " no seu " + (i + 1) + "º tiro.");
        }
    }
}