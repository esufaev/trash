#include <iostream>
#include <atomic>
#include <thread>
#include <chrono>
#include <cassert>

#include "parfor.h"

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

    pot::algorithms::parfor(executor, 0, 3, [&](const int i)
                            { std::cout << "I: " << i << std::endl; });

    executor.shutdown();
    return 0;
}
