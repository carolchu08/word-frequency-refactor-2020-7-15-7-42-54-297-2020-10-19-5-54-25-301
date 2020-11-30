import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_FEED = "\n";

    public String getResult(String inputSentence) throws CalculationErrorException {

        try {
            //split the input string with 1 to n pieces of spaces
            List<WordFrequency> wordFrequencyList = generateWordFrequency(inputSentence);
            return generateResultString(wordFrequencyList);

        } catch (Exception exception) {
            throw new CalculationErrorException(CALCULATE_ERROR);
        }
    }

    private String generateResultString(List<WordFrequency> wordFrequencyList) {
        wordFrequencyList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());
        StringJoiner wordFrequencyResultJoiner = new StringJoiner(LINE_FEED);
        for (WordFrequency word : wordFrequencyList) {
            wordFrequencyResultJoiner.add(generateWordFrequencyLine(word));
        }
        return wordFrequencyResultJoiner.toString();
    }

    private String generateWordFrequencyLine(WordFrequency word) {
        return String.format("%s %d", word.getWord(), word.getWordCount());
    }

    private List<WordFrequency> generateWordFrequency(String inputSentence) {
        List<String> words = Arrays.asList(inputSentence.split(WHITE_SPACE_REGEX));
        HashSet<String> distinctWords = new HashSet<>(words);
        return distinctWords.stream()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());
    }

}
