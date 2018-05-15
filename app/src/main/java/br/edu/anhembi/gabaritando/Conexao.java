package br.edu.anhembi.gabaritando;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by igorl on 12/05/2018.
 */

public class Conexao {

    public static String postDados(String urlUsuario, String parametrosUsuario) {

        URL url;
        //Canal responsável por fazer a conexão
        HttpsURLConnection connection = null;

        try {
            //Mascaração da URL e abertura da conexão
            url = new URL(urlUsuario);
            connection = (HttpsURLConnection) url.openConnection();

            //Definição de Propriedades (Método Utilizado, Tipo da comunicação, Tamanho da informação e o Idioma)
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametrosUsuario.getBytes().length));
            connection.setRequestProperty("Content-Language", "pt-BR");

            //Desabilitação de memória cache e abertura de entrada e saída de dados
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Solicitação
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(parametrosUsuario);
            dataOutputStream.flush();
            dataOutputStream.close();

            //Obtendo os dados da conexão
            InputStream inputStream = connection.getInputStream();

            //Recebimento dos dados e armazenando na codificação UTF-8
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String linha;
            //String para juntar as informações
            StringBuffer resposta = new StringBuffer();

            //Montando a String buffer
            while ((linha = bufferedReader.readLine()) != null) {
                resposta.append(linha);
                resposta.append('\r');
            }

            bufferedReader.close();

            return resposta.toString();

        } catch (Exception error){
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
