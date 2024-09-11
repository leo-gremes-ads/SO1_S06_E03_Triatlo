package view;

import controller.*;
import java.util.concurrent.Semaphore;

public class Triatlo
{
    public static void main(String[] args)
    {
        Competicao competicao = new Competicao();
        Semaphore semaforo = new Semaphore(5);
        Competidor[] competidores = new Competidor[25];
        for (int i = 0; i < 25; i++) {
            competidores[i] =  new Competidor(i + 1, semaforo, competicao);
            competidores[i].start();
        }
        for (int i = 0; i < 25; i++) {
            try {
                competidores[i].join();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("------------- RANKING -------------------");
        competicao.listarRanking();
    }
}