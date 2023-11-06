package task1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MyWordCount {
	// public static final String fileName = "data/hamlet.txt";
	public static final String fileName = "data/fit.txt";

	private List<String> words = new ArrayList<>();

	public MyWordCount() {
		try {
			this.words.addAll(Utils.loadWords(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt)
	// In this method, we do not consider the order of tokens.
	public List<WordCount> getWordCounts() {
		List<WordCount> l1 = new ArrayList<>();
		for (String s : words) {
			WordCount w1 = new WordCount(s, 1);
			if (!l1.contains(w1)) {
				l1.add(w1);
			} else {
				w1 = l1.get(l1.indexOf(w1));
				w1.setCount(w1.getCount() + 1);
			}
		}
		return l1;
	}

	// Returns the words that their appearance are 1, do not consider duplidated
	// words
	public Set<String> getUniqueWords() {
		Set<String> l1 = new HashSet<>();
		for (String s : words) {
			if (l1.contains(s)) {
				l1.remove(s);
			} else {
				l1.add(s);
			}

		}
		return l1;
	}

	// Returns the words in the text file, duplicated words appear once in the
	// result
	public Set<String> getDistinctWords() {
		Set<String> l1 = new HashSet<>();
		for (String s : words) {

			l1.add(s);

		}
		return l1;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according ascending order of tokens
	// Example: An - 3, Bug - 10, ...
	public Set<WordCount> printWordCounts() {
		List<WordCount> l1 = new ArrayList<>();
		for (String s : words) {
			WordCount w1 = new WordCount(s, 1);
			if (!l1.contains(w1)) {
				l1.add(w1);
			} else {
				w1 = l1.get(l1.indexOf(w1));
				w1.setCount(w1.getCount() + 1);
			}
		}
		Set<WordCount> re = new TreeSet<>(new Comparator<WordCount>() {

			@Override
			public int compare(WordCount o1, WordCount o2) {
				// TODO Auto-generated method stub
				return o1.getWord().compareTo(o2.getWord());
			}
		});
		re.addAll(getWordCounts());

		return re;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according descending order of occurences
	// Example: Bug - 10, An - 3, Nam - 2.
	public Set<WordCount> exportWordCountsByOccurence() {
		Comparator<WordCount> compareTO = new Comparator<WordCount>() {

			@Override
			public int compare(WordCount o1, WordCount o2) {
				int countCompare = o1.getCount() - o2.getCount();
				if (countCompare != 0) {
					return countCompare;

				}
				return o1.getWord().compareTo(o2.getWord());
			}
		};

		Set<WordCount> re = new TreeSet<>(compareTO);
		re.addAll(getWordCounts());
		return re;
	}

	// delete words begining with the given pattern (i.e., delete words begin with
	// 'A' letter
	public Set<String> filterWords(String pattern) {
		Set<String> re = new HashSet<>();
		for (String s : words) {
			if(!s.startsWith(pattern)) {
				re.add(s);
			}
		}
		return re;
	}

	public static void main(String[] args) {
		MyWordCount m1 = new MyWordCount();

		System.out.println(m1.getWordCounts());
		System.out.println(m1.getUniqueWords());
		System.out.println(m1.getDistinctWords());
		System.out.println(m1.printWordCounts());
		System.out.println(m1.exportWordCountsByOccurence());
		System.out.println(m1.filterWords("D"));
	}
}