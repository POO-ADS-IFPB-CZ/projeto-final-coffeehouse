package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    public static <T> void salvar(ArrayList<T> lista, String caminho) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(caminho));

            out.writeObject(lista);

            out.close();

        } catch (IOException e) {

            System.out.println("Erro ao salvar arquivo.");

        }

    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> carregar(String caminho) {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(caminho));

            ArrayList<T> lista =
                    (ArrayList<T>) in.readObject();

            in.close();

            return lista;

        }

        catch (Exception e) {

            return new ArrayList<>();

        }

    }


public class GeradorId {

    public static <T> int gerarId(List<T> lista) {

        return lista.size() + 1;

    }

}

}