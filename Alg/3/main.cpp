#include <iostream>
#include <vector>
#include <algorithm>
#include <random>
#include <chrono>
#include <iomanip>
#include <functional>

std::vector<int> generate_random_data(size_t size, int min_val, int max_val)
{
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> dist(min_val, max_val);
    std::vector<int> data(size);
    for (auto &val : data)
    {
        val = dist(gen);
    }
    return data;
}

void bubble_sort(std::vector<int> &data)
{
    for (size_t i = 0; i < data.size(); ++i)
    {
        for (size_t j = 0; j < data.size() - i - 1; ++j)
        {
            if (data[j] > data[j + 1])
            {
                std::swap(data[j], data[j + 1]);
            }
        }
    }
}

void insertion_sort(std::vector<int> &data)
{
    for (size_t i = 1; i < data.size(); ++i)
    {
        int key = data[i];
        size_t j = i;
        while (j > 0 && data[j - 1] > key)
        {
            data[j] = data[j - 1];
            --j;
        }
        data[j] = key;
    }
}

void quick_sort(std::vector<int> &data, int low, int high)
{
    if (low < high)
    {
        int pivot = data[high];
        int i = low - 1;
        for (int j = low; j < high; ++j)
        {
            if (data[j] < pivot)
            {
                std::swap(data[++i], data[j]);
            }
        }
        std::swap(data[i + 1], data[high]);
        int partition_index = i + 1;

        quick_sort(data, low, partition_index - 1);
        quick_sort(data, partition_index + 1, high);
    }
}

void merge_sort(std::vector<int> &data)
{
    if (data.size() <= 1)
        return;

    size_t mid = data.size() / 2;
    std::vector<int> left(data.begin(), data.begin() + mid);
    std::vector<int> right(data.begin() + mid, data.end());

    merge_sort(left);
    merge_sort(right);

    size_t i = 0, j = 0, k = 0;
    while (i < left.size() && j < right.size())
    {
        data[k++] = (left[i] < right[j]) ? left[i++] : right[j++];
    }
    while (i < left.size())
        data[k++] = left[i++];
    while (j < right.size())
        data[k++] = right[j++];
}

void heapify(std::vector<int> &data, int n, int i)
{
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < n && data[left] > data[largest])
        largest = left;
    if (right < n && data[right] > data[largest])
        largest = right;

    if (largest != i)
    {
        std::swap(data[i], data[largest]);
        heapify(data, n, largest);
    }
}

void heap_sort(std::vector<int> &data)
{
    for (int i = data.size() / 2 - 1; i >= 0; --i)
    {
        heapify(data, data.size(), i);
    }
    for (int i = data.size() - 1; i > 0; --i)
    {
        std::swap(data[0], data[i]);
        heapify(data, i, 0);
    }
}

template <typename SortFunction>
double measure_sort_time(SortFunction sort_func, std::vector<int> data)
{
    auto start = std::chrono::high_resolution_clock::now();
    sort_func(data);
    auto end = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> elapsed = end - start;
    return elapsed.count();
}

void bucket_sort(std::vector<int> &data)
{
    if (data.empty())
        return;

    int min_val = *std::min_element(data.begin(), data.end());
    int max_val = *std::max_element(data.begin(), data.end());

    int bucket_count = static_cast<int>(data.size());
    std::vector<std::vector<int>> buckets(bucket_count);

    for (int num : data)
    {
        int bucket_index = (bucket_count - 1) * (num - min_val) / (max_val - min_val);
        buckets[bucket_index].push_back(num);
    }

    data.clear();
    for (auto &bucket : buckets)
    {
        std::sort(bucket.begin(), bucket.end()); 
        data.insert(data.end(), bucket.begin(), bucket.end());
    }
}

void quick_sort_wrapper(std::vector<int> &data)
{
    if (!data.empty())
        quick_sort(data, 0, static_cast<int>(data.size()) - 1);
}

int main()
{
    const size_t data_size = 10000;
    const int min_val = 1, max_val = 1000;

    auto data = generate_random_data(data_size, min_val, max_val);

    std::vector<std::pair<std::string, std::function<void(std::vector<int> &)>>> sort_algorithms = {
        {"Bubble Sort", bubble_sort},
        {"Insertion Sort", insertion_sort},
        {"Heap Sort", heap_sort},
        {"Merge Sort", merge_sort},
        {"Quick Sort", quick_sort_wrapper},
        {"Bucket Sort", bucket_sort},
    };

    for (const auto &[name, sort_func] : sort_algorithms)
    {
        std::cout << name << ": ";
        std::vector<double> times;
        for (int i = 0; i < 10; ++i)
        {
            auto data_copy = data;
            times.push_back(measure_sort_time(sort_func, data_copy));
        }
        double min_time = *std::min_element(times.begin(), times.end());
        double max_time = *std::max_element(times.begin(), times.end());
        double avg_time = std::accumulate(times.begin(), times.end(), 0.0) / times.size();
        std::cout << "Min: " << min_time << "s, Max: " << max_time << "s, Avg: " << avg_time << "s\n";
    }

    return 0;
}
