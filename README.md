# Entropy and Motif Analysis in Genetic Sequences

## Project Description
This project implements a system that generates artificial genetic sequences, searches for the most frequent motifs within them, and applies Shannon entropy to filter repetitive sequences. The approach follows a "divide and conquer" algorithm to optimize both the generation and analysis of data.

The key components of this project are:
- Generation of random DNA sequences based on given nucleotide probabilities.
- Identification of the most frequent motifs of a specified size.
- Calculation of Shannon entropy to measure sequence randomness and filter low-entropy sequences.

## Table of Contents

1. [Usage](#usage)
3. [Project Structure](#project-structure)
4. [Results](#results)
5. [References](#references)


## Usage

1. **Generate Sequences**: The program generates artificial genetic sequences with adjustable probabilities for the nucleotide bases (`A`, `C`, `G`, `T`). You can modify the number of sequences (`n`), the length of each sequence (`m`), and the probabilities for each nucleotide in the `MotifFinder.java` file.

2. **Motif Search**: Specify the length of the motif you want to find (e.g., 5 bases). The system will search through the generated sequences and identify the most frequent motif.

3. **Entropy Filtering**: Shannon entropy is calculated for each sequence to measure its diversity. You can set an entropy threshold (e.g., 1.5) to filter out sequences with low entropy (those that are too repetitive). The sequences that pass the entropy filter are saved in a separate file.

### Example Output
- After running the program, two files will be generated:
  - `sequences.txt`: Contains the generated sequences of nucleotide bases.
  - `filtered_sequences.txt`: Contains the sequences that passed the entropy filtering process.
  
- Additionally, the program will output to the console the most frequent motif found, the number of occurrences, and the time it took to find the motif.

#####Example
Motif: ACGTA
Occurrences: 15
Execution time: 120 ms
## Project Structure

The project is organized as follows:

- **README.md**: This file provides a detailed explanation of the project, including installation instructions, usage, and project structure.
  
- **src/**:
  - **MotifFinder.java**: The main Java file that handles the following:
    - Generates artificial DNA sequences based on specified parameters.
    - Searches for the most frequent motifs.
    - Calculates Shannon entropy to filter out repetitive sequences.
  
- **sequences.txt**: This file contains the generated sequences, with each line representing a sequence of nucleotide bases (`A`, `C`, `G`, `T`).

- **filtered_sequences.txt**: This file contains the sequences that passed the Shannon entropy filtering (i.e., those with entropy above a certain threshold).

- **report/**:
  - **report.pdf**: The project report, including analysis, results, and conclusions from the experiments conducted. The report is structured in sections such as systemic analysis, complexity, chaos analysis, and results.
## Results
 The following tables summarize the key results obtained.

### Without Entropy Filtering
In this experiment, no entropy filtering was applied to the sequences. The system identified the most frequent motifs directly from the generated sequences.

| **DB Size** | **Probabilities**              | **Motif Size** | **Motif** | **Occurrences** | **Time (ms)** |
|-------------|--------------------------------|----------------|-----------|-----------------|---------------|
| 1000        | A: 0.25, C: 0.25, G: 0.25, T: 0.25 | 5              | ACGTA     | 15              | 120           |
| 2000        | A: 0.25, C: 0.25, G: 0.25, T: 0.25 | 5              | TGACA     | 12              | 200           |
| 1000        | A: 0.40, C: 0.20, G: 0.20, T: 0.20 | 5              | AAAAC     | 22              | 150           |

### With Entropy Filtering
In this experiment, sequences with low entropy were filtered out. The system applied a Shannon entropy threshold of 1.5 to remove repetitive sequences and identify motifs in the more chaotic sequences.

| **Filtered DB Size** | **Entropy Threshold** | **Motif Size** | **Motif** | **Occurrences** | **Time (ms)** |
|----------------------|----------------------|----------------|-----------|-----------------|---------------|
| 800                  | 1.5                  | 5              | CGTAC     | 9               | 110           |
| 1500                 | 1.5                  | 5              | GATCA     | 10              | 180           |

### Key Observations:
- **Without filtering**, the system identified motifs quickly but many sequences had low diversity.
- **With entropy filtering**, the database was reduced significantly, leading to fewer occurrences of motifs but higher sequence diversity.
- Filtering based on entropy improved the quality of the motifs found by removing highly repetitive sequences.

The performance of the system varied depending on the size of the database and the applied entropy threshold. In general, smaller filtered databases resulted in faster execution times due to fewer sequences being analyzed.
## References

1. Shannon, C. E. (1948). A Mathematical Theory of Communication. *Bell System Technical Journal*, 27, 379-423.  
   This foundational paper introduces the concept of information entropy, which is used in this project to measure the randomness of DNA sequences and filter repetitive sequences.

2. Mount, D. W. (2004). *Bioinformatics: Sequence and Genome Analysis*. Cold Spring Harbor Laboratory Press.  
   A comprehensive guide to the field of bioinformatics, providing insights into sequence analysis, motif searching, and the application of computational methods in genetics.
