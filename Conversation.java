import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple chatbot that has a conversation with the user
 * The chatbot will ask the user how many rounds they want to chat for
 * The chatbot will then ask the user "What's on your mind?" and respond with a canned response
 * The chatbot will then ask the user for input and respond with a canned response
 * The chatbot will continue to ask for input and respond until the number of rounds is reached
 * The chatbot will then say "See ya!" and print the transcript of the conversation
 */
class Conversation implements Chatbot {

  // Attributes 
  private int rounds;
  private List<String> transcript;
  private String[] responses;
  private Random random;
  private Map<String, String> mirrorWords;
  /**
   * Constructor 
   */
  Conversation() {
    this.transcript = new ArrayList<>(); 
    this.responses = new String[] {
      "Uh-huh...",
      "Sounds interesting!",
      "Tell me more about it!",
      "I see...",
      "Go on...",
      "Really?",
      "Wow...",
      "That's cool!",
      "Got it!",
      "Good to know!"
    }; 
    this.random = new Random();
    this.mirrorWords = new HashMap<>();
    initializeMirrorWords();
  }

  /**
   * Initializes the mirrorWords map with words and their mirrored counterparts
   */
  private void initializeMirrorWords() {
    mirrorWords.put("I", "you");
    mirrorWords.put("we", "you");
    mirrorWords.put("me", "you");
    mirrorWords.put("my", "your");
    mirrorWords.put("mine", "yours");
    mirrorWords.put("am", "are");
    mirrorWords.put("I'm", "you're");
    mirrorWords.put("you", "I");
    mirrorWords.put("your", "my");
    mirrorWords.put("yours", "mine");
    mirrorWords.put("are", "am");
    mirrorWords.put("you're", "I'm");
  }
  /**
   * Starts and runs the conversation with the user
   * Asks the user how many rounds they want to chat for
   */
  public void chat() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("How many rounds?");
    this.rounds = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Hi there! What's on your mind?");
    transcript.add("Hi there! What's on your mind?");

    for (int i = 0; i < rounds; i++){
      String userInput = scanner.nextLine();
      transcript.add(userInput);

      String response = respond(userInput);
      System.out.println(response);
      transcript.add(response);
    }

    System.out.println("See ya!");
    transcript.add("See ya!");

    scanner.close();
  }


  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("\nTRANSCRIPT:");
    for (String line : transcript) {
      System.out.println(line);
    }

  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    //Check for punctuation at the end of the input
    String punctuation = "";
    if (inputString.endsWith(".") || inputString.endsWith("!") || inputString.endsWith("?")){
      punctuation = inputString.substring(inputString.length() - 1);
      inputString = inputString.substring(0, inputString.length() - 1);
    }

    String[] words = inputString.split(" ");
    StringBuilder response = new StringBuilder();
    boolean mirrored = false;

    for (String word : words) {
      String mirroredWord = mirrorWords.getOrDefault(word, word);
      if (!mirroredWord.equals(word)){
        mirrored = true;
      }
      response.append(mirroredWord).append(" ");
  }

  //If the response is mirrored, add punctuation to the end of the response
  String finalResponse;
  if (punctuation.equals("?")){
    finalResponse = response.toString().trim() + "? I don't know. What do you think?";
  } else if (mirrored) {
    if (punctuation.equals(".") || punctuation.equals("!")) {
      punctuation = "?";
    }
    finalResponse = response.toString().trim() + punctuation;
  } else {
    int index = random.nextInt(responses.length);
    finalResponse =  responses[index];
    }
  
  //Capitalize the first letter of the response
  if (finalResponse.length() > 0) {
    finalResponse = finalResponse.substring(0, 1).toUpperCase() + finalResponse.substring(1);
  }
  return finalResponse;
}

  /**
   * Main method to run the conversation
   */
  public static void main(String[] arguments) {
    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();
  }
}
