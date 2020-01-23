import java.util.Scanner;

public class Main
{
    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for(;;)
        {
            try {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);
                String firstElement = tokens[0];
                if (firstElement.equals("add") && tokens.length == 2) {
                    executor.addCustomer(tokens[1]);
                } else if (firstElement.equals("list")) {
                    executor.listCustomers();
                } else if (firstElement.equals("remove") && tokens.length == 2){
                    executor.removeCustomer(tokens[1]);
                } else if (firstElement.equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (firstElement.equals("help")){
                    System.out.println(helpText);
                } else {
                    System.out.println(commandError);
                }
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
