package com.example.latihanlks.backgtoundTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.latihanlks.Activity_menu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask  extends AsyncTask<String, String, String> {

    Context context;
    public BackgroundTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String REGISTRASI_URL = "http://192.168.54.227:3000/api/register";
        String LOGIN_URL = "http://192.168.54.227:3000/api/login";
        String GETDATA_BARANG = "http://192.168.54.227:3000/api/barang";


        if (type.equals("login")){
            String email = params[1];
            String password = params[2];

            try{
                URL url = new URL(LOGIN_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String Crud_Data = URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");

                buffer.write(Crud_Data);
                buffer.flush();
                buffer.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));

                StringBuilder sb = new StringBuilder();
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    sb.append(line).append("\n");
                }

                JSONObject object = new JSONObject(sb.toString());
                String message = object.getString("message");


                bufferedReader.close();
                is.close();
                httpURLConnection.disconnect();
                return message;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                 return "User Not Found";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (type.equals("registrasi")){
            String namaLengkap = params[1];
            String username = params[2];
            String alamat = params[3];
            String password = params[4];

            try {
                URL url = new URL(REGISTRASI_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                OutputStream os = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String inputData = URLEncoder.encode("nama_lengkap", "UTF-8") + "=" +
                        URLEncoder.encode(namaLengkap, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("alamat" , "UTF-8") + "=" +
                        URLEncoder.encode(alamat, "UTF-8") + "&" +
                        URLEncoder.encode("password" , "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");

                buffer.write(inputData);
                buffer.flush();
                buffer.close();
                os.close();

                InputStream is = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));

                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    sb.append(line).append("\n");
                }

                bufferedReader.close();
                is.close();
                connection.disconnect();

                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("databarang")){
            try{
                URL url = new URL(GETDATA_BARANG);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                InputStream is = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                StringBuilder sb = new StringBuilder();

                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    sb.append(line).append("\n");
                }

                is.close();
                bufferedReader.close();
                connection.disconnect();
                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Failed Job";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();

        if (s.contains("Login Berhasil")){
            Intent intent = new Intent(context, Activity_menu.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
