import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        if (components.length != 4){
            throw new IllegalArgumentException("Error! Wrong format. Correct format: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }else {
            String formatPhoneNum = cleanNumberPhone(components[3]);
            if (!checkEmailCorrect(components[2])){
                throw new IllegalArgumentException("Error! Wrong e-mail format");
            }else if (!checkNumberPhoneCorrect(formatPhoneNum)) {
                throw new IllegalArgumentException("Error! Wrong phone number format");
            }else {
                String name = components[0] + " " + components[1];
                storage.put(name, new Customer(name, "+" + formatPhoneNum, components[2]));
                System.out.println("New user created!");
            }
        }
    }

    public void listCustomers()
    {
        if (storage.isEmpty()){
            System.out.println("User list is empty");
        }
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        if (storage.containsKey(name)) {
            storage.remove(name);
            System.out.println("User " + name + " was removed");
        }else{
            throw new IllegalArgumentException("Error! This name not found.");
        }
    }

    public int getCount()
    {
        return storage.size();
    }
    private static boolean checkEmailCorrect(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    private static String cleanNumberPhone(String phone) {

        return phone.replaceAll("\\D+", "");
    }
    private static boolean checkNumberPhoneCorrect(String phone) {
        return phone.matches("(7)\\d{10}");
    }
}
