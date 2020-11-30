import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_FEED = "\n";
    public static final String SPACE = " ";

    public String getResult(String inputSentence) {

        try {
            //split the input string with 1 to n pieces of spaces
            List<WordFrequency> wordFrequencyList = generateWordFrequency(inputSentence);
            return generateResultString(wordFrequencyList);

        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private String generateResultString(List<WordFrequency> wordFrequencyList) {
        StringJoiner wordFrequencyResultJoiner = new StringJoiner(LINE_FEED);
        for (WordFrequency word : wordFrequencyList) {
            wordFrequencyResultJoiner.add(word.getWord() + SPACE + word.getWordCount());
        }
        return wordFrequencyResultJoiner.toString();
    }

    private List<WordFrequency> generateWordFrequency(String inputSentence) {
        String[] words = inputSentence.split(WHITE_SPACE_REGEX);

        List<WordFrequency> wordFrequencyList = new ArrayList<>();
        for (String word : words) {
            wordFrequencyList.add(new WordFrequency(word, 1));
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordFrequency>> wordCountMap = getWordCountMap(wordFrequencyList);

        List<WordFrequency> wordCountList = new ArrayList<>();
        for (Map.Entry<String, List<WordFrequency>> entry : wordCountMap.entrySet()) {
            wordCountList.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
        }

        wordCountList.sort((wordFrequency1, wordFrequency2) -> wordFrequency2.getWordCount() - wordFrequency1.getWordCount());
        return wordCountList;
    }


    private Map<String, List<WordFrequency>> getWordCountMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordCountMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!wordCountMap.containsKey(wordFrequency.getWord())) {
                List<WordFrequency> frequencyList = new ArrayList<>();
                frequencyList.add(wordFrequency);
                wordCountMap.put(wordFrequency.getWord(), frequencyList);
            } else {
                wordCountMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }


        return wordCountMap;
    }


}
