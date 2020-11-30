import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


public class  WordFrequencyGame {
    public String getResult(String inputSentence) {


        if (inputSentence.split("\\s+").length == 1) {
            return inputSentence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = inputSentence.split("\\s+");

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

                StringJoiner wordFrequencyResultJoiner = new StringJoiner("\n");
                for (WordFrequency word : wordFrequencyList) {
                    String wordResultLine = word.getWord() + " " + word.getWordCount();
                    wordFrequencyResultJoiner.add(wordResultLine);
                }
                return wordFrequencyResultJoiner.toString();
            } catch (Exception exception) {


                return "Calculate Error";
            }
        }
    }


    private Map<String, List<WordFrequency>> getWordCountMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordCountMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!wordCountMap.containsKey(wordFrequency.getWord())) {
                ArrayList word = new ArrayList<>();
                word.add(wordFrequency);
                wordCountMap.put(wordFrequency.getWord(), word);
            } else {
                wordCountMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }


        return wordCountMap;
    }


}
