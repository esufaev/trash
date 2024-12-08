#include <vector>
#include <iostream>
#include <stdexcept>
#include <functional>
#include <memory>
#include <random>
#include <algorithm>

//   Задача: Найти "последнюю лекцию"
 
//   Легенда:
//   В Казанском федеральном университете проводится олимпиада по программированию,
//   где студенты решают задачи, основанные на реальных ситуациях.
//   Представьте, что вы отвечаете за расписание лекций. У вас есть список временных
//   интервалов, где каждый интервал `[start_i, end_i]` представляет время начала и конца лекции.
//   Ваша задача — для каждой лекции найти следующую подходящую лекцию.
 
//   "Правой лекцией" для лекции `i` считается лекция `j`, которая:
//     - начинается не раньше, чем заканчивается лекция `i` (то есть `start_j >= end_i`),
//     - начинается как можно раньше (минимальное `start_j`).
 
//   Если подходящей лекции нет, нужно вернуть `-1` для этой позиции.
 
//   Формат ввода:
//     - `intervals` — массив интервалов, где `intervals[i] = [start_i, end_i]`.
//       Гарантируется, что все `start_i` уникальны.
 
//   Формат вывода:
//     - Массив индексов правых лекций: для каждого интервала `i` верните индекс `j`,
//       либо `-1`, если такой лекции нет.
 
//   Примеры:
 
//   Пример 1:
//   Ввод: intervals = [[1,2]]
//   Вывод: [-1]
//   Объяснение:
//     Есть только одна лекция, так что нет следующей подходящей.
 
//   Пример 2:
//   Ввод: intervals = [[3,4], [2,3], [1,2]]
//   Вывод: [-1, 0, 1]
//   Объяснение:
//     - Для лекции `[3,4]` нет следующей лекции, так что результат `-1`.
//     - Для лекции `[2,3]` следующая подходящая лекция — это `[3,4]`.
//     - Для лекции `[1,2]` следующая подходящая лекция — это `[2,3]`.
 
class solution_base
{
public:
    std::vector<int> solution_bs(std::vector<std::vector<int>>& intervals)
    {
        int n = intervals.size();
        std::vector<int> result(n, -1);
        std::vector<std::pair<int, int>> starts(n);
        for (int i = 0; i < n; i++)
        {
            starts[i] = {intervals[i][0], i};
        }
        std::sort(starts.begin(), starts.end());

        for (int i = 0; i < n; i++)
        {
            int lo = 0, hi = n - 1;
            while (lo <= hi)
            {
                int mid = lo + (hi - lo) / 2;
                if (starts[mid].first >= intervals[i][1])
                {
                    result[i] = starts[mid].second;
                    hi = mid - 1;
                }
                else
                {
                    lo = mid + 1;
                }
            }
        }

        return result;
    }

    std::vector<int> solution_en(std::vector<std::vector<int>> &intervals)
    {
        int n = intervals.size();
        std::vector<int> result(n, -1);

        for (int i = 0; i < n; i++)
        {
            int min_diff = INT_MAX;
            int right_index = -1;

            for (int j = 0; j < n; j++)
            {
                if (intervals[j][0] >= intervals[i][1])
                {
                    int diff = intervals[j][0] - intervals[i][1];
                    if (diff < min_diff)
                    {
                        min_diff = diff;
                        right_index = j;
                    }
                }
            }

            result[i] = right_index;
        }

        return result;
    }
};