#include <vector>
#include <iostream>
#include <stdexcept>
#include <functional>
#include <memory>
#include <random>
#include <algorithm>

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