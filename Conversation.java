import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Conversation implements Chatbot {

  // Attributes 
  private int rounds;
  private List<String> transcript;
  /**
   * Constructor 
   */
  Conversation() {
    this.transcript = new ArrayList<>();  
  }

  /**
   * Starts and runs the conversation with the user
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
    return "Uh-huh..."; 
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
