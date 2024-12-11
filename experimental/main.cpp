#include <iostream>
#include <atomic>
#include <thread>
#include <chrono>
#include <cassert>
#include "thread_pool_executor.h"

using namespace pot::executors;

// Первая корутина
pot::coroutines::task<int> async_computation(int value)
{
    std::cout << "Starting coroutine 1..." << std::endl;
    std::cout << "This thread: " << std::this_thread::get_id() << std::endl;

    std::this_thread::sleep_for(std::chrono::seconds(2));

    int result = value * 2;
    std::cout << "Coroutine 1 finished with result: " << result << std::endl;
    co_return result;
}

// Вторая корутина
pot::coroutines::task<int> async_computation_2(int value)
{
    std::cout << "Starting coroutine 2..." << std::endl;
    std::cout << "This thread: " << std::this_thread::get_id() << std::endl;

    std::this_thread::sleep_for(std::chrono::seconds(3));

    int result = value + 10;
    std::cout << "Coroutine 2 finished with result: " << result << std::endl;
    co_return result;
}
  
int main()
{
    thread_pool_executor<true> executor("GlobalQueueExecutor", 4);

    auto result_task_1 = executor.run([]()
                                      { return async_computation(20); });

    // auto result_task_2 = executor.run([]()
    //                                   { return async_computation_2(20); });
    int result1 = result_task_1.get();
    // int result2 = result_task_2.get();

    std::cout << "Main thread: " << std::this_thread::get_id() << std::endl;

    std::cout << "Result 1: " << result1 << std::endl;
    // std::cout << "Result 2: " << result2 << std::endl;

    executor.shutdown();
    return 0;
}
