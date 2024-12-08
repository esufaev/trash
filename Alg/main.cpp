#include <vector>
#include <iostream>
#include <stdexcept>
#include <functional>
#include <memory>
#include <random>

#include "thread_pool_executor.h"
#include "solution.h"

class MemoryLimiter
{
    size_t memory_limit;
    size_t current_memory = 0;

public:
    explicit MemoryLimiter(size_t limit) : memory_limit(limit) {}

    void allocate(size_t size)
    {
        if (current_memory + size > memory_limit)
        {
            throw std::runtime_error("Memory limit exceeded");
        }
        current_memory += size;
    }

    void deallocate(size_t size)
    {
        if (current_memory < size)
        {
            current_memory = 0;
        }
        else
        {
            current_memory -= size;
        }
    }

    size_t used_memory() const { return current_memory; }
};

std::shared_ptr<MemoryLimiter> global_memory_limiter;

void *operator new(size_t size)
{
    if (global_memory_limiter)
    {
        global_memory_limiter->allocate(size);
    }
    return malloc(size);
}

void operator delete(void *ptr, size_t size) noexcept
{
    if (global_memory_limiter)
    {
        global_memory_limiter->deallocate(size);
    }
    free(ptr);
}

// template <typename T>
// void check_input_data(const T &input)
// {
//     static_assert(std::is_same<T, std::vector<std::vector<int>>>::value, "Input data should be a vector of vectors of integers");

//     for (const auto &interval : input)
//     {
//         if (interval.size() != 2)
//         {
//             throw std::invalid_argument("Each interval must contain exactly 2 integers");
//         }
//     }
// }

class solution : public solution_base
{
public:
    template <typename InputType>
    std::vector<int> run(InputType intervals)
    {

        if constexpr (std::is_same_v<InputType, std::vector<std::vector<int>>> &&
                      std::is_same_v<decltype(solution_bs(intervals)), std::vector<int>>)
        {
            for (const auto &interval : intervals)
            {
                if (interval.size() != 2)
                {
                    throw std::invalid_argument("Each interval must contain exactly 2 integers");
                }
            }
            return solution_bs(intervals);
        }
        throw std::invalid_argument("Invalid input type");
    }
};

template <typename Func>
void test_solution_with_memory(pot::executors::thread_pool_executor_lq &executor,
                               Func solution_func,
                               std::vector<std::vector<int>> &input,
                               int timeout_ms,
                               size_t memory_limit,
                               const std::string &name)
{
    global_memory_limiter = std::make_shared<MemoryLimiter>(memory_limit);

    auto future = executor.run([solution_func, &input]()
                               { return solution_func(input); });

    try
    {
        if (future.wait_for(std::chrono::milliseconds(timeout_ms)) == std::future_status::ready)
        {
            auto result = future.get();
            std::cout << name << ": Success. Memory used: " << global_memory_limiter->used_memory() << " байт\n";
        }
        else
        {
            throw std::runtime_error(name + " timed out");
            std::abort();
        }
    }
    catch (const std::exception &e)
    {
        std::cerr << "Error in " << name << ": " << e.what() << "\n";
        std::abort();
    }

    global_memory_limiter.reset();
}

std::vector<std::vector<int>> generate_large_intervals(size_t size)
{
    std::vector<std::vector<int>> intervals(size, std::vector<int>(2));
    std::mt19937 rng(2349786);
    std::uniform_int_distribution<int> dist_start(1, 1000000);
    std::uniform_int_distribution<int> dist_length(1, 100);

    for (size_t i = 0; i < size; ++i)
    {
        int start = dist_start(rng);
        intervals[i][0] = start;
        intervals[i][1] = start + dist_length(rng);
    }
    return intervals;
}

int main()
{
    solution sl;
    pot::executors::thread_pool_executor_lq executor("Main Pool");

    std::vector<std::pair<std::string, std::function<std::vector<int>(std::vector<std::vector<int>> &)>>> solutions = 
    {
        {"BS", [&sl](std::vector<std::vector<int>> &input) { return sl.run(input); }},
        {"EN", [&sl](std::vector<std::vector<int>> &input) { return sl.run(input); }},
    };

    int timeout_ms = 2000;

    std::vector<std::vector<int>> small_intervals = {{1, 2}, {2, 3}, {3, 4}};
    for (auto &[name, func] : solutions)
    {
        test_solution_with_memory(executor, func, small_intervals, timeout_ms, 10 * 1024, name + " (Small)");
    }

    auto large_intervals = generate_large_intervals(100000);
    for (auto &[name, func] : solutions)
    {
        size_t memory_limit = 50 * 1024 * 1024;
        test_solution_with_memory(executor, func, large_intervals, timeout_ms, memory_limit, name + " (Large)");
    }

    auto extreme_intervals = generate_large_intervals(500000);
    for (auto &[name, func] : solutions)
    {
        size_t memory_limit = 200 * 1024 * 1024;
        test_solution_with_memory(executor, func, extreme_intervals, timeout_ms, memory_limit, name + " (Extreme)");
    }

    std::cout << "All tests completed.\n";
    executor.shutdown();

    return 0;
}
