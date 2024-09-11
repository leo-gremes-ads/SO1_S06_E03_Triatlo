package controller;

import java.util.ArrayList;
import java.util.Arrays;

public class Competicao
{
    public ArrayList<Integer> chegadas;
    public int[] pontuacao;

    public Competicao()
    {
        this.pontuacao = new int[25];
        this.chegadas = new ArrayList<Integer>();
    }

    public void chegar(int id)
    {
        chegadas.add(id);
    }

    public void addPtosTiro(int id, int pts)
    {
        this.pontuacao[id - 1] += pts;
    }

    public void listarRanking()
    {
        int ptos = 250;
        int competidor, tiro;
        System.out.println(chegadas);
        while (chegadas.size() > 0) {
            competidor = chegadas.remove(0);
            pontuacao[competidor - 1] += ptos;
            ptos -= 10;
        }
        System.out.println(Arrays.toString(pontuacao));
        int[] indice = new int[25];
        for (int i = 0; i < 25; i++)
            indice[i] = i;
        for (int i = 0; i < 24; i++) {
            for (int j = i + 1; j < 25; j++) {
                if (pontuacao[i] < pontuacao[j]) {
                    inverterValor(pontuacao, i, j);
                    inverterValor(indice, i, j);
                }
            }
        }
        for (int i = 0; i < 25; i++) {
            System.out.println("Competidor " + (indice[i] + 1) + " -> pontuacao: " + pontuacao[i]);
        }
    }

    public void inverterValor(int[] lista, int a, int b)
    {
        int tmp = lista[a];
        lista[a] = lista[b];
        lista[b] = tmp;
    }
}