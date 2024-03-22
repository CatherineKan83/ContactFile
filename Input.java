import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Input {
    public static void main(String[] args) {
        Boolean check = false;
        Boolean ch = false;
        String op;
        System.setProperty("console.encoding", "cp866");
        Scanner scanner = new Scanner(System.in, "cp866");
        while(check!=true){
            try {
                System.out.println("Введите данные через пробел: Фамилия Имя Отчество дата_рождения(дд.мм.гггг) номер_телефона(без форматированя) пол(f-женский/m-мужской)");
                String input = scanner.nextLine();

                String[] data = input.split(" ");
                if (data.length < 6){
                    throw new IllegalArgumentException("Введено меньше требуемого объёма данных.");
                } else if(data.length >6){
                    throw new IllegalArgumentException("Введено больше требуемого объёма данных.");
                }
                String lastName = data[0].substring(0,1).toUpperCase()+data[0].substring(1);
                String firstName = data[1].substring(0,1).toUpperCase()+data[1].substring(1);
                String patronymic = data[2].substring(0,1).toUpperCase()+data[2].substring(1);
                String dateOfBirth = data[3];
                long phoneNumber = Long.parseLong(data[4]);
                char gender = data[5].charAt(0);

                if (!dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                    throw new IllegalArgumentException("Введен неверный формат даты рождения. Допустимый формат dd.mm.yyyy.");
                }

                if(Integer.valueOf(dateOfBirth.substring(3,5))<12){
                    if(Integer.valueOf(dateOfBirth.substring(3,5))==1 | Integer.valueOf(dateOfBirth.substring(3,5))==3 | Integer.valueOf(dateOfBirth.substring(3,5))==5 | Integer.valueOf(dateOfBirth.substring(3,5))==7 | Integer.valueOf(dateOfBirth.substring(3,5))==8 | Integer.valueOf(dateOfBirth.substring(3,5))==10 | Integer.valueOf(dateOfBirth.substring(3,5))==12){
                        if ( Integer.valueOf(dateOfBirth.substring(0,2))>31){
                            
                            throw new IllegalArgumentException("Введено неверное число.");
                        }
                    } else if (Integer.valueOf(dateOfBirth.substring(3,5))==4 | Integer.valueOf(dateOfBirth.substring(3,5))==6 | Integer.valueOf(dateOfBirth.substring(3,5))==9 | Integer.valueOf(dateOfBirth.substring(3,5))==11){
                        if ( Integer.valueOf(dateOfBirth.substring(0,2))>30){
                            throw new IllegalArgumentException("Введено неверное число.");
                        }
                    } else if (Integer.valueOf(dateOfBirth.substring(3,5))==2){
                        if ( Integer.valueOf(dateOfBirth.substring(0,2))>29){
                            throw new IllegalArgumentException("Введено неверное число.");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Введён неверный месяц.");
                }
                if (gender != 'f' && gender != 'm') {
                    throw new IllegalArgumentException("Введен неверный символ пола. Допустимые символы - f/m.");
                }

                String fileName = lastName + ".txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
                    writer.write("<"+ lastName + "> <" + firstName + "> <" + patronymic + "> <" + dateOfBirth + "> <" + phoneNumber + "> <" + gender + ">");
                    writer.newLine();
                } catch (IOException e) {
                    System.err.println("Ошибка при записи в файл: " + e.getMessage());
                    e.printStackTrace();
                }

                System.out.println("Данные успешно записаны в файл " + fileName);
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка: " + e.getMessage());
                e.printStackTrace();
            } finally {
                System.out.println("Что хотите сделать?\n1)Продолжить \n2)Выйти ");
                while(ch!=true){
                    op = scanner.nextLine();
                    switch (op) {
                        case "1":
                            ch = true;
                            break;
                        case "2":
                            check=true;
                            ch = true;
                            break;
                        default:
                            System.out.println("Ошибка ввода. Попробуйте снова. Введите число, соответствующее вашему решению.");
                            break;
                    }
                }                    
                ch=false;

                if(check == true){
                    scanner.close();
                }
            }
        }
    }
}