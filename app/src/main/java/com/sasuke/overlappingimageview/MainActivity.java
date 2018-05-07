package com.sasuke.overlappingimageview;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    OverlappingImageView overlappingImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overlappingImageView = (OverlappingImageView) findViewById(R.id.oiv);
        overlappingImageView.setThumbnailUrl(getDummyUrls(), false);
    }

    private ArrayList<String> getDummyUrls() {
        ArrayList<String> list = new ArrayList<>();
        list.add("https://recommenderdb.000webhostapp.com/images/action.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/horror.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/romantic.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/scifi.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/mystery.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/action.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/horror.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/romantic.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/scifi.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/mystery.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/action.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/horror.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/romantic.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/scifi.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/mystery.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/action.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/horror.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/romantic.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/scifi.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/mystery.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/action.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/horror.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/romantic.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/scifi.jpg");
        list.add("https://recommenderdb.000webhostapp.com/images/mystery.jpg");
        return list;
    }

    private ArrayList<Integer> getDummyDrawableRes() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.ic_launcher_background);
        list.add(R.drawable.ic_launcher_background);
        list.add(R.drawable.ic_launcher_background);
        list.add(R.drawable.ic_launcher_background);
        list.add(R.drawable.ic_launcher_background);
        return list;
    }

    private ArrayList<File> getDummyFiles() {
        ArrayList<File> list = new ArrayList<>();
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "yet.jpg");
        if (file.exists()) {
            Toast.makeText(this, "EXISTS", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "NOT EXISTS", Toast.LENGTH_SHORT).show();
        list.add(file);
        list.add(file);
        list.add(file);
        list.add(file);
        list.add(file);
        list.add(file);
        list.add(file);
        return list;
    }
}
