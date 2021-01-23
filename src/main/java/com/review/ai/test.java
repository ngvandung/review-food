//package com.review.ai;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
////Multinomial Naive Bayes
//public class test {
//
//    public static void readDataForKeyword(String filePath, HashSet<String> words, HashSet<String> dictionary) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                words.add(line.trim());
//                dictionary.add(line.trim());
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.toString());
//        }
//    }
//
//    public static void readDataTraining(String filePath, List<String> dataTraining) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                dataTraining.add(line.trim());
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.toString());
//        }
//    }
//
//    private static double countOccurrences(String str, String subStr) {
//        int lastIndex = 0;
//        double count = 0;
//
//        while (lastIndex != -1) {
//
//            lastIndex = str.indexOf(subStr, lastIndex);
//
//            if (lastIndex != -1) {
//                count++;
//                lastIndex += subStr.length();
//            }
//        }
//
//        return count;
//    }
//
//    public static double computeLamda(List<String> dataTraining, HashSet<String> dictionaries) {
//        double lamda = 0;
//        for (String word : dataTraining) {
//            for (String dictionary : dictionaries) {
//                if (word.contains(dictionary)) {
//                    ++lamda;
//                }
//            }
//        }
//        return lamda + dictionaries.size();
//    }
//
//
//    public static void main(String[] args) throws FileNotFoundException {
//        HashSet<String> positiveDictionaries = new HashSet<>();
//        HashSet<String> negativeDictionaries = new HashSet<>();
//        List<String> posDataTraining = new ArrayList<>();
//        List<String> negDataTraining = new ArrayList<>();
//
//        //Tap hop toan bo tu dien trong data training (Ky hieu: V)
//        HashSet<String> posAndNegDictionaries = new HashSet<>();
//
//        readDataForKeyword("C:\\Users\\ddung\\Desktop\\pos.txt", positiveDictionaries, posAndNegDictionaries);
//        readDataForKeyword("C:\\Users\\ddung\\Desktop\\neg.txt", negativeDictionaries, posAndNegDictionaries);
//        readDataTraining("C:\\Users\\ddung\\Desktop\\pos_data.txt", posDataTraining);
//        readDataTraining("C:\\Users\\ddung\\Desktop\\neg_data.txt", negDataTraining);
//
////        System.out.println("positiveDictionaries: " + positiveDictionaries);
////        System.out.println("negativeDictionaries: " + negativeDictionaries);
////        System.out.println("posDataTraining: " + posDataTraining);
////        System.out.println("negDataTraining: " + negDataTraining);
////        System.out.println("posAndNegDictionaries: " + posAndNegDictionaries);
//
//        Map<String, Double> lamdasOfPositive = new HashMap<>();
//        double lamdaOfPositive = computeLamda(posDataTraining, positiveDictionaries);
//        for (String dictionary : posAndNegDictionaries) {
//            int countOccurences = 0;
//            if (positiveDictionaries.contains(dictionary)) {
//                for (String word : posDataTraining) {
//                    countOccurences += countOccurrences(word, dictionary);
//                }
//            }
//            lamdasOfPositive.put(dictionary, (countOccurences + 1d) / lamdaOfPositive);
//        }
//
//        Map<String, Double> lamdasOfNegative = new HashMap<>();
//        double lamdaOfNegative = computeLamda(negDataTraining, negativeDictionaries);
//        for (String dictionary : posAndNegDictionaries) {
//            int countOccurences = 0;
//            if (negativeDictionaries.contains(dictionary)) {
//                for (String word : negDataTraining) {
//                    countOccurences += countOccurrences(word, dictionary);
//                }
//            }
//            lamdasOfNegative.put(dictionary, (countOccurences + 1d) / lamdaOfNegative);
//        }
//
//        String dataTest = "Món ăn no bụng nhưng phục vụ rất dở đã thế giá lại rất đắt nhưng được cái nấu ăn rất hoàn hảo và ngon";
//        int lengthInput = dataTest.split(" ").length;
//
//        double predict_neg_prob = 0, predict_pos_prob = 0;
//        double neg_prob_count = 0d, pro_prob_cout = 0d;
//        for (String dictionary : positiveDictionaries) {
//            pro_prob_cout += countOccurrences(dataTest, dictionary);
//        }
//        for (String dictionary : negativeDictionaries) {
//            neg_prob_count += countOccurrences(dataTest, dictionary);
//        }
//
//        predict_pos_prob = pro_prob_cout / lengthInput;
//        System.out.print(predict_pos_prob);
//        for (String dictionary : posAndNegDictionaries) {
//            if (dataTest.contains(dictionary)) {
////                System.out.print(" * " + (Math.pow(lamdasOfPositive.get(dictionary),
////                        countOccurrences(dataTest, dictionary))) + "(" + dictionary + ")");
//                predict_pos_prob = predict_pos_prob * (Math.pow(lamdasOfPositive.get(dictionary),
//                        countOccurrences(dataTest, dictionary)));
//            }
//        }
//        System.out.println();
//        predict_neg_prob = neg_prob_count / lengthInput;
//        System.out.print(predict_neg_prob);
//        for (String dictionary : posAndNegDictionaries) {
//            if (dataTest.contains(dictionary)) {
////                System.out.print(" * " + (Math.pow(lamdasOfNegative.get(dictionary),
////                        countOccurrences(dataTest, dictionary))) + "(" + dictionary + ")");
//                predict_neg_prob = predict_neg_prob * (Math.pow(lamdasOfNegative.get(dictionary),
//                        countOccurrences(dataTest, dictionary)));
//            }
//        }
//
//        System.out.println();
//        System.out.println(predict_neg_prob + " - " + predict_pos_prob);
//        if (predict_neg_prob > predict_pos_prob) {
//            System.out.println("=> Binh luan che");
//        } else if (predict_neg_prob < predict_pos_prob) {
//            System.out.println("=> Binh luan khen");
//        } else {
//            System.out.println("=> Khong xac dinh");
//        }
//
//    }
//}
