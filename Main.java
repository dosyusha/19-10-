import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Supplier;

/**
 * 60 заданий по работе со строками в Java.
 * Каждое задание — отдельный метод (номер указан в комментарии).
 * В main() показаны примеры вызова для заданий 1–59, а задание 60 — это меню.
 */
public class Main {

    // =====================================================================
    // 1. ОСНОВНЫЕ МЕТОДЫ КЛАССА String
    // =====================================================================

    /** 1. Длина строки, первый и последний символ; пустая строка обрабатывается отдельно. */
    static String info(String s) {
        if (s == null || s.isEmpty()) {
            return "Строка пустая";
        }
        return "Длина: " + s.length()
                + ", первый: '" + s.charAt(0)
                + "', последний: '" + s.charAt(s.length() - 1) + "'";
    }

    /** 2. Верхний регистр. */
    static String toUpper(String s) {
        return s.toUpperCase();
    }

    /** 2. Нижний регистр. */
    static String toLower(String s) {
        return s.toLowerCase();
    }

    /** 3. Удаление пробелов в начале и конце. */
    static String trimSpaces(String s) {
        return s.trim();
    }

    /** 4. Начинается с префикса и заканчивается суффиксом. */
    static boolean hasPrefixAndSuffix(String s, String prefix, String suffix) {
        return s.startsWith(prefix) && s.endsWith(suffix);
    }

    /** 5. Замена всех вхождений символа другим символом. */
    static String replaceChar(String s, char from, char to) {
        return s.replace(from, to);
    }

    /** 6. Количество вхождений символа. */
    static int countChar(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    /** 7. Индексы первого и последнего вхождения подстроки (-1, если нет). */
    static String firstAndLastIndex(String s, String sub) {
        return "первый: " + s.indexOf(sub) + ", последний: " + s.lastIndexOf(sub);
    }

    /** 8. Содержит ли строка слово без учёта регистра. */
    static boolean containsIgnoreCase(String s, String word) {
        return s.toLowerCase().contains(word.toLowerCase());
    }

    /** 9. Сравнение без учёта регистра. */
    static boolean equalsIgnoreCase(String a, String b) {
        return a.equalsIgnoreCase(b);
    }

    /** 10. Убрать лишние пробелы между словами (остаётся по одному). */
    static String normalizeSpaces(String s) {
        String t = s.trim();
        if (t.isEmpty()) {
            return "";
        }
        return String.join(" ", t.split("\\s+"));
    }

    // =====================================================================
    // 2. ОБРАБОТКА СИМВОЛОВ И ПОДСТРОК
    // =====================================================================

    /** 11. Разворот строки без готового метода reverse. */
    static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /** 12. Палиндром, игнорируя регистр и пробелы. */
    static boolean isPalindrome(String s) {
        String t = s.replace(" ", "").toLowerCase();
        int left = 0;
        int right = t.length() - 1;
        while (left < right) {
            if (t.charAt(left) != t.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /** 13. Первые N символов с проверкой N. */
    static String firstN(String s, int n) {
        if (n < 0 || n > s.length()) {
            throw new IllegalArgumentException("N должно быть от 0 до " + s.length());
        }
        return s.substring(0, n);
    }

    /** 14. Подстрока между индексами [from, to) с проверкой. */
    static String substringBetween(String s, int from, int to) {
        if (from < 0 || to > s.length() || from > to) {
            throw new IllegalArgumentException("Нужно: 0 <= from <= to <= " + s.length());
        }
        return s.substring(from, to);
    }

    /** 15. Удалить все пробелы. */
    static String removeSpaces(String s) {
        return s.replace(" ", "");
    }

    /** 16. Удалить все цифры. */
    static String removeDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /** 17. Оставить только буквы и пробелы. */
    static String onlyLettersAndSpaces(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c) || c == ' ') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /** 18. Свести повторяющиеся пробелы к одному без регулярных выражений. */
    static String collapseSpacesNoRegex(String s) {
        StringBuilder sb = new StringBuilder();
        boolean prevSpace = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (!prevSpace) {
                    sb.append(c);
                }
                prevSpace = true;
            } else {
                sb.append(c);
                prevSpace = false;
            }
        }
        return sb.toString();
    }

    /** 19. Все индексы символа. */
    static List<Integer> indicesOf(String s, char c) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                result.add(i);
            }
        }
        return result;
    }

    /** 20. Разделить пополам; при нечётной длине первая часть короче. */
    static String[] splitHalf(String s) {
        int mid = s.length() / 2; // для нечётной длины целочисленное деление даёт меньшую первую часть
        return new String[]{s.substring(0, mid), s.substring(mid)};
    }

    // =====================================================================
    // 3. СЛОВА И ПРЕДЛОЖЕНИЯ
    // =====================================================================

    /** Вспомогательный метод: разбить текст на слова (пустые элементы не попадают в массив). */
    static String[] words(String s) {
        String t = s.trim();
        if (t.isEmpty()) {
            return new String[0];
        }
        return t.split("\\s+");
    }

    /** 21. Количество слов. */
    static int countWords(String s) {
        return words(s).length;
    }

    /** 22. Самое длинное слово (при равенстве — первое). */
    static String longestWord(String s) {
        String best = "";
        for (String w : words(s)) {
            if (w.length() > best.length()) {
                best = w;
            }
        }
        return best;
    }

    /** 23. Самое короткое слово (пустые элементы не учитываются). */
    static String shortestWord(String s) {
        String[] ws = words(s);
        if (ws.length == 0) {
            return "";
        }
        String best = ws[0];
        for (String w : ws) {
            if (w.length() < best.length()) {
                best = w;
            }
        }
        return best;
    }

    /** 24. Каждое слово с заглавной буквы, остальные — строчные. */
    static String capitalizeWords(String s) {
        String[] ws = words(s);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ws.length; i++) {
            String w = ws[i];
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(w.substring(0, 1).toUpperCase()).append(w.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    /** 25. Слова, начинающиеся с заданной буквы (без учёта регистра). */
    static int countWordsStartingWith(String s, char letter) {
        int count = 0;
        char lower = Character.toLowerCase(letter);
        for (String w : words(s)) {
            if (Character.toLowerCase(w.charAt(0)) == lower) {
                count++;
            }
        }
        return count;
    }

    private static boolean isSentenceEnd(char c) {
        return c == '.' || c == '!' || c == '?';
    }

    /** 26. Количество предложений (группа "..." или "?!" считается одним концом). */
    static int countSentences(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isSentenceEnd(s.charAt(i)) && (i == s.length() - 1 || !isSentenceEnd(s.charAt(i + 1)))) {
                count++;
            }
        }
        return count;
    }

    /** 27. Слова в обратном порядке (сами слова не меняются). */
    static String reverseWords(String s) {
        String[] ws = words(s);
        StringBuilder sb = new StringBuilder();
        for (int i = ws.length - 1; i >= 0; i--) {
            sb.append(ws[i]);
            if (i > 0) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    /** 28. Самое частое слово (регистр не важен); при равенстве — первое встретившееся. */
    static String mostFrequentWord(String s) {
        Map<String, Integer> freq = new LinkedHashMap<>(); // сохраняет порядок появления
        for (String w : words(s)) {
            String key = w.toLowerCase();
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }
        String best = "";
        int bestCount = 0;
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            if (e.getValue() > bestCount) { // строго больше -> при равенстве остаётся первое
                best = e.getKey();
                bestCount = e.getValue();
            }
        }
        return best;
    }

    /** 29. Все слова уникальны (без учёта регистра)? */
    static boolean hasOnlyUniqueWords(String s) {
        Set<String> seen = new HashSet<>();
        for (String w : words(s)) {
            if (!seen.add(w.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    /** 30. Сократить до N символов с многоточием; итоговая длина не больше N. */
    static String truncate(String s, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N не может быть отрицательным");
        }
        if (s.length() <= n) {
            return s;
        }
        String dots = "...";
        if (n <= dots.length()) {
            return dots.substring(0, n);
        }
        return s.substring(0, n - dots.length()) + dots;
    }

    // =====================================================================
    // 4. ПОИСК, СРАВНЕНИЕ И ПРЕОБРАЗОВАНИЕ
    // =====================================================================

    /** 31. Анаграммы (без учёта регистра и пробелов). */
    static boolean areAnagrams(String a, String b) {
        char[] x = a.replace(" ", "").toLowerCase().toCharArray();
        char[] y = b.replace(" ", "").toLowerCase().toCharArray();
        Arrays.sort(x);
        Arrays.sort(y);
        return Arrays.equals(x, y);
    }

    /** 32. Самый длинный общий префикс. */
    static String commonPrefix(String a, String b) {
        int i = 0;
        while (i < a.length() && i < b.length() && a.charAt(i) == b.charAt(i)) {
            i++;
        }
        return a.substring(0, i);
    }

    /** 33. Самая длинная общая подстрока (динамическое программирование). */
    static String longestCommonSubstring(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        int best = 0;
        int endInA = 0;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > best) {
                        best = dp[i][j];
                        endInA = i;
                    }
                }
            }
        }
        return a.substring(endInA - best, endInA);
    }

    /** 34. Неперекрывающиеся вхождения подстроки. */
    static int countOccurrences(String s, String sub) {
        if (sub.isEmpty()) {
            return 0;
        }
        int count = 0;
        int idx = s.indexOf(sub);
        while (idx != -1) {
            count++;
            idx = s.indexOf(sub, idx + sub.length()); // перескакиваем через найденное
        }
        return count;
    }

    /** 35. Заменить только первое вхождение подстроки. */
    static String replaceFirstOccurrence(String s, String target, String replacement) {
        int idx = s.indexOf(target);
        if (idx == -1 || target.isEmpty()) {
            return s;
        }
        return s.substring(0, idx) + replacement + s.substring(idx + target.length());
    }

    /** 36. Удалить все вхождения подстроки. */
    static String removeAllOccurrences(String s, String sub) {
        if (sub.isEmpty()) {
            return s;
        }
        return s.replace(sub, "");
    }

    /** 37. Сравнение версий по числовым компонентам: -1, 0 или 1. */
    static int compareVersions(String a, String b) {
        String[] x = a.split("\\.");
        String[] y = b.split("\\.");
        int n = Math.max(x.length, y.length);
        for (int i = 0; i < n; i++) {
            int p = i < x.length ? Integer.parseInt(x[i]) : 0;
            int q = i < y.length ? Integer.parseInt(y[i]) : 0;
            if (p != q) {
                return p < q ? -1 : 1;
            }
        }
        return 0;
    }

    /** 38. Сортировка имён по алфавиту без учёта регистра (исходный массив не меняется). */
    static String[] sortNames(String[] names) {
        String[] copy = names.clone();
        Arrays.sort(copy, String.CASE_INSENSITIVE_ORDER);
        return copy;
    }

    /** 39. Слова, содержащие подстроку, без повторений (регистр не важен). */
    static Set<String> wordsContaining(String text, String sub) {
        Set<String> result = new LinkedHashSet<>();
        for (String w : words(text)) {
            if (w.toLowerCase().contains(sub.toLowerCase())) {
                result.add(w);
            }
        }
        return result;
    }

    /** 40. Можно ли получить b из a удалением ровно одного символа? */
    static boolean canRemoveOneChar(String a, String b) {
        if (a.length() != b.length() + 1) {
            return false;
        }
        for (int i = 0; i < a.length(); i++) {
            String candidate = a.substring(0, i) + a.substring(i + 1);
            if (candidate.equals(b)) {
                return true;
            }
        }
        return false;
    }

    // =====================================================================
    // 5. ФОРМАТИРОВАНИЕ И ПРОВЕРКА ДАННЫХ
    // =====================================================================

    /** 41. Простая проверка email. */
    static boolean isValidEmail(String s) {
        int at = s.indexOf('@');
        if (at == -1 || at != s.lastIndexOf('@')) {
            return false; // нет '@' или их больше одного
        }
        if (at == 0 || at == s.length() - 1) {
            return false; // пустая часть до или после '@'
        }
        int dot = s.indexOf('.', at + 1);
        // точка должна быть после '@', не сразу за ним и не в самом конце
        return dot > at + 1 && dot < s.length() - 1;
    }

    /** 42. Телефон: '+7' и 10 цифр; пробелы и дефисы только между цифрами. */
    static boolean isValidPhone(String s) {
        if (!s.startsWith("+7")) {
            return false;
        }
        String body = s.substring(1); // "7 900-123-45-67"
        int digits = 0;
        for (int i = 0; i < body.length(); i++) {
            char c = body.charAt(i);
            if (Character.isDigit(c)) {
                digits++;
            } else if (c == ' ' || c == '-') {
                boolean digitBefore = i > 0 && Character.isDigit(body.charAt(i - 1));
                boolean digitAfter = i < body.length() - 1 && Character.isDigit(body.charAt(i + 1));
                if (!digitBefore || !digitAfter) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return digits == 11; // 7 + 10 цифр
    }

    /** 43. Пароль: >= 8 символов, есть заглавная, строчная и цифра. */
    static boolean isStrongPassword(String s) {
        if (s.length() < 8) {
            return false;
        }
        boolean upper = false, lower = false, digit = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) upper = true;
            else if (Character.isLowerCase(c)) lower = true;
            else if (Character.isDigit(c)) digit = true;
        }
        return upper && lower && digit;
    }

    /** 44. "Иван Петров" -> "Петров, И." */
    static String formatName(String fullName) {
        String[] parts = words(fullName);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Нужно ровно два слова: имя и фамилия");
        }
        String first = parts[0];
        String last = parts[1];
        return last + ", " + first.substring(0, 1).toUpperCase() + ".";
    }

    /** 45. "1234567" -> "1 234 567" */
    static String formatThousands(String digits) {
        if (!isOnlyDigits(digits)) {
            throw new IllegalArgumentException("Строка должна состоять только из цифр");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length(); i++) {
            if (i > 0 && (digits.length() - i) % 3 == 0) {
                sb.append(' ');
            }
            sb.append(digits.charAt(i));
        }
        return sb.toString();
    }

    /** 46. Только цифры (без Integer.parseInt). */
    static boolean isOnlyDigits(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /** 47. "дд-мм-гггг" -> "гггг/мм/дд" с проверкой формата. */
    static String convertDate(String date) {
        String[] p = date.split("-");
        if (p.length != 3 || p[0].length() != 2 || p[1].length() != 2 || p[2].length() != 4
                || !isOnlyDigits(p[0]) || !isOnlyDigits(p[1]) || !isOnlyDigits(p[2])) {
            throw new IllegalArgumentException("Ожидается формат дд-мм-гггг");
        }
        int day = Integer.parseInt(p[0]);
        int month = Integer.parseInt(p[1]);
        if (day < 1 || day > 31 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Недопустимые день или месяц");
        }
        return p[2] + "/" + p[1] + "/" + p[0];
    }

    /** 48. Нормализация имени пользователя. */
    static String normalizeUsername(String s) {
        return normalizeSpaces(s);
    }

    /** 49. Допустимый идентификатор Java. */
    static boolean isJavaIdentifier(String s) {
        if (s.isEmpty()) {
            return false;
        }
        char first = s.charAt(0);
        if (!(Character.isLetter(first) || first == '_' || first == '$')) {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(Character.isLetterOrDigit(c) || c == '_' || c == '$')) {
                return false;
            }
        }
        return true;
    }

    /** 50. Домен из URL. */
    static String extractDomain(String url) {
        String d = url.trim();
        int scheme = d.indexOf("://");
        if (scheme != -1) {
            d = d.substring(scheme + 3);
        }
        int end = d.length();
        for (int i = 0; i < d.length(); i++) {
            char c = d.charAt(i);
            if (c == '/' || c == '?' || c == '#' || c == ':') { // путь, параметры, якорь, порт
                end = i;
                break;
            }
        }
        d = d.substring(0, end);
        if (d.startsWith("www.")) {
            d = d.substring(4);
        }
        return d;
    }

    // =====================================================================
    // 6. КОМПЛЕКСНЫЕ ЗАДАНИЯ
    // =====================================================================

    private static boolean isPunctuation(char c) {
        return "!\"#%&'()*,-./:;?@[\\]_{}«»—…".indexOf(c) != -1;
    }

    /** 51. Статистика по тексту. */
    static String textStatistics(String s) {
        int letters = 0, digits = 0, spaces = 0, punct = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) letters++;
            else if (Character.isDigit(c)) digits++;
            else if (Character.isWhitespace(c)) spaces++;
            else if (isPunctuation(c)) punct++;
        }
        return "символов: " + s.length() + ", букв: " + letters + ", цифр: " + digits
                + ", пробелов: " + spaces + ", знаков препинания: " + punct;
    }

    /** 52. Шифр Цезаря для английских букв. */
    static String caesarEncrypt(String s, int shift) {
        int k = ((shift % 26) + 26) % 26; // работает и для отрицательных сдвигов
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sb.append((char) ('A' + (c - 'A' + k) % 26));
            } else if (c >= 'a' && c <= 'z') {
                sb.append((char) ('a' + (c - 'a' + k) % 26));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /** 53. Дешифрование — это шифрование с обратным сдвигом. */
    static String caesarDecrypt(String s, int shift) {
        return caesarEncrypt(s, -shift);
    }

    /** 54. Заменить запрещённые слова звёздочками той же длины (регистр не важен). */
    static String censor(String text, String[] banned) {
        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i <= text.length(); i++) {
            char c = i < text.length() ? text.charAt(i) : ' ';
            if (i < text.length() && Character.isLetterOrDigit(c)) {
                word.append(c);
            } else {
                if (word.length() > 0) {
                    result.append(isBanned(word.toString(), banned) ? "*".repeat(word.length()) : word);
                    word.setLength(0);
                }
                if (i < text.length()) {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }

    private static boolean isBanned(String word, String[] banned) {
        for (String b : banned) {
            if (word.equalsIgnoreCase(b)) {
                return true;
            }
        }
        return false;
    }

    /** 55. Слова только в первом тексте и только во втором. */
    static String compareTexts(String t1, String t2) {
        Set<String> set1 = new LinkedHashSet<>();
        Set<String> set2 = new LinkedHashSet<>();
        for (String w : words(t1)) set1.add(w.toLowerCase());
        for (String w : words(t2)) set2.add(w.toLowerCase());

        Set<String> onlyFirst = new LinkedHashSet<>(set1);
        onlyFirst.removeAll(set2);
        Set<String> onlySecond = new LinkedHashSet<>(set2);
        onlySecond.removeAll(set1);
        return "только в первом: " + onlyFirst + "; только во втором: " + onlySecond;
    }

    /** 56. Баланс круглых скобок. */
    static boolean isBalanced(String s) {
        int open = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            } else if (c == ')') {
                open--;
                if (open < 0) {
                    return false; // закрывающая скобка без пары
                }
            }
        }
        return open == 0;
    }

    /** 57. Нормализация ФИО. */
    static String normalizeFio(String s) {
        return normalizeSpaces(s);
    }

    /** 58. Индексы всех вхождений фразы без учёта регистра. */
    static List<Integer> findAllIgnoreCase(String text, String phrase) {
        List<Integer> result = new ArrayList<>();
        if (phrase.isEmpty()) {
            return result;
        }
        String t = text.toLowerCase();
        String p = phrase.toLowerCase();
        int idx = t.indexOf(p);
        while (idx != -1) {
            result.add(idx);
            idx = t.indexOf(p, idx + 1);
        }
        return result;
    }

    /** 59. Первые 20 слов + '...' если слов больше. */
    static String firstTwentyWords(String text) {
        String[] ws = words(text);
        int limit = Math.min(20, ws.length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < limit; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(ws[i]);
        }
        if (ws.length > 20) {
            sb.append("...");
        }
        return sb.toString();
    }

    // =====================================================================
    // 60. КОНСОЛЬНЫЙ «ТЕКСТОВЫЙ ПОМОЩНИК»
    // =====================================================================

    static void textAssistant() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> menuCountWords(scanner);
                case "2" -> menuReverse(scanner);
                case "3" -> menuSearch(scanner);
                case "4" -> menuReplace(scanner);
                case "5" -> menuPalindrome(scanner);
                case "0" -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неизвестный пункт меню, попробуйте ещё раз.");
            }
        }
    }

    static void printMenu() {
        System.out.println();
        System.out.println("=== Текстовый помощник ===");
        System.out.println("1. Подсчёт слов");
        System.out.println("2. Разворот строки");
        System.out.println("3. Поиск подстроки");
        System.out.println("4. Замена подстроки");
        System.out.println("5. Проверка палиндрома");
        System.out.println("0. Выход");
        System.out.print("Выберите пункт: ");
    }

    static String ask(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    static void menuCountWords(Scanner scanner) {
        String text = ask(scanner, "Введите текст: ");
        System.out.println("Количество слов: " + countWords(text));
    }

    static void menuReverse(Scanner scanner) {
        String text = ask(scanner, "Введите текст: ");
        System.out.println("Результат: " + reverse(text));
    }

    static void menuSearch(Scanner scanner) {
        String text = ask(scanner, "Введите текст: ");
        String phrase = ask(scanner, "Что искать: ");
        List<Integer> found = findAllIgnoreCase(text, phrase);
        if (found.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            System.out.println("Найдено " + found.size() + " раз(а), индексы: " + found);
        }
    }

    static void menuReplace(Scanner scanner) {
        String text = ask(scanner, "Введите текст: ");
        String target = ask(scanner, "Что заменить: ");
        String replacement = ask(scanner, "На что заменить: ");
        if (target.isEmpty()) {
            System.out.println("Искомая подстрока не может быть пустой.");
            return;
        }
        System.out.println("Результат: " + text.replace(target, replacement));
    }

    static void menuPalindrome(Scanner scanner) {
        String text = ask(scanner, "Введите текст: ");
        System.out.println(isPalindrome(text) ? "Это палиндром." : "Это не палиндром.");
    }

    // =====================================================================
    // MAIN: примеры вызова всех заданий
    // =====================================================================

    /** Выполняет код и возвращает результат или текст ошибки (чтобы демо не падало). */
    static Object attempt(Supplier<Object> action) {
        try {
            return action.get();
        } catch (RuntimeException e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    static void show(int number, Object result) {
        System.out.println(number + ") " + result);
    }

    public static void main(String[] args) {
        // чтобы русские буквы выводились правильно
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println("===== Основные методы String =====");
        show(1, info("Привет") + " | пустая: " + info(""));
        show(2, toUpper("Hello Java") + " / " + toLower("Hello Java"));
        show(3, "[" + trimSpaces("   текст   ") + "]");
        show(4, hasPrefixAndSuffix("Hello, world", "Hello", "world"));
        show(5, replaceChar("banana", 'a', 'o'));
        show(6, countChar("banana", 'a'));
        show(7, firstAndLastIndex("abcabcabc", "abc"));
        show(8, containsIgnoreCase("Я люблю JAVA", "java"));
        show(9, equalsIgnoreCase("JaVa", "jAvA"));
        show(10, "[" + normalizeSpaces("  много    лишних   пробелов  ") + "]");

        System.out.println("\n===== Символы и подстроки =====");
        show(11, reverse("Java"));
        show(12, isPalindrome("А роза упала на лапу Азора"));
        show(13, attempt(() -> firstN("Программа", 4)) + " | " + attempt(() -> firstN("abc", 10)));
        show(14, attempt(() -> substringBetween("Программа", 2, 6)) + " | " + attempt(() -> substringBetween("abc", 3, 1)));
        show(15, removeSpaces("a b c d"));
        show(16, removeDigits("a1b2c3"));
        show(17, onlyLettersAndSpaces("Привет, мир! 123"));
        show(18, "[" + collapseSpacesNoRegex("a   b    c") + "]");
        show(19, indicesOf("banana", 'a'));
        show(20, Arrays.toString(splitHalf("abcde")) + " " + Arrays.toString(splitHalf("abcd")));

        System.out.println("\n===== Слова и предложения =====");
        String sample = "  Java   очень   интересный язык  ";
        show(21, countWords(sample));
        show(22, longestWord("кот собака слон лев"));
        show(23, shortestWord("  кот  собака слон  "));
        show(24, capitalizeWords("пРиВеТ мир ЖИВЕТ"));
        show(25, countWordsStartingWith("Кот катит колесо, мышь бежит", 'к'));
        show(26, countSentences("Привет! Как дела? Всё хорошо... Спасибо."));
        show(27, reverseWords("один два три"));
        show(28, mostFrequentWord("да нет Да нет да"));
        show(29, hasOnlyUniqueWords("Один два ОДИН") + " / " + hasOnlyUniqueWords("один два три"));
        show(30, truncate("Это очень длинный текст", 10) + " | " + truncate("short", 10));

        System.out.println("\n===== Поиск, сравнение, преобразование =====");
        show(31, areAnagrams("Listen", "Silent") + " / " + areAnagrams("abc", "abd"));
        show(32, commonPrefix("программа", "программист"));
        show(33, longestCommonSubstring("abcdefg", "xxcdefyy"));
        show(34, countOccurrences("aaaa", "aa"));
        show(35, replaceFirstOccurrence("кот и кот", "кот", "пёс"));
        show(36, removeAllOccurrences("a-b-c-d", "-"));
        show(37, compareVersions("2.10", "2.9") + " (1 = первая версия больше)");
        show(38, Arrays.toString(sortNames(new String[]{"олег", "Анна", "борис", "Владимир"})));
        show(39, wordsContaining("кот котёнок скот кот собака", "кот"));
        show(40, canRemoveOneChar("abcd", "abd") + " / " + canRemoveOneChar("abcd", "ab"));

        System.out.println("\n===== Форматирование и проверка =====");
        show(41, isValidEmail("user@example.com") + " / " + isValidEmail("user@@example.com") + " / " + isValidEmail("user@example"));
        show(42, isValidPhone("+7 900-123-45-67") + " / " + isValidPhone("+7 900-123-45-6") + " / " + isValidPhone("+7--9001234567"));
        show(43, isStrongPassword("Abcdef12") + " / " + isStrongPassword("abcdef12"));
        show(44, attempt(() -> formatName("Иван Петров")));
        show(45, formatThousands("1234567"));
        show(46, isOnlyDigits("12345") + " / " + isOnlyDigits("12a45"));
        show(47, attempt(() -> convertDate("23-10-2026")) + " | " + attempt(() -> convertDate("2-10-2026")));
        show(48, "[" + normalizeUsername("  user   name  ") + "]");
        show(49, isJavaIdentifier("_value1") + " / " + isJavaIdentifier("1value"));
        show(50, extractDomain("https://www.example.com/path/page?x=1"));

        System.out.println("\n===== Комплексные задания =====");
        show(51, textStatistics("Привет, мир! 2026 год."));
        String encrypted = caesarEncrypt("Hello, World!", 3);
        show(52, encrypted);
        show(53, caesarDecrypt(encrypted, 3));
        show(54, censor("Это плохое слово и ПЛОХОЕ тоже", new String[]{"плохое"}));
        show(55, compareTexts("кот собака мышь", "собака слон кот"));
        show(56, isBalanced("(a + (b * c))") + " / " + isBalanced("(()") + " / " + isBalanced(")("));
        show(57, "[" + normalizeFio("  Иванов    Иван   Иванович ") + "]");
        show(58, findAllIgnoreCase("Java и java, JAVA!", "java"));
        show(59, firstTwentyWords("раз два три четыре пять шесть семь восемь девять десять "
                + "одиннадцать двенадцать тринадцать четырнадцать пятнадцать шестнадцать "
                + "семнадцать восемнадцать девятнадцать двадцать двадцать-один"));

        System.out.println("\n===== 60. Текстовый помощник =====");
        textAssistant();
    }
}