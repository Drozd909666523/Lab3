package labs;

public class Lotto {
    private final Card[][] cards; // Двумерный массив карт, где строки — игроки, а столбцы — карты игрока

    // Конструктор для инициализации игры с заданным количеством игроков
    public Lotto(int countOfPlayers) {
        cards = new Card[countOfPlayers][3]; // Каждый игрок может иметь до 3 карт
    }

    // Метод для добавления карты конкретному игроку
    public void addCard(int numberOfPlayer) {
        boolean hasPlace = true;

        // Проверка, что номер игрока корректен
        if (numberOfPlayer - 1 < cards.length && numberOfPlayer - 1 >= 0) {
            // Пытаемся добавить карту для игрока, если есть место
            for (int i = 0; i < cards[numberOfPlayer - 1].length; i++) {
                if (cards[numberOfPlayer - 1][i] == null && hasPlace) {
                    cards[numberOfPlayer - 1][i] = new Card(); // Создаем новую карту
                    hasPlace = false; // Место занято
                }
            }
            // Если место для карты нет, выводим сообщение
            if (hasPlace) {
                System.out.println("Количество карточек уже максимально");
            }
        } else {
            System.out.println("Некорректный номер игрока"); // Сообщение о неправильном номере игрока
        }
    }

    // Метод для подсчета количества закрытых и открытых номеров на картах игрока
    public int[][] countClosedAndOpenedNumbers(int numberOfPlayer) {
        int[][] count = new int[cards[numberOfPlayer - 1].length][2]; // Массив для хранения результатов
        int i = 0;
        // Перебираем карты игрока, если карта существует, получаем информацию о закрытых и открытых номерах
        while (i < cards[numberOfPlayer - 1].length && cards[numberOfPlayer - 1][i] != null) {
            count[i] = cards[numberOfPlayer - 1][i].countClosedAndOpenedNumbers(); // Записываем количество закрытых и открытых номеров
            i++;
        }
        return count; // Возвращаем результат
    }

    // Метод для закрытия конкретного числа на всех картах игры
    public void closeNumber(int number) {
        int j;
        // Перебираем всех игроков и их карты
        for (int i = 0; i < cards.length; i++) {
            j = 0;
            // Перебираем карты игрока, если карта существует, закрываем указанное число
            while (j < cards[i].length && cards[i][j] != null) {
                cards[i][j].closeNumber(number); // Закрываем число на карте
                j++;
            }
        }
    }

    // Метод для проверки, закрыта ли карта игрока
    public void cardWasClosedCheck(int numberOfPlayer) {
        int i = 0;

        // Перебираем карты игрока и проверяем, закрыта ли каждая из них
        while (i < cards[numberOfPlayer - 1].length && cards[numberOfPlayer - 1][i] != null) {
            if (cards[numberOfPlayer - 1][i].cardWasClosedCheck()) { // Проверка, закрыта ли карта
                System.out.println("Карта номер " + (i + 1) + ", " + numberOfPlayer + "-го игрока была закрыта.");
            } else {
                System.out.println("Карта номер " + (i + 1) + ", " + numberOfPlayer + "-го игрока ещё не закрыта.");
            }
            i++;
        }
    }

    // Метод для поиска победного номера для игрока по массиву чисел
    public int[] FindWinnerNumber(int numberOfPlayer, int[] numbersArr) {
        int[] winnerNumbers = new int[cards[numberOfPlayer - 1].length]; // Массив для хранения победных номеров
        int i = 0;

        // Перебираем карты игрока и ищем победные номера
        while (i < cards[numberOfPlayer - 1].length && cards[numberOfPlayer - 1][i] != null) {
            winnerNumbers[i] = cards[numberOfPlayer - 1][i].FindWinnerNumber(numbersArr); // Записываем победный номер
            i++;
        }
        return winnerNumbers; // Возвращаем массив победных номеров
    }

    // Метод для нахождения победителя игры по количеству закрытых номеров
    public int[] findWinnerPlayer() {
        int countOfClosed = -1; // Счетчик для хранения максимального числа закрытых номеров
        int currentCountOfClosed;
        int[] winners = {0, 0}; // Массив для хранения информации о победителях
        int j;

        // Перебираем всех игроков и считаем закрытые номера на их картах
        for (int i = 0; i < cards.length; i++) {
            currentCountOfClosed = 0;
            j = 0;
            // Перебираем карты игрока и суммируем количество закрытых номеров
            while (j < cards[i].length && cards[i][j] != null) {
                currentCountOfClosed += cards[i][j].countClosedAndOpenedNumbers()[0]; // Суммируем количество закрытых номеров
                j++;
            }
            // Если у текущего игрока больше закрытых номеров, он становится победителем
            if (currentCountOfClosed > countOfClosed) {
                countOfClosed = currentCountOfClosed;
                winners[0] = i + 1; // Номер победителя
                winners[1] = 1; // Количество победителей
            } else if (currentCountOfClosed == countOfClosed) {
                winners[1]++; // Если количество закрытых номеров одинаковое, увеличиваем количество победителей
            }
        }

        return winners; // Возвращаем результат с номером победителя и количеством победителей
    }
}