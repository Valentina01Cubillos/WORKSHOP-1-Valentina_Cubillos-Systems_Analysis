/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Workshop;
/*Author Laura Valentina Cubillos Acero*/
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.Collectors;

public class MotifFinder {

    private static final char[] BASES = {'A', 'C', 'G', 'T'};

    public static void main(String[] args) throws IOException {
        int n = 1000; // Number of sequences
        int m = 10;   // Size of each sequence
        double[] probabilities = {0.25, 0.25, 0.25, 0.25}; // Probabilities of A, C, G, T
        
        // Generate artificial database
        List<String> sequences = generateSequences(n, m, probabilities);
        saveToFile(sequences, "sequences.txt");
        
        // Find the most frequent otif
        int motifSize = 5;
        String mostFrequentMotif = findMotif(sequences, motifSize);
        System.out.println("The most frequent motif is " + mostFrequentMotif);
        
        // Apply Shannon Entropy to filter sequences
        double threshold = 1.5; // Entropy threshold value
        List<String> filteredSequences = filterByEntropy(sequences, threshold);
        saveToFile(filteredSequences, "filtered_sequences.txt");
    }

    // Genera una lista de secuencias de tamaño m con las probabilidades dadas
    public static List<String> generateSequences(int n, int m, double[] probabilities) {
        Random random = new Random();
        List<String> sequences = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                sb.append(generateBase(probabilities, random));
            }
            sequences.add(sb.toString());
        }
        return sequences;
    }

    // Generate a base (A, C, G, T) according to the probabilities
    private static char generateBase(double[] probabilities, Random random) {
        double p = random.nextDouble();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < BASES.length; i++) {
            cumulativeProbability += probabilities[i];
            if (p <= cumulativeProbability) {
                return BASES[i];
            }
        }
        return BASES[BASES.length - 1]; // On error, returns T
    }

    // Save a list of sequences to a text file
    public static void saveToFile(List<String> sequences, String filename) throws IOException {
        Files.write(Paths.get(filename), sequences);
    }

    // Find the most common motif for a given size
    public static String findMotif(List<String> sequences, int motifSize) {
        Map<String, Integer> motifCounts = new HashMap<>();
        
        for (String sequence : sequences) {
            for (int i = 0; i <= sequence.length() - motifSize; i++) {
                String motif = sequence.substring(i, i + motifSize);
                motifCounts.put(motif, motifCounts.getOrDefault(motif, 0) + 1);
            }
        }
        
        return motifCounts.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("No motif found");
    }

    // Filter sequences with low entropy using given threshold
    public static List<String> filterByEntropy(List<String> sequences, double threshold) {
        return sequences.stream()
            .filter(seq -> calculateEntropy(seq) >= threshold)
            .collect(Collectors.toList());
    }

    // Calculate the Shannon entropy of a sequence
    public static double calculateEntropy(String sequence) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        
        // Count frequencies of each base
        for (char base : sequence.toCharArray()) {
            frequencyMap.put(base, frequencyMap.getOrDefault(base, 0) + 1);
        }
        
        double entropy = 0.0;
        int length = sequence.length();
        
        // Calculate entropy
        for (int count : frequencyMap.values()) {
            double probability = (double) count / length;
            entropy -= probability * (Math.log(probability) / Math.log(2));
        }
        
        return entropy;
    }
}





