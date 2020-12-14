//package com.review.ai;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//public class test {
//    public static void main(String[] args) throws FileNotFoundException {
//        HashSet<String> posWord = new HashSet<>();
//        HashSet<String> negWord = new HashSet<>();
//
//        List<String> dtWord = new ArrayList<>();
//
//        HashSet<String> dictionary = new HashSet<>();
//
//        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ddung\\Desktop\\pos.txt"))) {
//            String line = null;
//            while((line = br.readLine()) != null){
//                posWord.add(line.trim());
//                dictionary.add(line.trim());
//            }
//        } catch (IOException ex){
//            System.out.println(ex.toString());
//        }
//
//        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ddung\\Desktop\\neg.txt"))) {
//            String line = null;
//            while((line = br.readLine()) != null){
//                negWord.add(line.trim());
//                dictionary.add(line.trim());
//            }
//        } catch (IOException ex){
//            System.out.println(ex.toString());
//        }
//
//        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ddung\\Desktop\\data.txt"))) {
//            String line = null;
//            while((line = br.readLine()) != null){
//                dtWord.add(line.trim());
//            }
//        } catch (IOException ex){
//            System.out.println(ex.toString());
//        }
//
//        int totWordCount = posWord.size() + negWord.size();
//        int negWordCount = negWord.size();
//        int posWordCount = posWord.size();
//
//        double negProb = 1.0 * negWordCount / totWordCount;
//        double posProb = 1.0 * posWordCount / totWordCount;
//
//        for (String content : dtWord) {
//            double predict_neg_prob = negProb;
//            double predict_pos_prob = posProb;
//
//            int found_neg = 0;
//            int found_pos = 0;
//
//            String saveCount = content;
//
//            for (String keyword : dictionary) {
//                if(content.contains(keyword)){
//                    content = content.replace(keyword, "");
//
//                    if(negWord.contains(keyword)){
//                        predict_neg_prob = predict_neg_prob*1.0/negWordCount;
//                        found_neg++;
//                    }
//
//                    if(posWord.contains(keyword)){
//                        predict_pos_prob = predict_pos_prob*1.0/posWordCount;
//                        found_pos++;
//                    }
//                }
//            }
//
//            if(found_neg == 0) predict_neg_prob = 0;
//            if(found_pos == 0) predict_pos_prob = 0;
//
//            System.out.println("Binh luan: " + saveCount);
//
//            System.out.println("Xac suat: Che: " + predict_neg_prob + " | Khen: " + predict_pos_prob);
//
//            if(predict_neg_prob > predict_pos_prob){
//                System.out.println("=> Binh luan che");
//            } else if(predict_neg_prob < predict_pos_prob) {
//                System.out.println("=> Binh luan khen");
//            } else {
//                System.out.println("=> Khong xac dinh");
//            }
//        }
//    }
//}
