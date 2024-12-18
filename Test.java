import labs.Lotto;

public class Test {
    public static void main(String[] args) {
        // Создаем объект игры Lotto с 2 игроками
        Lotto lotto = new Lotto(2);

        // 1. Проверка метода addCard()
        System.out.println("Тест 1: Добавление карт игрокам");
        lotto.addCard(1); // Добавляем первую карту для игрока 1
        lotto.addCard(1); // Добавляем вторую карту для игрока 1
        lotto.addCard(1); // Добавляем третью карту для игрока 1
        lotto.addCard(1); // Попытка добавить четвертую карту для игрока 1 (не должно быть возможности)
        lotto.addCard(2); // Добавляем первую карту для игрока 2
        lotto.addCard(2); // Добавляем вторую карту для игрока 2

        // 2. Проверка метода countClosedAndOpenedNumbers()
        System.out.println("\nТест 2: Подсчет закрытых и открытых номеров для игрока 1");
        int[][] closedOpened1 = lotto.countClosedAndOpenedNumbers(1);
        for (int i = 0; i < closedOpened1.length; i++) {
            System.out.println("Карта " + (i + 1) + ": Закрытых номеров = " + closedOpened1[i][0] +
                    ", Открытых номеров = " + closedOpened1[i][1]);
        }

        // 3. Проверка метода closeNumber()
        System.out.println("\nТест 3: Закрытие числа 5 на всех картах");
        lotto.closeNumber(1); // Закрываем число 1 на всех картах
        lotto.closeNumber(2); // Закрываем число 2 на всех картах
        lotto.closeNumber(3); // Закрываем число 3 на всех картах
        lotto.closeNumber(4); // Закрываем число 4 на всех картах
        lotto.closeNumber(5); // Закрываем число 5 на всех картах
        int[][] closedOpenedAfterClose = lotto.countClosedAndOpenedNumbers(1);
        for (int i = 0; i < closedOpenedAfterClose.length; i++) {
            System.out.println("Карта " + (i + 1) + " после закрытия чисел от 1 до 5: Закрытых номеров = " +
                    closedOpenedAfterClose[i][0] + ", Открытых номеров = " + closedOpenedAfterClose[i][1]);
        }

        for (int i = 6; i < 96; i++) {
            lotto.closeNumber(i); // Закрываем число i на всех картах
        }

        // 4. Проверка метода cardWasClosedCheck()
        System.out.println("\nТест 4: Проверка закрытия карт игрока 1");
        lotto.cardWasClosedCheck(1);

        System.out.println("\nТест 4: Проверка закрытия карт игрока 2");
        lotto.cardWasClosedCheck(2);

        // 5. Проверка метода FindWinnerNumber()
        System.out.println("\nТест 5: Поиск победного номера для игрока 1");
        int[] numbersArr = {96, 97, 98, 99, 100};
        int[] winnerNumbers = lotto.FindWinnerNumber(1, numbersArr);
        System.out.println("Победные номера для игрока 1: ");
        for (int num : winnerNumbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 6. Проверка метода findWinnerPlayer()
        System.out.println("\nТест 6: Поиск победителя игры");
        int[] winners = lotto.findWinnerPlayer();
        System.out.println("Победитель: Игрок " + winners[0] + ", количество победителей = " + winners[1]);
    }
}
