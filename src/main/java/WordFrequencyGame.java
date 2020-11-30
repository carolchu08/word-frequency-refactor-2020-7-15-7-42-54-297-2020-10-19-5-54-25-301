import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


public class  WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_FEED = "\n";
    public static final String SPACE = " ";

    public String getResult(String inputSentence) {


        if (inputSentence.split(WHITE_SPACE_REGEX).length == 1) {
            return inputSentence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = inputSentence.split(WHITE_SPACE_REGEX);

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencyList.add(wordFrequency);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordCountMap = getWordCountMap(wordFrequencyList);

                List<WordFrequency> wordCountList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordCountMap.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    wordCountList.add(wordFrequency);
                }
                wordFrequencyList = wordCountList;

                wordFrequencyList.sort((wordFrequency1, wordFrequency2) -> wordFrequency2.getWordCount() - wordFrequency1.getWordCount());

                StringJoiner wordFrequencyResultJoiner = new StringJoiner(LINE_FEED);
                for (WordFrequency word : wordFrequencyList) {
                    String wordResultLine = word.getWord() + SPACE + word.getWordCount();
                    wordFrequencyResultJoiner.add(wordResultLine);
                }
                return wordFrequencyResultJoiner.toString();
            } catch (Exception exception) {


                return CALCULATE_ERROR;
            }
        }
    }


    private Map<String, List<WordFrequency>> getWordCountMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordCountMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!wordCountMap.containsKey(wordFrequency.getWord())) {
                ArrayList frequencyList = new ArrayList<>();
                frequencyList.add(wordFrequency);
                wordCountMap.put(wordFrequency.getWord(), frequencyList);
            } else {
                wordCountMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }


        return wordCountMap;
    }


}
