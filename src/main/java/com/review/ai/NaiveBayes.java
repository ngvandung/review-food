package com.review.ai;

import com.review.constant.CommonConstant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NaiveBayes {

    private String realPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
    private String pathFolder = realPath.substring(realPath.lastIndexOf("C:"), realPath.lastIndexOf("/review"))
            + "/publics/review/";

    public NaiveBayes() {

    }

//    public int prediction(String comment) {
//        HashSet<String> posWord = new HashSet<>();
//        HashSet<String> negWord = new HashSet<>();
//
//        //List<String> dtWord = new ArrayList<>();
//
//        HashSet<String> dictionary = new HashSet<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(pathFolder + "pos.txt"))) {
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                posWord.add(line.trim());
//                dictionary.add(line.trim());
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.toString());
//        }
//
//        try (BufferedReader br = new BufferedReader(new FileReader(pathFolder + "neg.txt"))) {
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                negWord.add(line.trim());
//                dictionary.add(line.trim());
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.toString());
//        }
//
////        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ddung\\Desktop\\data.txt"))) {
////            String line = null;
////            while ((line = br.readLine()) != null) {
////                dtWord.add(line.trim());
////            }
////        } catch (IOException ex) {
////            System.out.println(ex.toString());
////        }
//
//        int totWordCount = posWord.size() + negWord.size();
//        int negWordCount = negWord.size();
//        int posWordCount = posWord.size();
//
//        double negProb = 1.0 * negWordCount / totWordCount;
//        double posProb = 1.0 * posWordCount / totWordCount;
//
//        //for (String content : dtWord) {
//        double predict_neg_prob = negProb;
//        double predict_pos_prob = posProb;
//
//        int found_neg = 0;
//        int found_pos = 0;
//
//        String saveCount = comment;
//
//        for (String keyword : dictionary) {
//            if (saveCount.contains(keyword)) {
//                saveCount = saveCount.replace(keyword, "");
//
//                if (negWord.contains(keyword)) {
//                    predict_neg_prob = predict_neg_prob * 1.0 / negWordCount;
//                    found_neg++;
//                }
//
//                if (posWord.contains(keyword)) {
//                    predict_pos_prob = predict_pos_prob * 1.0 / posWordCount;
//                    found_pos++;
//                }
//            }
//        }
//
//        if (found_neg == 0) predict_neg_prob = 0;
//        if (found_pos == 0) predict_pos_prob = 0;
//
//        if (predict_neg_prob > predict_pos_prob) {
//            System.out.println("======> BINH LUAN CHE");
//            return CommonConstant.CHE;
//        } else if (predict_neg_prob < predict_pos_prob) {
//            System.out.println("======> BINH LUAN KHEN");
//            return CommonConstant.KHEN;
//        } else {
//            System.out.println("======> BINH LUAN KHONG XAC DINH");
//            return 0; //Khong xac dinh
//        }
//        //}
//    }

    public static void readDataForKeyword(String filePath, HashSet<String> words, HashSet<String> dictionary) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
                dictionary.add(line.trim());
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void readDataTraining(String filePath, List<String> dataTraining) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                dataTraining.add(line.trim());
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    private static double countOccurrences(String str, String subStr) {
        int lastIndex = 0;
        double count = 0;

        while (lastIndex != -1) {

            lastIndex = str.indexOf(subStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += subStr.length();
            }
        }

        return count;
    }

    public static double computeLamda(List<String> dataTraining, HashSet<String> dictionaries) {
        double lamda = 0;
        for (String word : dataTraining) {
            for (String dictionary : dictionaries) {
                if (word.contains(dictionary)) {
                    ++lamda;
                }
            }
        }
        return lamda + dictionaries.size();
    }

    public static double computeProbability(HashSet<String> posAndNegDictionaries, String comment, Map<String, Double> lamdas) {
        double predict_prob = 1d;
        for (String dictionary : posAndNegDictionaries) {
            if (comment.contains(dictionary)) {
                predict_prob = predict_prob * (Math.pow(lamdas.get(dictionary),
                        countOccurrences(comment, dictionary)));
            }
        }
        return predict_prob;
    }

    public int prediction(String comment) {
        HashSet<String> positiveDictionaries = new HashSet<>();
        HashSet<String> negativeDictionaries = new HashSet<>();
        List<String> posDataTraining = new ArrayList<>();
        List<String> negDataTraining = new ArrayList<>();

        //Tap hop toan bo tu dien trong data training (Ky hieu: V)
        HashSet<String> posAndNegDictionaries = new HashSet<>();

        readDataForKeyword(pathFolder + "pos.txt", positiveDictionaries, posAndNegDictionaries);
        readDataForKeyword(pathFolder + "neg.txt", negativeDictionaries, posAndNegDictionaries);
        readDataTraining(pathFolder + "pos_data.txt", posDataTraining);
        readDataTraining(pathFolder + "neg_data.txt", negDataTraining);

        Map<String, Double> lamdasOfPositive = new HashMap<>();
        double lamdaOfPositive = computeLamda(posDataTraining, positiveDictionaries);
        for (String dictionary : posAndNegDictionaries) {
            int countOccurences = 0;
            if (positiveDictionaries.contains(dictionary)) {
                for (String word : posDataTraining) {
                    countOccurences += countOccurrences(word, dictionary);
                }
            }
            lamdasOfPositive.put(dictionary, (countOccurences + 1d) / lamdaOfPositive);
        }

        Map<String, Double> lamdasOfNegative = new HashMap<>();
        double lamdaOfNegative = computeLamda(negDataTraining, negativeDictionaries);
        for (String dictionary : posAndNegDictionaries) {
            int countOccurences = 0;
            if (negativeDictionaries.contains(dictionary)) {
                for (String word : negDataTraining) {
                    countOccurences += countOccurrences(word, dictionary);
                }
            }
            lamdasOfNegative.put(dictionary, (countOccurences + 1d) / lamdaOfNegative);
        }

        double lengthInput = posDataTraining.size() + negDataTraining.size();

        double predict_pos_prob = (posDataTraining.size() / lengthInput) * computeProbability(posAndNegDictionaries, comment, lamdasOfPositive);
        double predict_neg_prob = (negDataTraining.size() / lengthInput) * computeProbability(posAndNegDictionaries, comment, lamdasOfNegative);

        if (predict_neg_prob > predict_pos_prob) {
            System.out.println("======> BINH LUAN CHE");
            return CommonConstant.CHE;
        } else if (predict_neg_prob < predict_pos_prob) {
            System.out.println("======> BINH LUAN KHEN");
            return CommonConstant.KHEN;
        } else {
            System.out.println("======> BINH LUAN KHONG XAC DINH");
            return 0; //Khong xac dinh
        }

    }

    public static void main(String[] args) {
        float s = 0;
        for (int i = 0; i < 100; i++) {
            s += 0.2f;
        }
        //System.out.println(Math.abs(s - 20f));
        if (s == 20f) {
            System.out.println(s);
        }
    }
}
