package labs;

import java.util.Random;

public class Card {
    private final String[] numbers = new String[10]; // Массив для хранения 10 случайных чисел на карточке

    // Конструктор, генерирующий 10 уникальных случайных чисел для карты
    public Card() {
        Random random = new Random(); // Создаем объект для генерации случайных чисел
        boolean hasDuplicate; // Переменная для проверки уникальности чисел
        int index = 0; // Индекс для вставки чисел
        int x; // Переменная для хранения случайного числа

        // Генерация чисел до тех пор, пока не заполним массив
        while (index < 10) {
            x = random.nextInt(100) + 1; // Генерация случайного числа от 1 до 100
            hasDuplicate = false;
            // Проверка, нет ли дублирующегося числа в массиве
            for (int i = 0; i < index; i++) {
                if (String.valueOf(x).equals(this.numbers[i])) {
                    hasDuplicate = true;
                }
            }
            // Если число уникальное, добавляем его в массив
            if (!hasDuplicate) {
                this.numbers[index] = String.valueOf(x);
                index++;
            }
        }
    }

    // Метод для подсчета количества закрытых ("X") и открытых номеров на карте
    public int[] countClosedAndOpenedNumbers() {
        int[] count = {0, 0}; // Массив, где [0] — количество закрытых, [1] — количество открытых
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].equals("X")) {
                count[0]++; // Увеличиваем счетчик закрытых номеров
            } else {
                count[1]++; // Увеличиваем счетчик открытых номеров
            }
        }
        return count; // Возвращаем массив с подсчитанными значениями
    }

    // Метод для закрытия конкретного числа на карте
    public void closeNumber(int number) {
        for (int i = 0; i < numbers.length; i++) {
            if (String.valueOf(number).equals(numbers[i])) {
                numbers[i] = "X"; // Закрываем число, заменяя его на "X"
            }
        }
    }

    // Метод для проверки, закрыта ли вся карта (все числа заменены на "X")
    public boolean cardWasClosedCheck() {
        boolean cardIsClosed = true; // По умолчанию предполагаем, что карта закрыта
        for (int i = 0; i < numbers.length; i++) {
            if (!numbers[i].equals("X")) { // Если хоть одно число не закрыто
                cardIsClosed = false; // Карта не закрыта
            }
        }
        return cardIsClosed; // Возвращаем результат
    }

    // Метод для поиска победного номера на карте (если номер присутствует в массиве)
    public int FindWinnerNumber(int[] numbersArr) {
        int winnerNumber = -1; // По умолчанию победного номера нет
        int indexOfRequiredNumber = -1; // Индекс первого открытого номера на карте

        // Находим первый открытый номер на карте
        for (int i = 0; i < numbers.length; i++) {
            if (!numbers[i].equals("X")) {
                if (indexOfRequiredNumber == -1) {
                    indexOfRequiredNumber = i; // Запоминаем индекс первого открытого числа
                }
            }
        }

        // Если нашли открытый номер, ищем его в массиве чисел
        if (indexOfRequiredNumber != -1) {
            for (int i = 0; i < numbersArr.length; i++) {
                // Если число из массива совпадает с открытым номером на карте, это победный номер
                if (String.valueOf(numbersArr[i]).equals(numbers[indexOfRequiredNumber])) {
                    winnerNumber = numbersArr[i]; // Устанавливаем победный номер
                }
            }
        }

        return winnerNumber; // Возвращаем найденный победный номер или -1, если его нет
    }

    // Переопределенный метод toString для представления карты в строковом виде
    @Override
    public String toString() {
        String s = "[ ";

        for (int i = 0; i < numbers.length; i++)
            s += numbers[i] + " "; // Добавляем каждое число карты в строку

        return s + "]"; // Возвращаем строку с числами карты
    }
}